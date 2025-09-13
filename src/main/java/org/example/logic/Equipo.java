package org.example.logic;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;


import java.util.Objects;

@XmlRootElement(name = "equipo")
@XmlAccessorType(XmlAccessType.FIELD)
public class Equipo {
    private String id;
    private String nombre;
    private int ganados;
    private int empatados;
    private int perdidos;

    public Equipo() {}


    public Equipo(String id, String nombre) {
        this.nombre = nombre;
        this.ganados = 0;
        this.empatados = 0;
        this.perdidos = 0;
        this.id = id;
    }

    public int getPartidosJugados() {
        return ganados + empatados + perdidos;
    }
    public int getPuntos() {
        return ganados * 3 + empatados;
    }

    //Getters y Setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getGanados() {
        return ganados;
    }

    public void setGanados(int ganados) {
        this.ganados = ganados;
    }

    public int getEmpatados() {
        return empatados;
    }

    public void setEmpatados(int empatados) {
        this.empatados = empatados;
    }

    public int getPerdidos() {
        return perdidos;
    }

    public void setPerdidos(int perdidos) {
        this.perdidos = perdidos;
    }

    @Override
    public String toString() {return nombre;}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Equipo equipo = (Equipo) obj;
        return Objects.equals(id, equipo.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
