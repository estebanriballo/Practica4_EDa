package practica4_eda;
import graphsDS_ESI_UCLM_v2.Identity;
import utils.SequentialFileReadable;

public class Escala implements SequentialFileReadable, Identity{
    private int id;
    private String nombre;
    private String pais;
    private float lat;
    private float lon;
    private Tipo tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public void readData(String[] data) {
        this.id = Integer.parseInt(data[0]);
        this.nombre = data[1];
        this.pais = data[2];
        this.lat = Float.parseFloat(data[3]);
        this.lon = Float.parseFloat(data[4]);
        this.tipo = Tipo.valueOf(data[5]);
    }

    @Override
    public String getID() {
        return this.nombre;
    }
}
