public class estacaoSegmento extends Segmento {
    public String nome;
    public int passageirosEsperando;
    public int ticksParadaRestantes;

    public estacaoSegmento(int id, String nome, int esperando) {
        super(id);
        this.nome = nome;
        this.passageirosEsperando = esperando;
        this.ticksParadaRestantes = 0;
    }

    public void startStop() {
        ticksParadaRestantes = 1;
    }

    
}
