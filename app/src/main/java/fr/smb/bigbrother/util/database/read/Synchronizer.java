package fr.smb.bigbrother.util.database.read;

import java.util.ArrayList;

public class Synchronizer {

    static ArrayList<Reader> list = new ArrayList<>();

    public void update(){
        for(Reader r : list ){
            r.realUpdate();
        }
    }

    public void add(Reader r){
        list.add(r);
    }
}
