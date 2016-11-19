package tec.com.pr01_alumnosrealm;

/**
 * Created by Froylan on 15/11/2016.
 */

public class AlumnosModels {
    private String nombre;
    private String apellidos, control, carrera, email;

    public AlumnosModels(String apellidos, String carrera, String control, String email, String nombre){
        this.apellidos = apellidos;
        this.carrera = carrera;
        this.control = control;
        this.email = email;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getControl() {
        return control;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCarrera() {
        return carrera;
    }
    public String getEmail() {
        return email;


    }

    @Override
    public String toString() {
        return "No. Control:"+control+
                "\n Nombre:"+nombre+" "+apellidos+
                "\n carrera: "+carrera+
                "\n email: "+email;
    }

}
