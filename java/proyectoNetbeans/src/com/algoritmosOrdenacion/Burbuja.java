package com.algoritmosOrdenacion;

import java.util.ArrayList;

public class Burbuja extends Ordenador{
    public void ordenar(ArrayList<Integer> array){
        if(array==null) return;

        boolean ordenado;
        int ord = 1;
        do{
            ordenado = true;
            for(int i=0; i < (array.size()-ord); i++){
                if(array.get(i)>array.get(i+1)){
                        //Pendiente
                        Integer aux = array.get(i+1);
                        array.set(i+1, array.get(i));
                        array.set(i, aux);
                        ordenado = false;
                }
            }
            ord++;
            imprime(array);
                
        }while(ordenado == false);
        
    }
}
