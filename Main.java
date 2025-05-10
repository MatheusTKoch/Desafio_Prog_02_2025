public class Main {
    public static void main(String[] args) {
        String header = """
            === Simulação do Sistema Ferroviário ===
            
            Configuração:
            • Estações: 5
            • Horário: 8:00 - 17:30
            • Intervalo entre trens: 30 min
            • Capacidade por parada: 10 passageiros
            """;
        
        System.out.println(header);
        
        Simulacao sim = new Simulacao();
        try {
            sim.run();
        } catch (Exception e) {
            System.err.printf("Erro: %s%n", e.getMessage());
        }
        
        System.out.println("\nSimulação finalizada.");
    }
}