public class Trem {
    public String id;
    public Segmento current;
    public enum State { MOVING, AT_STATION, DONE }
    public State state = State.MOVING;
    public int onboard = 0;

    public Trem(String id, Segmento start) {
        this.id = id;
        this.current = start;
        start.enter(this);
    }

    public void tick(Simulacao sim) {
        if (state == State.AT_STATION) {
            // decrementa parada; se terminou, embarca/desembarca
            if (current.stopTicksRemaining-- <= 0) {
                // desembarca até 10
                int off = Math.min(10, onboard);
                onboard -= off;
                // embarca até 10 ou até acabar fila
                int on = Math.min(10, current.waitingPassengers);
                current.waitingPassengers -= on;
                onboard += on;
                sim.record(current.id, on, off);
                state = State.MOVING;
            }
            return;
        }

        // tenta ir pro próximo
        Segmento nxt = current.next;
        if (nxt == null) { 
            state = State.DONE;
            return;
        }
        if (nxt.canEnter()) {
            current.exit();
            nxt.enter(this);
            current = nxt;
            state = (nxt.type == SegmentType.STATION)
                    ? State.AT_STATION
                    : State.MOVING;
        }
    }
}