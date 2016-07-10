package com.jugando.algoritmos;

public class ImprimirDiagonal {
    public static void main(String[] args){
        int[][] matriz = {
            {1, 4, 2, 5, 1},
            {3, 9, 8, 1, 5},
            {5, 1, 3, 3, 2},
            {1, 9, 2, 6, 6},
            {9, 2, 2, 3, 7},
        };
        imprimirDiagonal(matriz);
    }

    private static void imprimirDiagonal(int[][] matriz) {
        
        //For para imprimir la parte superior de la matriz
        for(int cont=0; cont<matriz.length; cont++){
            int i = cont;
            int j = 0;
            System.out.println();
            while(i >= 0){
                System.out.print(matriz[i][j]+" ");
                i--;
                j++;
            }
        }
        
        /*
            {1, 4, 2, 5, 1},
            {3, 9, 8, 1, 5},
            {5, 1, 3, 3, 2},
            {1, 9, 2, 6, 6},
            {9, 2, 2, 3, 7},
        */
        for(int cont=1; cont<matriz.length; cont++){
            int i = matriz.length-1;
            int j = cont;
            System.out.println();
            
            while(j<matriz.length){
                System.out.print(matriz[i][j]+" ");
                i--;
                j++;
            }
        }
        
        
        //System.out.println("xxx= "+matriz[3][2]);
        
        
        
    }
}
