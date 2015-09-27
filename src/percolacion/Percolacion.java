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
 * @author Sango
 */
public class Percolacion {

    /**
     * @param args the command line arguments
     */
    static boolean[][] matriz;
    static int tamanioM;

    public static void main(String[] args) {
        // TODO code application logic here

        ArrayList<float[]> resultados = new ArrayList<float[]>();
        int acumulado = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Bienvenido a Percolacion");

        System.out.println("Ingrese el tamaño de la matriz");
        tamanioM = scan.nextInt();

        System.out.println("Ingrese el delta de la probabilidad (numero entero entre 0 y 100)");
        float aumentoP = scan.nextFloat()/100;

        System.out.println("Ingrese el numero de matrices por probabilidad");
        int numeroM = scan.nextInt();
        
        matriz=new boolean[tamanioM][tamanioM];
        System.out.println(aumentoP);
        for (float i = 0; i <= 1; i+= aumentoP) {
            System.out.println(i);
            acumulado = 0;
            for (int j = 0; j < numeroM; j++) {
                crearMatriz(tamanioM, i);
                seeMatrix();
                int pos[] = {0, 0};
                acumulado = +pathFinder(pos);
                System.out.println("+++++++++++++++++++");
            }
            System.out.println("acumulado de "+i+": "+acumulado);
            float a = (acumulado / numeroM);
            float aux[] = {i, a};
            resultados.add(aux);
        }
        grafica(resultados);
    }

    public static void crearMatriz(int n, float p) {
        //True = sólido
        //False = hueco
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                float temp = rand.nextFloat();
                if (temp <= p) {
                    matriz[i][j] = true;
                } else {
                    matriz[i][j] = false;
                }
            }
        }
    }

    public static void seeMatrix(){
        for (int i = 0; i < tamanioM; i++) {
            for (int j = 0; j < tamanioM; j++) {
                if(matriz[i][j]){
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
    public static int pathFinder(int[] posicion) {
        int retorno;
        if (posicion[0] >= tamanioM || posicion[1] >= tamanioM || posicion[0] < 0 || posicion[1] < 0) {
            return 0;
        }
        int a;
        int b;
        if (matriz[posicion[0]][posicion[1]] && posicion[1] == 0) {
            a = posicion[0] + 1;
            b = posicion[1];
            int temp[] = {a, b};
            return pathFinder(temp);
        }
        if (matriz[posicion[0]][posicion[1]]) {
            return 0;
        }

        if (posicion[1] == tamanioM - 1) {
            return 1;
        }
        matriz[posicion[0]][posicion[1]]=true;
        seeMatrix();
        //evalua camino hacia abajo
        a = posicion[0];
        b = posicion[1] + 1;
        int temp[] = {a, b};
        retorno = pathFinder(temp);
        if (retorno == 1) {
            return retorno;
        }
        //evalua el camino hacia la derecha
        a = posicion[0]+1;
        b = posicion[1];
        temp[0] =a;
        temp[1] =b;
        retorno = pathFinder(temp);
        if (retorno == 1) {
            return retorno;
        }
        
        //evalua el camino hacia la izquierda
        a = posicion[0]-1;
        b = posicion[1];
        temp[0] =a;
        temp[1] =b;
        retorno = pathFinder(temp);
        if (retorno == 1) {
            return retorno;
        }
        
        //evalua el camino hacia arriba
        a = posicion[0];
        b = posicion[1]-1;
        temp[0] =a;
        temp[1] =b;
        retorno = pathFinder(temp);
        if (retorno == 1) {
            return retorno;
        }

        return retorno;
    }

    public static void grafica(ArrayList<float[]> resultados) {

    }
}
