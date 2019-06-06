/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.controlador;

/**
 *
 * @author desarrollo
 */

//Avance Rubén, parte hecha para el toolbar


import agenda.PrincipalApp; 
import agenda.controlador.personas.PersonaResumenController;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;


public class VistaRaizController {

   
    // Reference to the main application
    private PrincipalApp mainApp;


    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(PrincipalApp mainApp) {
        this.mainApp = mainApp;
    }

   
    @FXML
    private void handleNew() {
        
        mainApp.getPersonasInfo().clear();
        mainApp.setPersonFilePath(null);
 
        //mainApp.setPersonFilePath(null); //esta parte es la que tenía yo antes.
    }
        @FXML
    private void handleNewAnotaciones() {
        
        mainApp.getAnotacionesInfo().clear();
        mainApp.setPersonFilePath(null);

        //mainApp.setPersonFilePath(null); //esta parte es la que tenía yo antes.
    }

   
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getEscenarioPrincipal());

        if (file != null) {
            mainApp.loadPersonDataFromFile(file);
        }
    }
    
    @FXML
    private void handleOpenAnotaciones() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getEscenarioPrincipal());

        if (file != null) {
            mainApp.loadAnotacionDataFromFile(file);
        }
    }

    
    @FXML
    private void handleSave() {
        File personFile = mainApp.getPersonFilePath();
        if (personFile != null) {
            mainApp.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }
    
        @FXML
    private void handleSaveAnotaciones() {
        File notasFile = mainApp.getPersonFilePath();
        if (notasFile != null) {
            mainApp.saveAnotacionDataToFile(notasFile);
        } else {
            handleSaveAs();
        }
    }

    
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getEscenarioPrincipal());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.savePersonDataToFile(file);
        }
    }
    
        @FXML
    private void handleSaveAsAnotaciones() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getEscenarioPrincipal());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.saveAnotacionDataToFile(file);
        }
    }

   
    @FXML
    private void handleAbout() {        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("AddressApp");
        alert.setHeaderText(null);
        alert.setContentText("Creado por Rubén Izquierdo.");
        alert.showAndWait(); 
    }

  
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    
        @FXML
    private void manejadorBotonVolver() {
        
        mainApp.mostrarVistaPrincipal();
    }
}