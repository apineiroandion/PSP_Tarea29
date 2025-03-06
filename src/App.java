import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        // Obtener la lista de proveedores de seguridad instalados en la JVM
        Provider[] providers = Security.getProviders();

        // Mostrar los algoritmos de hash disponibles
        System.out.println("Algoritmos de hash disponibles:");
        for (Provider provider : providers) {
            Set<Provider.Service> services = provider.getServices();
            for (Provider.Service service : services) {
                if (service.getType().equals("MessageDigest")) {
                    System.out.println(service.getAlgorithm());
                }
            }
        }

        // Permitir al usuario seleccionar un algoritmo
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el nombre del algoritmo de hash que deseas usar: ");
        String algorithm = scanner.nextLine();

        // Pedir al usuario que introduzca un texto
        System.out.print("Introduce el texto a resumir: ");
        String inputText = scanner.nextLine();

        try {
            // Calcular el resumen (hash) del texto usando el algoritmo seleccionado
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            byte[] hashBytes = messageDigest.digest(inputText.getBytes());
            StringBuilder hashString = new StringBuilder();
            for (byte b : hashBytes) {
                hashString.append(String.format("%02x", b));
            }

            // Mostrar el resumen (hash) del texto
            System.out.println("Resumen (hash) del texto: " + hashString.toString());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Algoritmo de hash no válido: " + algorithm);
        }

        scanner.close();
    }
}