public class TipoEstructura {
    public String controlador(String nombreEntidad, String TipoEstructura, String nombreDeAplicacion) {

        switch (TipoEstructura) {
            case "0":
                return nombreEntidad + "/controller";
            case "1":

                return nombreDeAplicacion+"/controller";
            default:
                return nombreDeAplicacion +"/controller";

        }
    }

    public String entidad(String nombreEntidad, String TipoEstructura) {

        switch (TipoEstructura) {
            case "0":
                return nombreEntidad + "/entity";
            case "1":

                return "/entity";
            default:
                return "/entity";

        }
    }

    public String dtos(String nombreEntidad, String TipoEstructura) {

        switch (TipoEstructura) {
            case "0":
                return nombreEntidad + "/entity/dtos";
            case "1":

                return "/entity/dtos";
            default:
                return "/entity/dtos";

        }
    }

    public String mapper(String nombreEntidad, String TipoEstructura) {

        switch (TipoEstructura) {
            case "0":
                return nombreEntidad + "/entity/mapper";
            case "1":

                return "/entity/mapper";
            default:
                return "/entity/mapper";

        }
    }

     public String repositorio(String nombreEntidad, String TipoEstructura) {

        switch (TipoEstructura) {
            case "0":
                return nombreEntidad + "/repository";
            case "1":

                return "/repository";
            default:
                return "/repository";

        }
    }

     public String servicio(String nombreEntidad, String TipoEstructura) {

        switch (TipoEstructura) {
            case "0":
                return nombreEntidad + "/service";
            case "1":

                return "/service";
            default:
                return "/service";

        }
    }

    public String servicioImpl(String nombreEntidad, String TipoEstructura) {

        switch (TipoEstructura) {
            case "0":
                return nombreEntidad + "/service/impl";
            case "1":

                return "/service/impl";
            default:
                return "/service/impl";

        }
    }

}
