package com.whale.beewin.bean;

/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class Bank {
    private int logo;
    private String name;
    private String cost;

    public Bank(int logo, String name, String cost) {
        this.logo = logo;
        this.name = name;
        this.cost = cost;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "logo=" + logo +
                ", name='" + name + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
