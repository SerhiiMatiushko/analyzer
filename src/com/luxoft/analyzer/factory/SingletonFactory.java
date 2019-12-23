package com.luxoft.analyzer.factory;

import com.luxoft.analyzer.log.Log;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SingletonFactory {

    private final static Log LOG = new Log(SingletonFactory.class);
    private final static ConcurrentMap<String,Object> serviceContainer = new ConcurrentHashMap<>();

    public static <T> T getInstance(Class<T> type){
        String key = type.toString();
        Object instance= serviceContainer.get(key);
        if(instance == null){
            synchronized (type) {
                try{
                    instance = type.newInstance();
                    LOG.debug("Created instance "+type.toString());
                    serviceContainer.put(key, instance);
                }catch(IllegalAccessException | InstantiationException e){
                    LOG.error("Not Created Singleton instance for class : "+key+" - Exception Message : "+e.getMessage());
                }
            }
        }
        return type.cast(instance);
    }
}
