package d_imposto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt(); //num cidades
        int capacity = scn.nextInt(); //capacidade carruagem

        City[] cities = new City[n + 1];
        int totalTax = 0;

        for (int i = 1; i <= n; i++) {
            int tax = scn.nextInt();
            totalTax += tax;
            cities[i] = new City(i, tax);
        }

        for (int i = 0; i < n - 1; i++) {
            int a = scn.nextInt();
            int b = scn.nextInt();
            int c = scn.nextInt();

            City cityA = cities[a];
            City cityB = cities[b];

            Road road = new Road(c, cityA, cityB);
            cityA.getRoads().add(road);
            cityB.getRoads().add(road);
        }
        
        int id = 1;
        int totalDistance = calcDistance(capacity, cities, totalTax, id);
        System.out.println(totalDistance);
    }

    public static int calcDistance(int capacity, City[] cities, int totalTax, int id) {
        Stack<City> navCities = new Stack();
        Stack<Road> navRoads = new Stack();

        City destiny = null;

        City currentCity = cities[1]; //capital
        int carriage = 0;
        int totalDistance = 0;
        Road previousRoad = null;

        while (cities[1].getTax() != totalTax) {
            int i = 0;
            for (i = 0; i < currentCity.getRoads().size(); i++) {
                if (currentCity.getRoads().get(i).getCityTwo() == currentCity) {
                    destiny = currentCity.getRoads().get(i).getCityOne();
                } else {
                    destiny = currentCity.getRoads().get(i).getCityTwo();
                }

                if (!currentCity.getRoads().get(i).isVisited()) {
                    currentCity.setCount(i);
                    navCities.push(currentCity);
                    previousRoad = currentCity.getRoads().get(i);
                    navRoads.push(previousRoad);

                    totalDistance += previousRoad.getLength();
                    previousRoad.setIsVisited(true);

                    currentCity = destiny;
                    i = -1;
                }
            }

            i = 0;

            while (currentCity.getTax() != 0) {
                previousRoad = navRoads.peek();

                if (currentCity.getTax() > capacity) {
                    carriage += capacity;
                    int auxTax = currentCity.getTax();
                    auxTax -= capacity;
                    currentCity.setTax(auxTax);
                    totalDistance += previousRoad.getLength() * 2;
                } else {
                    carriage += currentCity.getTax();
                    currentCity.setTax(0);
                    totalDistance += previousRoad.getLength();
                }
            }
            previousRoad.setOk(true);

            currentCity = navCities.pop();
            navRoads.pop();
            i = currentCity.getCount() + 1;
            int auxTax = currentCity.getTax();
            auxTax += carriage;
            currentCity.setTax(auxTax);
            carriage = 0;
        }
        return totalDistance;
    }
}
