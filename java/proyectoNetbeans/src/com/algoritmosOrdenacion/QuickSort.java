/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algoritmosOrdenacion;

import java.util.ArrayList;

/**
 *
 * @author armando
 */
public class QuickSort extends Ordenador{

    @Override
    public void ordenar(ArrayList<Integer> array) {
        quicksort(array,0,array.size()-1);
    }   
    public void quicksort(ArrayList<Integer> array, int iz, int de){
        int i = iz;
        int j = de;   
        int pivote = array.get( (de+iz)/2 );
        
        do{
            while(array.get(i) < pivote){ i++; }
            while(array.get(j) > pivote){ j--; }

            if(i <= j){
                int auxi = array.get(i);
                array.set( i, array.get(j) );
                array.set(j, auxi);
                i++;
                j--;
            }
            imprime(array);
        }while(i <= j);
        
        if(j>iz) quicksort(array,iz, j);
        if(i<de) quicksort(array,i, de);
    }
}