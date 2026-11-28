package practica4_eda;

public class Distancia {
    private double valor;

    public Distancia(double valor){
        this.valor = valor;
    }

    public double getValor(){
        return valor;
    }

    public void setValor(double valor){
        this.valor = valor;
    }

    public static double calcularDis(Escala a, Escala b){
        double f = 111.32;
        double latm = ((a.getLat()+b.getLat()) / 2.0) * (Math.PI / 180.0);

        double diferenciaLat = b.getLat() - a.getLat();
        double diferenciaLon = (b.getLon() - a.getLon()) * Math.cos(latm);

        return f * Math.sqrt(Math.pow(diferenciaLat, 2) + Math.pow(diferenciaLon, 2));
    }

    @Override 
    public String toString(){
        return valor + "km";
    }
}
