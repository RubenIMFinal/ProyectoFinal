package agenda.controlador.anotaciones;

import agenda.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import agenda.PrincipalApp;
import agenda.controlador.BaseController;
import agenda.modelo.Anotacion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class AnotacionesResumenController extends BaseController{
    @FXML
    private TableView<Anotacion> tablaAnotaciones;
    @FXML
    private TableColumn<Anotacion, String> columnaTitulo;
    @FXML
    private TableColumn<Anotacion, String> columnaDescripcion;    
    @FXML
    private Label etiquetaTitulo;
    @FXML
    private Label etiquetaDescripcion;
    @FXML
    private Label etiquetaFecha;
    
    // Referencia la función main de la aplicación
    //private PrincipalApp mainApp;
    
    public AnotacionesResumenController() {
        
    }    
       
    
    /**
     * Initializes the controller class.
     */    
    @FXML
    @Override
    public void initialize() {
         //columnaDescripcion.setCellValueFactory(cellData -> cellData.getValue().descripcionProperty());
         //Rubén avance...copiado de personaresumecontroller
         columnaTitulo.setCellValueFactory(cellData -> cellData.getValue().tituloProperty());
         //columnaDescripcion.setCellValueFactory(cellData -> cellData.getValue().primerApellidoProperty());
        
             
        // Limpiar el detalle de la persona existente
        mostrarDetallesPersona(null);         
       
        //Asociamos los "listener"
        tablaAnotaciones.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetallesPersona(newValue));
    }  
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */   
    @Override
    public void setMainApp(PrincipalApp mainApp) {
        super.setMainApp(mainApp);        
       
        tablaAnotaciones.setItems(mainApp.getAnotacionesInfo());
    }    
    
    /**
    *  llamado cuando el usuario pulsa el botón volver al menú principal
    */
    @FXML
    private void manejadorBotonVolver() {
        
        mainApp.mostrarVistaPrincipal();
    }
}