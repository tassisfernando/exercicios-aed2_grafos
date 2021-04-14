/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b_setas.novo;

/**
 *
 * @author tassi
 */
public class Edge {
    Vertex origin;
    Vertex destiny;
    int weight;

    public Edge(Vertex origin, Vertex destiny, int weight) {
        this.origin = origin;
        this.destiny = destiny;
        this.weight = weight;
    }
    
    public Edge() {
        
    }

    public Vertex getOrigin() {
        return origin;
    }

    public void setOrigin(Vertex origin) {
        this.origin = origin;
    }

    public Vertex getDestiny() {
        return destiny;
    }

    public void setDestiny(Vertex destiny) {
        this.destiny = destiny;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
