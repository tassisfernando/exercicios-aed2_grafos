package c_ir_e_vir.novo;

public class Vertex implements Comparable<Vertex> {
    private int value;
    private int distance;
    private boolean visited = false;
    
    public Vertex(int value, int distance) {
        this.value = value;
        this.distance = distance;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    

    @Override
    public int compareTo(Vertex t) {
        return Integer.compare(this.distance, t.distance);
    }

    @Override
    public int hashCode() {
        return this.value;
    }
    
    @Override
    public boolean equals(Object o) {
        if(o instanceof Vertex) {
            Vertex aux = (Vertex) o;
            return aux.value == this.value;
        }
        return false;
    }    
}
