/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejbs;

import java.text.ParseException;
import javax.ejb.Local;

/**
 *
 * @author armando
 */
@Local
public interface MyStatelessSessionBeanLocal {
    public String hola(String nombre) throws ParseException;
}
