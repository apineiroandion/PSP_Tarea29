import java.security.Provider;
import java.security.Security;
import java.security.MessageDigest;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        // Obtener la lista de proveedores de seguridad instalados en la JVM
        Provider[] providers = Security.getProviders();

        // Recorrer cada proveedor
        for (Provider provider : providers) {
            // Obtener los servicios del proveedor
            Set<Provider.Service> services = provider.getServices();

            // Filtrar los servicios que correspondan a algoritmos de hash (MessageDigest)
            for (Provider.Service service : services) {
                if (service.getType().equals("MessageDigest")) {
                    // Mostrar en la consola los nombres de los algoritmos de hash disponibles
                    System.out.println(service.getAlgorithm());
                }
            }
        }
    }
}