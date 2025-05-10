public class Segmento {
    public int id;
    public SegmentType type;
    public Segmento next;
    public Trem occupiedBy;
    public int waitingPassengers;    // só usado em STATION
    public int stopTicksRemaining;   // só usado em STATION

    public Segmento(int id, SegmentType type) {
        this.id = id;
        this.type = type;
    }

    public boolean canEnter() {
        return occupiedBy == null;
    }

    public void enter(Trem t) {
        occupiedBy = t;
        // se chegou numa estação, inicia contagem de parada
        if (type == SegmentType.STATION) {
            stopTicksRemaining = 1;
        }
    }

    public void exit() {
        occupiedBy = null;
    }
}