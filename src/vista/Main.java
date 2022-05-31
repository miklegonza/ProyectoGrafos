package vista;

/**
 * Proyecto Final. Ejercicio 2. Crear una aplicación que permita crear un
 * algoritmo para recorrer un grafo en profundidad (DFS) y en amplitud (BFS).
 * Debe tener las siguientes condiciones.
 *
 * <li>El usuario digita la cantidad de los nodos del grafo (n).
 *
 * <li>El usuario digita el valor de cada uno de los nodos del grafo. (Letras de
 * la A - Z).
 *
 * <li>Se debe imprimir el resultado del recorrido en anchura y en profundidad.
 *
 * <li>Se puede mostrar por interfaz gráfica o por consola.
 *
 * @author MICHAEL STEAVEN GONZALEZ VARGAS - 30000058404
 */
public class Main {

    public static void main(String[] args) {
        FrameGrafo v = new FrameGrafo();
        v.setVisible(true);
    }

}
