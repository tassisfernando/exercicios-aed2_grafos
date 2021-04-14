package d_imposto;

public class Road {

    private int length;
    private City cityOne;
    private City cityTwo;
    private boolean ok = false;
    private boolean isVisited = false;

    public Road(int length, City cityOne, City cityTwo) {
        this.length = length;
        this.cityOne = cityOne;
        this.cityTwo = cityTwo;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public City getCityOne() {
        return cityOne;
    }

    public void setCityOne(City cityOne) {
        this.cityOne = cityOne;
    }

    public City getCityTwo() {
        return cityTwo;
    }

    public void setCityTwo(City cityTwo) {
        this.cityTwo = cityTwo;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }
}
