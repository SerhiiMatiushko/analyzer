package com.luxoft.analyzer.service;

import com.luxoft.analyzer.entity.ResultEntity;
import com.luxoft.analyzer.entity.StockEntity;

public interface ParserService {
    StockEntity toStockEntity(String row);
    String getString(ResultEntity resultEntity, int width);
    String getFirstRow(int width);
    boolean checkValues(String [] values);
    String[] format(String row);
}
