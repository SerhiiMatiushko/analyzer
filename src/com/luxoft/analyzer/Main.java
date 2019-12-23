package com.luxoft.analyzer;

import com.luxoft.analyzer.entity.CompanyName;
import com.luxoft.analyzer.entity.ResultEntity;
import com.luxoft.analyzer.entity.StockEntity;
import com.luxoft.analyzer.factory.SingletonFactory;
import com.luxoft.analyzer.service.AnalyzeService;
import com.luxoft.analyzer.service.ParserService;
import com.luxoft.analyzer.service.impl.AnalyzeServiceImpl;
import com.luxoft.analyzer.service.impl.CommandLineServiceImpl;
import com.luxoft.analyzer.service.impl.FileServiceImpl;
import com.luxoft.analyzer.service.impl.ParserServiceImpl;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static List<StockEntity> stockEntities = new LinkedList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to Analyzer API");
        SingletonFactory.getInstance(CommandLineServiceImpl.class).run();
    }

    public static List<String> readDataFromFile(String path) {
        return SingletonFactory.getInstance(FileServiceImpl.class).openAndRead(path);
    }

    public static void parseToStockEntity(List<String> values) {
        ParserService parser = SingletonFactory.getInstance(ParserServiceImpl.class);
        values.stream()
                .filter(value->parser.checkValues(parser.format(value)))
                .forEach(value -> stockEntities.add(parser.toStockEntity(value)));
    }

    public static void saveResult(String path){
        int width = 10;
        AnalyzeService analyzeService = SingletonFactory.getInstance(AnalyzeServiceImpl.class);
        List<ResultEntity> resultEntities = new LinkedList<>();
        for(CompanyName name:CompanyName.values()){
            List<StockEntity> filteredList = stockEntities.stream()
                    .filter(stockEntity -> stockEntity.getStock().equals(name))
                    .collect(Collectors.toList());
            resultEntities.add(analyzeService.analyze(filteredList));
        }
        ParserService parserService = SingletonFactory.getInstance(ParserServiceImpl.class);
        String firstRow = parserService.getFirstRow(width);
        List<String> rows = resultEntities.stream()
                .map(resultEntity -> parserService.getString(resultEntity,width))
                .collect(Collectors.toList());

        SingletonFactory.getInstance(FileServiceImpl.class).save(path, firstRow, rows);
        stockEntities.clear();
    }

    public static void analyze(String pathToSource, String pathToTarget){
        parseToStockEntity(readDataFromFile(pathToSource));
        saveResult(pathToTarget);
        stockEntities.clear();
    }
}