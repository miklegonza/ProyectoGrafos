package modelo;

import controlador.Lista;

/**
 * Clase modelo {@code Vertice} que define los campos que requiere un vértice
 * para existir. Un vértice en un grafo es como un nodo que tiene un elemento y
 * unos enlaces, que en este caso con las aristas.
 *
 * @author Michael González
 */
public class Vertice {

    private Lista<Arista> aristasEnlazadas;
    private Lista<Vertice> vecinos;
    private String dato;
    private boolean visitado;

    /**
     * Constructor que crea el vértice y lo inicializa con su respectivo
     * elemento. También inicializa la lista que va a contener todas las aristas
     * enlazadas a dicho vértice.
     *
     * @param dato Elemento del vértice
     */
    public Vertice(String dato) {
        this.dato = dato;
        this.aristasEnlazadas = new Lista<>();
        this.vecinos = new Lista<>();
    }

    /**
     * Método {@code toString()} que retorna una representación del vértice
     * concatenado en una cadena de caracteres.
     *
     * @return
     */
    @Override
    public String toString() {
        return "Vértice: " + dato;
    }

    /**
     * Devuelve el número de aristas o vecinos que tiene el vértice.
     *
     * @return el tamaño del arreglo de aristas
     */
    public int tamanioEnlaces() {
        return aristasEnlazadas.size();
    }

    /**
     * Comprueba si la arista ya está conectada a ese vértice.
     *
     * @param arista arista que se va a comprobar
     * @return verdadero o falso si la arista se encuentra en la lista de
     * aristas
     */
    public boolean estaEnlazado(Arista arista) {
        return aristasEnlazadas.contains(arista);
    }

    /**
     * Agrega una arista al vértice en cuestión.
     *
     * @param arista arista que se va a agragar a la lista
     */
    public void asignarArista(Arista arista) {
        if (!estaEnlazado(arista)) {
            aristasEnlazadas.add(arista);
        }
    }

    /**
     * Se elimina una arista de la lista de aristas enlazadas.
     *
     * @param arista arista que se desea eliminar
     */
    public void eliminarEnlace(Arista arista) {
        if (estaEnlazado(arista)) {
            aristasEnlazadas.remove(arista);
        }
    }

    //Getters
    /**
     * Retorna la arista deseada.
     *
     * @param index indice especificado
     * @return La arista en el índice especificado
     */
    public Arista getArista(int index) {
        return aristasEnlazadas.get(index);
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public boolean getVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    /**
     * Devuelve una copia de la lista de aristas para su manipulación. Esto
     * sirve para no correr el riesgo de dañar la lista principal de aristas en
     * un crud, por ejemplo.
     *
     * @return Copia de la lista de aristas
     */
    public Lista<Arista> getList() {
        return new Lista<>(this.aristasEnlazadas);
    }

    public void setList(Lista<Arista> aristasEnlazadas) {
        this.aristasEnlazadas = aristasEnlazadas;
    }

    public Lista<Vertice> getVecinos() {
        for (Arista a : aristasEnlazadas) {
            vecinos.add(a.getDestino());
        }
        return new Lista<>(this.vecinos);
    }

    public void setVecinos(Lista<Vertice> vecinos) {
        this.vecinos = vecinos;
    }
    //End Getters
}
