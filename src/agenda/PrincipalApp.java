package agenda;

import agenda.controlador.anotaciones.AnotacionesResumenController;
import agenda.controlador.BaseController;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import agenda.modelo.Persona;
import agenda.modelo.Anotacion;
import agenda.controlador.personas.PersonaResumenController;
import agenda.controlador.personas.VentanaEditarPersonaController;
import agenda.controlador.VistaPrincipalController;
import agenda.modelo.PersonListWrapper;
import java.io.File;
import java.util.prefs.Preferences;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
       

public class PrincipalApp extends Application {

    private Stage escenarioPrincipal;    
    private BorderPane disenoRaiz;
    
    private ObservableList<Persona> personasInfo = FXCollections.observableArrayList();
    private ObservableList<Anotacion> anotacionesInfo = FXCollections.observableArrayList();
    
    
    /*
        Punto de INICIO  de la agenda, es la primera función a ejecutar.
    */
    @Override
    public void start(Stage escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
        this.escenarioPrincipal.setTitle("Mi Agenda : Agustín");
                
        iniciarDisenoRaiz();      
       
        mostrarVistaPrincipal();
    }
    
    /**
     * Constructor de la clase MainApp
     */
    public PrincipalApp() {
        // Añadimos las personas de ejemplo          
        personasInfo.add(new Persona("Nombre 1", "Apellido 1"));
        personasInfo.add(new Persona("Nombre 2", "Apellido 2"));
        personasInfo.add(new Persona("Nombre 3", "Apellido 3"));
        personasInfo.add(new Persona("Nombre 4", "Apellido 4"));
        personasInfo.add(new Persona("Nombre 5", "Apellido 5"));
        personasInfo.add(new Persona("Nombre 6", "Apellido 6"));
        personasInfo.add(new Persona("Nombre 7", "Apellido 7"));
        personasInfo.add(new Persona("Nombre 8", "Apellido 8"));
        personasInfo.add(new Persona("Nombre 9", "Apellido 9"));
        
        // Añadimos las anotaciones de ejemplo
        anotacionesInfo.add(new Anotacion("Titulo 1", "contenido de la anotación 1"));
        anotacionesInfo.add(new Anotacion("Titulo 2", "contenido de la anotación 2"));
        anotacionesInfo.add(new Anotacion("Titulo 3", "contenido de la anotación 3"));
        anotacionesInfo.add(new Anotacion("Titulo 4", "contenido de la anotación 4"));
        anotacionesInfo.add(new Anotacion("Titulo 5", "contenido de la anotación 5"));
        anotacionesInfo.add(new Anotacion("Titulo 6", "contenido de la anotación 6"));
        
    }
    
    /**
     * Función para inicializar el diseño Raiz
     */
    public void iniciarDisenoRaiz() {
        try {
            // Cargamos el diseño Raíz desde el fxml de la vista
            FXMLLoader cargadorFxml = new FXMLLoader(PrincipalApp.class.getResource("vista/VistaRaiz.fxml"));           
            disenoRaiz = (BorderPane) cargadorFxml.load();            

            // Cargamos el diseñoRaiz en el escenario
            this.escenarioPrincipal.setScene(new Scene(disenoRaiz));
            this.escenarioPrincipal.show();
        } catch (IOException excepcion) {                     
           excepcion.printStackTrace();
        }
    }

    /**
     * Función para mostrar la vista resumen de una persona
     */
    public void mostrarVistaResumenPersonas() {
        try {
            // Cargamos el diseño de la vista de persona desde el fxml de la vista.
            FXMLLoader cargadorFxml = new FXMLLoader(PrincipalApp.class.getResource("vista/personas/PersonaVistaResumen.fxml"));            
            AnchorPane personOverview = (AnchorPane) cargadorFxml.load();

            // Cargamos la vista de persona en el diseño raíz
            disenoRaiz.setCenter(personOverview);
            
             // Damos al controller acceso al mainApp.
            PersonaResumenController controlador = cargadorFxml.getController();
            controlador.setMainApp(this);
            
        } catch (IOException excepcion) {
            excepcion.printStackTrace();
        }
    }
       
    
    /**
     * Función para mostrar la vista resumen de una persona
     */
    public void mostrarVistaAnotacionesResumen() {
        try {
            // Cargamos el diseño de la vista de persona desde el fxml de la vista.
            FXMLLoader cargadorFxml = new FXMLLoader(PrincipalApp.class.getResource("vista/anotaciones/AnotacionesResumen.fxml"));            
            AnchorPane anotacionesResumen = (AnchorPane) cargadorFxml.load();

            // Cargamos la vista de persona en el diseño raíz
            disenoRaiz.setCenter(anotacionesResumen);
            
             // Damos al controller acceso al mainApp.
            AnotacionesResumenController controlador = cargadorFxml.getController();
            controlador.setMainApp(this);
            
        } catch (IOException excepcion) {
            excepcion.printStackTrace();
        }
    }
    
