public class Segmento {
    public int id;
    public Trem ocupadoPor = null;
    public Segmento next;
    public Segmento prev;

    public Segmento(int id) {
        this.id = id;
    }

    public boolean podeEntrar() {
        return ocupadoPor == null;
    }

    public void entrar(Trem trem) {
        ocupadoPor = trem;
    }

    public void sair() {
        ocupadoPor = null;
    }

}