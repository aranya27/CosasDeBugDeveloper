package com.jugando.algoritmos;

public class Hanoi {

    public static void main(String[] args) {
        int n = 2;
        Hanoi(n, "Torre 1", "Torre 2", "Torre 3");  //1:origen  2:auxiliar 3:destino
    }

    public static void Hanoi(int n, String origen, String auxiliar, String destino) {
        if (n == 1) {
            System.out.println("mover disco de " + origen + " a " + destino);
        } else {
            Hanoi(n - 1, origen, destino, auxiliar);
            System.out.println("mover disco de " + origen + " a " + destino);
            Hanoi(n - 1, auxiliar, origen, destino);
        }
    }
}
