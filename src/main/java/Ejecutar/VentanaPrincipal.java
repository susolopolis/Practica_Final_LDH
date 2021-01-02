package Ejecutar;

import Objetos.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*! Clase de la ventana principal de la aplicación */
public class VentanaPrincipal extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private JPanel pnPrincipal; /**< Un objeto JPanel. El panel principal de la Interfaz Gráfica de Usuario (IGU). */
    private JPanel pnObjetos; /**< Un objeto JPanel. El panel en el que se ubicará la lista de objetos. */
    private JPanel pnLocalizacion; /**< Un objeto JPanel. El panel en el que se ubicará la localización de los objetos. */
    private JLabel lbListaObjetos; /**< Una etiqueta JLabel. Etiqueta de título correspondiente al a lista de objetos. */

    private JScrollPane scrollPaneListaObjetos; /**< Un objeto JScrollPane. El panel de scroll que permitirá almacenar toda la lista. */
    private JList<Objeto_Casa> listObjetos; /**< Una lista JList. Lista que se mostrará en el panel de lista de objetos. */
    private DefaultListModel<Objeto_Casa> modelObjetos; /**< Un modelo DefaultListModel. El modelo se vinculará con la lista JList de objetos. */
    private DefaultListModel<Objeto_Casa> modelObjetosGuardados; /**< Un modelo DefaultListModel. El modelo será usado para la lista de objetos guardados. */

    private JPanel pnBotones; /**< Un objeto JPanel. El panel en el que se ubicarán los botones de acción sobre el objeto. */
    private JPanel pnBtInferior; /**< Un objeto JPanel. El panel inferior en el que se ubicará el panel de botones. */
    protected JButton btRecuperar; /**< Un objeto JButton. El botón de recuperar para acceder a la lista de objetos guardados. */
    protected JButton btGuardar; /**< Un objeto JButton. El botón para guardar en una lista de objetos almacenados. */
    private JPanel pnBtSuperior; /**< Un objeto JPanel. El panel superior integrado en el panel de botones. */
    protected JButton btCrear; /**< Un objeto JButton. El botón para crear un objeto y almacenarlo en otra lista de objetos. */
    protected JButton btActualizar; /**< Un objeto JButton. El botón para actualizar los datos del objeto seleccionado así como su posición. */
    protected JButton btEliminar; /**< Un objeto JButton. El botón para eliminar un objeto de la lista actual de objetos. */
    private JLabel lbInformacionObjetos; /**< Una etiqueta JLabel. Etiqueta de título correspondiente a la información de los objetos. */
    private JPanel pnCampos; /**< Un objeto JPanel. El panel dedicado a rellenar o mostrar los campos para un objeto determinado. */
    private JPanel pnPosiciones; /**< Un objeto JPanel. El panel para ubicar los elementos de mostrar la posición del objeto. */
    private JLabel lbId; /**< Una etiqueta JLabel. Etiqueta de título correspondiente al ID. */
    private JTextField txId; /**< Un campo de texto JTextField. Campo de texto para el ID del objeto. */
    private JLabel lbMarca; /**< Una etiqueta JLabel. Etiqueta de título correspondiente a la marca. */
    private JTextField txMarca; /**< Un campo de texto JTextField. Campo de texto para la marca del objeto. */
    private JLabel lbModelo; /**< Una etiqueta JLabel. Etiqueta de título correspondiente al modelo. */
    private JTextField txModelo; /**< Un campo de texto JTextField. Campo de texto para el modelo del objeto. */
    private JLabel lbValor; /**< Una etiqueta JLabel. Etiqueta de título correspondiente al valor. */
    private JTextField txValor; /**< Un campo de texto JTextField. Campo de texto para el valor del objeto. */
    private JCheckBox chckbxEditar; /**< Un objeto JCheckBox. Checkbox para habilitar los campos de texto y permitir que sean editables. */
    private JSeparator separator; /**< Un objeto JSeparator. Separador para reestructurar correctamente los elementos visuales en el layout. */
    private JLabel lbPosicionX; /**< Una etiqueta JLabel. Etiqueta de título correspondiente a la posición X. */
    private JTextField txPosicionX; /**< Un campo de texto JTextField. Campo de texto para la posición X del objeto. */
    private JLabel lbPosicionY; /**< Una etiqueta JLabel. Etiqueta de título correspondiente a la posición Y. */
    private JTextField txPosicionY; /**< Un campo de texto JTextField. Campo de texto para la posición Y del objeto. */

    Casa casa; /**< Un objeto Casa. Objeto Casa empleado para instanciar la lógica del proyecto y realizar las operaciones correspondientes. */

    Objeto_Casa ultimaSeleccion = null; /**< Un objeto Objeto_Casa. Objeto que representa el último elemento de la casa seleccionado en la interfaz. */

    /**
     * Método que instancia la propia clase VentanaPrincipal y visibiliza la interfaz.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaPrincipal frame = new VentanaPrincipal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor de la clase de la ventana.
     *
     * En este constructor se inicializa el objeto VentanaPrincipal, así como los elementos de la lógica necesarios para
     * simular la aplicación del localizador electrónico en el hogar. Los objetos involucrados serían el atributo casa
     * así como modelObjetosGuardados. Posteriormente, se establece el título de la ventana así como otras propiedades básicas
     * de la ventana. Se añadirán los correspondientes paneles, así como su layout, que contendrán el resto de elementos
     * de la IGU.
     */
    public VentanaPrincipal() {
        casa = new Casa(1000, 500);
        modelObjetosGuardados = new DefaultListModel<Objeto_Casa>();

        setTitle("Seguimiento de objetos en el hogar");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/ObjectTrackingIcon.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 400);
        pnPrincipal = new JPanel();
        pnPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnPrincipal.setLayout(new BorderLayout(0, 0));
        setContentPane(pnPrincipal);
        pnPrincipal.add(getPnObjetos(), BorderLayout.WEST);
        pnPrincipal.add(getPnLocalizacion(), BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    /**
     * Getter del panel de objetos.
     *
     * Este método devuelve el valor que tiene el objeto del panel de objetos para el atributo 'pnObjetos'
     * @return El panel de objetos.
     */
    private JPanel getPnObjetos() {
        if (pnObjetos == null) {
            pnObjetos = new JPanel();
            GridBagLayout gbl_pnObjetos = new GridBagLayout();
            gbl_pnObjetos.columnWidths = new int[]{214, 0};
            gbl_pnObjetos.rowHeights = new int[]{36, 244, 70, 0, 0, 0};
            gbl_pnObjetos.columnWeights = new double[]{1.0, Double.MIN_VALUE};
            gbl_pnObjetos.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
            pnObjetos.setLayout(gbl_pnObjetos);
            GridBagConstraints gbc_lbListaObjetos = new GridBagConstraints();
            gbc_lbListaObjetos.fill = GridBagConstraints.BOTH;
            gbc_lbListaObjetos.insets = new Insets(0, 0, 5, 0);
            gbc_lbListaObjetos.gridx = 0;
            gbc_lbListaObjetos.gridy = 0;
            pnObjetos.add(getLbListaObjetos(), gbc_lbListaObjetos);
            GridBagConstraints gbc_scrollPaneListaObjetos = new GridBagConstraints();
            gbc_scrollPaneListaObjetos.fill = GridBagConstraints.BOTH;
            gbc_scrollPaneListaObjetos.insets = new Insets(0, 0, 5, 0);
            gbc_scrollPaneListaObjetos.gridx = 0;
            gbc_scrollPaneListaObjetos.gridy = 1;
            pnObjetos.add(getScrollPaneListaObjetos(), gbc_scrollPaneListaObjetos);
            GridBagConstraints gbc_pnBotones = new GridBagConstraints();
            gbc_pnBotones.insets = new Insets(0, 0, 5, 0);
            gbc_pnBotones.fill = GridBagConstraints.BOTH;
            gbc_pnBotones.gridx = 0;
            gbc_pnBotones.gridy = 2;
            pnObjetos.add(getPnBotones(), gbc_pnBotones);
        }
        return pnObjetos;
    }

    /**
     * Getter del panel de localización.
     *
     * Este método devuelve el valor que tiene el objeto del panel de localización para el atributo 'pnLocalizacion'
     * @return El panel de localización.
     */
    private JPanel getPnLocalizacion() {
        if (pnLocalizacion == null) {
            pnLocalizacion = new JPanel();
            pnLocalizacion.setLayout(new BorderLayout(0, 0));
            pnLocalizacion.add(getLbInformacionObjetos(), BorderLayout.NORTH);
            pnLocalizacion.add(getPnCampos(), BorderLayout.CENTER);
            pnLocalizacion.add(getPnPosiciones(), BorderLayout.SOUTH);
        }
        return pnLocalizacion;
    }

    /**
     * Getter de la etiqueta de la lista de objetos.
     *
     * Este método devuelve el valor que tiene el objeto de la etiqueta de la lista de objetos para el atributo 'lbListaObjetos'
     * @return La etiqueta de lista de objetos.
     */
    private JLabel getLbListaObjetos() {
        if (lbListaObjetos == null) {
            lbListaObjetos = new JLabel("Lista de objetos:");
            lbListaObjetos.setVerticalAlignment(SwingConstants.TOP);
            lbListaObjetos.setFont(new Font("Tahoma", Font.BOLD, 16));
        }
        return lbListaObjetos;
    }

    /**
     * Getter del panel de scroll para la lista de objetos.
     *
     * Este método devuelve el valor que tiene el objeto del panel de scroll de lista de objetos para el atributo 'scrollPaneListaObjetos'
     * @return El panel de scroll de lista de objetos.
     */
    private JScrollPane getScrollPaneListaObjetos() {
        if (scrollPaneListaObjetos == null) {
            scrollPaneListaObjetos = new JScrollPane();
            scrollPaneListaObjetos.setViewportBorder(new EmptyBorder(1, 1, 1, 1));
            scrollPaneListaObjetos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPaneListaObjetos.setViewportView(getListObjetos());
        }
        return scrollPaneListaObjetos;
    }

    /**
     * Getter de la lista de objetos.
     *
     * Este método devuelve el valor que tiene el objeto de la lista de objetos para el atributo 'listObjetos'.
     * Cabe destacar que la lista se actualizará siempre y cuando el valor seleccionado se actualice, y esto
     * afecta al uso del objeto 'modelObjetos', que se modificará a través del método actualizarValores().
     *
     * @return La lista de objetos de la casa.
     */
    private JList<Objeto_Casa> getListObjetos() {
        if (listObjetos == null) {
            modelObjetos = new DefaultListModel<Objeto_Casa>();
            listObjetos = new JList<Objeto_Casa>();
            listObjetos.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent arg0) {
                    if (!listObjetos.isSelectionEmpty())
                        actualizarValores(modelObjetos.get(listObjetos.getSelectedIndex()));
                }
            });
            listObjetos.setModel(modelObjetos);
            listObjetos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
        return listObjetos;
    }

    /**
     * Getter del panel botones.
     *
     * Este método devuelve el valor que tiene el objeto del panel de botones para el atributo 'pnBotones'.
     * @return El panel de botones.
     */
    private JPanel getPnBotones() {
        if (pnBotones == null) {
            pnBotones = new JPanel();
            pnBotones.setLayout(new GridLayout(2, 3, 0, 0));
            pnBotones.add(getPnBtSuperior());
            pnBotones.add(getPnBtInferior());
        }
        return pnBotones;
    }

    /**
     * Getter del panel inferior de botones.
     *
     * Este método devuelve el valor que tiene el objeto del panel inferior de botones para el atributo 'pnBtInferior'.
     * @return El panel inferior de botones.
     */
    private JPanel getPnBtInferior() {
        if (pnBtInferior == null) {
            pnBtInferior = new JPanel();
            pnBtInferior.add(getBtRecuperar());
            pnBtInferior.add(getBtGuardar());
        }
        return pnBtInferior;
    }

    /**
     * Getter del botón de recuperar.
     *
     * Este método devuelve el valor que tiene el objeto del botón recuperar para el atributo 'btRecuperar'.
     * Como actionPerformed el botón accederá a una lista adicional de objetos previamente guardados para poder recuperarlos
     * e incorporarlos nuevamente a la lista actual de objetos sobre los que se está haciendo seguimiento (en caso de que
     * no esté ya en dicha lista).
     * @return El botón de recuperar.
     */
    private JButton getBtRecuperar() {
        if (btRecuperar == null) {
            btRecuperar = new JButton("Recuperar");
            btRecuperar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    Object[] opciones = modelObjetosGuardados.toArray();

                    Object objetoSeleccionado = JOptionPane.showInputDialog(getReferenciaThis(),
                            "Escoge un objeto a recuperar",
                            "Recuperar objeto",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            opciones,
                            ultimaSeleccion);

                    if (objetoSeleccionado == null) {
                        // Si el usuario cancela; no ha seleccionado nada; o pulsó Escape or hit escape
                        JOptionPane.showMessageDialog(null, "\r\nOperación cancelada");
                    } else {
                        // Se recupera el objeto
                        ultimaSeleccion = (Objeto_Casa) objetoSeleccionado;
                        recuperarObjeto(ultimaSeleccion);
                    }
                }
            });
        }
        return btRecuperar;
    }

    /**
     * Método que permite recuperar un objeto de la lista de objetos.
     *
     * En este método se incorpora el elemento que se pasa como parámetro a la lista actual de objetos de tracking.
     * Primeramente se comprueba si el objeto ya estaba presente en la lista o no.
     * @param objetoARecuperar El objeto que se desea recuperar.
     */
    public void recuperarObjeto(Objeto_Casa objetoARecuperar) {
        if (!doesItContain(objetoARecuperar, modelObjetos)) {
            modelObjetos.addElement(objetoARecuperar);
            casa.agregar_objeto(objetoARecuperar);
        } else {
            JOptionPane.showMessageDialog(null, "El objeto que desea recuperar ya existe" +
                    " en la lista actual");
        }
    }

    /**
     * Getter del botón de guardar.
     *
     * Este método devuelve el valor que tiene el objeto del botón guardar para el atributo 'btGuardar'.
     * Como actionPerformed del botón se tomará el objeto a almacenar y se comprobará que la lista de objetos guardados
     * no contenga dicho elemento. Una vez evaluado el guardado se muestra un mensaje informativo sobre la operación realizada.
     * @return El botón de guardar.
     */
    private JButton getBtGuardar() {
        if (btGuardar == null) {
            btGuardar = new JButton("Guardar");
            btGuardar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    if (!listObjetos.isSelectionEmpty()) {
                        Objeto_Casa objetoAGuardar = listObjetos.getSelectedValue();
                        if (modelObjetosGuardados.isEmpty()) {
                            modelObjetosGuardados.addElement(objetoAGuardar);
                            JOptionPane.showMessageDialog(null, "Se ha guardado el objeto correctamente.");
                        } else {
                            if (!doesItContain(objetoAGuardar, modelObjetosGuardados)) {
                                modelObjetosGuardados.addElement(objetoAGuardar);
                                JOptionPane.showMessageDialog(null, "Se ha guardado el objeto correctamente.");
                            } else {
                                JOptionPane.showMessageDialog(null, "El objeto "
                                        + objetoAGuardar.getModelo() + " con ID " + objetoAGuardar.getID() +
                                        " ya estaba guardado.", "Objeto previamente almacenado", JOptionPane.WARNING_MESSAGE);
                            }
                        }

                    }

                }
            });
        }
        return btGuardar;
    }

    /**
     * Getter del panel superior de botones.
     *
     * Este método devuelve el valor que tiene el objeto del panel superior de botones para el atributo 'pnBtSuperior'.
     * @return El panel superior de botones
     */
    private JPanel getPnBtSuperior() {
        if (pnBtSuperior == null) {
            pnBtSuperior = new JPanel();
            pnBtSuperior.add(getBtCrear());
            pnBtSuperior.add(getBtActualizar());
            pnBtSuperior.add(getBtEliminar());
        }
        return pnBtSuperior;
    }

    /**
     * Getter del botón de crear.
     *
     * Este método devuelve el valor que tiene el objeto del botón crear para el atributo 'btCrear'.
     * Como actionPerformed del botón se tomará el objeto que se desea crear y se comprobará que no exista en la lista
     * de objetos de tracking (es decir, que no se repita el campo ID).
     * @return El botón de crear.
     */
    private JButton getBtCrear() {
        if (btCrear == null) {
            btCrear = new JButton("Crear");
            btCrear.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    if (getChckbxEditar().isSelected()) {

                        String campoID = getTxId().getText();
                        String campoMarca = getTxMarca().getText();
                        String campoModelo = getTxModelo().getText();
                        String campoValor = getTxValor().getText();

                        if (!(campoID.isBlank() && campoMarca.isBlank() && campoModelo.isBlank() && campoValor.isBlank())) {
                            Objeto_Casa objetoACrear = new Objeto_Casa(campoID, campoMarca, campoModelo, campoValor, casa);
                            if (modelObjetos.isEmpty() || !doesItContain(objetoACrear, modelObjetos)) {
                                modelObjetos.addElement(objetoACrear);
                                casa.agregar_objeto(objetoACrear);
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Ya existe un objeto con el mismo ID en la lista. Por favor, introduce otro ID");
                            }
                        }
                    }
                }
            });
        }
        return btCrear;
    }

    /**
     * Método que permite comprobar un elemento en el modelo de la lista de objetos que se pase como parámetro.
     *
     * En este método se comprueba dado un objeto para comprobarse, y dado un modelo de lista en el que realizar
     * dicha búsqueda, aquel objeto que coincida en el atributo ID de tal forma que se informe del conflicto en
     * caso de ya existir, o 'false' si no existen dos objetos con el mismo ID.
     * @param objetoAComprobar El objeto que se desea buscar.
     * @param modeloDeBusqueda El modelo de la lista en el que se desea buscar.
     * @return Un booleano informando de la presencia del elemento a buscar en la lista deseada.
     */
    public boolean doesItContain(Objeto_Casa objetoAComprobar, DefaultListModel<Objeto_Casa> modeloDeBusqueda) {
        for (int i = 0; i < modeloDeBusqueda.size(); i++) {
            if (modeloDeBusqueda.get(i).getID().equals(objetoAComprobar.getID())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Getter del botón de actualizar.
     *
     * Este método devuelve el valor que tiene el objeto del botón actualizar para el atributo 'btActualizar'.
     * Como actionPerformed se comprueba que haya un elemento seleccionado en la lista para poder ser actualizado y,
     * posteriormente, se dispone a refrescar los valores que se deban actualizar en la IGU, de tal forma que pueda
     * comprobarse el desplazamiento simulado del objeto en los límites establecidos dentro del hogar, y se pueda
     * consultar también toda la información restante relativa al objeto en cuestión.
     * @return El botón de actualizar.
     */
    private JButton getBtActualizar() {
        if (btActualizar == null) {
            btActualizar = new JButton("Actualizar");
            btActualizar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    if (!listObjetos.isSelectionEmpty()) {
                        Objeto_Casa objetoActualizable = modelObjetos.get(listObjetos.getSelectedIndex());
                        actualizarValores(objetoActualizable);
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor, selecciona " +
                                "un elemento de la lista para actualizar la información.");
                    }

                }
            });
        }
        return btActualizar;
    }

    /**
     * Método que permite refrescar la información mostrada para el objeto deseado.
     *
     * En este método se utiliza el objeto que sea pasado como parámetro para mostrar en los campos correspondientes
     * el conjunto de atributos que conforman la clase de origen, de tal modo que el usuario disponga de toda la
     * información relativa al objeto, y además, se calcule la nueva posición del objeto.
     * @param objetoActualizable El objeto sobre el que se desea mostrar y actualizar sus valores.
     */
    public void actualizarValores(Objeto_Casa objetoActualizable) {
        String idActualizado = objetoActualizable.getInfo()[0];
        String marcaActualizada = objetoActualizable.getInfo()[1];
        String modeloActualizado = objetoActualizable.getInfo()[2];
        String valorActualizado = objetoActualizable.getInfo()[3];

        getTxId().setText(idActualizado);
        getTxMarca().setText(marcaActualizada);
        getTxModelo().setText(modeloActualizado);
        getTxValor().setText(valorActualizado);

        objetoActualizable.calculate_next_pos(Ruta.COTA_DE_DESPLAZAMIENTO_X, Ruta.COTA_DE_DESPLAZAMIENTO_Y);

        String valorPosXActualizado = objetoActualizable.getInfo()[4];
        String valorPosYActualizado = objetoActualizable.getInfo()[5];

        getTxPosicionX().setText(valorPosXActualizado);
        getTxPosicionY().setText(valorPosYActualizado);
    }

    /**
     * Getter del botón de eliminar.
     *
     * Este método devuelve el valor que tiene el objeto del botón eliminar para el atributo 'btEliminar'.
     * Como actionPerformed se procede a eliminar el objeto seleccionado tanto de la lista de la interfaz gráfica
     * como de la lista perteneciente a la lógica instanciada de la clase 'Casa', de tal forma que no haya registro
     * de dicho objeto. Además, se vacían los campos para continuar usando la aplicación.
     * @return El botón de eliminar.
     */
    private JButton getBtEliminar() {
        if (btEliminar == null) {
            btEliminar = new JButton("Eliminar");
            btEliminar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    if (!listObjetos.isSelectionEmpty()) {
                        Objeto_Casa objetoABorrar = listObjetos.getSelectedValue();
                        modelObjetos.removeElement(objetoABorrar);
                        casa.eliminar_objeto(objetoABorrar);
                        vaciarCampos();
                    } else {
                        JOptionPane.showMessageDialog(null, "Puede que la lista " +
                                "se encuentre vacía o no haya seleccionado ningún elemento.");
                    }
                }
            });
        }
        return btEliminar;
    }

    /**
     * Método para vaciar los campos.
     *
     * En este método se vacían todos los campos pertenecientes en el panel de información del objeto.
     * Para ello se 'settean' los valores como una cadena vacía.
     */
    public void vaciarCampos() {
        getTxId().setText("");
        getTxMarca().setText("");
        getTxModelo().setText("");
        getTxValor().setText("");
        getTxPosicionX().setText("");
        getTxPosicionY().setText("");
    }

    /**
     * Getter de la etiqueta de la información de los objetos.
     *
     * Este método devuelve el valor que tiene el objeto de la etiqueta de información de objetos para el atributo 'lbInformacionObjetos'.
     * @return La etiqueta con el texto sobre la información de los objetos.
     */
    private JLabel getLbInformacionObjetos() {
        if (lbInformacionObjetos == null) {
            lbInformacionObjetos = new JLabel("Informacion del objeto:");
            lbInformacionObjetos.setHorizontalAlignment(SwingConstants.CENTER);
            lbInformacionObjetos.setFont(new Font("Sylfaen", Font.BOLD, 22));
        }
        return lbInformacionObjetos;
    }

    /**
     * Getter del panel de campos.
     *
     * Este método devuelve el valor que tiene el objeto del panel de campos para el atributo 'pnCampos'.
     * @return El panel de campos.
     */
    private JPanel getPnCampos() {
        if (pnCampos == null) {
            pnCampos = new JPanel();
            pnCampos.setToolTipText("Cuando esta opci\u00F3n est\u00E1 deshabilitada, solo podr\u00E1 mostrarse la informaci\u00F3n. En caso contrario, se podr\u00E1n modificar los campos.");
            GridBagLayout gbl_pnCampos = new GridBagLayout();
            gbl_pnCampos.columnWidths = new int[]{119, 223, 0};
            gbl_pnCampos.rowHeights = new int[]{60, 60, 60, 60, 60, 0};
            gbl_pnCampos.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
            gbl_pnCampos.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
            pnCampos.setLayout(gbl_pnCampos);
            GridBagConstraints gbc_lbId = new GridBagConstraints();
            gbc_lbId.fill = GridBagConstraints.BOTH;
            gbc_lbId.insets = new Insets(0, 0, 5, 5);
            gbc_lbId.gridx = 0;
            gbc_lbId.gridy = 0;
            pnCampos.add(getLbId(), gbc_lbId);
            GridBagConstraints gbc_txId = new GridBagConstraints();
            gbc_txId.fill = GridBagConstraints.BOTH;
            gbc_txId.insets = new Insets(0, 0, 5, 0);
            gbc_txId.gridx = 1;
            gbc_txId.gridy = 0;
            pnCampos.add(getTxId(), gbc_txId);
            GridBagConstraints gbc_lbMarca = new GridBagConstraints();
            gbc_lbMarca.fill = GridBagConstraints.BOTH;
            gbc_lbMarca.insets = new Insets(0, 0, 5, 5);
            gbc_lbMarca.gridx = 0;
            gbc_lbMarca.gridy = 1;
            pnCampos.add(getLbMarca(), gbc_lbMarca);
            GridBagConstraints gbc_txMarca = new GridBagConstraints();
            gbc_txMarca.fill = GridBagConstraints.BOTH;
            gbc_txMarca.insets = new Insets(0, 0, 5, 0);
            gbc_txMarca.gridx = 1;
            gbc_txMarca.gridy = 1;
            pnCampos.add(getTxMarca(), gbc_txMarca);
            GridBagConstraints gbc_lbModelo = new GridBagConstraints();
            gbc_lbModelo.fill = GridBagConstraints.BOTH;
            gbc_lbModelo.insets = new Insets(0, 0, 5, 5);
            gbc_lbModelo.gridx = 0;
            gbc_lbModelo.gridy = 2;
            pnCampos.add(getLbModelo(), gbc_lbModelo);
            GridBagConstraints gbc_txModelo = new GridBagConstraints();
            gbc_txModelo.fill = GridBagConstraints.BOTH;
            gbc_txModelo.insets = new Insets(0, 0, 5, 0);
            gbc_txModelo.gridx = 1;
            gbc_txModelo.gridy = 2;
            pnCampos.add(getTxModelo(), gbc_txModelo);
            GridBagConstraints gbc_lbValor = new GridBagConstraints();
            gbc_lbValor.fill = GridBagConstraints.BOTH;
            gbc_lbValor.insets = new Insets(0, 0, 5, 5);
            gbc_lbValor.gridx = 0;
            gbc_lbValor.gridy = 3;
            pnCampos.add(getLbValor(), gbc_lbValor);
            GridBagConstraints gbc_txValor = new GridBagConstraints();
            gbc_txValor.fill = GridBagConstraints.BOTH;
            gbc_txValor.insets = new Insets(0, 0, 5, 0);
            gbc_txValor.gridx = 1;
            gbc_txValor.gridy = 3;
            pnCampos.add(getTxValor(), gbc_txValor);
            GridBagConstraints gbc_separator = new GridBagConstraints();
            gbc_separator.fill = GridBagConstraints.BOTH;
            gbc_separator.insets = new Insets(0, 0, 0, 5);
            gbc_separator.gridx = 0;
            gbc_separator.gridy = 4;
            pnCampos.add(getSeparator(), gbc_separator);
            GridBagConstraints gbc_chckbxEditar = new GridBagConstraints();
            gbc_chckbxEditar.fill = GridBagConstraints.BOTH;
            gbc_chckbxEditar.gridx = 1;
            gbc_chckbxEditar.gridy = 4;
            pnCampos.add(getChckbxEditar(), gbc_chckbxEditar);
        }
        return pnCampos;
    }

    /**
     * Getter del panel de posiciones.
     *
     * Este método devuelve el valor que tiene el objeto del panel de posiciones para el atributo 'pnPosiciones'.
     * @return El panel de posiciones.
     */
    private JPanel getPnPosiciones() {
        if (pnPosiciones == null) {
            pnPosiciones = new JPanel();
            GridBagLayout gbl_pnPosiciones = new GridBagLayout();
            gbl_pnPosiciones.columnWidths = new int[]{104, 137, 94, 137, 0};
            gbl_pnPosiciones.rowHeights = new int[]{20, 0};
            gbl_pnPosiciones.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
            gbl_pnPosiciones.rowWeights = new double[]{0.0, Double.MIN_VALUE};
            pnPosiciones.setLayout(gbl_pnPosiciones);
            GridBagConstraints gbc_lbPosicionX = new GridBagConstraints();
            gbc_lbPosicionX.insets = new Insets(0, 0, 0, 5);
            gbc_lbPosicionX.gridx = 0;
            gbc_lbPosicionX.gridy = 0;
            pnPosiciones.add(getLbPosicionX(), gbc_lbPosicionX);
            GridBagConstraints gbc_txPosicionX = new GridBagConstraints();
            gbc_txPosicionX.fill = GridBagConstraints.HORIZONTAL;
            gbc_txPosicionX.insets = new Insets(0, 0, 0, 5);
            gbc_txPosicionX.gridx = 1;
            gbc_txPosicionX.gridy = 0;
            pnPosiciones.add(getTxPosicionX(), gbc_txPosicionX);
            GridBagConstraints gbc_lbPosicionY = new GridBagConstraints();
            gbc_lbPosicionY.insets = new Insets(0, 0, 0, 5);
            gbc_lbPosicionY.gridx = 2;
            gbc_lbPosicionY.gridy = 0;
            pnPosiciones.add(getLbPosicionY(), gbc_lbPosicionY);
            GridBagConstraints gbc_txPosicionY = new GridBagConstraints();
            gbc_txPosicionY.fill = GridBagConstraints.HORIZONTAL;
            gbc_txPosicionY.gridx = 3;
            gbc_txPosicionY.gridy = 0;
            pnPosiciones.add(getTxPosicionY(), gbc_txPosicionY);
        }
        return pnPosiciones;
    }

    /**
     * Getter de la etiqueta del título para el ID.
     *
     * Este método devuelve el valor que tiene el objeto de la etiqueta del título de ID para el atributo 'lbId'.
     * @return La etiqueta con el texto sobre el título de ID.
     */
    private JLabel getLbId() {
        if (lbId == null) {
            lbId = new JLabel("ID: ");
            lbId.setHorizontalAlignment(SwingConstants.CENTER);
            lbId.setFont(new Font("Tahoma", Font.BOLD, 14));
        }
        return lbId;
    }

    /**
     * Getter del campo de texto para el ID.
     *
     * Este método devuelve el valor que tiene el objeto del campo de texto de ID para el atributo 'txId'.
     * @return El campo de texto para el ID.
     */
    private JTextField getTxId() {
        if (txId == null) {
            txId = new JTextField();
            txId.setColumns(10);
        }
        return txId;
    }

    /**
     * Getter de la etiqueta del título para la marca.
     *
     * Este método devuelve el valor que tiene el objeto de la etiqueta del título de marca para el atributo 'lbMarca'.
     * @return La etiqueta con el texto sobre el título de ID.
     */
    private JLabel getLbMarca() {
        if (lbMarca == null) {
            lbMarca = new JLabel("Marca: ");
            lbMarca.setHorizontalAlignment(SwingConstants.CENTER);
            lbMarca.setFont(new Font("Tahoma", Font.BOLD, 14));
        }
        return lbMarca;
    }

    /**
     * Getter del campo de texto para la marca.
     *
     * Este método devuelve el valor que tiene el objeto del campo de texto de la marca para el atributo 'txMarca'.
     * @return El campo de texto para la marca.
     */
    private JTextField getTxMarca() {
        if (txMarca == null) {
            txMarca = new JTextField();
            txMarca.setFont(new Font("Tahoma", Font.PLAIN, 11));
            txMarca.setColumns(10);
        }
        return txMarca;
    }

    /**
     * Getter de la etiqueta del título para el modelo.
     *
     * Este método devuelve el valor que tiene el objeto de la etiqueta del título del modelo para el atributo 'lbModelo'.
     * @return La etiqueta con el texto para el título del modelo.
     */
    private JLabel getLbModelo() {
        if (lbModelo == null) {
            lbModelo = new JLabel("Modelo:");
            lbModelo.setHorizontalAlignment(SwingConstants.CENTER);
            lbModelo.setFont(new Font("Tahoma", Font.BOLD, 14));
        }
        return lbModelo;
    }

    /**
     * Getter del campo de texto para el modelo.
     *
     * Este método devuelve el valor que tiene el objeto del campo de texto del modelo para el atributo 'txModelo'.
     * @return El campo de texto para el modelo.
     */
    private JTextField getTxModelo() {
        if (txModelo == null) {
            txModelo = new JTextField();
            txModelo.setColumns(10);
        }
        return txModelo;
    }

    /**
     * Getter de la etiqueta del título para el valor.
     *
     * Este método devuelve el valor que tiene el objeto de la etiqueta del título de valor para el atributo 'lbValor'.
     * @return La etiqueta con el texto para el título del valor.
     */
    private JLabel getLbValor() {
        if (lbValor == null) {
            lbValor = new JLabel("Valor: ");
            lbValor.setHorizontalAlignment(SwingConstants.CENTER);
            lbValor.setFont(new Font("Tahoma", Font.BOLD, 14));
        }
        return lbValor;
    }

    /**
     * Getter del campo de texto para el valor.
     *
     * Este método devuelve el valor que tiene el objeto del campo de texto de valor para el atributo 'txValor'.
     * @return El campo de texto para el valor.
     */
    private JTextField getTxValor() {
        if (txValor == null) {
            txValor = new JTextField();
            txValor.setColumns(10);
        }
        return txValor;
    }

    /**
     * Getter del checkbox de editar.
     *
     * Este método devuelve el valor que tiene el objeto del checkbox de editar para el atributo 'chckbxEditar'.
     * @return El checkbox de editar.
     */
    private JCheckBox getChckbxEditar() {
        if (chckbxEditar == null) {
            chckbxEditar = new JCheckBox("Editar");
            chckbxEditar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    boolean isSelected = getChckbxEditar().isSelected();
                    getTxId().setEditable(isSelected);
                    getTxMarca().setEditable(isSelected);
                    getTxModelo().setEditable(isSelected);
                    getTxValor().setEditable(isSelected);
                }
            });
            chckbxEditar.setHorizontalAlignment(SwingConstants.RIGHT);
            chckbxEditar.setSelected(true);
        }
        return chckbxEditar;
    }

    /**
     * Getter del separador.
     *
     * Este método devuelve el valor que tiene el objeto del separador para el atributo 'separator'.
     * @return El separador.
     */
    private JSeparator getSeparator() {
        if (separator == null) {
            separator = new JSeparator();
            separator.setForeground(SystemColor.scrollbar);
        }
        return separator;
    }

    /**
     * Getter de la etiqueta del título para la posición X.
     *
     * Este método devuelve el valor que tiene el objeto de la etiqueta del título de la posición X para el atributo 'lbPosicionX'.
     * @return La etiqueta con el texto para el título de la posición X.
     */
    private JLabel getLbPosicionX() {
        if (lbPosicionX == null) {
            lbPosicionX = new JLabel("Posicion X: ");
            lbPosicionX.setHorizontalAlignment(SwingConstants.CENTER);
            lbPosicionX.setFont(new Font("Tahoma", Font.BOLD, 14));
        }
        return lbPosicionX;
    }

    /**
     * Getter del campo de texto para la posición X.
     *
     * Este método devuelve el valor que tiene el objeto del campo de texto de la posición X para el atributo 'txPosicionX'.
     * @return El campo de texto para la posición X.
     */
    private JTextField getTxPosicionX() {
        if (txPosicionX == null) {
            txPosicionX = new JTextField();
            txPosicionX.setEditable(false);
            txPosicionX.setHorizontalAlignment(SwingConstants.CENTER);
            txPosicionX.setColumns(10);
        }
        return txPosicionX;
    }

    /**
     * Getter de la etiqueta del título para la posición Y.
     *
     * Este método devuelve el valor que tiene el objeto de la etiqueta del título de la posición Y para el atributo 'lbPosicionY'.
     * @return La etiqueta con el texto para el título de la posición Y.
     */
    private JLabel getLbPosicionY() {
        if (lbPosicionY == null) {
            lbPosicionY = new JLabel("Posicion Y: ");
            lbPosicionY.setHorizontalAlignment(SwingConstants.CENTER);
            lbPosicionY.setFont(new Font("Tahoma", Font.BOLD, 14));
        }
        return lbPosicionY;
    }

    /**
     * Getter del campo de texto para la posición Y.
     *
     * Este método devuelve el valor que tiene el objeto del campo de texto de la posición Y para el atributo 'txPosicionY'.
     * @return El campo de texto para la posición Y.
     */
    private JTextField getTxPosicionY() {
        if (txPosicionY == null) {
            txPosicionY = new JTextField();
            txPosicionY.setEditable(false);
            txPosicionY.setHorizontalAlignment(SwingConstants.CENTER);
            txPosicionY.setColumns(10);
        }
        return txPosicionY;
    }

    /**
     * Getter de la referencia a la propia clase.
     *
     * Este método devuelve el valor que tiene el objeto de la instancia actual sobre la propia clase.
     * @return La referencia a la propia clase (this).
     */
    private VentanaPrincipal getReferenciaThis() {
        return this;
    }
}