package com.chuches;

public class GirarMatriz {
    public static void main(String[] args){
        int[][] m = getMatriz();
        m = volterMatriz45Grados(volterMatriz45Grados(volterMatriz45Grados(volterMatriz45Grados(m))));
        
        for(int i=0; i<m.length; i++){
            for(int j=0; j<m.length; j++){
                System.out.print(m[i][j]+"   ");
            }
            System.out.println();
        }
    }
    
    public static int[][] getMatriz(){
        int[][] m = {
                        {1,2,3},
                        {4,5,6},
                        {7,8,9}
                    };
        return m;
    }

    private static int[][] volterMatriz45Grados(int[][] m) {
        int m2[][] = new int[m.length][m.length];
        int x = m.length-1;
        for(int i=0; i<m.length; i++){
            for(int j=0; j<m.length; j++){
                m2[j][x] = m[i][j];
            }
            x--;
        }        
        return m2;
    }
}
