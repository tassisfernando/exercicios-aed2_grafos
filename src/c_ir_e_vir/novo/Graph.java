package c_ir_e_vir.novo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Graph {

    private List<EdgeAdj>[] adjs;
    int numInters, numStreets;
    final int MAX = 10000000;

    private int shortestPath(int origin, int end) {
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        Vertex[] vertexs = new Vertex[numInters + 1];

        for (int i = 1; i <= numInters; i++) {
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

    public void fillGraph() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader bfReader = new BufferedReader(input);

        String[] line = bfReader.readLine().split(" ");

        numInters = Integer.parseInt(line[0]);//numero de circulos (vertices)  
        numStreets = Integer.parseInt(line[1]);//numero de setas (arestas)

        this.adjs = new ArrayList[numInters + 1];

        while (numInters != 0 && numStreets != 0) {

            for (int i = 1; i <= numInters; i++) {
                this.adjs[i] = new ArrayList<>();
            }

            for (int i = 1; i <= numStreets; i++) {
                line = bfReader.readLine().split(" ");

                int v = Integer.parseInt(line[0]);
                int w = Integer.parseInt(line[1]);

                adjs[v].add(new EdgeAdj(w, 1));
                int p = Integer.parseInt(line[2]);
                if (p == 2) {
                    adjs[w].add(new EdgeAdj(v, 1));
                }
            }

            int test1 = this.shortestPath(1, numInters);
            int test2 = this.shortestPath(numInters, 1);

            if (test1 == MAX || test2 == MAX) {
                System.out.println("0");
            } else {
                System.out.println("1");
            }

            line = bfReader.readLine().split(" ");
            
            numInters = Integer.parseInt(line[0]);//numero de circulos (vertices)  
            numStreets = Integer.parseInt(line[1]);//numero de setas (arestas)
            this.adjs = new ArrayList[numInters + 1];
        }
    }

    public static void main(String[] args) throws IOException {
        Graph irEvir = new Graph();
        irEvir.fillGraph();
    }

}
