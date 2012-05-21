package com.codingdojo.fizzbuzz;

import java.util.*;

public class FizzBuzz {
    
    public static void main(String[] args) {
        List<String> resultado = getFizzBuzzArray(100);
        for(String s : resultado){
            System.out.println(s);
        }
    }

    private static List<String> getFizzBuzzArray(int totalNumeros) {
        ArrayList<String> a = new ArrayList<String>();
        for(int i=0; i<=totalNumeros; i++){
            a.add(getFizzBuzzNumber(i));
        }
        return a;
    }

    private static String getFizzBuzzNumber(int numero) {
        if(numero%5==0 && numero%3==0){
            return "FizzBuzz";
        }
        else if(numero%3==0){
            return "Fizz";
        }
        else if(numero%5==0){
            return "Buzz";
        }else{
            return numero+"";
        }
    }
}
