package controlador;

import modelo.Arista;
import modelo.Vertice;

/**
 * Clase que crea un grafo usando el método de listas de adyacencia. Este método
 * dice que se enlistan los vértices que tengan adyacencia en una lista, que en
 * este caso es dinámica, organizando la estructura de datos no lineal de una
 * forma que la máquina pueda entenderla.
 *
 * @author Michael González
 */
public class ListaAdyacencia {

    /**
     * Lista que almacena los vértices ingresados.
     */
    private final Lista<Vertice> vertices;
    /**
     * Lista que almacena las aristas ingresadas y los vértices en los que
     * inciden.
     */
    private final Lista<Arista> aristas;

    /**
     * Constructor que inicializa las dos listas. Aquí se crea un grafo vacío.
     */
    public ListaAdyacencia() {
        this.vertices = new Lista<>();
        this.aristas = new Lista<>();
    }

    /**
     * Construcción de un grafo que acepta una lista de vertices por parámetro
     * de entrada
     *
     * @param vertices lista que va a inicializar el grafo con los parámetros
     * anteriormente ingresados.
     *
     */
    public ListaAdyacencia(Lista<Vertice> vertices) {
        this.vertices = new Lista<>();
        this.aristas = new Lista<>();

        for (Vertice v : vertices) {
            this.vertices.add(v);
        }

    }

    /**
     * Inserta un nuevo vértice. Si el vértice ya existe no se puede volver a
     * insertar.
     *
     * @param vertice Vértice a insertar
     * @return verdarero o falso si el vértice se inserta con éxito
     */
    public boolean insertarVertice(Vertice vertice) {
        if (!contieneElVertice(vertice)) {
            vertices.add(vertice);
            return true;
        }
        return false;
    }

