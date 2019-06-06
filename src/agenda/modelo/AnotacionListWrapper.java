/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.modelo;


import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 * 
 * @author Marco Jakob
 */
@XmlRootElement(name = "anotaciones")
public class AnotacionListWrapper {

    private List<Anotacion> notas;

    @XmlElement(name = "anotacion")
    public List<Anotacion> getNotas() {
        return notas;
    }

    public void setAnotacion(List<Anotacion> notas) {
        this.notas = notas;
    }
}
