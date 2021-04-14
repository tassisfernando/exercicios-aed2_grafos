package a_aeroporto;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        int a = scn.nextInt();
        int v = scn.nextInt();
        int cont = 1;
        while (a != 0 && v != 0) {
            Graph<Integer> airports = new Graph<>();

            for (int i = 1; i <= a; i++) {
                airports.addVertex(i);
            }
            
            

            for (int i = 1; i <= v; i++) {
                int x = scn.nextInt();
                int y = scn.nextInt();
                
                airports.addEdge(1.0, x, y);
            }
            int[] higherTraffic = getHigherTraffic(airports, a);

            System.out.println("Teste " + cont);

            for (int i = 0; i < higherTraffic.length; i++) {
                if (higherTraffic[i] == 0) {
                    break;
                }
                System.out.print(higherTraffic[i] + " ");
            }

            System.out.println("\n");
            cont++;
            a = scn.nextInt();
            v = scn.nextInt();
        }
    }

    public static int[] getHigherTraffic(Graph<Integer> airports, int a) {
        int[] higher = new int[a];

        List<Vertex<Integer>> vertex = airports.getVertex();

        int flightsIn = vertex.get(0).getEdgesIn().size();
        int flightsOut = vertex.get(0).getEdgesOut().size();
        int maior = flightsIn + flightsOut;
        higher[0] = 1;
        int filled = 1;

        for (int i = 1; i < a; i++) {
            flightsIn = vertex.get(i).getEdgesIn().size();
            flightsOut = vertex.get(i).getEdgesOut().size();
            int qtd = flightsIn + flightsOut;

            if (qtd > maior) {
                higher = new int[a];
                higher[0] = i + 1;
                maior = qtd;
                filled = 1;
            } else if (qtd == maior) {
                higher[filled] = i + 1;
                filled++;
            }

        }
        return higher;
    }
}
