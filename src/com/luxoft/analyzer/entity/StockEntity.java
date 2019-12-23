package com.luxoft.analyzer.entity;

public class StockEntity {
    private CompanyName stock;
    private Integer price;
    private Integer volume;

    public StockEntity(CompanyName stock, Integer price, Integer volume) {
        this.stock = stock;
        this.price = price;
        this.volume = volume;
    }

    public CompanyName getStock() {
        return stock;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getVolume() {
        return volume;
    }
}
