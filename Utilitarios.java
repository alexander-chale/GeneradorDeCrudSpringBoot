public class Utilitarios {
  
    public String validaPalabra(String palabra) {
        System.out.println("validaPalabra");
        return palabra.replaceAll("[^A-Za-z]", "");
    }
  

    public String generaMayusculaInicial(String palabra) {
        System.out.println("generaMayusculaInicial");
        return palabra.toUpperCase().charAt(0)
                + palabra.substring(1, palabra.length());
    }

    public String generaMinusculaInicial(String palabra) {
        System.out.println("generaMinusculaInicial");
        return palabra.toLowerCase().charAt(0)
                + palabra.substring(1, palabra.length());
    }

    

     




}
