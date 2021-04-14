package h_reducao;

public class Edge {
    private int vertexOne;
    private int vertexTwo;
    private int weight;

    public Edge(int vertexOne, int vertexTwo, int weight) {
        this.vertexOne = vertexOne;
        this.vertexTwo = vertexTwo;
        this.weight = weight;
    }

    public int getVertexOne() {
        return vertexOne;
    }

    public void setVertexOne(int vertexOne) {
        this.vertexOne = vertexOne;
    }

    public int getVertexTwo() {
        return vertexTwo;
    }

    public void setVertexTwo(int vertexTwo) {
        this.vertexTwo = vertexTwo;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
