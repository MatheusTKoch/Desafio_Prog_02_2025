import java.util.*;

public class Simulacao {
    private Segmento start;
    private List<Trem> Trems = new ArrayList<>();
    private Map<Integer, int[]> stats = new HashMap<>();

    public static void main(String[] args) {
        new Simulacao().run();
    }

    public void run() {
        buildTrack(5);              // por exemplo, 5 estações
        int totalMinutes = 9*60 + 30; // de 8:00 a 17:30 = 9h30 = 570 min
        for (int minute = 0; minute <= totalMinutes; minute++) {
            if (minute % 30 == 0) depart(minute);
            for (Trem t : new ArrayList<>(Trems)) {
                t.tick(this);
            }
            print(minute);
        }
        report();
    }

    private void buildTrack(int n) {
        // Ponto A
        start = new Segmento(0, SegmentType.SWITCH);
        Segmento cur = start;
        Random rnd = new Random();
        for (int i = 1; i <= n; i++) {
            // 1 km
            cur.next = new Segmento(i*3-2, SegmentType.KM);
            cur = cur.next;
            // estação
            Segmento st = new Segmento(i*3-1, SegmentType.STATION);
            st.waitingPassengers = rnd.nextInt(41) + 10;
            stats.put(st.id, new int[]{0,0});
            cur.next = st;
            cur = st;
            // desvio
            cur.next = new Segmento(i*3, SegmentType.SWITCH);
            cur = cur.next;
        }
        // Ponto B
        cur.next = new Segmento(n*3+1, SegmentType.SWITCH);
    }

    private void depart(int minute) {
        Trems.add(new Trem("T"+minute, start));
    }

    public void record(int stationId, int on, int off) {
        int[] arr = stats.get(stationId);
        arr[0] += on;
        arr[1] += off;
    }

    private void print(int m) {
        System.out.printf("Min %3d:", m);
        for (Trem t : Trems) {
            System.out.printf(" %s@%d(%s)", 
                t.id, t.current.id, t.state);
        }
        System.out.println();
    }

    private void report() {
        System.out.println("=== Relatório ===");
        for (var e : stats.entrySet()) {
            System.out.printf("Est%d: +%d/-%d%n",
                e.getKey(), e.getValue()[0], e.getValue()[1]);
        }
    }
}