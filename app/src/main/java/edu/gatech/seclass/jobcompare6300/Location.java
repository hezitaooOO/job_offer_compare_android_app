package edu.gatech.seclass.jobcompare6300;

public class Location {
    private String city;
    private String state;

    public Location(String city, String state) {
        this.city = city;
        this.state = state;
    }

    public String getCity() { return city; }

    public String getState() { return state; }

    @Override
    public String toString() {
        return state + "-" + city;
    }
}
