/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.controlador;

import agenda.PrincipalApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author desarrollo
 */
public class BaseController{ //implements Initializable {

    // Referencia la función main de la aplicación
    public PrincipalApp mainApp;  
    
    
    /**
     * Initializes the controller class.
     */    
    @FXML
    public void initialize() {
        // TODO
    } 
    
     public BaseController() {
        
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
