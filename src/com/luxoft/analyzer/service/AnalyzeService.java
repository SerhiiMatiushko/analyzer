package com.luxoft.analyzer.service;

import com.luxoft.analyzer.entity.ResultEntity;
import com.luxoft.analyzer.entity.StockEntity;
import java.util.List;

public interface AnalyzeService {
    ResultEntity analyze(List<StockEntity> stockEntities);
}
