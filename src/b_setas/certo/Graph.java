package b_setas.certo;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Graph {

    private List<EdgeAdj>[] adjs;
    int numC, numS, a, b;
    final int MAX = 10000000;

    private int shortestPath(int origin, int end) {
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        Vertex[] vertexs = new Vertex[numC + 1];

        for (int i = 1; i <= numC; i++) {
            Vertex vert;
            if (origin == i) {
                vert = new Vertex(i, 0);
            } else {
                vert = new Vertex(i, MAX);
            }
            vertexs[i] = vert;
            queue.add(vert);
        }

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            current.setVisited(true);

            for (EdgeAdj edAdj : adjs[current.getValue()]) {
                Vertex dest = vertexs[edAdj.getDest()];
                int dist = current.getDistance() + edAdj.getWeight();

                if (dest.isVisited()) {
                    continue;
                }

                if (dist < dest.getDistance()) {
                    dest.setDistance(current.getDistance() + edAdj.getWeight());
                    queue.remove(dest);
                    queue.add(dest);
                }

            }
        }
        return vertexs[end].getDistance();
    }

    public void fillGraph() {
        Scanner scn = new Scanner(System.in);

        numC = scn.nextInt(); //numero de circulos (vertices)  
        numS = scn.nextInt(); //numero de setas (arestas)

        a = scn.nextInt(); //vertice início
        b = scn.nextInt(); //vertice final

        this.adjs = new ArrayList[numC + 1];

        for (int i = 1; i <= numC; i++) {
            this.adjs[i] = new ArrayList<>();
        }

        for (int i = 1; i <= numS; i++) {

            int v1 = scn.nextInt();
            int v2 = scn.nextInt();

            adjs[v1].add(new EdgeAdj(v2, 0));
            adjs[v2].add(new EdgeAdj(v1, 1));
        }

        int bibi = this.shortestPath(a, b);
        int bibika = this.shortestPath(b, a);

        if (bibi == MAX || bibika == MAX) {
            System.out.println("Bibibibika");
        } else if (bibi < bibika) {
            System.out.println("Bibi: " + bibi);
        } else if (bibi > bibika) {
            System.out.println("Bibika: " + bibika);
        } else {
            System.out.println("Bibibibika");
        }
    }

    public static void main(String[] args) {
        Graph game = new Graph();
        game.fillGraph();
    }

}
