package com.luxoft.analyzer.entity;

public class ResultEntity {
    private CompanyName stock;
    private int open;
    private int close;
    private int min;
    private int max;
    private double average;
    private int volume;

    public ResultEntity() {
    }

    public ResultEntity(CompanyName stock, int open, int close, int min, int max, double average, int volume) {
        this.stock = stock;
        this.open = open;
        this.close = close;
        this.min = min;
        this.max = max;
        this.average = average;
        this.volume = volume;
    }

    public CompanyName getStock() {
        return stock;
    }

    public int getOpen() {
        return open;
    }

    public int getClose() {
        return close;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public double getAverage() {
        return average;
    }

    public int getVolume() {
        return volume;
    }
}
