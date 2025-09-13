package org.example.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "partido")
@XmlAccessorType(XmlAccessType.FIELD)
public class Partido {
    private Equipo equipoCasa;
    private Equipo equipoVisita;
    private int golesCasa;
    private int golesVisita;

    public Partido() {}

    public Partido(Equipo equipoCasa, Equipo equipoVisita, int golesCasa, int golesVisita) {
        this.equipoCasa = equipoCasa;
        this.equipoVisita = equipoVisita;
        this.golesCasa = golesCasa;
        this.golesVisita = golesVisita;
    }

    public Equipo getEquipoCasa() {
        return equipoCasa;
    }

    public void setEquipoCasa(Equipo equipoCasa) {
        this.equipoCasa = equipoCasa;
    }

    public Equipo getEquipoVisita() {
        return equipoVisita;
    }

    public void setEquipoVisita(Equipo equipoVisita) {
        this.equipoVisita = equipoVisita;
    }

    public int getGolesCasa() {
        return golesCasa;
    }

    public void setGolesCasa(int golesCasa) {
        this.golesCasa = golesCasa;
    }

    public int getGolesVisita() {
        return golesVisita;
    }

    public void setGolesVisita(int golesVisita) {
        this.golesVisita = golesVisita;
    }

    @Override
    public String toString() {
        return "Partido: " + equipoCasa.getNombre() + " (" + golesCasa + ") vs " +
                equipoVisita.getNombre() + " (" + golesVisita + ")";
    }
}
