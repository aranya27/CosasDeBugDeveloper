package com.jugando.probandoreflection;

import com.jugando.probandoreflection.annotations.MyAnnotation;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@MyAnnotation(name = "someName", value = "Hello World")
public class Main {

    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Main m = new Main();
        Class clase;
        clase = m.getClass();

        Constructor[] constructors = clase.getConstructors();

        for (Constructor c : constructors) {
            System.out.println("c = " + c);
        }

        Constructor c = clase.getConstructor(int.class);
        Main m2 = (Main) c.newInstance(10);
        System.out.println("m2.a = " + m2.a);

        Annotation[] annotations = clase.getAnnotations();

        for (Annotation annotation : annotations) {
            if (annotation instanceof MyAnnotation) {
                MyAnnotation myAnnotation = (MyAnnotation) annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value: " + myAnnotation.value());
            }
        }

    }

    public Main() {
    }

    public int a;

    public Main(int a) {
        this.a = a;
    }

    public int suma(int i, int j) {
        return i + j;
    }
}
