package agenda.controlador.personas;

import agenda.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import agenda.PrincipalApp;
import agenda.controlador.BaseController;
import agenda.modelo.Persona;
import javafx.scene.control.Alert;

public class PersonaResumenController extends BaseController{
    @FXML
    private TableView<Persona> tablaPersona;
    @FXML
    private TableColumn<Persona, String> columnaNombre;
    @FXML
    private TableColumn<Persona, String> columnaPrimerApellido;
    @FXML
    private Label etiquetaNombre;
    @FXML
    private Label etiquetaPrimerApellido;
    @FXML
    private Label etiquetaCalle;
    @FXML
    private Label etiquetaCodigoPostal;
    @FXML
    private Label etiquetaCiudad;
    @FXML
    private Label etiquetaCumpleanios;

    /**
     * Contructor de la clase
     * el constructor es llamado antes del método initialize().
     */
    public PersonaResumenController() { }

    /**
     * Inicializa el controlador. El método es automáticamente llamado 
     * cuando es cargado el archivo .fxml asociado
     */
    @FXML
    @Override
    public void initialize() {        
       
        columnaNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        columnaPrimerApellido.setCellValueFactory(cellData -> cellData.getValue().primerApellidoProperty());
        
             
        // Limpiar el detalle de la persona existente
        mostrarDetallesPersona(null);         
       
        //Asociamos los "listener"
        tablaPersona.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetallesPersona(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(PrincipalApp mainApp) {
        super.setMainApp(mainApp);

        // Añadimos información a la tabla de personas.
        tablaPersona.setItems(mainApp.getPersonasInfo());
    }
    
    /**
     * Completar los textos correspondientes para mostrar la información de una persona.
     * si no se asocia persona, todo los textos son borrados
     * 
     * @param persona objecto persona o null
     */
    private void mostrarDetallesPersona(Persona persona) {
        if (persona != null) {
            
            // Rellenar el area de persona, con un contenido determinado.
            etiquetaNombre.setText(persona.getNombre());
            etiquetaPrimerApellido.setText(persona.getPrimerApellido());
            etiquetaCalle.setText(persona.getCalle());
            etiquetaCodigoPostal.setText(Integer.toString(persona.getCodigoPostal()));
            etiquetaCiudad.setText(persona.getCiudad());
            etiquetaCumpleanios.setText(DateUtil.formatear(persona.getCumpleanios()));
            
        } else {
            
            // Resetear los valores por defecto del area de persona.
            etiquetaNombre.setText("---");
            etiquetaPrimerApellido.setText("---");
            etiquetaCalle.setText("---");
            etiquetaCodigoPostal.setText("---");
            etiquetaCiudad.setText("---");
            etiquetaCumpleanios.setText("");
            
        }
    }
    
    /**
    * Called when the user clicks on the delete button.
    */
    @FXML
    private void manejadorBorrraPersona() {
        int selectedIndex = tablaPersona.getSelectionModel().getSelectedIndex();
        
        if (selectedIndex >= 0) {
            tablaPersona.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.initOwner(mainApp.getEscenarioPrincipal());
            alerta.setTitle("No existe selección");
            alerta.setHeaderText("No se ha seleccionado una persona");
            alerta.setContentText("Por favor, seleccione una persona en la tabla");
            
            alerta.showAndWait();
        }
    }
    
        /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void manejadorNuevaPersona() {
        Persona tempPerson = new Persona();
        boolean okClicked = mainApp.mostrarVentanaEditarPersona(tempPerson);
        if (okClicked) {
            mainApp.getPersonasInfo().add(tempPerson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void manejadorEditarPersona() {
        Persona personaSeleccionada = tablaPersona.getSelectionModel().getSelectedItem();
        if (personaSeleccionada != null) {
            boolean okClicked = mainApp.mostrarVentanaEditarPersona(personaSeleccionada);
            if (okClicked) {
                mostrarDetallesPersona(personaSeleccionada);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getEscenarioPrincipal());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            
            alert.showAndWait();
        }
    }    
    
    /**
    *  llamado cuando el usuario pulsa el botón volver al menú principal
    */
    @FXML
    private void manejadorBotonVolver() {
        
        mainApp.mostrarVistaPrincipal();
    }
    
}