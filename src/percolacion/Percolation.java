/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package percolacion;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @authors: Sebastian Valencia, Christian Jiménez, Deici Gómez
 */
public class Percolation {

    /**
     * @param args the command line arguments
     */
    static boolean[][] matrix;
    static int sizeM;
    public static Graphic ui;
    public static ArrayList<float[]> results = new ArrayList<float[]>();
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        ui = new Graphic();
        Scanner scan = new Scanner(System.in);
        
        int accumulated = 0;
       
        System.out.println("Bienvenido a Percolacion");

        System.out.println("Ingrese el tamaño de la matriz");
        sizeM = scan.nextInt();

        System.out.println("Ingrese el delta de la probabilidad (numero entero entre 0 y 100)");
        float increaseP = scan.nextFloat()/100;

        System.out.println("Ingrese el numero de matrices por probabilidad");
        int numberM = scan.nextInt();
        
        matrix=new boolean[sizeM][sizeM];
        System.out.println(increaseP);
        for (float i = 0; i <= 1; i+= increaseP) {
            System.out.println(i);
            accumulated = 0;
            for (int j = 0; j < numberM; j++) {
                createMatrix(sizeM, i);
                //seeMatrix();
                int pos[] = {0, 0};
                //System.out.print(" ANTES " + accumulated);
                accumulated = accumulated + pathFinder(pos);
                //System.out.print(" PATH " + pathFinder(pos));
                //System.out.print(" LUEGO "+ accumulated);
                //System.out.println("+++++++++++++++++++");
            }
            float tempN = (float)numberM;
            System.out.println( "acumulado de "+i+": "+accumulated + " probabilidad " + (accumulated/tempN));
            results.add(new float[]{i,(accumulated/tempN)});
            
            //float a = (accumulated / numberM);
            //float aux[] = {i, a};
            //results.add(aux);
        }
        graphic(results);
    }

    public static void createMatrix(int n, float p) {
        //True = sólido
        //False = hueco
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                float temp = rand.nextFloat();
                if (temp <= p) {
                    matrix[i][j] = true;
                } else {
                    matrix[i][j] = false;
                }
            }
        }
    }

    public static void seeMatrix(){
        for (int i = 0; i < sizeM; i++) {
            for (int j = 0; j < sizeM; j++) {
                if(matrix[i][j]){
                    System.out.print("X ");
                }
                else{
                    System.out.print("O ");
                }
            }
            System.out.println("");
            
        }
        System.out.println("");
        System.out.println("-----");
        System.out.println("");
    
    }
    public static int pathFinder(int[] position) {
        int returnT;
        if (position[0] >= sizeM || position[1] >= sizeM || position[0] < 0 || position[1] < 0) {
            return 0;
        }
        int a;
        int b;
        if (matrix[position[0]][position[1]] && position[1] == 0) {
            a = position[0] + 1;
            b = position[1];
            int temp[] = {a, b};
            return pathFinder(temp);
        }
        if (matrix[position[0]][position[1]]) {
            return 0;
        }

        if (position[1] == sizeM - 1) {
            return 1;
        }
        matrix[position[0]][position[1]]=true;
        //seeMatrix();
        //evalua camino hacia abajo
        a = position[0];
        b = position[1] + 1;
        int temp[] = {a, b};
        returnT = pathFinder(temp);
        if (returnT == 1) {
            return returnT;
        }
        //evalua el camino hacia la derecha
        a = position[0]+1;
        b = position[1];
        temp[0] =a;
        temp[1] =b;
        returnT = pathFinder(temp);
        if (returnT == 1) {
            return returnT;
        }
        
        //evalua el camino hacia la izquierda
        a = position[0]-1;
        b = position[1];
        temp[0] =a;
        temp[1] =b;
        returnT = pathFinder(temp);
        if (returnT == 1) {
            return returnT;
        }
        
        //evalua el camino hacia arriba
        a = position[0];
        b = position[1]-1;
        temp[0] =a;
        temp[1] =b;
        returnT = pathFinder(temp);
        if (returnT == 1) {
            return returnT;
        }

        return returnT;
    }

    public static void graphic(ArrayList<float[]> results) {

    }
}