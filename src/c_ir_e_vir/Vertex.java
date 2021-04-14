package c_ir_e_vir;

import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {

    private Integer data;
    private List<Edge> edgesIn;
    private List<Edge> edgesOut;
    private Vertex previous;
    private ArrayList<Vertex> neighbors = new ArrayList<Vertex>();
    private Double distance;
    private boolean isVisited = false;

    public Vertex(Integer data) {
        this.data = data;
        this.edgesIn = new ArrayList<>();
        this.edgesOut = new ArrayList<>();
    }

    public Vertex(Integer data, Double distance) {
        this.distance = distance;
        this.data = data;
        this.edgesIn = new ArrayList<>();
        this.edgesOut = new ArrayList<>();
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public Vertex getPrevious() {
        return previous;
    }

    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }

    public ArrayList<Vertex> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(ArrayList<Vertex> neighbors) {
        this.neighbors = neighbors;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public List<Edge> getEdgesIn() {
        return edgesIn;
    }

    public void setEdgesIn(List<Edge> edgesIn) {
        this.edgesIn = edgesIn;
    }

    public List<Edge> getEdgesOut() {
        return edgesOut;
    }

    public void setEdgesOut(List<Edge> edgesOut) {
        this.edgesOut = edgesOut;
    }

    public void addEdgeIn(Edge edge) {
        this.edgesIn.add(edge);
    }

    public void addEdgeOut(Edge edge) {
        this.edgesOut.add(edge);
    }

    @Override
    public int compareTo(Vertex vertex) {

        if (this.getDistance() < vertex.getDistance()) {
            return -1;
        } else if (this.getDistance() == vertex.getDistance()) {
            return 0;
        }

        return 1;
    }
}
