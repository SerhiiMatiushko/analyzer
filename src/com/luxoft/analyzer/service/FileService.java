package com.luxoft.analyzer.service;

import java.util.List;

public interface FileService {
    List<String> openAndRead(String path);
    void save(String path, String firstRow, List<String> values);
}
