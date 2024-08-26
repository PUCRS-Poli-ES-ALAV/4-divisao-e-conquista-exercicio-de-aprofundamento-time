/*Enunciado:
 *  Multiplicação Inteira de n-bits recebe dois números inteiros x e y de n-bits e retorna o resutado de x * y.

Assim, novamente:

implemente o algortimo abaixo;
teste-o para os 3 casos de valores inteiros: com 4 bits, com 16 bits e com 64 bits. Nestes testes, contabilize o número de iterações que o algoritmo executa, e o tempo gasto.

 */

/*
 * Explicação do Algoritmo
Caso Base: Se n = 1, o algoritmo retorna o produto direto de x e y.
Divisão e Conquista:
O número x é dividido em duas partes: a (parte superior) e b (parte inferior).
O número y é dividido em c (parte superior) e d (parte inferior).
São realizadas quatro multiplicações recursivas: e, f, g e h.
Finalmente, o resultado é combinado usando a fórmula: 2^(2m)*e + 2^m*(g + h) + f.
Contagem de Iterações: Um contador global é incrementado a cada chamada recursiva para monitorar o número de iterações.
Medição de Tempo: O tempo total gasto pelo algoritmo é medido em nanossegundos usando System.nanoTime().
Teste do Algoritmo
O algoritmo é testado para 3 tamanhos de bits:

4 bits: Máximo valor representável é 15 (1111 em binário).
16 bits: Máximo valor representável é 65535 (1111111111111111 em binário).
64 bits: Máximo valor representável é 2^64 - 1.

Execução
Ao executar o código abaixo, o algoritmo realizará a multiplicação dos valores máximos possíveis para os tamanhos de bits 
especificados e fornecerá o número de iterações e o tempo gasto em nanossegundos.

O resultado esperado para o produto de dois valores de 4 bits (1111 * 1111), 
dois valores de 16 bits (1111111111111111 * 1111111111111111), e dois valores de 64 bits (111...1 * 111...1) será exibido, 
junto com o número de iterações e o tempo necessário para calcular o produto.

Nota: Este algoritmo é um exemplo de implementação básica e pode ser ineficiente para grandes valores de n, 
especialmente devido à natureza recursiva do algoritmo e à ausência de otimizações que poderiam reduzir 
o número de chamadas recursivas.
 */

public class MultiplyNBits {

    private static long iterationCount = 0;  // Contador de iterações

    public static long multiply(long x, long y, int n) {
        iterationCount++;

        // Caso base: se n for 1, multiplica diretamente
        if (n == 1) {
            return x * y;
        }

        int m = (n + 1) / 2;  // Calcula m como o teto de n / 2

        // Divide x e y em duas partes
        long a = x >> m;         // Parte superior de x
        long b = x - (a << m);   // Parte inferior de x
        long c = y >> m;         // Parte superior de y
        long d = y - (c << m);   // Parte inferior de y

        // Multiplicações recursivas
        long e = multiply(a, c, m);
        long f = multiply(b, d, m);
        long g = multiply(b, c, m);
        long h = multiply(a, d, m);

        // Combina os resultados e retorna
        return (e << (2 * m)) + ((g + h) << m) + f;
    }

    public static void main(String[] args) {
        testMultiply(4);   // Teste com 4 bits
        testMultiply(16);  // Teste com 16 bits
        testMultiply(64);  // Teste com 64 bits
    }

    private static void testMultiply(int bitSize) {
        long maxVal = (1L << bitSize) - 1;  // Valor máximo representável com bitSize bits

        long x = maxVal;  // Valor de x (máximo possível)
        long y = maxVal;  // Valor de y (máximo possível)

        iterationCount = 0;  // Reset do contador de iterações

        long startTime = System.nanoTime();  // Início da contagem do tempo
        long result = multiply(x, y, bitSize);
        long endTime = System.nanoTime();  // Fim da contagem do tempo

        System.out.println("Bits: " + bitSize);
        System.out.println("Resultado: " + result);
        System.out.println("Iterações: " + iterationCount);
        System.out.println("Tempo (ns): " + (endTime - startTime));
        System.out.println();
    }
}
