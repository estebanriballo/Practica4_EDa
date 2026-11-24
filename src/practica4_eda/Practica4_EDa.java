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
        

        
        System.out.println(g.toString());
    }
    
}
