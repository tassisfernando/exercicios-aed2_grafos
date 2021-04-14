package d_imposto;

import java.util.ArrayList;
import java.util.List;

public class City {

    private int idCity;
    private List<Road> roads = new ArrayList<>();
    private int tax;
    private int count;

    public City(int idCity, int tax) {
        this.idCity = idCity;
        this.tax = tax;
        this.count = count;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public List<Road> getRoads() {
        return roads;
    }

    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
