package controlador;

import javax.swing.JOptionPane;
import modelo.Arista;
import modelo.Vertice;

/**
 * Clase que ejecuta el papel de controlador entre la vista y el modelo. Llama
 * los métodos creados en el controlador, pide los elementos requeridos del
 * modelo y los envía a la vista en el formato deseado.
 *
 * @author Michael González
 */
public class Acciones {

    /**
     * Título que tendrá el JFrame y todas las ventanas emergentes necesitadas.
     */
    private final String TITULO = "Proyecto Grafos";
    /**
     * Objeto para instanciar los métodos requeridos de la clase
     * {@link ListaAdyacencia}.
     */
    ListaAdyacencia grafo = new ListaAdyacencia();
    /**
     * Lista de tipo {@link Vertice} que almacena los vértices ingresados.
     */
    Lista<Vertice> vertices = new Lista<>(grafo.getListaVertices());
    /**
     * Lista de tipo {@link Arista} que almacena as aristas ingresados.
     */
    Lista<Arista> aristas = new Lista<>(grafo.getListaAristas());

    /**
     * Lista que almacena los elementos de cada vértice de la lista
     * {@code vertices}.
     *
     * @return la lista de tipo String con los elementos de los vértices
     * @see #vertices
     */
    public Lista<String> listaVertices() {
        Lista<String> lista = new Lista<>();
        for (int i = 0; i < vertices.size(); i++) {
            lista.add(vertices.get(i).getDato());
        }
        return lista;
    }

    /**
     * Crea la cantidad de vértices especificada.
     *
     * @param cantidadVertices Número de vértices a crear
     * @see ListaAdyacencia#insertarVertice(modelo.Vertice)
     */
    public void crearVertices(int cantidadVertices) {
        for (int i = 0; i < cantidadVertices; i++) {
            String element = JOptionPane.showInputDialog(null, "¿Qué valor tendrá el vértice " + i + '?', TITULO, 3);
            Vertice ver = new Vertice(element);
            if (grafo.insertarVertice(ver)) {
                vertices.add(ver);
                vertices.get(ver).setVisitado(false);
                JOptionPane.showMessageDialog(null, "Ha ingresado el " + ver.toString(), TITULO, 1);
            } else {
                JOptionPane.showMessageDialog(null, "El vértice ya existe", "ERROR", 0);
                --i;
            }
        }
    }

    /**
     * Crea las aristas entre los vértices. Se crean las que hayan sido
     * esprcificadas.
     *
     * @param cantidadAristas Número de aristas a crear
     * @param opcVertices Arreglo String con los elementos de los vértices. Esto
     * es necesario para preguntarle al usuario qué vértices serán el origen y
     * el destino.
     * @see ListaAdyacencia#insertarArista(modelo.Vertice, modelo.Vertice)
     */
    public void crearAristas(int cantidadAristas, String[] opcVertices) {
        for (int i = 0; i < cantidadAristas; i++) {
            String datoOrigen = (String) JOptionPane.showInputDialog(null, "¿Desde qué vértice saldrá la arista " + i + '?', TITULO, 3, null, opcVertices, opcVertices[0]);
            String datoDestino = (String) JOptionPane.showInputDialog(null, "¿A qué vértice llegará la arista " + i + '?', TITULO, 3, null, opcVertices, opcVertices[0]);
            Vertice origen = null, destino = null;
            for (int j = 0; j < vertices.size(); j++) {
                if (vertices.get(j).getDato().equals(datoOrigen)) {
                    origen = vertices.get(j);
                }
                if (vertices.get(j).getDato().equals(datoDestino)) {
                    destino = vertices.get(j);
                }
            }
            Arista ar = new Arista(origen, destino);
            if (grafo.insertarArista(origen, destino)) {
                aristas.add(ar);
                JOptionPane.showMessageDialog(null, "Ha ingresado: \n" + ar.toStringSinPeso(), TITULO, 1);
            } else {
                JOptionPane.showMessageDialog(null, "La arista entre los vértices seleccionados ya existe", "ERROR", 0);
                --i;
            }
        }
    }

    /**
     * Almacena los vértices, las aristas y las adyacencias en un contador de
     * tipo String para su impresión.
     *
     * @return Grafo concatenado en una variable para su impresión.
     */
    public String printGrafo() {
        String print = "", adyacentes = "";
        for (int i = 0; i < vertices.size(); i++) {
            Vertice temp = vertices.get(i);
            print += temp.toString() + "\n";
            for (int j = 0; j < temp.tamanioEnlaces(); j++) {
                Arista ar = new Arista(temp.getArista(j));
                adyacentes += ar.getAdyacente(temp).getDato() + " ";
                print += ar.toStringSinPeso();
            }
            print += "Vecinos de " + temp.toString() + " → " + adyacentes + "\n";
            adyacentes = "";
        }
        return print;
    }

    /**
     * Obtiene y retorna la impresión del recorrido en profundidad del grafo.
     * Este se realiza en la clase {@link ListaAdyacencia}.
     *
     * @return El recorrido en profundidad concatenado en una variable.
     * @see ListaAdyacencia#dfs() Ubicación.
     */
    public String printDFS() {
        return grafo.dfs();
    }

    /**
     * Obtiene y retorna la impresión del recorrido en Amplitud del grafo. Este
     * se realiza en la clase {@link ListaAdyacencia}.
     *
     * @return El recorrido en Amplitud concatenado en una variable.
     * @see ListaAdyacencia#bfs() Ubicación.
     */
    public String printBFS() {
        return grafo.bfs();
    }

    /**
     * Elimina el vértice especificado con sus adyacencias.
     *
     * @param verticeABorrar El elemento del vértice especificado.
     * @see ListaAdyacencia#eliminarVertice(modelo.Vertice)
     */
    public void eliminarVertice(String verticeABorrar) {
        for (Vertice borrarVertice : vertices) {
            if (borrarVertice.getDato().equals(verticeABorrar)) {
                if (grafo.eliminarVertice(borrarVertice)) {
                    vertices.remove(borrarVertice);
                    JOptionPane.showMessageDialog(null, "El " + borrarVertice.toString() + " se ha borrado exitosamente", TITULO, 1);
                } else {
                    JOptionPane.showMessageDialog(null, "El vértice a borrar no fue encontrado", TITULO, 0);
                }
            }
        }
    }

    /**
     * Elimina la arista especificada. Esto también elimina las adyacencias
     * entre los vértices que tenían que ver con dicha arista.
     *
     * @param verticeOrigen vértice del que sale la arista
     * @param verticeDestino vértice al que llega la arista
     * @see ListaAdyacencia#eliminarArista(modelo.Arista)
     */
    public void eliminarArista(String verticeOrigen, String verticeDestino) {
        String confirmarOrigen, confirmarDestino;
        boolean encontrado = false;
        for (Vertice buscarVertice : vertices) {
            for (Arista aristaEliminar : buscarVertice.getList()) {
                confirmarOrigen = aristaEliminar.getOrigen().getDato();
                if (confirmarOrigen.equals(verticeOrigen)) {
                    encontrado = true;
                }
            }
            for (Arista aristaEliminar : buscarVertice.getList()) {
                confirmarDestino = aristaEliminar.getDestino().getDato();
                if (encontrado && confirmarDestino.equals(verticeDestino)) {
                    if (grafo.eliminarArista(aristaEliminar)) {
                        aristas.remove(aristaEliminar);
                        JOptionPane.showMessageDialog(null, "La arista se ha eliminado", TITULO, 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar. \nEntre los vértces seleccionados no existen aristas.", TITULO, 2);
                    }
                }
            }
        }
    }

}
