package com.projecteuler.problem3;

import java.util.ArrayList;

/**
 * Programa que resuelve el problema 3 de projecteuler.net
 * A continuacion se muestra la descripcion del problema
 * 
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 * @author armando
 */
public class Problem3 {
    public static void main(String[] args) {
        System.out.println("Resultado = "+getLargestPrimeFactor(600851475143d));
    }

    public static String getLargestPrimeFactor(double n){        
        ArrayList<Double> primeFactors = getPrimeFactors(n);        
        return primeFactors.isEmpty() ? "No hubo resultado" : primeFactors.get(primeFactors.size()-1)+"";
    }
    
    private static ArrayList<Double> getPrimeFactors(double n) {
        ArrayList<Double> primeFactors = new ArrayList<Double>();
        for(double i=2; i<=n/2; i++){            
            if(n%i==0 && esNumeroPrimo(i)){
                primeFactors.add(i);
            }
            if(seConsiguieronTodosLosPrimeFactors(primeFactors,n)){
                break;
            }
        }
        return primeFactors;
    }
    
    private static boolean seConsiguieronTodosLosPrimeFactors(ArrayList<Double> primeFactors, double n) {
        double m=1d;
        for(double d : primeFactors){
            m *= d;
            if(m==n) return true;
        }
        return false;
    }
    
    public static boolean esNumeroPrimo(double n){
        if(n<2) return false;
        for(double i=n-1; i>1; i--)
            if(n%i==0) return false;        
        return true;
    }
}