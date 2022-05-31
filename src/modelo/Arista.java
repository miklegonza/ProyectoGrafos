package modelo;

/**
 * Clase modelo {@code Arista} que crea cada una de las aristas del grafo. Una
 * arista es un enlace entre dos nodos o vértices. Tiene un vértice de origen y
 * uno de destino mas un peso que representará coste de recorrido entre un
 * vértice y otro.
 *
 * @author Michael González
 */
public class Arista {

    private Vertice origen;
    private Vertice destino;
    private int peso;

    /**
     * Constructor que crea la arista al recibir una arista origen y una arista
     * destino. También recibe un peso numérico que indica el coste para llegar
     * desde un punto hasta el otro.
     *
     * @param origen vétice de origen de la arista
     * @param destino vértice de destino de la arista
     * @param peso define el coste o valor de la arista desde el origen al
     * destino
     */
    public Arista(Vertice origen, Vertice destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    /**
     * Constructor que crea la arista al recibir un vértice {@code origen} y un
     * vértice {@code destino}.
     *
     * @param origen vétice de origen de la arista
     * @param destino vértice de destino de la arista
     *
     */
    public Arista(Vertice origen, Vertice destino) {
        this(origen, destino, 1);
    }

    public Arista(Arista arista) {
        this(arista.origen, arista.destino, 1);
    }

    /**
     * Método {@code toString} para imprimir la arista. Concatena el vértice
     * {@code origen} y el vértice {@code destino}.
     *
     * @return
     */
    public String toStringSinPeso() {
        return "Arista entre vértices {" + this.origen + ", " + this.destino + "} \n";
    }

    /**
     * Método {@code toString} para imprimir la arista. Concatena el vértice
     * {@code origen}, el vértice {@code destino} y el {@code peso} asignado a
     * la arista, en caso de tenerlo.
     *
     * @return
     */
    public String toStringPeso() {
        return "Arista entre vértices {" + this.origen + ", " + this.destino + "}, con peso " + this.peso + "\n";
    }

    //Getters & Setters
    public Vertice getAdyacente(Vertice ingresado) {
        return (ingresado.equals(this.origen)) ? this.destino : this.origen;
    }

    public Vertice getOrigen() {
        return this.origen;
    }

    public Vertice getDestino() {
        return this.destino;
    }

    public int getPeso() {
        return this.peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    //End Getters & Setters

}
