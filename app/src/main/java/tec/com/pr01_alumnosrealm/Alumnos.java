package tec.com.pr01_alumnosrealm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Froylan on 15/11/2016.
 */

public class Alumnos extends RealmObject {
    @PrimaryKey
    private String control;
    private String nombre;
    private String apellidos, carrera, email;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
