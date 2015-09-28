
package com.productoConsumidor;

public class Deadlock {
    static class Friend {
        private final String name;
        public Friend(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s"
                + "  has bowed to me!%n", 
                this.name, bower.getName());
            bower.bowBack(this);
        }
        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s"
                + " has bowed back to me!%n",
                this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        /*
        final Friend alphonse =
            new Friend("Alphonse");
        final Friend gaston =
            new Friend("Gaston");
        new Thread(new Runnable() {
            public void run() { alphonse.bow(gaston); }
        }).start();
        new Thread(new Runnable() {
            public void run() { gaston.bow(alphonse); }
        }).start();
        */
        
        final Prueba p = new Prueba();
        
        new Thread(
            new Runnable(){
                @Override
                public void run() {
                    p.metodo1();
                }
            }
        ).start();
        
        new Thread(
            new Runnable(){
                @Override
                public void run() {
                    p.metodo2();
                }
            }
        ).start();
        
        
    }
    
    
    static class Prueba{
        public synchronized void metodo1(){
            for(int i=0; i<1000; i++){
                System.out.println("metodo 1: "+i);
            }
        }
        
        public synchronized void metodo2(){
            for(int i=0; i<1000; i++){
                System.out.println("metodo 2: "+i);
            }
        }
    }
}