package h_reducao;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private int qtdVertex;
    List<Edge> edges;

    public Graph(int qtdVertex) {
        this.qtdVertex = qtdVertex;
        this.edges = new ArrayList<>();
    }

    public void addEdge(int vertOne, int vertTwo, int weight) {
        Edge newEdge = new Edge(vertOne, vertTwo, weight);
        edges.add(newEdge);
    }

    public int search(int[] subset, int i) {
        if (subset[i] == -1) {
            return i;
        }
        return search(subset, subset[i]);
    }

    public void joinGroup(int[] subset, int v1, int v2) {
        int v1Set = search(subset, v1);
        int v2Set = search(subset, v2);
        subset[v1Set] = v2Set;
    }

    //Soma dos pesos das arestas
    public List<Edge> kruskal() {
        List<Edge> edgeKrusk = new ArrayList<>();
        this.orderEdges();

        int[] subset = new int[this.qtdVertex];

        for (int i = 0; i < subset.length; i++) {
            subset[i] = -1;
        }

        for (int i = 0; i < this.edges.size(); i++) {
            int v1 = search(subset, edges.get(i).getVertexOne());
            int v2 = search(subset, edges.get(i).getVertexTwo());
            if (v1 != v2) {
                edgeKrusk.add(edges.get(i));
                this.joinGroup(subset, v1, v2);
            }
        }
        return edgeKrusk;
    }

    public int sumKruskal() {
        List<Edge> edgesKrusk = this.kruskal();

        int sum = 0;
        for (Edge edge : edgesKrusk) {
            sum += edge.getWeight();
        }

        return sum;
    }

    public void orderEdges() {
        Edge aux;
        int i;
        for (int j = 1; j < this.edges.size(); j++) {
            aux = this.edges.get(j);
            for (i = j - 1; (i >= 0) && (this.edges.get(i).getWeight() > aux.getWeight()); i--) {
                this.edges.set(i + 1, this.edges.get(i));
            }
            this.edges.set(i + 1, aux);
        }
    }
}
