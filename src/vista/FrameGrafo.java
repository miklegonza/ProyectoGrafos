package vista;

import controlador.Acciones;
import controlador.Lista;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * Clase que representa la ventana que se va a mostrar. La ventana tiene las
 * opciones que se pueden realizar con el grafo. Tiene un botón para ejecutar la
 * opción y otro, en otro panel, para imprimir el grafo según como se vaya
 * actualizando.
 *
 * @author Michael González
 */
public class FrameGrafo extends JFrame implements ActionListener {

    //Declaración de variables
    private final Acciones grafo;
    private final String TITULO;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel lblImagen;
    private JLabel lblTituloOpciones;
    private JLabel lblOpciones;
    private JLabel lblTituloGrafo;
    private JTextArea recorrido;
    private JTextArea resumenGrafo;
    private JComboBox<Integer> opc;
    private JButton btnAceptar;
    private JButton btnActualizar;
    private JScrollPane scrollBar;
    private JScrollPane scrollBar2;
    private ImageIcon imagen;
    //Fin de declaración de variables

    /**
     * Constructor que crea la ventana.
     */
    public FrameGrafo() {
        this.grafo = new Acciones();
        this.TITULO = "Proyecto Grafos";
        componentes(TITULO);
    }

    /**
     * Método que crea, inicializa y ubica cada uno de los componentes que
     * tendrá el JFrame.
     *
     * @param titulo El título de la ventana
     */
    private void componentes(String titulo) {
        //Arreglo de opciones
        Integer[] items = {0, 1, 2, 3, 4, 5, 6, 7};

        //Propiedades del JFrame
        setTitle(titulo);
        setSize(750, 450);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        //Inicialización de las variables
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        lblTituloOpciones = new JLabel();
        lblOpciones = new JLabel();
        lblTituloGrafo = new JLabel();
        lblImagen = new JLabel();
        recorrido = new JTextArea();
        resumenGrafo = new JTextArea();
        opc = new JComboBox<>(items);
        btnAceptar = new JButton();
        btnActualizar = new JButton();
        scrollBar = new JScrollPane();
        scrollBar2 = new JScrollPane();
        imagen = new ImageIcon("image.png");

        //Crear panel 1
        panel1.setBackground(Color.BLACK);
        panel1.setBounds(0, 0, 380, 450);
        panel1.setLayout(null);

        //Crear el panel 2
        panel2.setBackground(Color.GRAY);
        panel2.setBounds(380, 0, 370, 450);
        panel2.setLayout(null);

        //Crear el panel 3
        panel3.setBackground(Color.BLACK);
        panel3.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE, 1),
                "Recorridos",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Times New Roman", 0, 15),
                Color.WHITE));
        panel3.setBounds(40, 300, 300, 90);
        panel3.setLayout(null);

        //Crear el icono
        lblImagen.setBounds(20, 30, 55, 50);
        lblImagen.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(55, 50, Image.SCALE_SMOOTH)));

        //Crear el listado de opciones
        lblTituloOpciones.setText("Seleccione una opción:");
        lblTituloOpciones.setFont(new Font("Times New Roman", 1, 21));
        lblTituloOpciones.setForeground(Color.WHITE);
        lblTituloOpciones.setBounds(100, 30, 290, 24);
        lblOpciones.setText("<html>1. Crear vértices y agregar elemetos.<p>"
                + "2. Crear aristas y su respectivo peso.<p>"
                + "3. Mostrar grafo en DFS (Profundidad).<p>"
                + "4. Mostrar grafo en BFS (Amplitud).<p>"
                + "5. Eliminar un vértices y sus adyacencias.<p>"
                + "6. Eliminar una arista del grafo.<p>"
                + "7. Salir.<html>");
        lblOpciones.setFont(new Font("Times New Roman", 0, 16));
        lblOpciones.setForeground(Color.WHITE);
        lblOpciones.setBounds(90, 75, 250, 150);

        //Crear el JComboBox
        opc.setFont(new Font("Times New Roman", 0, 15));
        opc.setBackground(new Color(51, 51, 51));
        opc.setForeground(Color.WHITE);
        opc.setBounds(90, 250, 100, 25);

        //Crear el botón de aceptar para las opciones
        btnAceptar.setText("Aceptar");
        btnAceptar.setFont(new Font("Times New Roman", 0, 15));
        btnAceptar.setBackground(new Color(51, 51, 51));
        btnAceptar.setForeground(Color.WHITE);
        btnAceptar.setBounds(230, 250, 100, 25);

        //Crear el área de texto del recorrido
        recorrido.setFont(new Font("Times New Roman", 0, 20));
        recorrido.setForeground(Color.WHITE);
        recorrido.setBackground(Color.BLACK);
        recorrido.setEditable(false);
        recorrido.setBounds(20, 25, 260, 50);
        scrollBar2.setViewportView(recorrido);
        scrollBar2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollBar2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollBar2.setBounds(20, 25, 260, 50);

        //Crear el título del grafo
        lblTituloGrafo.setText("Grafo");
        lblTituloGrafo.setFont(new Font("Times New Roman", 1, 23));
        lblTituloGrafo.setForeground(Color.BLACK);
        lblTituloGrafo.setBounds(40, 20, 100, 24);

        //Crear la impresión del grafo
        resumenGrafo.setFont(new Font("Times New Roman", 0, 14));
        resumenGrafo.setForeground(Color.BLACK);
        resumenGrafo.setBackground(Color.GRAY);
        resumenGrafo.setEditable(false);
        resumenGrafo.setBounds(20, 60, 315, 325);
        scrollBar.setViewportView(resumenGrafo);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollBar.setBounds(20, 60, 315, 325);

        //Crear el botón para actualizar
        btnActualizar.setText("Actualizar");
        btnActualizar.setFont(new Font("Times New Roman", 0, 15));
        btnActualizar.setBackground(new Color(51, 51, 51));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setBounds(245, 15, 95, 25);

        //Agregar los componentes al panel1
        panel1.add(lblTituloOpciones);
        panel1.add(lblOpciones);
        panel1.add(lblTituloGrafo);
        panel1.add(opc);
        panel1.add(btnAceptar);
        panel1.add(lblImagen);
        panel1.add(panel3);

        //Agregar los componentes al panel2
        panel2.add(lblTituloGrafo);
        panel2.add(scrollBar);
        panel2.add(btnActualizar);

        //Agregar los componentes al panel3
        panel3.add(scrollBar2);

        //Agregar los componentes al JFrame
        this.add(panel1);
        this.add(panel2);

        //Creación de listeners
        btnAceptar.addActionListener(this);
        btnActualizar.addActionListener(this);

    }

    /**
     * Ejecución del programa. Ejecuta los métodos necesarios para la creación
     * del grafo.
     *
     * @param opc la acción que se desea ejecutar
     */
    public void ejecucion(int opc) {
        Lista<String> vertices;
        try {
            switch (opc) {
                case 1:
                    //Crear vértices y agregar elemetos.
                    int cantidadVertices = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de vértices que desea agregar", TITULO, 3));
                    grafo.crearVertices(cantidadVertices);
                    resumenGrafo.setText(grafo.printGrafo());
                    break;
                case 2:
                    //Crear aristas y su respectivo peso.
                    vertices = new Lista<>(grafo.listaVertices());
                    String[] opcVertices = new String[vertices.size()];
                    for (int i = 0; i < vertices.size(); i++) {
                        opcVertices[i] = vertices.get(i);
                    }
                    int cantidadAristas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de aristas que desea agregar", TITULO, 3));
                    grafo.crearAristas(cantidadAristas, opcVertices);
                    break;
                case 3:
                    //Mostrar grafo en DFS (Profundidad).
                    recorrido.setText(" DFS → " + grafo.printDFS());
                    break;
                case 4:
                    //Mostrar grafo en BFS (Amplitud).
                    recorrido.setText(" BFS → " + grafo.printBFS());
                    break;
                case 5:
                    //Eliminar un vértices y sus adyacencias.
                    vertices = new Lista<>(grafo.listaVertices());
                    String[] opcionesBorrar = new String[vertices.size()];
                    for (int i = 0; i < vertices.size(); i++) {
                        opcionesBorrar[i] = vertices.get(i);
                    }
                    String verticeBorrar = (String) JOptionPane.showInputDialog(null, "¿Qué vértice desea eliminar?", TITULO, 3, null, opcionesBorrar, opcionesBorrar[0]);
                    grafo.eliminarVertice(verticeBorrar);
                    break;
                case 6:
                    //Eliminar una arista del grafo.
                    vertices = new Lista<>(grafo.listaVertices());
                    String[] buscarVertices = new String[vertices.size()];
                    //Se llena el arreglo con los vértices
                    for (int i = 0; i < vertices.size(); i++) {
                        buscarVertices[i] = vertices.get(i);
                    }
                    String verticeOrigen = (String) JOptionPane.showInputDialog(null, "¿Desde qué vértice sale la arista?", TITULO, 3, null, buscarVertices, buscarVertices[0]);
                    String verticeDestino = (String) JOptionPane.showInputDialog(null, "¿A qué vértice llegará la arista?", TITULO, 3, null, buscarVertices, buscarVertices[0]);
                    grafo.eliminarArista(verticeOrigen, verticeDestino);
                    break;
                case 7:
                    //Salir.
                    int salir = JOptionPane.showConfirmDialog(null, "Si sale, se eliminarán todos los datos ingresados. \n"
                            + "                            ¿Continuar?", TITULO, JOptionPane.YES_NO_OPTION, 2);
                    if (salir == 0) {
                        System.exit(0);
                        JOptionPane.showMessageDialog(null, "¡Vuelva pronto!", TITULO, JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "¡Opción erronea! \nPor favor ingrese una opción válida", "ERROR", 0);
                    break;
            }
        } catch (NumberFormatException e) {
            String ex = (e.getMessage().equals("null")) ? "Error. Ha intentado terminar el programa a la fuerza. :/" : "Por favor, ingrese un valor numérico.";
            JOptionPane.showMessageDialog(null, ex, "ERROR", 0);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No encontrado. \nError: " + e, "ERROR", 0);
        }
    }

    /**
     * Método de la clase abstracta {@code ActionListener} que lee una acción en
     * el componente seleccionado.
     *
     * @param e Objeto de tipo {@code ActionEvent} que recibe la acción
     * ejecutada
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Botón aceptar
        if (e.getSource() == btnAceptar) {
            ejecucion(opc.getItemAt(opc.getSelectedIndex()));
        }

        //Botón actualizar
        if (e.getSource() == btnActualizar) {
            resumenGrafo.setText(grafo.printGrafo());
        }

    }

}
