import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Scanner;

public class GeneradorCrudBcv {
    public static void main(String[] args) throws IOException {

        Scanner entidad = new Scanner(System.in);
        System.out.println("Ingrese el nombre de la entidad en camelCase");
        String nombreEntidad = entidad.next();

        Scanner tipo = new Scanner(System.in);
        System.out.println("Ingrese 0 = Para id Long");
        System.out.println("Ingrese 1 = Para id String");
        String tipoId = tipo.next();

        Properties config = new Properties();
        InputStream configInput = null;

        configInput = new FileInputStream("config.properties");
        config.load(configInput);

        String tipoEstructura = config.getProperty("tipoEstructura");
        String nombreDeAplicacion = config.getProperty("nombreDeAplicacion");

        String nombreEntidadCamelCase = nombreEntidad.toUpperCase().charAt(0)
                + nombreEntidad.substring(1, nombreEntidad.length());

        if ("0".equals(tipoEstructura)) {
            String directorioControlador = nombreEntidad + "/controller";
            String directorioEntidad = nombreEntidad + "/entity";
            String directorioRepositorio = nombreEntidad + "/repository";
            String directorioServicio = nombreEntidad + "/service";
            String directorioServicioImplementacion = nombreEntidad + "/service/impl";

            System.out.println("CREANDO LA ESTRUCTURA BCV DEL CRUD PARA SPRING BOOT... ");

            // eliminamos la carpeta nombreEntidad con todos sus subcarpetas o archivos si
            // existe

            File archivo = new File(nombreEntidad);
            deleteFile(archivo);

            File directorioPrincipal = new File(nombreEntidad);

            if (directorioPrincipal.mkdir()) {
                System.out.println("   Directorio " + nombreEntidad + " creado satisfactoriamente.");
            }

            File directorioController = new File(directorioControlador);
            if (directorioController.mkdir()) {
                System.out.println("   Directorio " + nombreEntidad + "/controller creado satisfactoriamente.");

                try (FileWriter fw = new FileWriter(
                        directorioControlador + "/" + nombreEntidadCamelCase + "Controller.java",
                        true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)) {
                    out.println("package " + nombreDeAplicacion + "." + nombreEntidad + ".controller;");
                    out.println("");
                    out.println("import org.springframework.web.bind.annotation.CrossOrigin;");
                    out.println("import org.springframework.web.bind.annotation.RequestMapping;");
                    out.println("import org.springframework.web.bind.annotation.RestController;");
                    out.println("");
                    if ("0".equals(tipoId)) {
                        out.println("import " + nombreDeAplicacion + ".bases.controllers.BaseControllerIdLongImpl;");
                    }

                    else {
                        out.println("import " + nombreDeAplicacion + ".bases.controllers.BaseControllerIdStringImpl;");

                    }

                    out.println(
                            "import " + nombreDeAplicacion + "." + nombreEntidad + ".entity." + nombreEntidadCamelCase
                                    + ";");
                    out.println("import " + nombreDeAplicacion + "." + nombreEntidad + ".service.impl."
                            + nombreEntidadCamelCase + "ServiceImpl;");
                    out.println("");
                    out.println("@RestController");
                    out.println("@CrossOrigin(origins = \"*\")");
                    out.println("@RequestMapping(path = \"api/v1/" + nombreEntidad + "\")");
                    if ("0".equals(tipoId)) {
                        out.println(
                                "public class " + nombreEntidadCamelCase
                                        + "Controller extends BaseControllerIdLongImpl<"
                                        + nombreEntidadCamelCase + "," + nombreEntidadCamelCase + "ServiceImpl> {");
                    } else {
                        out.println(
                                "public class " + nombreEntidadCamelCase
                                        + "Controller extends BaseControllerIdStringImpl<"
                                        + nombreEntidadCamelCase + "," + nombreEntidadCamelCase + "ServiceImpl> {");

                    }
                    out.println("");
                    out.println("");
                    out.println("}");

                    if ("0".equals(tipoId)) {
                        System.out.println("      Archivo " + nombreEntidadCamelCase
                                + "Controller.java con Id Long creado satisfactoriamente.");
                    }

                    else {
                        System.out.println("      Archivo " + nombreEntidadCamelCase
                                + "Controller.java con Id String creado satisfactoriamente.");

                    }

                } catch (IOException e) {
                    // exception handling left as an exercise for the reader
                }
            }

            File directorioEntity = new File(directorioEntidad);

            if (directorioEntity.mkdir()) {
                System.out.println("   Directorio " + nombreEntidad + "/entity creado satisfactoriamente.");
            }

            File directorioEntityDtos = new File(nombreEntidad + "/entity/dtos");

            if (directorioEntityDtos.mkdir()) {
                System.out.println("   Directorio " + nombreEntidad + "/entity/dtos creado satisfactoriamente.");
            }

            File directorioEntityMapper = new File(nombreEntidad + "/entity/mapper");

            if (directorioEntityMapper.mkdir()) {
                System.out.println("   Directorio " + nombreEntidad + "/entity/mapper creado satisfactoriamente.");
            }

            File directorioRepository = new File(directorioRepositorio);

            if (directorioRepository.mkdir()) {
                System.out.println("   Directorio " + nombreEntidad + "/repository creado satisfactoriamente.");
                try (FileWriter fw = new FileWriter(
                        directorioRepositorio + "/" + nombreEntidadCamelCase + "Repository.java",
                        true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)) {
                    out.println("package " + nombreDeAplicacion + "." + nombreEntidad + ".repository;");
                    out.println("");
                    out.println("import org.springframework.stereotype.Repository;");
                    out.println("import com.bcv.cusg." + nombreEntidad + ".entity." + nombreEntidadCamelCase + ";");
                    out.println("import com.bcv.cusg.bases.repositories.BaseRepository;");
                    out.println("");
                    out.println("@Repository");
                    if ("0".equals(tipoId)) {
                        out.println("public interface " + nombreEntidadCamelCase + "Repository extends BaseRepository<"
                                + nombreEntidadCamelCase + ", Long> {");

                    }

                    else {
                        out.println("public interface " + nombreEntidadCamelCase + "Repository extends BaseRepository<"
                                + nombreEntidadCamelCase + ", String> {");

                    }
                    out.println("");
                    out.println("");
                    out.println("}");
                    if ("0".equals(tipoId)) {
                        System.out.println("      Archivo " + nombreEntidadCamelCase
                                + "Repository.java con Id Long creado satisfactoriamente.");
                    }

                    else {
                        System.out.println("      Archivo " + nombreEntidadCamelCase
                                + "Repository.java con Id String creado satisfactoriamente.");

                    }

                } catch (IOException e) {
                    // exception handling left as an exercise for the reader
                }
            }

            File directorioService = new File(directorioServicio);

            if (directorioService.mkdir()) {
                System.out.println("   Directorio " + nombreEntidad + "/service creado satisfactoriamente.");
                try (FileWriter fw = new FileWriter(directorioServicio + "/" + nombreEntidadCamelCase + "Service.java",
                        true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)) {
                    out.println("package " + nombreDeAplicacion + "." + nombreEntidad + ".service;");
                    out.println("");

                    out.println(
                            "import " + nombreDeAplicacion + "." + nombreEntidad + ".entity." + nombreEntidadCamelCase
                                    + ";");
                    out.println("import com.bcv.cusg.bases.services.BaseService;");
                    out.println("");
                    if ("0".equals(tipoId)) {
                        out.println("public interface " + nombreEntidadCamelCase + "Service extends BaseService<"
                                + nombreEntidadCamelCase + ", Long> {");
                    }

                    else {
                        out.println("public interface " + nombreEntidadCamelCase + "Service extends BaseService<"
                                + nombreEntidadCamelCase + ", String> {");

                    }

                    out.println("");
                    out.println("");
                    out.println("}");
                    if ("0".equals(tipoId)) {
                        System.out.println("      Archivo " + nombreEntidadCamelCase
                                + "Service.java con Id Long creado satisfactoriamente.");
                    }

                    else {
                        System.out.println("      Archivo " + nombreEntidadCamelCase
                                + "Service.java con Id String creado satisfactoriamente.");

                    }

                } catch (IOException e) {
                    // exception handling left as an exercise for the reader
                }
            }

            File directorioServiceImpl = new File(directorioServicioImplementacion);

            if (directorioServiceImpl.mkdir()) {
                System.out.println("   Directorio " + nombreEntidad + "/service/impl creado satisfactoriamente.");
                try (FileWriter fw = new FileWriter(
                        directorioServicioImplementacion + "/" + nombreEntidadCamelCase + "ServiceImpl.java",
                        true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)) {
                    out.println("package " + nombreDeAplicacion + "." + nombreEntidad + ".service.impl;");
                    out.println("");
                    out.println("import org.springframework.beans.factory.annotation.Autowired;");
                    out.println("import org.springframework.stereotype.Service;");
                    out.println("");
                    out.println(
                            "import " + nombreDeAplicacion + "." + nombreEntidad + ".entity." + nombreEntidadCamelCase
                                    + ";");
                    out.println("import " + nombreDeAplicacion + "." + nombreEntidad + ".repository."
                            + nombreEntidadCamelCase + "Repository;");
                    out.println(
                            "import " + nombreDeAplicacion + "." + nombreEntidad + ".service." + nombreEntidadCamelCase
                                    + "Service;");
                    out.println("import " + nombreDeAplicacion + ".bases.repositories.BaseRepository;");
                    out.println("import " + nombreDeAplicacion + ".bases.services.BaseServiceImpl;");
                    out.println("");
                    out.println("@Service");
                    if ("0".equals(tipoId)) {
                        out.println("public class " + nombreEntidadCamelCase + "ServiceImpl extends BaseServiceImpl<"
                                + nombreEntidadCamelCase + ", Long> implements " + nombreEntidadCamelCase
                                + "Service {");

                    }

                    else {
                        out.println("public class " + nombreEntidadCamelCase + "ServiceImpl extends BaseServiceImpl<"
                                + nombreEntidadCamelCase + ", String> implements " + nombreEntidadCamelCase
                                + "Service {");

                    }
                    out.println("");
                    out.println("@Autowired");
                    out.println("private " + nombreEntidadCamelCase + "Repository " + nombreEntidad + "Repository;");
                    out.println("");
                    if ("0".equals(tipoId)) {
                        out.println("public " + nombreEntidadCamelCase + "ServiceImpl(BaseRepository<"
                                + nombreEntidadCamelCase + ", Long> baseRepository) {");

                    }

                    else {
                        out.println("public " + nombreEntidadCamelCase + "ServiceImpl(BaseRepository<"
                                + nombreEntidadCamelCase + ", String> baseRepository) {");

                    }
                    out.println("     super(baseRepository);");
                    out.println("}");
                    out.println("");
                    out.println("}");
                    if ("0".equals(tipoId)) {
                        System.out.println("      Archivo " + nombreEntidadCamelCase
                                + "ServiceImpl.java con Id Long creado satisfactoriamente.");
                    }

                    else {
                        System.out.println("      Archivo " + nombreEntidadCamelCase
                                + "ServiceImpl.java con Id String creado satisfactoriamente.");

                    }

                } catch (IOException e) {
                    // exception handling left as an exercise for the reader
                }
            }
        } else {
            System.out.println("eligio la estructura normal esto hay que hacerlo" + tipoEstructura);

            String directorioControlador = nombreDeAplicacion + "/controller";
            String directorioEntidad = nombreDeAplicacion + "/entity";
            String directorioEntidadDtos = nombreDeAplicacion + "/entity/dtos";
            String directorioEntidadMapper = nombreDeAplicacion + "/entity/mapper";

            String directorioRepositorio = nombreDeAplicacion + "/repository";
            String directorioServicio = nombreDeAplicacion + "/service";
            String directorioServicioImplementacion = nombreDeAplicacion + "/service/impl";

            System.out.println("CREANDO LA ESTRUCTURA NORMAL DEL CRUD PARA SPRING BOOT... ");

            // eliminamos la carpeta nombreEntidad con todos sus subcarpetas o archivos si
            // existe

            // File archivo = new File(nombreDeAplicacion);
            // deleteFile(archivo);

            File directorioPrincipal = new File(nombreDeAplicacion);

            if (directorioPrincipal.mkdir()) {
                System.out.println("   Directorio " + nombreDeAplicacion + " creado satisfactoriamente.");
            } else {
                System.out.println("El directorio principal ya existe");
            }

            File directorioController = new File(directorioControlador);

            if (directorioController.mkdir()) {
                System.out.println("   Directorio " + directorioControlador + " creado satisfactoriamente.");
            } else {
                System.out.println("El directorio Controlador ya existe");
            }
            File directorioEntity = new File(directorioEntidad);

            if (directorioEntity.mkdir()) {
                System.out.println("   Directorio " + directorioEntidad + " creado satisfactoriamente.");
            } else {
                System.out.println("El directorio Entidad ya existe");
            }
            File directorioEntityDtos = new File(directorioEntidadDtos);
            if (directorioEntityDtos.mkdir()) {
                System.out.println("   Directorio " + directorioEntidadDtos + " creado satisfactoriamente.");
            } else {
                System.out.println("El directorio Entidad Dtos ya existe");
            }

             File directorioEntityMapper = new File(directorioEntidadMapper);
            if (directorioEntityMapper.mkdir()) {
                System.out.println("   Directorio " + directorioEntidadMapper + " creado satisfactoriamente.");
            } else {
                System.out.println("El directorio Entidad Dtos ya existe");
            }

            File directorioRepository = new File(directorioRepositorio);
            if (directorioRepository.mkdir()) {
                System.out.println("   Directorio " + directorioRepositorio + " creado satisfactoriamente.");
            } else {
                System.out.println("El directorio repositorio ya existe");
            }

            File directorioService = new File(directorioServicio);
            if (directorioService.mkdir()) {
                System.out.println("   Directorio " + directorioServicio + " creado satisfactoriamente.");
            } else {
                System.out.println("El directorio servicio ya existe");
            }
            File directorioServiceImpl = new File(directorioServicioImplementacion);

            if (directorioServiceImpl.mkdir()) {
                System.out.println("   Directorio " + directorioServicioImplementacion + " creado satisfactoriamente.");
            } else {
                System.out.println("El directorio servicio implementacion ya existe");
            }

            try (FileWriter fw = new FileWriter(
                    directorioControlador + "/" + nombreEntidadCamelCase + "Controller.java",
                    true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)) {
                out.println("package " + nombreDeAplicacion + "." + nombreEntidad + ".controller;");
                out.println("");
                out.println("import org.springframework.web.bind.annotation.CrossOrigin;");
                out.println("import org.springframework.web.bind.annotation.RequestMapping;");
                out.println("import org.springframework.web.bind.annotation.RestController;");
                out.println("");
                if ("0".equals(tipoId)) {
                    out.println("import " + nombreDeAplicacion + ".bases.controllers.BaseControllerIdLongImpl;");
                }

                else {
                    out.println("import " + nombreDeAplicacion + ".bases.controllers.BaseControllerIdStringImpl;");

                }

                out.println(
                        "import " + nombreDeAplicacion + "." + nombreEntidad + ".entity." + nombreEntidadCamelCase
                                + ";");
                out.println("import " + nombreDeAplicacion + "." + nombreEntidad + ".service.impl."
                        + nombreEntidadCamelCase + "ServiceImpl;");
                out.println("");
                out.println("@RestController");
                out.println("@CrossOrigin(origins = \"*\")");
                out.println("@RequestMapping(path = \"api/v1/" + nombreEntidad + "\")");
                if ("0".equals(tipoId)) {
                    out.println(
                            "public class " + nombreEntidadCamelCase
                                    + "Controller extends BaseControllerIdLongImpl<"
                                    + nombreEntidadCamelCase + "," + nombreEntidadCamelCase + "ServiceImpl> {");
                } else {
                    out.println(
                            "public class " + nombreEntidadCamelCase
                                    + "Controller extends BaseControllerIdStringImpl<"
                                    + nombreEntidadCamelCase + "," + nombreEntidadCamelCase + "ServiceImpl> {");

                }
                out.println("");
                out.println("");
                out.println("}");

                if ("0".equals(tipoId)) {
                    System.out.println("      Archivo " + nombreEntidadCamelCase
                            + "Controller.java con Id Long creado satisfactoriamente.");
                }

                else {
                    System.out.println("      Archivo " + nombreEntidadCamelCase
                            + "Controller.java con Id String creado satisfactoriamente.");

                }

            } catch (IOException e) {
                // exception handling left as an exercise for the reader
            }

          
          

            

            if (directorioRepository.mkdir()) {
                System.out.println("   Directorio " + nombreEntidad + "/repository creado satisfactoriamente.");
                try (FileWriter fw = new FileWriter(
                        directorioRepositorio + "/" + nombreEntidadCamelCase + "Repository.java",
                        true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)) {
                    out.println("package " + nombreDeAplicacion + "." + nombreEntidad + ".repository;");
                    out.println("");
                    out.println("import org.springframework.stereotype.Repository;");
                    out.println("import com.bcv.cusg." + nombreEntidad + ".entity." + nombreEntidadCamelCase + ";");
                    out.println("import com.bcv.cusg.bases.repositories.BaseRepository;");
                    out.println("");
                    out.println("@Repository");
                    if ("0".equals(tipoId)) {
                        out.println("public interface " + nombreEntidadCamelCase + "Repository extends BaseRepository<"
                                + nombreEntidadCamelCase + ", Long> {");

                    }

                    else {
                        out.println("public interface " + nombreEntidadCamelCase + "Repository extends BaseRepository<"
                                + nombreEntidadCamelCase + ", String> {");

                    }
                    out.println("");
                    out.println("");
                    out.println("}");
                    if ("0".equals(tipoId)) {
                        System.out.println("      Archivo " + nombreEntidadCamelCase
                                + "Repository.java con Id Long creado satisfactoriamente.");
                    }

                    else {
                        System.out.println("      Archivo " + nombreEntidadCamelCase
                                + "Repository.java con Id String creado satisfactoriamente.");

                    }

                } catch (IOException e) {
                    // exception handling left as an exercise for the reader
                }
            }

            if (directorioService.mkdir()) {
                System.out.println("   Directorio " + nombreEntidad + "/service creado satisfactoriamente.");
                try (FileWriter fw = new FileWriter(directorioServicio + "/" + nombreEntidadCamelCase + "Service.java",
                        true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)) {
                    out.println("package " + nombreDeAplicacion + "." + nombreEntidad + ".service;");
                    out.println("");

                    out.println(
                            "import " + nombreDeAplicacion + "." + nombreEntidad + ".entity." + nombreEntidadCamelCase
                                    + ";");
                    out.println("import com.bcv.cusg.bases.services.BaseService;");
                    out.println("");
                    if ("0".equals(tipoId)) {
                        out.println("public interface " + nombreEntidadCamelCase + "Service extends BaseService<"
                                + nombreEntidadCamelCase + ", Long> {");
                    }

                    else {
                        out.println("public interface " + nombreEntidadCamelCase + "Service extends BaseService<"
                                + nombreEntidadCamelCase + ", String> {");

                    }

                    out.println("");
                    out.println("");
                    out.println("}");
                    if ("0".equals(tipoId)) {
                        System.out.println("      Archivo " + nombreEntidadCamelCase
                                + "Service.java con Id Long creado satisfactoriamente.");
                    }

                    else {
                        System.out.println("      Archivo " + nombreEntidadCamelCase
                                + "Service.java con Id String creado satisfactoriamente.");

                    }

                } catch (IOException e) {
                    // exception handling left as an exercise for the reader
                }
            }

            if (directorioServiceImpl.mkdir()) {
                System.out.println("   Directorio " + nombreEntidad + "/service/impl creado satisfactoriamente.");
                try (FileWriter fw = new FileWriter(
                        directorioServicioImplementacion + "/" + nombreEntidadCamelCase + "ServiceImpl.java",
                        true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)) {
                    out.println("package " + nombreDeAplicacion + "." + nombreEntidad + ".service.impl;");
                    out.println("");
                    out.println("import org.springframework.beans.factory.annotation.Autowired;");
                    out.println("import org.springframework.stereotype.Service;");
                    out.println("");
                    out.println(
                            "import " + nombreDeAplicacion + "." + nombreEntidad + ".entity." + nombreEntidadCamelCase
                                    + ";");
                    out.println("import " + nombreDeAplicacion + "." + nombreEntidad + ".repository."
                            + nombreEntidadCamelCase + "Repository;");
                    out.println(
                            "import " + nombreDeAplicacion + "." + nombreEntidad + ".service." + nombreEntidadCamelCase
                                    + "Service;");
                    out.println("import " + nombreDeAplicacion + ".bases.repositories.BaseRepository;");
                    out.println("import " + nombreDeAplicacion + ".bases.services.BaseServiceImpl;");
                    out.println("");
                    out.println("@Service");
                    if ("0".equals(tipoId)) {
                        out.println("public class " + nombreEntidadCamelCase + "ServiceImpl extends BaseServiceImpl<"
                                + nombreEntidadCamelCase + ", Long> implements " + nombreEntidadCamelCase
                                + "Service {");

                    }

                    else {
                        out.println("public class " + nombreEntidadCamelCase + "ServiceImpl extends BaseServiceImpl<"
                                + nombreEntidadCamelCase + ", String> implements " + nombreEntidadCamelCase
                                + "Service {");

                    }
                    out.println("");
                    out.println("@Autowired");
                    out.println("private " + nombreEntidadCamelCase + "Repository " + nombreEntidad + "Repository;");
                    out.println("");
                    if ("0".equals(tipoId)) {
                        out.println("public " + nombreEntidadCamelCase + "ServiceImpl(BaseRepository<"
                                + nombreEntidadCamelCase + ", Long> baseRepository) {");

                    }

                    else {
                        out.println("public " + nombreEntidadCamelCase + "ServiceImpl(BaseRepository<"
                                + nombreEntidadCamelCase + ", String> baseRepository) {");

                    }
                    out.println("     super(baseRepository);");
                    out.println("}");
                    out.println("");
                    out.println("}");
                    if ("0".equals(tipoId)) {
                        System.out.println("      Archivo " + nombreEntidadCamelCase
                                + "ServiceImpl.java con Id Long creado satisfactoriamente.");
                    }

                    else {
                        System.out.println("      Archivo " + nombreEntidadCamelCase
                                + "ServiceImpl.java con Id String creado satisfactoriamente.");

                    }

                } catch (IOException e) {
                    // exception handling left as an exercise for the reader
                }
            }
        }

    }

    public static void deleteFile(File file) {
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
