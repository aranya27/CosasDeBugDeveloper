package com.algoritmosOrdenacion;

import java.util.ArrayList;

public class Seleccion extends Ordenador{
    public void ordenar(ArrayList<Integer> array){
        Integer min;
        for(int i=0; i<array.size(); i++){
            min = array.get(i);
            for(int j=(i+1); j<array.size(); j++){
                if( array.get(i) > array.get(j) ){
                        //Pendiente
                        Integer aux = array.get(j);
                        array.set(j,array.get(i));
                        array.set(i,aux);
                }

                imprime(array);
            }
        }
    }	
}