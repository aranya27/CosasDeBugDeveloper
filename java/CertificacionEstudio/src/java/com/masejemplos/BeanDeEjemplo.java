    package com.masejemplos;

    import javax.ejb.Stateless;


    @Stateless
    public class BeanDeEjemplo {
        public String HolaMundo() {
            return "Hola mundo desde bean de sesion";
        }
    }


