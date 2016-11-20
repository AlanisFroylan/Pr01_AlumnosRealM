package tec.com.pr01_alumnosrealm;

/**
 * Created by Froylan on 15/11/2016.
 */

public class AlumnosModels {
    private String nombre;
    private String apellidos, control, carrera, email;

    public AlumnosModels(String control, String nombre, String apellidos, String carrera, String email){
        this.control = control;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.carrera = carrera;
        this.email = email;

    }

    public String getControl() {
        return control;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public String getCarrera() {
        return carrera;
    }
    public String getEmail() {return email;


    }

    @Override
    public String toString() {

        return "No.Control:"+control+
                "\nNombre:"+nombre+" "+apellidos+
                "\nCarrera:"+carrera+
                "\nEmail:"+email;


    }


}
