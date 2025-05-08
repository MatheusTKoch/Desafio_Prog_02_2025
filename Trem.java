public class Trem {
    public enum State { MOVENDO, ESPERANDO, NA_ESTACAO, PRONTO };
    public String id;
    public Segmento current;
    public State state = State.MOVENDO;
    public int passageirosABordo = 0;

    public Trem(String id, Segmento current) {
        this.id = id;
        this.current = current;
    }

}
