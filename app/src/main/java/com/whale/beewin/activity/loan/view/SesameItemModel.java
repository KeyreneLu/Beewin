package com.whale.beewin.activity.loan.view;

import java.io.Serializable;

public class SesameItemModel implements Serializable {
    private String area;//区域:良好,中等,较差...
    private int min;//该区域最小值
    private int max;//该区域最大值

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}

