package fr.smb.bigbrother.util.database.read;

import java.util.HashMap;
import java.util.Map;

import fr.smb.bigbrother.util.Util;

public class out {

    Map<String,String> sMap = new HashMap<>();
    Map<String, Integer> iMap = new HashMap<>();
    Map<String, Boolean> bMap = new HashMap<>();


    //----------String----------

    public void addString(String key, String value){
        sMap.put(key,value);
    }

    public void updateString(String key, String value){
        sMap.remove(key);
        addString(key,value);
        //sMap.replace(key,value);
    }

    public String getString(String key){
        if(sMap.containsKey(key)){
            return sMap.get(key);
        }else{
            return null;
        }
        //return sMap.getOrDefault(key,"");
    }

    public String getString(String key, String defaultValue){
        if(sMap.containsKey(key)){
            return sMap.get(key);
        }else{
            return defaultValue;
        }
        //return sMap.getOrDefault(key,"");
    }


    //----------Int----------

    public void addInt(String key, int value){
        iMap.put(key,value);
    }

    public void updateInt(String key, int value){
        iMap.remove(key);
        addInt(key,value);
        //iMap.replace(key,value);
    }

    public int getInt(String key){
        if(iMap.containsKey(key)){
            return iMap.get(key);
        }else{
            return 0;
        }
        //return iMap.getOrDefault(key,0); -> Api24
    }

    public int getInt(String key, int defaultValue){
        if(iMap.containsKey(key)){
            return iMap.get(key);
        }else{
            return defaultValue;
        }
        //return iMap.getOrDefault(key,0); -> Api24
    }


    //----------Boolean----------

    public void addBoolean(String key, boolean value){
        bMap.put(key,value);
    }

    public void updateBoolean(String key, boolean value){
        bMap.remove(key);
        addBoolean(key,value);
        //bMap.replace(key,value);
    }

    public boolean getBoolean(String key){
        if(bMap.containsKey(key)){
            return bMap.get(key);
        }else{
            return false;
        }
        //return bMap.getOrDefault(key,false); -> Api24
    }

    public boolean getBoolean(String key, boolean defaultValue){
        if(bMap.containsKey(key)){
            return bMap.get(key);
        }else{
            return defaultValue;
        }
        //return bMap.getOrDefault(key,false); -> Api24
    }
}
