package c_ir_e_vir;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main2 {

    public static void main(String[] args) throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader bfReader = new BufferedReader(input);

        String[] line = bfReader.readLine().split(" ");

        int numInters = Integer.parseInt(line[0]);
        int numStreets = Integer.parseInt(line[1]);

        Graph graph = new Graph();

        while (numInters != 0 && numStreets != 0) {

            for (int i = 1; i <= numInters; i++) {
                graph.addVertex(i);
            }

            for (int i = 1; i <= numStreets; i++) {
                line = bfReader.readLine().split(" ");
                
                Integer v = Integer.parseInt(line[0]); //identificadores de interseções
                Integer w = Integer.parseInt(line[1]);

                graph.addEdge(1.0, v, w);
                int p = Integer.parseInt(line[2]);

                if (p == 2) {
                    graph.addEdge(1.0, w, v);
                }
            }

            Integer root = 1;
            Integer end = numInters;
            
            Vertex rootVert = graph.getVertex(root);
            Vertex endVert = graph.getVertex(end);
            
            List test1 = graph.shortestPath(rootVert, endVert);
            List test2 = graph.shortestPath(endVert, rootVert);
            
            if (!test1.isEmpty() && !test2.isEmpty()) {
                System.out.println("1");
            } else {
                System.out.println("0");
            }

            line = bfReader.readLine().split(" ");
            numInters = Integer.parseInt(line[0]);
            numStreets = Integer.parseInt(line[1]);
            graph.clear();
        }
    }
}
