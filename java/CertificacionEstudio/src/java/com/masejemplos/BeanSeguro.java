package com.masejemplos;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;

@Singleton
@DeclareRoles(
    { Roles.ADMIN, Roles.VENTAS, Roles.CLIENTE, Roles.INVITADO }
)
@RolesAllowed({})
public class BeanSeguro {
    
    @Resource
    private SessionContext context;
    
    @RolesAllowed( Roles.ADMIN )
    public void metodoDeNegocio1(){
        //Lógica de negocio
    }
    
    @RolesAllowed( {Roles.ADMIN, Roles.VENTAS} )
    public void metodoDeNegocio2(){
        //Lógica de negocio
    }
    
    @PermitAll
    public void metodoDeNegocio3(){
        //Lógica de negocio
    }
    
    @PermitAll
    public void metodoDeNegocio4(){
        if ( context.isCallerInRole(Roles.ADMIN) ){
            // Lógica de negocio para ADMIN
        }
        else{
            // Lógica de negocio para alguien más
        }
    }
}
