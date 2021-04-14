package c_ir_e_vir.teste;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    int value;
    List<Edge> adj;
    
    public Vertex() {
    }
    
    public Vertex(int value) {
        this.value = value;
        adj = new ArrayList();
    }

    public int getValue() {
        return value;
    }

    public List<Edge> getAdj() {
        return adj;
    }
}
