package b_setas;

public class Edge {
    private Double weight;
    private Vertex in;
    private Vertex end;

    public Edge(Double weight, Vertex in, Vertex end) {
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

    public Vertex getIn() {
        return in;
    }

    public void setIn(Vertex in) {
        this.in = in;
    }

    public Vertex getEnd() {
        return end;
    }

    public void setEnd(Vertex end) {
        this.end = end;
    }
    
    
}
