package com.projecteuler.problem1;

/**
 * Programa que resuelve el problema 1 de projecteuler.net
 * A continuacion se muestra la descripcion del problema
 * 
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. 
 * The sum of these multiples is 23.
 * Find the sum of all the multiples of 3 or 5 below 1000.
 * @author armando
 */
public class Problem1 {
    public static void main(String[] args){
        System.out.println("Resultado = "+getSumaDeMultiplos(1000));
    }

    private static int getSumaDeMultiplos(int numero) {
        int sumaTotal=0;
        for(int i=1; i<numero; i++){
            if(isMultiplo(i,3) || isMultiplo(i,5)){
                sumaTotal += i;                
            }
        }
        
        return sumaTotal;
    }
    
    private static boolean isMultiplo(int numero, int multiplo) {
        if(numero%multiplo==0)
            return true;
        return false;
        
    }
}
