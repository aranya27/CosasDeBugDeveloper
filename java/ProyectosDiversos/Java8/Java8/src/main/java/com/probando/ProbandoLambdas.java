
package com.probando;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        
        List<Integer> numeros = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<Integer> numerosPares = numeros.stream().filter(n -> n % 2 ==0).collect(Collectors.toList());
        numerosPares.forEach(System.out::println);
        List<Persona> personas = crearListaPersonas();
        List<String> nombres = personas.stream().filter(p -> p.getSexo() == 'F').map(Persona::getNombre).collect(Collectors.toList());
        nombres.forEach(System.out::println);
        
        String s = "hola";
        Predicate predicate = (ss) -> ss != null;
        predicate.test(s);
        
    }
    
    
    
    public static List<Persona> crearListaPersonas(){
        List<Persona> personas = new ArrayList<>();
        personas.add( new Persona("Pedrito", 40, 'M') );
        personas.add( new Persona("Susana", 20, 'F') );
        personas.add( new Persona("Juan", 15, 'M') );
        personas.add( new Persona("Elena", 45, 'F') );
        personas.add( new Persona("Marcela", 18, 'F') );
        
        return personas;
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