    /**
     * Elimina un vértice especificado. Adicionalmente, elimina las adyacencias
     * que dicho vértice tenía.
     *
     * @param vertice El vértice que se va a eliminar.
     * @return verdadero o falso si el vértice se elimina correctamente
     */
    public boolean eliminarVertice(Vertice vertice) {
        for (Vertice verticeEliminar : vertices) {
            if (verticeEliminar.equals(vertice)) {
                for (Arista aristaEliminar : aristas) {
                    Vertice posibleOrigen = aristaEliminar.getOrigen();
                    Vertice posibleDestino = aristaEliminar.getDestino();
                    if (verticeEliminar.equals(posibleOrigen) || verticeEliminar.equals(posibleDestino)) {
                        posibleOrigen.eliminarEnlace(aristaEliminar);
                        posibleDestino.eliminarEnlace(aristaEliminar);
                        aristas.remove(aristaEliminar);
                    }
                }
                vertices.remove(verticeEliminar);
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si el vértice recibido como parámetro existe en la lista de
     * vértices.
     *
     * @param vertice vértice buscado
     * @return verdadero o falso si el vertice se encuentra.
     */
    public boolean contieneElVertice(Vertice vertice) {
        return vertices.contains(vertice);
    }

    /**
     * Inserta una arista especificando los vertices de origen y destino, además
     * de un coste o peso inicial que tendrá la arista. La arista se insertará
     * cuando no haya más aristas conectando ese mismo origen y ese mismo
     * destino. No se insertará si el origen es igual al destino.
     *
     * @param origen el vértice de origen de la arista.
     * @param destino el vértice de destino.
     * @param peso valor que emplea la arista para llegar del origen al destino.
     * @return verdadero o falso si la arista se insertó correctamente.
     */
    public boolean insertarArista(Vertice origen, Vertice destino, int peso) {
        Arista arista = new Arista(origen, destino, peso);
        //Validar si los vértives son los mismos
        if (origen.equals(destino)) {
            return false;
        }
        //Validar si alguno de los dos vértices ya están enlazados a esa arista
        if (origen.estaEnlazado(arista) || destino.estaEnlazado(arista)) {
            return false;
        }
        //Si la arista no existe, se inserta
        if (!contieneLaArista(arista)) {
            aristas.add(arista);
            origen.asignarArista(arista);
            destino.asignarArista(arista);
            return true;
        }
        return false;
    }

    /**
     * Inserta una arista entre los vertices de origen y destino sin un peso
     * definido.
     *
     * @param origen el vértice de origen de la arista.
     * @param destino el vértice de destino.
     * @return verdadero o falso si la arista se insertó correctamente.
     */
    public boolean insertarArista(Vertice origen, Vertice destino) {
        return insertarArista(origen, destino, 1);
    }

    /**
     * Inserta una arista ya definida. La envía al método de inserción que no
     * requiere de un peso y este la envía al primer método de inserción.
     *
     * @param arista el vértice de origen de la arista.
     * @return verdadero o falso si la arista se insertó correctamente.
     */
    public boolean insertarArista(Arista arista) {
        return insertarArista(arista.getOrigen(), arista.getDestino());
    }

    /**
     * Elimina una arista del grafo. Por tanto, los vertices que unían pierden
     * la adyacencia entre ellos
     *
     * @param arista arista que se quiere eliminar del grafo
     * @return Verdadero o falso si se logó eliminar correctamente
     */
    public boolean eliminarArista(Arista arista) {
        for (Arista aristaEliminar : aristas) {
            if (aristaEliminar.equals(arista)) {
                aristaEliminar.getOrigen().eliminarEnlace(aristaEliminar);
                aristaEliminar.getDestino().eliminarEnlace(aristaEliminar);
                aristas.remove(aristaEliminar);
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si en la lista de aristas ya existe la arista.
     *
     * @param arista arista que se va a comprobar
     * @return verdadero o falso si la arista se encuentra en la lista de
     * aristas
     */
    public boolean contieneLaArista(Arista arista) {
        return aristas.contains(arista);
    }

    /**
     * Imprimir el recorrido del grafo en DFS (Depth First Search). El recorrido
     * en profundidad busca el primer camino que encuentra y lo recorre hasta
     * llegar a su punto más lejano o profundo. Cuando llega al final comienza a
     * devolverse mientras verifica que los vértices por los que pasa no hayan
     * sido ya visitados. En el caso del recorrido en profundidad, se usa la
     * estructura de datos {@code Stack} o "pila" para su organización e
     * impresión.
     *
     * @return Contador con los vértices en el orden por profundidad.
     */
    public String dfs() {
        Vertice primero = vertices.get(0);
        Pila<Vertice> pila = new Pila<>();
        pila.push(primero);
        primero.setVisitado(true);
        String cont = primero.getDato() + " ";
        while (!pila.isEmpty()) {
            Vertice v = pila.peek();
            Vertice hijo = getNoVisitado(v);
            if (hijo != null) {
                hijo.setVisitado(true);
                cont += hijo.getDato() + " ";
                pila.push(hijo);
            } else {
                pila.pop();
            }
        }
        limpiarVisitados();
        return cont;
    }

    /**
     * Imprimir el recorrido del grafo en BFS (Breadth First Search). El
     * recorrido en amplitud se asemeja a un recorrido por niveles en un árbol
     * binario. Se recorre pirmero un vértice inicial y se comienzan a recorrer
     * sus vértices adyacentes consecutivamente hasta llegar al último. En el
     * caso del recorrido en Amplitud, se usa la estructura de datos
     * {@code Queue} o "cola" para su organización e impresión.
     *
     * @return Contador con los vértices en el orden por Amplitud.
     */
    public String bfs() {
        Vertice primero = vertices.get(0);
        Cola<Vertice> cola = new Cola<>();
        cola.queue(primero);
        String cont = primero.getDato() + " ";
        primero.setVisitado(true);
        while (!cola.isEmpty()) {
            Vertice v = cola.pop();
            Vertice hijo;
            while ((hijo = getNoVisitado(v)) != null) {
                hijo.setVisitado(true);
                cont += hijo.getDato() + " ";
                cola.queue(hijo);
            }
        }
        limpiarVisitados();
        return cont;
    }

    /**
     * Obtiene el vértice que no haya sido visitado para evaluarlo en los dos
     * métodos de recorrido del grafo.
     *
     * @param v Vértica a evaluar
     * @return El vértice que no ha sido visitado. Null si todos ya fueron
     * visitados.
     */
    public Vertice getNoVisitado(Vertice v) {
        for (int i = 0; i < vertices.size(); i++) {
            if (v.getVecinos().contains(vertices.get(i)) && (vertices.get(i).getVisitado() == false)) {
                return vertices.get(i);
            }
        }
        return null;
    }

    /**
     * Asigna un valor falso a los vértices del grafo para luego poder usarlo
     * con otros fines.
     */
    public void limpiarVisitados() {
        for (int i = 0; i < vertices.size(); i++) {
            Vertice v = vertices.get(i);
            v.setVisitado(false);
        }
    }

    //Getters
    public Vertice getVertice(int index) {
        Vertice buscado = vertices.get(index);
        return buscado != null ? buscado : null;
    }

    public Arista getArista(int index) {
        Arista buscado = aristas.get(index);
        return buscado != null ? buscado : null;
    }

    public Lista<Vertice> getListaVertices() {
        return new Lista<>(this.vertices);
    }

    public Lista<Arista> getListaAristas() {
        return new Lista<>(this.aristas);
    }
    //End Getters

}
