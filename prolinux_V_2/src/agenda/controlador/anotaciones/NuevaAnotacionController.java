/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.controlador.anotaciones;

import agenda.modelo.Anotacion;
import agenda.modelo.Persona;
import agenda.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import agenda.util.DateUtil;

/**
 *
 * @author desarrollo
 */


//NI ESTA CLASE NI SU .FXML SE ESTAN USANDO al dar errores.
//Editamos, añadimos...directamente en los edit text de anotaciones.
//No estan borradas para que quede registrado el trabajo.

public class NuevaAnotacionController {
    
    @FXML
    private TextField titulo;
    
    @FXML
    private TextField descripcion;
    
    @FXML
    private TextField fecha;
    
    
    private Stage escenarioVentana;
    private Anotacion anotacion;
    private boolean okClicked = false;
    
    @FXML
    public void initialize() {
    }
    
    public void setEscenarioVentana(Stage escenarioVentana) {
        this.escenarioVentana = escenarioVentana;
    }
     
    public void setNota(Anotacion nota) {
       this.anotacion  = nota;

       titulo.setText(nota.getTitulo());
       descripcion.setText(nota.getDescripcion());
       fecha.setText(DateUtil.formatear(nota.getFecha()));
      fecha.setPromptText("dd.mm.yyyy");
    }
        
    public boolean isOkClicked() {
        return okClicked;
    }
    
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            anotacion.setTitulo(titulo.getText());
            anotacion.setDescripcion(descripcion.getText());
            anotacion.setFecha(DateUtil.parse(fecha.getText()));
            
            okClicked = true;
            escenarioVentana.close();
        }
    } 
    
        @FXML
    private void handleCancel() {
        escenarioVentana.close();
    }
    
       private boolean isInputValid() {
        String errorMessage = "";

        if (titulo.getText() == null || titulo.getText().length() == 0) {
            errorMessage += "Título no válido\n"; 
        }
        if (descripcion.getText() == null || descripcion.getText().length() == 0) {
            errorMessage += "Descripción no válida\n"; 
        }

        if (fecha.getText() == null || fecha.getText().length() == 0) {
            errorMessage += "Cumpleaños no vaĺido\n";
        } else {
            if (!DateUtil.validDate(fecha.getText())) {
                errorMessage += "Cumpleaños no válido. Debe usar el formato dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(escenarioVentana);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
    
        
    
}
