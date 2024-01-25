import java.io.File;

public class Utilitarios {
    // Todas estas validaciones deben ser mas robustas, esa es la idea, luego
    // las arreglo mejor

    public String validaPalabra(String palabra) {
        // Le quita todos los caracteres especiales incluidos los numeros.
        // System.out.println("validaPalabra " + palabra);
        return palabra.replaceAll("[^A-Za-z]", "");
    }

    public String generaMayusculaInicial(String palabra) {
        return palabra.toUpperCase().charAt(0)
                + palabra.substring(1, palabra.length());
    }

    public String generaMinusculaInicial(String palabra) {
        return palabra.toLowerCase().charAt(0)
                + palabra.substring(1, palabra.length());
    }

    public Integer valida0o1(Integer numero) {
        // Aqui aun no se que mas validar
        if ((numero == 0) || (numero == 1)) {
            return numero;
        } else {
            return 1000;
        }

    }

    public void deleteFile(File file) {
        // Elimina archivos y careptas creados previamente
        if (file.exists()) {
            if (file.isFile())
                file.delete();
            else {
                File f[] = file.listFiles();
                for (int i = 0; i < f.length; i++) {
                    deleteFile(f[i]);
                }
                file.delete();
            }
        } else
            // System.out.println("el archivo no existe");
            ;

    }

}
