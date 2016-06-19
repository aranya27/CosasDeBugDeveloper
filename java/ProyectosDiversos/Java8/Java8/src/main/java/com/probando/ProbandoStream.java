/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.probando;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author armando
 */
public class ProbandoStream {

    public static void main(String[] args) {
        /*
        Revisar
        http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html
        http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html
        
        */
        
        
        
        //Inicializamos todo
        List<Persona> personas = new ArrayList<Persona>();
        personas.add(new Persona("Armando", 29, 'M'));
        personas.add(new Persona("Fernando", 31, 'M'));
        personas.add(new Persona("Claudia", 18, 'F'));
        personas.add(new Persona("Beto", 22, 'M'));
        
        personas.forEach(p -> System.out.println(p.getNombre()));

        Collections.sort(personas, new Comparator<Persona>() { //Usando clase anonima
            
            @Override
            public int compare(Persona obj1, Persona obj2) {
                return obj1.getEdad().compareTo(obj2.getEdad());
            }

            
        });
        
        System.out.println("Impresion 1");
        imprimirPersonas(personas);
        
        
        Collections.sort(personas, (Persona p1, Persona p2) -> p2.getEdad().compareTo(p1.getEdad()) ); //Usando lambda
        Collections.sort(personas, (p1, p2)                 -> p2.getEdad().compareTo(p1.getEdad()) ); //Usando lambda otra manera de hacer lo mismo
        
        System.out.println("Impresion 2");
        imprimirPersonas(personas);
        
        personas = personas.stream().filter(p -> p.getSexo().equals('M')).collect(toList());
        System.out.println("Impresion 3");
        imprimirPersonas(personas);
        
        int totalAge = personas.stream().mapToInt(p -> p.getEdad()).sum();
        System.out.println("totalAge = "+totalAge);
        
    }

    private static void imprimirPersonas(List<Persona> personas) {
        personas.forEach(p -> {
            System.out.println("Edad: "+p.getEdad()+", Sexo: "+p.getSexo()+", Nombre: "+p.getNombre());
        });
    }
}
