package com.luxoft.analyzer.service.impl;

import com.luxoft.analyzer.log.Log;
import com.luxoft.analyzer.service.FileService;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileServiceImpl implements FileService {

    private final static Log LOG = new Log(FileServiceImpl.class);

    public List<String> openAndRead(String path) {
        List<String> stocs = new LinkedList<>();
        FileReader fileReader = null;
        Scanner scanner = null;
        try {
            fileReader = new FileReader(path);
            scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                stocs.add(scanner.nextLine());
            }
            LOG.debug("File has been read.");
            fileReader.close();
        } catch (FileNotFoundException e) {
            LOG.error(String.format("File %s Not Found", path));
        } catch (IOException e) {
            LOG.error(String.format("Scanner Not Closed", path));
        }
        return stocs;
    }

    public void save(String path, String firstRow, List<String> values){
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(firstRow+"\r\n");
            values.forEach(s -> {
                try {
                    fileWriter.write(s+"\r\n");
                    LOG.info(s);
                } catch (IOException e) {
                    LOG.error("File was Not Created");
                }
            });
            fileWriter.close();
            LOG.debug("File has been created "+path);
        } catch (IOException e) {
            LOG.error("File was Not Created");
        }
    }
}