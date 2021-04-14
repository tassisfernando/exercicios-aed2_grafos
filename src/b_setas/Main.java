package b_setas;

import java.util.List;
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
            jogo.addEdge(1.0, v1, v2);
        }

        List<Integer> menorBibi = jogo.shortestPath(jogo.getVertex(a), jogo.getVertex(b));
        List<Integer> menorBibika = jogo.shortestPath(jogo.getVertex(b), jogo.getVertex(a));

        int bibi = menorBibi.size();
        int bibika = menorBibika.size();
        
        System.out.println(bibi);
        System.out.println(bibika);

        if (bibi < bibika) {
            System.out.println("Bibi: " + bibi);
        } else if (bibi > bibika) {
            System.out.println("Bibika: " + bibika);
        } else {
            System.out.println("Bibibibika");
        }
    }
}
