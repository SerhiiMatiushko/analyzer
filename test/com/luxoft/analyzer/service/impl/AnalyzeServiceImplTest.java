package com.luxoft.analyzer.service.impl;

import com.luxoft.analyzer.entity.CompanyName;
import com.luxoft.analyzer.entity.ResultEntity;
import com.luxoft.analyzer.entity.StockEntity;
import com.luxoft.analyzer.factory.SingletonFactory;
import com.luxoft.analyzer.service.AnalyzeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnalyzeServiceImplTest {
    private AnalyzeService analyzeService = null;

    @BeforeEach
    void setUp() {
        analyzeService = SingletonFactory.getInstance(AnalyzeServiceImpl.class);
    }

    @Test
    void analyzeOk() {
        List<StockEntity> stockEntities = new LinkedList();
        stockEntities.add(new StockEntity(CompanyName.APPL, 100, 3000));
        stockEntities.add(new StockEntity(CompanyName.APPL, 300, 2000));
        stockEntities.add(new StockEntity(CompanyName.APPL, 200, 7000));
        ResultEntity resultEntity = analyzeService.analyze(stockEntities);
        assertEquals(CompanyName.APPL, resultEntity.getStock());
        assertEquals(100, resultEntity.getOpen());
        assertEquals(200, resultEntity.getClose());
        assertEquals(100, resultEntity.getMin());
        assertEquals(300, resultEntity.getMax());
        assertEquals(200, resultEntity.getAverage());
        assertEquals(12000, resultEntity.getVolume());
    }
}