package practica4_eda;

import graphsDS_ESI_UCLM_v2.*;
import java.io.FileNotFoundException;
import java.util.*;
import utils.SequentialFile;

public class Practica4_EDa {

    public static void main(String[] args) throws FileNotFoundException {
        Graph <Escala, Vuelo> g = new TreeMapGraph<>();
        
        SequentialFile <Escala> escalas = new SequentialFile<>("escalas_migracion_aves.csv", ",");
            
        SequentialFile <Vuelo> vuelos = new SequentialFile<>("vuelos_migracion_aves.csv", ",");

        escalas.skip();
        vuelos.skip();
        
        while(!escalas.isEndOfFile()){
            Escala e = new Escala();
            escalas.read(e);
            g.insertVertex(e);
        }
        
        while(!vuelos.isEndOfFile()){
            Vuelo v = new Vuelo();
            vuelos.read(v);
            
            Vertex<Escala> origen = g.getVertex(v.getNombre_origen());
            Vertex<Escala> destino = g.getVertex(v.getNombre_destino());
            
            if(origen != null && destino != null){
                g.insertEdge(origen, destino, v);
            }   
        }
        
        Iterator <Edge<Vuelo>> it = g.getEdges();
        
        double suma = 0;
        
        while(it.hasNext()){
            suma += it.next().getData().getDistancia_km();
        }
        
        System.out.println("Distancia media de todos los tramos de vuelo: " + suma/g.getM() + " km");
        
        Scanner sc = new Scanner(System.in);
        Graph<Escala, Vuelo> gEcologico = null;
        boolean salir = false;
        
        while(!salir){
            System.out.print("> ");
            String linea = sc.nextLine().trim();
            
            if(linea.isEmpty()){
                continue;
            }
            
            String[] partes = linea.split("\\s+");
            String comando = partes[0].toLowerCase();
            
            switch(comando){
                case "c":
                    if (partes.length < 2){
                        System.out.println("Error, formato incorrecto. Su formato es c <distancia_n>");
                        break;
                    }

                    double n = Double.parseDouble(partes[1]);
                    gEcologico = new  TreeMapGraph<>();

                    Iterator<Vertex <Escala> > iteVertices = g.getVertices();
                    while(iteVertices.hasNext()){
                        gEcologico.insertVertex(iteVertices.next().getData());
                    }

                    Iterator<Vertex<Escala>> ite1 = gEcologico.getVertices();
                    while(ite1.hasNext()){
                        Vertex<Escala> v1 = ite1.next();

                        Iterator<Vertex<Escala>> ite2 = gEcologico.getVertices();
                        while(ite2.hasNext()){
                            Vertex<Escala> v2 = ite2.next();

                            if(v1.equals(v2)){
                                continue;
                            }

                            //Calcular distancia con la formula del enunciado
                            double dist = Distancia.calcularDis(v1.getData(), v2.getData());

                            if(dist <= n){
                                Vertex<Escala> origenGrafo = g.getVertex(String.valueOf(v1.getData().getId()));  
                                Vertex<Escala> destinoGrafo = g.getVertex(String.valueOf(v2.getData().getId()));  

                                Edge<Vuelo> vueloReal = null;
                                if(origenGrafo != null && destinoGrafo != null){
                                    int id1 = v1.getData().getId();
                                    int id2 = v2.getData().getId();

                                    vueloReal = g.getEdge(id1 + " - " + id2);

                                    if(vueloReal == null){
                                        vueloReal = g.getEdge(id2 + " - " + id1);
                                    }
                                }

                                boolean condicion = false;
                                if(vueloReal == null){
                                    condicion = true; // no hay vuelo real
                                }else if(vueloReal.getData().getProb_exito() >= 0.95){
                                    condicion = true; // hay vuelo pero su exito es mayor que 0.95
                                }
                                if(condicion){
                                    gEcologico.insertEdge(v2, v1,new Distancia(dist)); 
                                }
                            }
                        }
                    }

                    System.out.println("Numero de escalas:" + gEcologico.getN());
                    System.out.println("Numero de tramos:" + gEcologico.getM());
                    break;
                case "v":
                    if(gEcologico == null){
                        System.out.println("Primero debes crear el grafo de conectividad ecológica (comando c).");
                    }else if(partes.length == 4){
                        int idOrigen = Integer.parseInt(partes[1]);
                        int idDestino = Integer.parseInt(partes[2]);
                        String direccion = partes[3].toLowerCase();
                        
                    }else{
                        System.out.println("Uso correcto: v <origen> <destino> <n|s>");
                    }
                    break;
                case "p":
                    if(gEcologico == null){
                        System.out.println("Primero debes crear el grafo de conectividad ecológica (comando c).");
                    }else if(partes.length == 3){
                        String origen = partes[1];
                        String destino = partes[2];
                        
                    }else{
                        System.out.println("Uso: p <origen> <destino>");
                    }
                    
                    break;
                case "s":
                    salir = true;
                    break;
                default:
                    System.out.println("Comando no reconocido.");
                    break;
            }
        }
    }
}
