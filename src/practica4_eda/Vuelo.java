package practica4_eda;
import graphsDS_ESI_UCLM_v2.Identity;
import utils.SequentialFileReadable;

public class Vuelo implements SequentialFileReadable, Identity{
    private int id_origen;
    private String nombre_origen;
    private int id_destino;
    private String nombre_destino;
    private float distancia_km;
    private float riesgo_mortalidad;
    private float prob_exito;
    private int capacidad_flujo_diario_k;

    @Override
    public void readData(String[] data) {
        this.id_origen = Integer.parseInt(data[0]);
        this.nombre_origen = data[1];
        this.id_destino = Integer.parseInt(data[2]);
        this.nombre_destino = data[3];
        this.distancia_km = Float.parseFloat(data[4]);
        this.riesgo_mortalidad = Float.parseFloat(data[5]);
        this.prob_exito = Float.parseFloat(data[6]);
        this.capacidad_flujo_diario_k = Integer.parseInt(data[7]);
    }

    public int getId_origen() {
        return id_origen;
    }

    public void setId_origen(int id_origen) {
        this.id_origen = id_origen;
    }

    public String getNombre_origen() {
        return nombre_origen;
    }

    public void setNombre_origen(String nombre_origen) {
        this.nombre_origen = nombre_origen;
    }

    public int getId_destino() {
        return id_destino;
    }

    public void setId_destino(int id_destino) {
        this.id_destino = id_destino;
    }

    public String getNombre_destino() {
        return nombre_destino;
    }

    public void setNombre_destino(String nombre_destino) {
        this.nombre_destino = nombre_destino;
    }

    public float getDistancia_km() {
        return distancia_km;
    }

    public void setDistancia_km(float distancia_km) {
        this.distancia_km = distancia_km;
    }

    public float getRiesgo_mortalidad() {
        return riesgo_mortalidad;
    }

    public void setRiesgo_mortalidad(float riesgo_mortalidad) {
        this.riesgo_mortalidad = riesgo_mortalidad;
    }

    public float getProb_exito() {
        return prob_exito;
    }

    public void setProb_exito(float prob_exito) {
        this.prob_exito = prob_exito;
    }

    public int getCapacidad_flujo_diario_k() {
        return capacidad_flujo_diario_k;
    }

    public void setCapacidad_flujo_diario_k(int capacidad_flujo_diario_k) {
        this.capacidad_flujo_diario_k = capacidad_flujo_diario_k;
    }

    @Override
    public String getID() {
        return this.id_origen + " - " + this.id_destino;
    }
}
