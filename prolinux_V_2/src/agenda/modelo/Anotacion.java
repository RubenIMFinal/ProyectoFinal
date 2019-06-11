package agenda.modelo;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase asociada a anotación
 *
 * @author Agustin Aguilera
 */
public class Anotacion {

    private final StringProperty titulo;
    private final StringProperty descripcion;            
    private final ObjectProperty<LocalDate> fecha;

    /**
     * Contructor por defecto
     */
    public Anotacion() {
        this(null, null);
    }

    /**
     * Constructor asignando valores a campos adicionales.
     * 
     * @param nombre
     * @param primerApellido
     */
    public Anotacion(String titulo, String descripcion) {
        this.titulo = new SimpleStringProperty(titulo);
        this.descripcion = new SimpleStringProperty(descripcion);

        // Campos adicionales
        this.fecha = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String getTitulo() {
        return titulo.get();
    }

    public void setTitulo(String titulo) {
        this.titulo.set(titulo);
    }

    public StringProperty tituloProperty() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

   
    public LocalDate getFecha() {
        return fecha.get();
    }

    public void setFecha(LocalDate fecha) {
        this.fecha.set(fecha);
    }

    public ObjectProperty<LocalDate> fechaProperty() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Anotacion{" + "titulo=" + titulo + ", descripcion=" + descripcion + ", fecha=" + fecha + '}';
    }
    
}