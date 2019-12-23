package com.luxoft.analyzer.service.impl;

import com.luxoft.analyzer.entity.CompanyName;
import com.luxoft.analyzer.entity.ResultEntity;
import com.luxoft.analyzer.entity.StockEntity;
import com.luxoft.analyzer.exception.StockEntityListIsEmptyException;
import com.luxoft.analyzer.service.AnalyzeService;
import java.util.List;

public class AnalyzeServiceImpl implements AnalyzeService {
    public ResultEntity analyze(List<StockEntity> stockEntities){
        CompanyName stock = stockEntities.stream()
                .findAny()
                .map(stockEntity -> stockEntity.getStock())
                .orElseThrow(()->new StockEntityListIsEmptyException("List<StockEntity> or required filed is Empty "));

        int open = stockEntities.get(0).getPrice();

        int close = stockEntities.get(stockEntities.size()-1).getPrice();

        int min = stockEntities.stream()
                .mapToInt(stockEntity -> stockEntity.getPrice())
                .min()
                .orElseThrow(()->new StockEntityListIsEmptyException("List<StockEntity> or required filed is Empty "));

        int max = stockEntities.stream()
                .mapToInt(stockEntity -> stockEntity.getPrice())
                .max()
                .orElseThrow(()->new StockEntityListIsEmptyException("List<StockEntity> or required filed is Empty "));

        double average = stockEntities.stream()
                .mapToDouble(stockEntity -> stockEntity.getPrice())
                .average()
                .orElseThrow(()->new StockEntityListIsEmptyException("List<StockEntity> or required filed is Empty "));

        int volume = stockEntities.stream()
                .mapToInt(stockEntity -> stockEntity.getVolume())
                .sum();
        return new ResultEntity(stock, open, close, min, max, average, volume);
    }
}