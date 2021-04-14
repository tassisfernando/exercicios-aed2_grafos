package a_aeroporto;

public class Edge<T> {
    private Double weight;
    private Vertex<T> in;
    private Vertex<T> end;

    public Edge(Double weight, Vertex<T> in, Vertex<T> end) {
        this.weight = weight;
        this.in = in;
        this.end = end;
    }
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Vertex<T> getIn() {
        return in;
    }

    public void setIn(Vertex<T> in) {
        this.in = in;
    }

    public Vertex<T> getEnd() {
        return end;
    }

    public void setEnd(Vertex<T> end) {
        this.end = end;
    }
    
    
}
