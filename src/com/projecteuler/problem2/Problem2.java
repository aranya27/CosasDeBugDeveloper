package com.projecteuler.problem2;

public class Problem2 {
    public static void main(String[] args) {
        System.out.println(getSumaFibo(4000000));
    }
    
    public static long getSumaFibo(long numeroMaximo){
        long resultado=0, numFibo;
        boolean salir = false;
        int i=1;
        while(salir==false){
            numFibo = fibonacci(i++);
            if(numFibo<numeroMaximo){
                if(esNumeroPar(numFibo))
                    resultado += numFibo;
            }else
                salir = true;            
        }
        return resultado;
    }
    
    public static boolean esNumeroPar(long numero){
        return numero%2==0 ? true:false;
    }
    
    public static long fibonacci(int numero) {
        if (numero < 2) 
            return numero;
        else return fibonacci(numero-1) + fibonacci(numero-2);
    }
}
