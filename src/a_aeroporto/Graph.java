package a_aeroporto;

import java.util.ArrayList;
import java.util.List;

public class Graph<T> {

    private List<Edge<T>> edges;
    private List<Vertex<T>> vertex;

    public Graph() {
        this.edges = new ArrayList<>();
        this.vertex = new ArrayList<>();
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public List<Vertex<T>> getVertex() {
        return vertex;
    }

    public void addVertex(T data) {
        Vertex<T> newVert = new Vertex<>(data);
        this.vertex.add(newVert);
    }

    public void addEdge(Double weight, T first, T end) {
        Vertex<T> inEdge = this.getVertex(first);
        Vertex<T> endEdge = this.getVertex(end);
        
        if (inEdge != null && endEdge != null) {
            Edge<T> newEdge = new Edge<>(weight, inEdge, endEdge);
            inEdge.addEdgeOut(newEdge);
            endEdge.addEdgeIn(newEdge);
            this.edges.add(newEdge);
        }

    }

    public Vertex<T> getVertex(T data) {
        for (int i = 0; i < vertex.size(); i++) {
            if (vertex.get(i).getData().equals(data)) {
                return vertex.get(i);
            }
        }
        return null;
    }

    //busca em largura
    public void breadthFirst() {
        if (this.vertex.size() > 0) {
                        
            List<Vertex<T>> marked = new ArrayList<>();
            List<Vertex<T>> queue = new ArrayList<>();

            Vertex<T> current = this.vertex.get(0); //começa do zero
            marked.add(current);
            System.out.println(current.getData());
            queue.add(current);

            while (queue.size() > 0) {
                Vertex<T> visited = queue.get(0);

                for (int i = 0; i < visited.getEdgesOut().size(); i++) {
                    Vertex<T> next = visited.getEdgesOut().get(i).getEnd();
                    if (!marked.contains(next)) {
                        marked.add(next);
                        System.out.println(next.getData());
                        queue.add(next);
                    }
                }
                queue.remove(0);
            }

        }
    }
    
    

}
