package br.pucrs;

import java.util.Random;

public class MaxValueAlgorithm {

    // Método para encontrar o maior valor usando divisão e conquista
    static long maxVal2(long[] A, int init, int end) {
        if (end - init <= 1) {
            return Math.max(A[init], A[end]);
        } else {
            int m = (init + end) / 2;
            long v1 = maxVal2(A, init, m);
            long v2 = maxVal2(A, m + 1, end);
            return Math.max(v1, v2);
        }
    }

    // Método para gerar um vetor de números aleatórios
    static long[] generateRandomArray(int size) {
        Random rand = new Random();
        long[] array = new long[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextLong();
        }
        return array;
    }

    public static void main(String[] args) {
        int[] sizes = {32, 2048, 1048576}; // Tamanhos dos vetores a serem testados
        
        for (int size : sizes) {
            long[] array = generateRandomArray(size);
            long startTime = System.currentTimeMillis();
            long maxValue = maxVal2(array, 0, array.length - 1);
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            System.out.println("Tamanho do vetor: " + size);
            System.out.println("Maior valor: " + maxValue);
            System.out.println("Tempo gasto: " + duration + " ms");
        }
    }
}
