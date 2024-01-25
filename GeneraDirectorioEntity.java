import java.io.File;

public class GeneraDirectorioEntity {

    public void GeneraDirectorioEntity(String directorioEntidad, String nombreEntidad) {
        File directorioEntity = new File(directorioEntidad);

        if (directorioEntity.mkdir()) {
            System.out.println("   Directorio " + nombreEntidad + "/entity creado satisfactoriamente.");
        }

    }
}
