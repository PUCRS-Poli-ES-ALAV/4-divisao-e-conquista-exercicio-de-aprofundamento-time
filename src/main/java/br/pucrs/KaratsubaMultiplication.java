package br.pucrs;

import java.math.BigInteger;

public class KaratsubaMultiplication {

    // Função principal para multiplicação de inteiros grandes usando o algoritmo de Karatsuba
    public static BigInteger karatsuba(BigInteger x, BigInteger y) {
        // Caso base: se um dos números for menor que 10, multiplique diretamente
        if (x.compareTo(BigInteger.TEN) < 0 || y.compareTo(BigInteger.TEN) < 0) {
            return x.multiply(y);
        }

        // Converte os números em strings
        String xStr = x.toString();
        String yStr = y.toString();
        int n = Math.max(xStr.length(), yStr.length());
        int m = n / 2;

        // Divida os números em duas partes
        BigInteger[] xParts = splitNumber(x, m);
        BigInteger[] yParts = splitNumber(y, m);

        // Obtenha as partes divididas
        BigInteger x1 = xParts[0];
        BigInteger x0 = xParts[1];
        BigInteger y1 = yParts[0];
        BigInteger y0 = yParts[1];

        // Conquista: recursão
        BigInteger P1 = karatsuba(x1, y1);
        BigInteger P2 = karatsuba(x0, y0);
        BigInteger P3 = karatsuba(x1.add(x0), y1.add(y0)).subtract(P1).subtract(P2);

        // Combina os resultados
        BigInteger result = P1.multiply(BigInteger.TEN.pow(2 * m))
                            .add(P3.multiply(BigInteger.TEN.pow(m)))
                            .add(P2);

        return result;
    }

    // Função para dividir um número em duas partes
    private static BigInteger[] splitNumber(BigInteger number, int m) {
        String numStr = number.toString();
        int length = numStr.length();
        String part1 = length <= m ? "0" : numStr.substring(0, length - m);
        String part2 = numStr.substring(length - m);

        return new BigInteger[] {
            new BigInteger(part1),
            new BigInteger(part2)
        };
    }

    public static void main(String[] args) {
        BigInteger x = new BigInteger("123456");
        BigInteger y = new BigInteger("123654");
        BigInteger result = karatsuba(x, y);
        System.out.println("Resultado da multiplicação: " + result);
    }
}
