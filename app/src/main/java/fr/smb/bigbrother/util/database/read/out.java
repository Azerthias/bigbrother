package fr.smb.bigbrother.util.database.read;

import java.util.HashMap;
import java.util.Map;

public class out {

    Map<String,String> sMap = new HashMap<>();
    Map<String, Integer> iMap = new HashMap<>();
    Map<String, Boolean> bMap = new HashMap<>();





    public void addString(String key, String value){
        sMap.put(key,value);
    }


    public void updateString(String key, String value){
        sMap.replace(key,value);
    }

    public String getString(String key){
        return sMap.getOrDefault(key,"");
    }

    public void addInt(String key, int value){
        iMap.put(key,value);
    }


    public void updateInt(String key, int value){
        iMap.replace(key,value);
    }

    public int getInt(String key){
        return iMap.getOrDefault(key,0);
    }


    public void addBoolean(String key, boolean value){
        bMap.put(key,value);
    }


    public void updateBoolean(String key, boolean value){
        bMap.replace(key,value);
    }

    public boolean getBoolean(String key){
        return bMap.getOrDefault(key,false);
    }
}
