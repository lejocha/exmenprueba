package org.example.logic;

import org.example.data.Data;
import org.example.data.XmlPersister;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

public class Service {
    private static Service instance;
    private Data data;

    private Service() {
        try {
            data = XmlPersister.instance().load();
        } catch (Exception e) {
            data = new Data();
        }
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    // Guardar datos en XML
    public void guardarDatos() {
        try {
            XmlPersister.instance().store(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ------------------ Equipos ------------------

    public void agregarEquipo(Equipo equipo) {
        if (!data.getEquipos().contains(equipo)) {
            data.getEquipos().add(equipo);
            guardarDatos();
        }
    }

    public void eliminarEquipo(String id) {
        data.getEquipos().removeIf(equipo -> equipo.getId().equals(id));
        guardarDatos();
    }

    public Equipo buscarEquipoPorId(String id) {
        return data.getEquipos().stream()
                .filter(equipo -> equipo.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Obtener equipos ordenados alfabéticamente (para ComboBox)
    public List<Equipo> obtenerEquiposOrdenados() {
        return data.getEquipos().stream()
                .sorted(Comparator.comparing(Equipo::getNombre))
                .collect(Collectors.toList());
    }

    // Tabla de posiciones ordenada por puntos (descendente)
    public List<Equipo> obtenerTablaPosiciones() {
        return data.getEquipos().stream()
                .sorted(Comparator.comparing(Equipo::getPuntos).reversed()
                        .thenComparing(Equipo::getNombre))
                .collect(Collectors.toList());
    }

    public List<Equipo> obtenerEquipos() {
        return data.getEquipos();
    }

    // ------------------ Partidos ------------------

    public void registrarPartido(Equipo equipoCasa, Equipo equipoVisita,
                                 int golesCasa, int golesVisita) {
        // Validar que los equipos no sean el mismo
        if (equipoCasa.equals(equipoVisita)) {
            throw new IllegalArgumentException("Los equipos Casa y Visita no pueden ser el mismo");
        }

        // Crear partido
        Partido partido = new Partido(equipoCasa, equipoVisita, golesCasa, golesVisita);
        data.getPartidos().add(partido);

        // Actualizar estadísticas
        actualizarEstadisticasPartido(equipoCasa, equipoVisita, golesCasa, golesVisita);

        // Guardar cambios
        guardarDatos();
    }

    private void actualizarEstadisticasPartido(Equipo casa, Equipo visita,
                                               int golesCasa, int golesVisita) {
        if (golesCasa > golesVisita) {
            // Casa gana
            casa.setGanados(casa.getGanados() + 1);
            visita.setPerdidos(visita.getPerdidos() + 1);
        } else if (golesVisita > golesCasa) {
            // Visita gana
            visita.setGanados(visita.getGanados() + 1);
            casa.setPerdidos(casa.getPerdidos() + 1);
        } else {
            // Empate
            casa.setEmpatados(casa.getEmpatados() + 1);
            visita.setEmpatados(visita.getEmpatados() + 1);
        }
    }

    public void eliminarPartido(Partido partido) {
        if (data.getPartidos().remove(partido)) {
            recalcularEstadisticas();
            guardarDatos();
        }
    }

    public List<Partido> obtenerPartidos() {
        return data.getPartidos();
    }

    // Obtener partidos de un equipo específico (para tabla derecha)
    public List<Partido> obtenerPartidosPorEquipo(Equipo equipo) {
        return data.getPartidos().stream()
                .filter(p -> p.getEquipoCasa().equals(equipo) ||
                        p.getEquipoVisita().equals(equipo))
                .collect(Collectors.toList());
    }

    // Recalcular todas las estadísticas desde cero
    private void recalcularEstadisticas() {
        // Resetear estadísticas
        for (Equipo equipo : data.getEquipos()) {
            equipo.setGanados(0);
            equipo.setEmpatados(0);
            equipo.setPerdidos(0);
        }

        // Recalcular basado en todos los partidos
        for (Partido partido : data.getPartidos()) {
            actualizarEstadisticasPartido(
                    partido.getEquipoCasa(),
                    partido.getEquipoVisita(),
                    partido.getGolesCasa(),
                    partido.getGolesVisita()
            );
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}