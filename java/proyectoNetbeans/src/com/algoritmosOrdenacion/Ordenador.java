package com.algoritmosOrdenacion;

import java.util.ArrayList;

public abstract class Ordenador{
    public abstract void ordenar(ArrayList<Integer> array);

    public void imprime(ArrayList<Integer> array){
        StringBuilder s = new StringBuilder("");
        for(Integer i : array){
                if(s.toString().equals(""))
                        s.append(i.intValue());
                else
                        s.append(","+i.intValue());
        }
        System.out.println(s.toString());
    }
}

