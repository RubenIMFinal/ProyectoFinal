/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.controlador;

import agenda.PrincipalApp;
import agenda.PrincipalConstApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author desarrollo
 */
public class VistaPrincipalController implements Initializable {

    // Referencia la función main de la aplicación
    private PrincipalApp mainApp;  
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
     public VistaPrincipalController() {
        
    }
     
     /**
    * Called when the user clicks on the delete button.
    */
    @FXML
    private void manejadorGestionarAgenda() {  
        mainApp.mostrarVistaResumenPersonas();
        //mainApp.cargarVista(PrincipalConstApp.TIPO_VISTA_PRINCIPAL_AGENDA);      
    }
    
    
     /**
    * Called when the user clicks on the delete button.
    */
    @FXML
    private void manejadorGestionarAnotaciones() {
        mainApp.mostrarVistaAnotacionesResumen();        
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(PrincipalApp mainApp) {
        this.mainApp = mainApp;
        
        //mainApp.mostrarVistaResumenPersonas();

        // Add observable list data to the table
        //tablaPersona.setItems(mainApp.getPersonasInfo());
    }
    
}
