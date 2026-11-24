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
                    
                    break;
                case "v":
                    
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
        
        System.out.println(g.toString());
    }
    
}
