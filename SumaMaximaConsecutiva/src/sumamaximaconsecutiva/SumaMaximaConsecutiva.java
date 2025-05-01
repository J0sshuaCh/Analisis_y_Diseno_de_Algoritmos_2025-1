package sumamaximaconsecutiva;
import java.util.*;
/**
 * @author Chavez Cerna, Joshua Nicolas (23200090)
 */
public class SumaMaximaConsecutiva {
    
    public static int[] sumaMaximaSubsecuencia(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[]{0, 0, 0};
        }
        
        boolean todosNegativos = true;
        for (int num : arr) {
            if (num >= 0) {
                todosNegativos = false;
                break;
            }
        }
        if (todosNegativos) {
            return new int[]{0, 0, 0};
        }
        
        int maxActual = arr[0];
        int maxGlobal = arr[0];
        int bajo = 0;
        int alto = 0;
        int inicioTemp = 0;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxActual + arr[i]) {
                maxActual = arr[i];
                inicioTemp = i;
            } else {
                maxActual += arr[i];
            }
            
            if (maxActual > maxGlobal) {
                maxGlobal = maxActual;
                bajo = inicioTemp + 1;  
                alto = i + 1;           
            }
        }
        
        return new int[]{maxGlobal, bajo, alto};
    }
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== PROGRAMA DE SUMA MAXIMA CONSECUTIVA ===");
        System.out.println("1. Usar arreglo por defecto");
        System.out.println("2. Ingresar valores manualmente");
        System.out.println("3. Generar valores aleatorios");
        System.out.print("Selecciona una opcion (1-3): ");
        
        int opcion = scanner.nextInt();
        int[] ejemplo;
        
        switch (opcion) {
            case 1:
                // Arreglo por defecto como en el ejemplo
                ejemplo = new int[]{-2, 11, -4, 13, -5, 9, -3, 2, -8, 4};
                System.out.println("Usando arreglo por defecto: " + Arrays.toString(ejemplo));
                break;
                
            case 2:
                // Ingreso manual de valores
                System.out.print("¿Cuantos valores deseas ingresar?: ");
                int n = scanner.nextInt();
                ejemplo = new int[n];
                
                for (int i = 0; i < n; i++) {
                    System.out.print("Valor #" + (i + 1) + ": ");
                    ejemplo[i] = scanner.nextInt();
                }
                System.out.println("Valores ingresados: " + Arrays.toString(ejemplo));
                break;
                
            case 3:
                // Generación aleatoria de valores
                System.out.print("¿Cuantos valores aleatorios deseas generar?: ");
                int cantidadRandom = scanner.nextInt();
                System.out.print("Rango minimo de valores: ");
                int min = scanner.nextInt();
                System.out.print("Rango maximo de valores: ");
                int max = scanner.nextInt();
                
                ejemplo = new int[cantidadRandom];
                Random random = new Random();
                
                for (int i = 0; i < cantidadRandom; i++) {
                    ejemplo[i] = random.nextInt(max - min + 1) + min;
                }
                System.out.println("Valores generados: " + Arrays.toString(ejemplo));
                break;
                
            default:
                System.out.println("Opcion no valida. Usando arreglo por defecto.");
                ejemplo = new int[]{-2, 11, -4, 13, -5, 9, -3, 2, -8, 4};
        }
        
        // Mostrar los valores con sus posiciones
        System.out.println("\nValores:");
        for (int i = 0; i < ejemplo.length; i++) {
            System.out.printf("%-8d", ejemplo[i]);
        }
        System.out.println();
        
        System.out.println("Posicion:");
        for (int i = 0; i < ejemplo.length; i++) {
            System.out.printf("%-8d", (i + 1));
        }
        System.out.println();
        
        // Ejecutar el algoritmo y obtener resultado
        int[] resultado = sumaMaximaSubsecuencia(ejemplo);
        int sumaMaxima = resultado[0];
        int inicio = resultado[1];
        int fin = resultado[2];
        
        // Mostrar el resultado
        if (sumaMaxima == 0 && inicio == 0 && fin == 0) {
            System.out.println("\nLa suma es cero (todos los valores son negativos).");
        } else {
            System.out.println("\nLa sumatoria maxima consecutiva es de a" + inicio + " hasta a" + fin + " con un valor de " + sumaMaxima + ".");
            
            // Mostrar la subsecuencia encontrada
            System.out.print("\nLa subsecuencia es: [");
            for (int i = inicio - 1; i < fin; i++) {
                System.out.print(ejemplo[i]);
                if (i < fin - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
            
            // Resaltar la subsecuencia en el arreglo original
            System.out.println("\nEn el arreglo original:");
            for (int i = 0; i < ejemplo.length; i++) {
                if (i >= inicio - 1 && i < fin) {
                    System.out.printf("%-8s", "[" + ejemplo[i] + "]");
                } else {
                    System.out.printf("%-8d", ejemplo[i]);
                }
            }
            System.out.println();
            
            System.out.println("Posicion:");
            for (int i = 0; i < ejemplo.length; i++) {
                System.out.printf("%-8d", (i + 1));
            }
            System.out.println();
        }
        
        // Análisis de complejidad
        System.out.println("\nAnalisis de complejidad del algoritmo:");
        System.out.println("- Complejidad temporal: O(n) - una sola pasada por el arreglo");
        System.out.println("- Complejidad espacial: O(1) - espacio auxiliar constante");
        System.out.println("  donde n es el tamanio del arreglo");
        
        scanner.close();
    }
}