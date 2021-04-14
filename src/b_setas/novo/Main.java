package b_setas.novo;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        Graph jogo = new Graph();

        int numC = scn.nextInt(); //numero de circulos (vertices)
        for (int i = 1; i <= numC; i++) {
            jogo.addVertex(i);
        }

        int numS = scn.nextInt(); //numero de setas (arestas)

        int a = scn.nextInt(); //vertice início
        int b = scn.nextInt(); //vertice final

        //Adicionando arestas
        for (int i = 1; i <= numS; i++) {
            int v1 = scn.nextInt();
            int v2 = scn.nextInt();
            jogo.addEdge(v1, v2, 0);
            jogo.addEdge(v2, v1, 1);
        }

        Vertex root = jogo.getVertex(a);
        Vertex destiny = jogo.getVertex(b);
        int bibi = jogo.getShortPath(root, destiny);
        int bibika = jogo.getShortPath(destiny, root);

        if (bibi < bibika) {
            System.out.println("Bibi: " + bibi);
        } else if (bibi > bibika) {
            System.out.println("Bibika: " + bibika);
        } else {
            System.out.println("Bibibibika");
        }
    }
}
