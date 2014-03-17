/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejbs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 *
 * @author armando
 */

@Stateless
public class MyStatelessSessionBean implements MyStatelessSessionBeanLocal {

    
    public String hola(String nombre) throws ParseException{
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.parse("10/10/2000");
        return "Hola "+nombre;
    }
    
    public int suma(int a, int b){
        return a+b;
    }

}
