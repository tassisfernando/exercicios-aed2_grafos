package b_setas.certo;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijsktra {

    String enter = "3 2 1 2\n"
            + "1 2\n"
            + "2 3";

    public static void main(String[] args) {
        new Dijsktra().init();
    }

    final int MAX = 20000000;
    int c, setas, a, b;

    ArrayList<Relacao>[] relations;

    static class Relacao {

        int destiny;
        int weight;

        public Relacao(int destiny, int weight) {
            this.destiny = destiny;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "[->" + destiny + "]w=" + weight;
        }
    }

    void init() {
        Scanner input;
        input = new Scanner(enter);
        input = new Scanner(System.in);
        c = input.nextInt();
        setas = input.nextInt();
        a = input.nextInt();
        b = input.nextInt();

        relations = new ArrayList[c + 1];
        for (int i = 1; i <= c; i++) {
            relations[i] = new ArrayList<Relacao>();
        }

        for (int i = 0; i < setas; i++) {
            int f = input.nextInt();
            int d = input.nextInt();

            relations[f].add(new Relacao(d, 0));
            relations[d].add(new Relacao(f, 1));
        }

        int d1 = dijkstra(a, b);
        int d2 = dijkstra(b, a);
        if (d1 == MAX || d2 == MAX) {
            System.out.println("Bibibibika");
        } else if (d1 < d2) {
            System.out.println("Bibi: " + d1);
        } else if (d2 < d1) {
            System.out.println("Bibika: " + d2);
        } else {
            System.out.println("Bibibibika");

        }

    }

    private int dijkstra(int a, int b) {
        VerticeDijkstra[] vertices = new VerticeDijkstra[c + 1];
        PriorityQueue<VerticeDijkstra> fila = new PriorityQueue<>();

        for (int i = 1; i <= c; i++) {
            VerticeDijkstra v = new VerticeDijkstra(i, MAX);
            if (i == a) {
                v.dist = 0;
            }

            vertices[i] = v;
            fila.add(v);
        }
        while (!fila.isEmpty()) {
            VerticeDijkstra v = fila.poll();

            v.closed = true;

            for (Relacao r : relations[v.index]) {
                VerticeDijkstra vDestiny = vertices[r.destiny];

                int distance = v.dist + r.weight;

                if (vDestiny.closed) {
                    continue;
                }

                if (distance < vDestiny.dist) {

                    vDestiny.dist = v.dist + r.weight;

                    fila.remove(vDestiny);
                    fila.add(vDestiny);
                }

            }
        }
        return vertices[b].dist;
    }

    static class VerticeDijkstra implements Comparable<VerticeDijkstra> {

        int index, dist;
        boolean closed;

        public VerticeDijkstra(int index, int dist) {
            this.index = index;
            this.dist = dist;
            this.closed = false;
        }

        @Override
        public int compareTo(VerticeDijkstra o) {
            return Integer.compare(this.dist, o.dist);
        }

        @Override
        public int hashCode() {
            return this.index;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof VerticeDijkstra)) {
                return false;
            }

            VerticeDijkstra o = (VerticeDijkstra) obj;

            return o.index == this.index;
        }

        public String toString() {
            return "[(" + this.index + ") D=" + this.dist + "  "
                    + (this.closed ? "fechado]" : "]");
        }

    }

}
