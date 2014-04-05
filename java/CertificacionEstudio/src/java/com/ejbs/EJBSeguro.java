/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejbs;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

/**
 *
 * @author armando
 */
@Stateless
@RolesAllowed("administrador")
public class EJBSeguro {

    public String greet(){
        return "Hola desde EJB Seguro";
    }
}
