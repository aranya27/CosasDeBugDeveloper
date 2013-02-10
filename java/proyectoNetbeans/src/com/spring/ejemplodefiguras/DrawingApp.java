package com.spring.ejemplodefiguras;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

/*
 * SIGUE este video http://www.youtube.com/watch?v=WUqyoH_G4Ko
 */
public class DrawingApp {
    public static void main(String[] args){
        //BeanFactory factory = new XmlBeanFactory(new FileSystemResource("src/figuritas.xml"));
        ApplicationContext context = new ClassPathXmlApplicationContext("figuritas.xml");
        
        Triangle triangle = (Triangle)context.getBean("triangle-name");
        triangle.draw();
        
    }
}
