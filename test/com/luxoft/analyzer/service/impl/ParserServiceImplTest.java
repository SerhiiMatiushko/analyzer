package com.luxoft.analyzer.service.impl;

import com.luxoft.analyzer.entity.CompanyName;
import com.luxoft.analyzer.entity.ResultEntity;
import com.luxoft.analyzer.entity.StockEntity;
import com.luxoft.analyzer.exception.ResultEntityIsNotValidException;
import com.luxoft.analyzer.factory.SingletonFactory;
import com.luxoft.analyzer.service.ParserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserServiceImplTest {

    static int width = 10;
    private static ParserService parserService = null;

    @BeforeAll
    static void init(){
        parserService = SingletonFactory.getInstance(ParserServiceImpl.class);
    }

    @Test
    void toStockEntityOk() {
        String testRowOk = "\uFEFFAPPL\t165\t126525";
        StockEntity stockEntity = parserService.toStockEntity(testRowOk);
        assertEquals(CompanyName.APPL, stockEntity.getStock());
        assertEquals(165, stockEntity.getPrice());
        assertEquals(126525, stockEntity.getVolume());
    }

    @Test
    void getStringOk() {
        int width = 10;
        String testRowOk = "APPL      165       156       80        325       156.547   13337686  ";
        ResultEntity resultEntity = new ResultEntity(CompanyName.APPL, 165, 156, 80, 325, 156.547, 13337686);
        assertEquals(testRowOk, parserService.getString(resultEntity, width));
    }

    @Test
    void getStringException() {
        ResultEntity resultEntity = new ResultEntity();
        assertThrows(ResultEntityIsNotValidException.class, ()->parserService.getString(resultEntity, width));
    }

    @Test
    void getFirstRow() {
        String testRow = "Stock     Open      Close     Min       Max       Average   Vol       ";
        assertEquals(testRow, parserService.getFirstRow(width));
    }
}