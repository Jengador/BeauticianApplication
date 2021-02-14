package com.example.beauticianapplication;

public class District implements Comparable<District>{

    int id;
    String districtName;

    @Override
    public String toString() {
        return this.districtName;
    }

    public int compareTo(District secondCity) {
        return this.getId() - secondCity.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
