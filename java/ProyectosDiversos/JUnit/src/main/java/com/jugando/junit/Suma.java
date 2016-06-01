package com.jugando.junit;


public class Suma implements Operador {

    @Override
    public double efectuarOperacion(double a, double b) {
        return a+b;
    }
    
}
