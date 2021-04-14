package h_reducao;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        
        int numCities = scn.nextInt();
        int numRoads = scn.nextInt();
        
        Graph map = new Graph(numRoads);
        
        for(int i = 0; i < numRoads; i++) {
            int cityOne = scn.nextInt();
            int cityTwo = scn.nextInt();
            int length = scn.nextInt();
            
            map.addEdge(cityOne, cityTwo, length);
        }
        
        System.out.println(map.sumKruskal());
    }
}
