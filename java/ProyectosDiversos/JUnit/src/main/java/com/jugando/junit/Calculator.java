package com.jugando.junit;

public class Calculator {
    
    private Operador operador;
    private int a,b;
    
    public int evaluate(String expression) {
        int sum = 0;
        for (String summand : expression.split("\\+")) {
            sum += Integer.valueOf(summand);
        }
        return sum;
    }

    
    public double efectuarOperacion(){
        return operador.efectuarOperacion(a,b);
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }
    
    
    
    
}
