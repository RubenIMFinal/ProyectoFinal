package agenda.controlador.anotaciones;

import agenda.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import agenda.PrincipalApp;
import agenda.controlador.BaseController;
import agenda.modelo.Anotacion;
import agenda.modelo.Persona;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AnotacionesResumenController extends BaseController{
    @FXML
    private TableView<Anotacion> tablaAnotaciones;
    @FXML
    private TableColumn<Anotacion, String> columnaTitulo;
    @FXML
    private TextField titulo;  
    @FXML
    private TextField descripcion;   
    @FXML
    private TextField fecha;
  
    private PrincipalApp mainApp;
    
    private ObservableList<Anotacion> notas;

    
    public AnotacionesResumenController() {
        
    }    
       
    /**
     * Initializes the controller class.
     */    
    @FXML
    @Override
    public void initialize() {
        columnaTitulo.setCellValueFactory(cellData -> cellData.getValue().tituloProperty());
              
        // Limpiar el detalle de la persona existente
        mostrarDetallesAnotacion(null);
       
        //Asociamos los "listener"
        tablaAnotaciones.getSelectionModel().selectedItemProperty().addListener(
               (observable, oldValue, newValue) -> mostrarDetallesAnotacion(newValue));
    }  
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */   
    @Override
    public void setMainApp(PrincipalApp mainApp) {
        super.setMainApp(mainApp);  
        notas = mainApp.getAnotacionesInfo();
       
      tablaAnotaciones.setItems(mainApp.getAnotacionesInfo());
    }
    
    public void mostrarDetallesAnotacion(Anotacion anotaciones){
        if (anotaciones != null) {
        // Fill the labels with info from the person object.
           titulo.setText(anotaciones.getTitulo());
           descripcion.setText(anotaciones.getDescripcion());
           fecha.setText(DateUtil.formatear(anotaciones.getFecha()));

        } else {
        // Notes is null, remove all the text.
           titulo.setText("");
           descripcion.setText("");
           fecha.setText("");
        }
    
    }    
    
    /**
    *  llamado cuando el usuario pulsa el botón volver al menú principal
    */
    
    //Por que no funciona?
    
    @FXML
    private void manejadorBotonVolver() {
        
        mainApp.mostrarVistaPrincipal();
    }
    
    //Ruben ---> al no funcionar anotaciones ¿?, en vez de copiar los metodos
    //de persona, pruebo con estos (usando edit text para editar directamente aqui):
    
    @FXML
    public void manejadorBotonEliminar(){
        int anotacionSeleccionada = tablaAnotaciones.getSelectionModel().getSelectedIndex();
        tablaAnotaciones.getItems().remove(anotacionSeleccionada);
    }
    
    @FXML
    public void manejadorBotonAnadir(){
        Anotacion nuevaAnotacion = new Anotacion();
        
        nuevaAnotacion.setTitulo(this.titulo.getText());
        nuevaAnotacion.setDescripcion(this.descripcion.getText());
        nuevaAnotacion.setFecha(DateUtil.parse(this.fecha.getText()));
        
        notas.add(nuevaAnotacion);
        
    }
    
    @FXML
    public void manejadorEditarAnotacion() {
        int notaSeleccionada = tablaAnotaciones.getSelectionModel().getSelectedIndex();
        Anotacion anotacion = tablaAnotaciones.getItems().get(notaSeleccionada);
        if(anotacion != null){
            anotacion.setTitulo(this.titulo.getText());
            anotacion.setDescripcion(this.descripcion.getText());
            anotacion.setFecha(DateUtil.parse(this.fecha.getText()));
 
       
        } else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getEscenarioPrincipal());
            alert.setTitle("Nada seleccionado");
            alert.setHeaderText("No has seleccionado una anotacion");
            alert.setContentText("Por favor selecciona una anotacion de la tabla.");
            
            alert.showAndWait(); 
        }
        

    }  
    
    
    
}