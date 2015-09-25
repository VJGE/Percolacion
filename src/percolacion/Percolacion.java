/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package percolacion;

import java.util.Random;

/**
 *
 * @author Sango
 */
public class Percolacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public static boolean[][] crearMatriz(int n, float p){
        //True = sólido
        //False = hueco
        boolean  matriz[][] = new boolean[n][n];
        Random rand = new Random();
        for (int i=0;i<n;i++){
           for (int j=0;j<n;j++){
               float temp =  rand.nextFloat();
               if (temp <= p){
                   matriz[i][j]= true ;
               }
           } 
        }      
      return matriz;  
    }     
}

 