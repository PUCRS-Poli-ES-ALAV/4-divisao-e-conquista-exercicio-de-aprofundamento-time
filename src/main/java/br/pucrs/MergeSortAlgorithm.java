package br.pucrs;

import java.util.Random;

public class MergeSortAlgorithm {

    // Função principal do Merge Sort
    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            mergeSort(array, left, middle);

            mergeSort(array, middle + 1, right);

            merge(array, left, middle, right);
        }
    }

    private static void merge(int[] array, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, middle + 1, rightArray, 0, n2);

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    private static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(); 
        }
        return array;
    }

    public static void main(String[] args) {
        int[] sizes = {32, 2048, 1048576}; 

        for (int size : sizes) {
            int[] array = generateRandomArray(size);
            long startTime = System.currentTimeMillis();
            mergeSort(array, 0, array.length - 1);
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            System.out.println("Tamanho do vetor: " + size);
            System.out.println("Tempo gasto: " + duration + " ms");
        }
    }
}
