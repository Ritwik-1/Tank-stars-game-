package com.mygdx.game.Game_Classes;

import java.io.*;

public class Serializ_functions {
    public static void deserialize() throws IOException,ClassNotFoundException{
        ObjectInputStream in = null;
        Serialize_attributes s1 = null;
        try{
            in = new ObjectInputStream(new FileInputStream("out.txt"));
            s1 =(Serialize_attributes) in.readObject();
        }
        finally {
            in.close();
        }
    }

    public static void serialize() throws IOException{
        Serialize_attributes g1 = new Serialize_attributes();
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new FileOutputStream("out.txt"));
            out.writeObject(g1);
        }
        finally {
            out.close();
        }
    }
}
