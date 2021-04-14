package a_aeroporto;

import java.util.ArrayList;
import java.util.List;

public class Vertex<T> {
    private T data;
    private List<Edge<T>> edgesIn;
    private List<Edge<T>> edgesOut;
    
    public Vertex(T data) {
        this.data = data;
        this.edgesIn = new ArrayList<>();
        this.edgesOut = new ArrayList<>();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<Edge<T>> getEdgesIn() {
        return edgesIn;
    }

    public void setEdgesIn(List<Edge<T>> edgesIn) {
        this.edgesIn = edgesIn;
    }

    public List<Edge<T>> getEdgesOut() {
        return edgesOut;
    }

    public void setEdgesOut(List<Edge<T>> edgesOut) {
        this.edgesOut = edgesOut;
    }
      
    public void addEdgeIn(Edge<T> edge) {
        this.edgesIn.add(edge);
    }
    
    public void addEdgeOut(Edge<T> edge) {
        this.edgesOut.add(edge);
    }
}