    /**
     * Función para mostrar la vista resumen de una persona
     */
    public void mostrarVistaPrincipal() {
        try {
            // Cargamos el diseño de la vista de persona desde el fxml de la vista.
            FXMLLoader cargadorFxml = new FXMLLoader(PrincipalApp.class.getResource("vista/VistaPrincipal.fxml"));            
            AnchorPane vistaPrincipal = (AnchorPane) cargadorFxml.load();

            // Cargamos la vista de persona en el diseño raíz
            disenoRaiz.setCenter(vistaPrincipal);
            
             // Damos al controller acceso al mainApp.
            VistaPrincipalController controlador = cargadorFxml.getController();
            controlador.setMainApp(this);
            
        } catch (IOException excepcion) {
            excepcion.printStackTrace();
        }
    }      
    
    
     /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     * 
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean mostrarVentanaEditarPersona(Persona persona) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader(PrincipalApp.class.getResource("vista/personas/VentanaEditarPersona.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Persona");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(escenarioPrincipal);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            VentanaEditarPersonaController controller = loader.getController();
            controller.setEscenarioVentana(dialogStage);
            controller.setPersona(persona);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtener el escenario principal.
     * @return
     */
    public Stage getEscenarioPrincipal() {
        return this.escenarioPrincipal;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Obtener el listado de personas 
     * @return
     */
    public ObservableList<Persona> getPersonasInfo() {
        return personasInfo;
    }
    
    /**
     * Obtener el listado de anotaciones 
     * @return
     */
    public ObservableList<Anotacion> getAnotacionesInfo() {
        return anotacionesInfo;
    }
    
    
    
     /**
     * Función para mostrar la vista resumen de una persona
     */
    /*
    public void cargarVista(int tipo_vista) {
        AnchorPane vista = null;
        BaseController controlador = null;
        FXMLLoader cargadorFxml = new FXMLLoader();
        System.out.println("Dentro ");
        try {           
            
            switch(tipo_vista) {
            case PrincipalConstApp.TIPO_VISTA_RAIZ:
                //Código asociado al cargar la vista raíz             
                
                break;
                
            case PrincipalConstApp.TIPO_VISTA_PRINCIPAL:
                System.out.println("Dentro 2");
                cargadorFxml.setLocation(PrincipalApp.class.getResource(PrincipalConstApp.URL_VISTA_PRINCIPAL));            
                vista = (AnchorPane) cargadorFxml.load();                                
                controlador = cargadorFxml.getController();
                
                break;
                
            case PrincipalConstApp.TIPO_VISTA_PRINCIPAL_AGENDA:
                System.out.println("Dentro 1");
                FXMLLoader cargador2Fxml = new FXMLLoader(PrincipalApp.class.getResource(PrincipalConstApp.URL_VISTA_PRINCIPAL_AGENDA));            
                vista = (AnchorPane) cargador2Fxml.load();                                
                controlador = cargador2Fxml.getController();              
                break;  
                
            case PrincipalConstApp.TIPO_VISTA_PRINCIPAL_ANOTACIONES:
                System.out.println("Dentro 3");
                cargadorFxml.setLocation(PrincipalApp.class.getResource(PrincipalConstApp.URL_VISTA_PRINCIPAL_ANOTACIONES));            
                vista = (AnchorPane) cargadorFxml.load();                                
                controlador = cargadorFxml.getController(); 
                break;    
                
            default:
                // code block
            }           
            
        } catch (IOException excepcion) {
            excepcion.printStackTrace();
        }
        
        //disenoRaiz.setCenter(vista);                       
       // controlador.setMainApp(this); 
    }
    */
    //ruben, avance para el toolbar:
    
        public File getPersonFilePath() {
    Preferences prefs = Preferences.userNodeForPackage(PrincipalApp.class);
    String filePath = prefs.get("filePath", null);
    if (filePath != null) {
        return new File(filePath);
    } else {
        return null;
    }
}
    
    
    
    
    
    
    //ruben avance para el toolbar:
    
    public void setPersonFilePath(File file) {
    Preferences prefs = Preferences.userNodeForPackage(PrincipalApp.class);
    if (file != null) {
        prefs.put("filePath", file.getPath());

        // Update the stage title.
        escenarioPrincipal.setTitle("AddressApp - " + file.getName());
    } else {
        prefs.remove("filePath");

        // Update the stage title.
        escenarioPrincipal.setTitle("AddressApp");
    }
}
    
    
    
    //Rubén avance, para el toolbar, para handleopen
    public void loadPersonDataFromFile(File file) {
    try {
        JAXBContext context = JAXBContext
                .newInstance(PersonListWrapper.class);
        Unmarshaller um = context.createUnmarshaller();

        // Reading XML from the file and unmarshalling.
        PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

        personasInfo.clear();
        personasInfo.addAll(wrapper.getPersons());

        // Save the file path to the registry.
        setPersonFilePath(file);

    } catch (Exception e) { // catches ANY exception
     
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText("Could not load data from file:\n" + file.getPath());
        alert.setContentText("Please select a person in the table.");
        alert.showAndWait();
    }
}
    
    //Rubén, hay que seguir con savePersonDataFile, 
    
    public void savePersonDataToFile(File file) {
    try {
        JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Wrapping our person data.
        PersonListWrapper wrapper = new PersonListWrapper();
        wrapper.setPersons(personasInfo);

        // Marshalling and saving XML to the file.
        m.marshal(wrapper, file);

        // Save the file path to the registry.
        setPersonFilePath(file);
    } catch (Exception e) { // catches ANY exception
        
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data from file:\n" + file.getPath());
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
    }
}
    
    
}