package br.pucrs;

import java.util.Random;

public class MaxValueAlgorithm {

    // Função para encontrar o maior valor em um vetor
    public static long maxVal1(long[] array, int n) {
        long max = array[0];
        for (int i = 1; i < n; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    // Função para gerar um vetor de números aleatórios
    private static long[] generateRandomArray(int size) {
        Random rand = new Random();
        long[] array = new long[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextLong(); // Valores aleatórios
        }
        return array;
    }

    public static void main(String[] args) {
        int[] sizes = {32, 2048, 1048576}; // Tamanhos dos vetores a serem testados
        
        for (int size : sizes) {
            long[] array = generateRandomArray(size);
            long startTime = System.currentTimeMillis();
            long maxValue = maxVal1(array, array.length);
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            System.out.println("Tamanho do vetor: " + size);
            System.out.println("Maior valor: " + maxValue);
            System.out.println("Tempo gasto: " + duration + " ms");
        }
    }
}
