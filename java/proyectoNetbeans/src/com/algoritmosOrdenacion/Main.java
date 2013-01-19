package com.algoritmosOrdenacion;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args){
        ArrayList<Integer> array = new ArrayList<Integer>();
        array.add(5);
        array.add(50);
        array.add(7);
        array.add(2);
        array.add(3);
        array.add(14);
        
        
        Ordenador o = new QuickSort();
        System.out.println("====ARRAY ORIGINAL====");
        o.imprime(array);
        System.out.println("====COMIENZO DE ORDENACION====");
        o.ordenar(array);
        System.out.println("====RESULTADO FINAL====");
        o.imprime(array);
    }
}



