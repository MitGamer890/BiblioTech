package util;
import java.util.Scanner;

/**
 * Métodos auxiliares.
 * @Author Andrés Rosado
 */
public class Utilidades {
    public static char leerRespuesta() {
        Scanner sc = new Scanner(System.in);
        char r = sc.next().charAt(0);
        return Character.toLowerCase(r);
    }
}