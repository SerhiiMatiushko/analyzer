package com.luxoft.analyzer.service.impl;

import com.luxoft.analyzer.entity.CompanyName;
import com.luxoft.analyzer.entity.ResultEntity;
import com.luxoft.analyzer.entity.StockEntity;
import com.luxoft.analyzer.exception.ResultEntityIsNotValidException;
import com.luxoft.analyzer.log.Log;
import com.luxoft.analyzer.service.ParserService;
import java.util.Optional;
import java.util.regex.Pattern;
import static com.luxoft.analyzer.util.StringUtils.replace;

public class ParserServiceImpl implements ParserService {
    private final static Log LOG = new Log(ParserServiceImpl.class);

    public StockEntity toStockEntity(String row){
        CompanyName stock = null;
        Integer price = null;
        Integer volume = null;

        String[] values = format(row);
            stock = CompanyName.valueOf(values[0]);
            price = Integer.valueOf(values[1]);
            volume = Integer.valueOf(values[2]);
            LOG.info("stock = "+stock+", price = "+price+", volume = "+volume);
        return new StockEntity(stock, price, volume);
    }

    public String getString(ResultEntity resultEntity, int width){
        String[] array = null;
        if (checkResultEntity(resultEntity)) {
            array = new String[]{resultEntity.getStock().toString(),
                    Integer.toString(resultEntity.getOpen()),
                    Integer.toString(resultEntity.getClose()),
                    Integer.toString(resultEntity.getMin()),
                    Integer.toString(resultEntity.getMax()),
                    String.format("%.3f", resultEntity.getAverage()),
                    Integer.toString(resultEntity.getVolume())};
        }else {
            String errorMessage = "ResultEntity instance is not valid";
            LOG.error(errorMessage);
            throw new ResultEntityIsNotValidException(errorMessage);
        }
        return getStringView(array, width);
    }

    public String getFirstRow(int width){
        String[] array = new String[]{"Stock", "Open", "Close", "Min", "Max", "Average", "Vol"};
        return getStringView(array, width);
    }

    public boolean checkValues(String [] values) {
        boolean check = false;
        if (values.length == 3) {
            if (values[0].length()==5) {
            } else if (Pattern.matches("[A-Z]{4}", values[0])) {
                if (Pattern.matches("\\d+", values[1]))
                    if (Pattern.matches("\\d+", values[2]))
                        check = true;
            }
        }
        return check;
    }

    public String[] format(String row){
        String [] values = row.split("\\s");
        if (values[0].length()==5) {
            values[0] = replace(values);
        }
        return values;
    }

    private String getStringView(String[] values, int width) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            String value = values[i];
            int countWhitespaces = width - value.length();
            StringBuilder element = new StringBuilder(value);
            for (int j = 0; j < countWhitespaces; j++)
                element.append(" ");
            stringBuilder.append(element);
        }
        return stringBuilder.toString();
    }

    private boolean checkResultEntity(ResultEntity resultEntity){
        boolean check = false;
        if (resultEntity!=null){
            if (resultEntity.getStock()!=null && resultEntity.getStock() instanceof CompanyName){
                check=true;
            }
        }
        return check;
    }
}
