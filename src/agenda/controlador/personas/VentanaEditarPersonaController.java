package agenda.controlador.personas;

import agenda.controlador.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import agenda.modelo.Persona;
import agenda.util.DateUtil;

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class VentanaEditarPersonaController extends BaseController {

    @FXML
    private TextField nombre;
    @FXML
    private TextField primerApellido;
    @FXML
    private TextField calle;
    @FXML
    private TextField codigoPostal;
    @FXML
    private TextField ciudad;
    @FXML
    private TextField cumpleanios;


    private Stage escenarioVentana;
    private Persona persona;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param escenarioVentana
     */
    public void setEscenarioVentana(Stage escenarioVentana) {
        this.escenarioVentana = escenarioVentana;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setPersona(Persona persona) {
        this.persona = persona;

        nombre.setText(persona.getNombre());
        primerApellido.setText(persona.getPrimerApellido());
        calle.setText(persona.getCalle());
        codigoPostal.setText(Integer.toString(persona.getCodigoPostal()));
        ciudad.setText(persona.getCiudad());
        cumpleanios.setText(DateUtil.formatear(persona.getCumpleanios()));
        cumpleanios.setPromptText("dd.mm.yyyy");
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            persona.setNombre(nombre.getText());
            persona.setPrimerApellido(primerApellido.getText());
            persona.setCalle(calle.getText());
            persona.setCodigoPostal(Integer.parseInt(codigoPostal.getText()));
            persona.setCiudad(ciudad.getText());
            persona.setCumpleanios(DateUtil.parse(cumpleanios.getText()));

            okClicked = true;
            escenarioVentana.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        escenarioVentana.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nombre.getText() == null || nombre.getText().length() == 0) {
            errorMessage += "Nombre no válido\n"; 
        }
        if (primerApellido.getText() == null || primerApellido.getText().length() == 0) {
            errorMessage += "Primer apellido no válido\n"; 
        }
        if (calle.getText() == null || calle.getText().length() == 0) {
            errorMessage += "Calle no válida\n"; 
        }

        if (codigoPostal.getText() == null || codigoPostal.getText().length() == 0) {
            errorMessage += "Código Postal no válido\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(codigoPostal.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Código Postal no válido (debe ser un entero)\n"; 
            }
        }

        if (ciudad.getText() == null || ciudad.getText().length() == 0) {
            errorMessage += "Ciudad no válida\n"; 
        }

        if (cumpleanios.getText() == null || cumpleanios.getText().length() == 0) {
            errorMessage += "Cumpleaños no vaĺido\n";
        } else {
            if (!DateUtil.validDate(cumpleanios.getText())) {
                errorMessage += "Cumpleaños no válido. Debe usar el formato dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(escenarioVentana);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
}