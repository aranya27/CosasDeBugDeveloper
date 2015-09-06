
package com.probando;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author armando
 */
public class ProbandoLambdas {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("Hello world from runnable!");
        r.run();
        
        
        
        MiInterface1 m1 = () -> System.out.println("hola");
        MiInterface2 m2 = () -> 2;
        MiInterface3 m3 = (n) -> 2;
        MiInterface3 m3_2 = (n) -> n*n;
        MiInterface4 m4 = (x,y) -> System.out.println(x+" - "+y);
        MiInterface5 m5 = System.out::println;
        MiInterface6 m6 = String::toLowerCase;
        MiInterface7 m7 = String::substring;
        MiInterface8 m8 = ArrayList<String>::new;
        
    }
}



interface MiInterface1{
    void metodo();
}
interface MiInterface2{
    int metodo();
}
interface MiInterface3{
    int metodo(int n);
}
interface MiInterface4{
    void metodo(String a, String b);
}
interface MiInterface5{
    void metodo(String s);
}
interface MiInterface6{
    String metodo(String s);
}
interface MiInterface7{
    String metodo(String s, int i);
}
interface MiInterface8{
    List metodo();
}