package c_ir_e_vir;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int numInters = scn.nextInt();
        int numStreets = scn.nextInt();

        Graph graph = new Graph();

        while (numInters != 0 && numStreets != 0) {

            for (int i = 1; i <= numInters; i++) {
                graph.addVertex(i);
            }

            for (int i = 1; i <= numStreets; i++) {
                Integer v = scn.nextInt(); //identificadores de interseções
                Integer w = scn.nextInt();

                graph.addEdge(1.0, v, w);
                int p = scn.nextInt();
                if (p == 2) {
                    graph.addEdge(1.0, w, v);
                }
            }

            Integer root = 1;
            Integer end = numInters;

            //mudar pra busca em profundidade
            if (graph.depthFirstSearch(root, new ArrayList<>(), end) == 1 && graph.depthFirstSearch(end, new ArrayList<>(), root) == 1) {
                System.out.println("1");
            } else {
                System.out.println("0");
            }
            
            //graph.depthFirstSearch(root, new ArrayList<>(), end);
           // System.out.println("-----------------------------");
           // graph.depthFirstSearch(end, new ArrayList<>(), root);

            numInters = scn.nextInt();
            numStreets = scn.nextInt();
        }
    }
}
