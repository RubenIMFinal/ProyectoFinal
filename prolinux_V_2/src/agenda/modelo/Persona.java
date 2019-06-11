package agenda.modelo;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase asociada a persona
 *
 * @author Agustin Aguilera
 */
public class Persona {

    private final StringProperty nombre;
    private final StringProperty primerApellido;
    private final StringProperty calle;
    private final IntegerProperty codigoPostal;
    private final StringProperty ciudad;
    private final ObjectProperty<LocalDate> cumpleanios;

    /**
     * Contructor por defecto
     */
    public Persona() {
        this(null, null);
    }

    /**
     * Constructor asignando valores a campos adicionales.
     * 
     * @param nombre
     * @param primerApellido
     */
    public Persona(String nombre, String primerApellido) {
        this.nombre = new SimpleStringProperty(nombre);
        this.primerApellido = new SimpleStringProperty(primerApellido);

        // Campos adicionales
        this.calle = new SimpleStringProperty("Calle al azar");
        this.codigoPostal = new SimpleIntegerProperty(1234);
        this.ciudad = new SimpleStringProperty("Madrid");
        this.cumpleanios = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getPrimerApellido() {
        return primerApellido.get();
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido.set(primerApellido);
    }

    public StringProperty primerApellidoProperty() {
        return primerApellido;
    }

    public String getCalle() {
        return calle.get();
    }

    public void setCalle(String calle) {
        this.calle.set(calle);
    }

    public StringProperty calleProperty() {
        return calle;
    }

    public int getCodigoPostal() {
        return codigoPostal.get();
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal.set(codigoPostal);
    }

    public IntegerProperty codigoPostalProperty() {
        return codigoPostal;
    }

    public String getCiudad() {
        return ciudad.get();
    }

    public void setCiudad(String ciudad) {
        this.ciudad.set(ciudad);
    }

    public StringProperty ciudadProperty() {
        return ciudad;
    }

    public LocalDate getCumpleanios() {
        return cumpleanios.get();
    }

    public void setCumpleanios(LocalDate cumpleanios) {
        this.cumpleanios.set(cumpleanios);
    }

    public ObjectProperty<LocalDate> cumpleaniosProperty() {
        return cumpleanios;
    }
}