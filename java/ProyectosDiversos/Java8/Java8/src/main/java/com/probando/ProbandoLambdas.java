
package com.probando;

/**
 *
 * @author armando
 */
public class ProbandoLambdas {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("Hello world from runnable!");
        r.run();
    }
}
