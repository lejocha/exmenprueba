package org.example.data;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

import org.example.logic.Equipo;
import org.example.logic.Partido;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {
    @XmlElementWrapper(name = "equipos")
    @XmlElement(name = "equipo")
    private List<Equipo> equipos;
    @XmlElementWrapper(name = "partidos")
    @XmlElement(name = "partido")
    private List<Partido> partidos;

    public Data() {
        equipos = new ArrayList<>();
        partidos = new ArrayList<>();
    }

    public List<Equipo> getEquipos() {
        if (equipos == null) {
            equipos = new ArrayList<>();
        }
        return equipos;
    }
    public List<Partido> getPartidos() {
        if (partidos == null) {
            partidos = new ArrayList<>();
        }
        return partidos;
    }
    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }
    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }
}
