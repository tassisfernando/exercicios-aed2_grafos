package b_setas.novo;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    List<Vertex> vertexs;

    public Graph() {
        vertexs = new ArrayList<>();
    }

    public void addVertex(int num) {
        Vertex vertex = new Vertex(num);
        vertexs.add(vertex);
    }

    public boolean hasVertex(int num) {
        for (Vertex vert : vertexs) {
            if (vert.getValue() == num) {
                return true;
            }
        }
        return false;
    }

    public void addEdge(int origin, int destiny, int weight) {
        if (!hasVertex(origin)) {
            addVertex(origin);
        }

        if (!hasVertex(destiny)) {
            addVertex(destiny);
        }

        Vertex vertOrig = getVertex(origin);
        Vertex vertDest = getVertex(destiny);

        Edge edge = new Edge(vertOrig, vertDest, weight);
        vertOrig.getAdj().add(edge);
    }

    public Vertex getVertex(int num) {
        for (Vertex vert : vertexs) {
            if (vert.getValue() == num) {
                return vert;
            }
        }
        return null;
    }

    public int getShortPath(Vertex root, Vertex destiny) {
        List<Vertex> visiteds = new ArrayList<>();
        int[] cost = new int[vertexs.size() + 1];

        for (int i = 0; i < cost.length; i++) {
            cost[i] = Integer.MAX_VALUE;
        }

        cost[root.getValue()] = 0;
        Vertex current = root;
        visiteds.add(root);

        while (visiteds.size() < vertexs.size()) {
            for (Edge e : current.getAdj()) {
                int calcCost = cost[current.getValue()] + e.getWeight();
                if (calcCost < cost[e.getDestiny().getValue()]) {
                    cost[e.getDestiny().getValue()] = calcCost;
                }
            }

            int shortest = Integer.MAX_VALUE;
            Vertex aux = null;
            for (int i = 0; i < cost.length; i++) {
                aux = this.getVertex(i);
                if (cost[i] < shortest && !visiteds.contains(aux)) {
                    shortest = cost[i];
                    current = aux;
                }
            }
            visiteds.add(current);
        }

        return cost[destiny.getValue()];
    }
}
