package c_ir_e_vir.novo;

public class EdgeAdj {
    private int dest;
    private int weight;

    public EdgeAdj(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
