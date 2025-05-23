package tarearecursividad;
import java.util.Stack;
import java.util.Scanner;

/**
 * Implementación mejorada de la Función de Ackermann
 * Universidad Nacional Mayor de San Marcos
 * Análisis y Diseño de Algoritmos - Divide y Vencerás
 * 
 * Esta clase implementa tanto la versión recursiva como iterativa
 * con análisis completo de complejidad y métricas de rendimiento.
 */
import java.util.Stack;

import java.util.Stack;

public class TareaRecursividad {

    public static class Resultado {
        int resultado;
        double tiempoMs;
        int pushCount;
        int popCount;

        public Resultado(int res, double t, int psh, int ppl) {
            this.resultado = res;
            this.tiempoMs = t;
            this.pushCount = psh;
            this.popCount = ppl;
        }
    }

    /**
     * Versión iterativa de la función Ackermann-Péter
     * 
     * @param m primer parámetro de Ackermann
     * @param n segundo parámetro de Ackermann
     * @return valor de A(m, n)
     */
    public static Resultado ackermann(int m, int n) {
        Stack<Integer> stack = new Stack<>();
        int pushCount = 0, popCount = 0;
        long startTime = System.nanoTime();

        stack.push(m);

        while (!stack.isEmpty()) {
            int currentM = stack.pop();
            popCount++;

            if (currentM == 0) {
                n += 1;
            } else if (n == 0) {
                stack.push(currentM - 1);
                pushCount++;
                n = 1;
            } else {
                stack.push(currentM - 1);
                stack.push(currentM);
                pushCount += 2;
                n -= 1;
            }
        }

        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1e6; //   ms

        return new Resultado(n, durationMs, pushCount, popCount);
    }

    public static void main(String[] args) {
        System.out.println("==================================================================");
        System.out.println("       Funcion Ackermann-Peter - Analisis de Complejidad");
        System.out.println("==================================================================");

        // Casos de prueba pequeños (Ackermann crece muy rápido)
        int[][] casosPrueba = {
            {0, 0}, {0, 1}, {0, 2},
            {1, 0}, {1, 1}, {1, 2},
            {2, 0}, {2, 1}, {2, 2},
            {3, 0}, {3, 1}, {3, 2}, {3, 3}
        };

        System.out.printf("%-5s %-5s %-10s %-12s %-8s %-8s%n", "m", "n", "Resultado", "Tiempo (ms)", "Pushes", "Pops");

        for (int[] caso : casosPrueba) {
            int m = caso[0];
            int n = caso[1];
            Resultado res = ackermann(m, n);
            System.out.printf("%-5d %-5d %-10d %-12.5f %-8d %-8d%n",
                    m, n, res.resultado, res.tiempoMs, res.pushCount, res.popCount);
        }

        System.out.println("\nComplejidad Temporal:");
        System.out.println("La funcion de Ackermann tiene una complejidad T(m,n) e O(A(m,n)).");
        System.out.println("Esto significa que su tiempo de ejecucion crece tan rapido como el valor devuelto,");
        System.out.println("lo cual es extremadamente rapido incluso para valores pequeños de m y n.");
    }
}