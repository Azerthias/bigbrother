package fr.smb.bigbrother.util.database.read;

import java.util.ArrayList;

public class Synchronizer {

    ArrayList<Reader> list = new ArrayList<>();

    protected void update(){
        for(Reader r : list){
            r.realUpdate();
        }
    }

    protected void add(Reader r){
        list.add(r);
    }
}
