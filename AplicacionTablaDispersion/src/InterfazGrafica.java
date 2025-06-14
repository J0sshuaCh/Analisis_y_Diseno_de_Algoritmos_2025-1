import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InterfazGrafica extends JFrame {
    private TablaDispersionLineal tablaLineal;
    private TablaDispersionEncadenamiento tablaEncadenamiento;
    private List<Cliente> clientes;
    
    // Componentes de la interfaz
    private JTextField txtCodigo, txtNombres, txtApellidos, txtTelefono;
    private JTextField txtCorreo, txtDireccion, txtCodigoPostal, txtBuscar;
    private JTextArea txtResultados, txtComparacion;
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;
    
    public InterfazGrafica() {
        // Inicializar estructuras de datos
        tablaLineal = new TablaDispersionLineal(100);
        tablaEncadenamiento = new TablaDispersionEncadenamiento(50);
        clientes = new ArrayList<>();
        
        inicializarInterfaz();
        cargarDatosPrueba();
    }
    
    private void inicializarInterfaz() {
        setTitle("Laboratorio - Tablas de Dispersión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Panel de entrada de datos
        JPanel panelEntrada = crearPanelEntrada();
        add(panelEntrada, BorderLayout.NORTH);
        
        // Panel central con pestañas
        JTabbedPane pestañas = new JTabbedPane();
        
        // Pestaña de datos
        JPanel panelDatos = crearPanelDatos();
        pestañas.addTab("Datos de Clientes", panelDatos);
        
        // Pestaña de búsqueda
        JPanel panelBusqueda = crearPanelBusqueda();
        pestañas.addTab("Búsqueda", panelBusqueda);
        
        // Pestaña de comparación
        JPanel panelComparacion = crearPanelComparacion();
        pestañas.addTab("Comparación de Métodos", panelComparacion);
        
        add(pestañas, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private JPanel crearPanelEntrada() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Ingreso de Cliente"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Campos de entrada
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Código:"), gbc);
        gbc.gridx = 1;
        txtCodigo = new JTextField(10);
        panel.add(txtCodigo, gbc);
        
        gbc.gridx = 2;
        panel.add(new JLabel("Nombres:"), gbc);
        gbc.gridx = 3;
        txtNombres = new JTextField(15);
        panel.add(txtNombres, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Apellidos:"), gbc);
        gbc.gridx = 1;
        txtApellidos = new JTextField(15);
        panel.add(txtApellidos, gbc);
        
        gbc.gridx = 2;
        panel.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 3;
        txtTelefono = new JTextField(10);
        panel.add(txtTelefono, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Correo:"), gbc);
        gbc.gridx = 1;
        txtCorreo = new JTextField(20);
        panel.add(txtCorreo, gbc);
        
        gbc.gridx = 2;
        panel.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 3;
        txtDireccion = new JTextField(20);
        panel.add(txtDireccion, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Código Postal:"), gbc);
        gbc.gridx = 1;
        txtCodigoPostal = new JTextField(10);
        panel.add(txtCodigoPostal, gbc);
        
        // Botones
        gbc.gridx = 2; gbc.gridy = 3;
        JButton btnAgregar = new JButton("Agregar Cliente");
        btnAgregar.addActionListener(this::agregarCliente);
        panel.add(btnAgregar, gbc);
        
        gbc.gridx = 3;
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(this::limpiarCampos);
        panel.add(btnLimpiar, gbc);
        
        return panel;
    }
    
    private JPanel crearPanelDatos() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Tabla de clientes
        String[] columnas = {"Código", "Nombres", "Apellidos", "Teléfono", "Correo", "Dirección", "C.P."};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaClientes = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaClientes);
        scrollTabla.setPreferredSize(new Dimension(800, 300));
        
        panel.add(scrollTabla, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelBusqueda() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Panel de búsqueda
        JPanel panelBuscar = new JPanel(new FlowLayout());
        panelBuscar.add(new JLabel("Buscar por nombre completo:"));
        txtBuscar = new JTextField(20);
        panelBuscar.add(txtBuscar);
        
        JButton btnBuscarLineal = new JButton("Buscar (Lineal)");
        btnBuscarLineal.addActionListener(this::buscarLineal);
        panelBuscar.add(btnBuscarLineal);
        
        JButton btnBuscarEncadenamiento = new JButton("Buscar (Encadenamiento)");
        btnBuscarEncadenamiento.addActionListener(this::buscarEncadenamiento);
        panelBuscar.add(btnBuscarEncadenamiento);
        
        panel.add(panelBuscar, BorderLayout.NORTH);
        
        // Área de resultados
        txtResultados = new JTextArea(15, 50);
        txtResultados.setEditable(false);
        JScrollPane scrollResultados = new JScrollPane(txtResultados);
        panel.add(scrollResultados, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelComparacion() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Botón de comparación
        JButton btnComparar = new JButton("Ejecutar Comparación de Rendimiento");
        btnComparar.addActionListener(this::compararMetodos);
        panel.add(btnComparar, BorderLayout.NORTH);
        
        // Área de comparación
        txtComparacion = new JTextArea(20, 60);
        txtComparacion.setEditable(false);
        txtComparacion.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollComparacion = new JScrollPane(txtComparacion);
        panel.add(scrollComparacion, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void cargarDatosPrueba() {
        Cliente[] clientesPrueba = {
            new Cliente("001", "Juan", "Pérez", "987654321", "juan@email.com", "Av. Lima 123", "15001"),
            new Cliente("002", "María", "García", "987654322", "maria@email.com", "Jr. Cusco 456", "15002"),
            new Cliente("003", "Carlos", "López", "987654323", "carlos@email.com", "Av. Arequipa 789", "15003"),
            new Cliente("004", "Ana", "Martínez", "987654324", "ana@email.com", "Jr. Tacna 321", "15004"),
            new Cliente("005", "Luis", "Rodríguez", "987654325", "luis@email.com", "Av. Brasil 654", "15005"),
            new Cliente("006", "Carmen", "Torres", "987654326", "carmen@email.com", "Jr. Huancayo 987", "15006"),
            new Cliente("007", "Pedro", "Flores", "987654327", "pedro@email.com", "Av. Venezuela 147", "15007"),
            new Cliente("008", "Rosa", "Vargas", "987654328", "rosa@email.com", "Jr. Ayacucho 258", "15008"),
            new Cliente("009", "Miguel", "Herrera", "987654329", "miguel@email.com", "Av. Colonial 369", "15009"),
            new Cliente("010", "Elena", "Castillo", "987654330", "elena@email.com", "Jr. Ica 741", "15010")
        };
        
        for (Cliente cliente : clientesPrueba) {
            clientes.add(cliente);
            tablaLineal.insertar(cliente);
            tablaEncadenamiento.insertar(cliente);
            
            Object[] fila = {
                cliente.getCodigo(),
                cliente.getNombres(),
                cliente.getApellidos(),
                cliente.getTelefono(),
                cliente.getCorreo(),
                cliente.getDireccion(),
                cliente.getCodigoPostal()
            };
            modeloTabla.addRow(fila);
        }
    }
    
    private void agregarCliente(ActionEvent e) {
        if (validarCampos()) {
            Cliente cliente = new Cliente(
                txtCodigo.getText(),
                txtNombres.getText(),
                txtApellidos.getText(),
                txtTelefono.getText(),
                txtCorreo.getText(),
                txtDireccion.getText(),
                txtCodigoPostal.getText()
            );
            
            clientes.add(cliente);
            tablaLineal.insertar(cliente);
            tablaEncadenamiento.insertar(cliente);
            
            Object[] fila = {
                cliente.getCodigo(),
                cliente.getNombres(),
                cliente.getApellidos(),
                cliente.getTelefono(),
                cliente.getCorreo(),
                cliente.getDireccion(),
                cliente.getCodigoPostal()
            };
            modeloTabla.addRow(fila);
            
            limpiarCampos(e);
            JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente");
        }
    }
    
    private boolean validarCampos() {
        if (txtCodigo.getText().trim().isEmpty() ||
            txtNombres.getText().trim().isEmpty() ||
            txtApellidos.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los campos Código, Nombres y Apellidos son obligatorios");
            return false;
        }
        return true;
    }
    
    private void limpiarCampos(ActionEvent e) {
        txtCodigo.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        txtCodigoPostal.setText("");
    }
    
    private void buscarLineal(ActionEvent e) {
        String nombreCompleto = txtBuscar.getText().trim();
        if (nombreCompleto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un nombre completo para buscar");
            return;
        }
        
        long inicio = System.nanoTime();
        Cliente cliente = tablaLineal.buscar(nombreCompleto);
        long fin = System.nanoTime();
        
        StringBuilder resultado = new StringBuilder();
        resultado.append("=== BÚSQUEDA LINEAL ===\n");
        resultado.append("Tiempo de búsqueda: ").append((fin - inicio) / 1000.0).append(" microsegundos\n");
        resultado.append("Nombre buscado: ").append(nombreCompleto).append("\n");
        
        if (cliente != null) {
            resultado.append("CLIENTE ENCONTRADO:\n");
            resultado.append(cliente.toString()).append("\n");
        } else {
            resultado.append("CLIENTE NO ENCONTRADO\n");
        }
        
        txtResultados.setText(resultado.toString());
    }
    
    private void buscarEncadenamiento(ActionEvent e) {
        String nombreCompleto = txtBuscar.getText().trim();
        if (nombreCompleto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un nombre completo para buscar");
            return;
        }
        
        long inicio = System.nanoTime();
        Cliente cliente = tablaEncadenamiento.buscar(nombreCompleto);
        long fin = System.nanoTime();
        
        StringBuilder resultado = new StringBuilder();
        resultado.append("=== BÚSQUEDA POR ENCADENAMIENTO ===\n");
        resultado.append("Tiempo de búsqueda: ").append((fin - inicio) / 1000.0).append(" microsegundos\n");
        resultado.append("Nombre buscado: ").append(nombreCompleto).append("\n");
        
        if (cliente != null) {
            resultado.append("CLIENTE ENCONTRADO:\n");
            resultado.append(cliente.toString()).append("\n");
        } else {
            resultado.append("CLIENTE NO ENCONTRADO\n");
        }
        
        txtResultados.setText(resultado.toString());
    }
    
    private void compararMetodos(ActionEvent e) {
        Cliente[] arrayClientes = clientes.toArray(new Cliente[0]);
        
        MedidorTiempo.ResultadoTiempo resultadoLineal = 
            MedidorTiempo.medirRendimientoLineal(arrayClientes);
        
        MedidorTiempo.ResultadoTiempo resultadoEncadenamiento = 
            MedidorTiempo.medirRendimientoEncadenamiento(arrayClientes);
        
        StringBuilder comparacion = new StringBuilder();
        comparacion.append("=== COMPARACIÓN DE MÉTODOS DE RESOLUCIÓN DE COLISIONES ===\n\n");
        
        comparacion.append("MÉTODO: REASIGNACIÓN LINEAL\n");
        comparacion.append("- Tiempo de inserción: ").append(resultadoLineal.tiempoInsercion / 1000.0).append(" microsegundos\n");
        comparacion.append("- Tiempo de búsqueda: ").append(resultadoLineal.tiempoBusqueda / 1000.0).append(" microsegundos\n");
        comparacion.append("- Elementos insertados: ").append(resultadoLineal.elementosInsertados).append("/").append(arrayClientes.length).append("\n");
        comparacion.append("- Factor de carga: ").append(String.format("%.2f", tablaLineal.factorCarga())).append("\n\n");
        
        comparacion.append("MÉTODO: ENCADENAMIENTO CON ÁRBOLES BINARIOS\n");
        comparacion.append("- Tiempo de inserción: ").append(resultadoEncadenamiento.tiempoInsercion / 1000.0).append(" microsegundos\n");
        comparacion.append("- Tiempo de búsqueda: ").append(resultadoEncadenamiento.tiempoBusqueda / 1000.0).append(" microsegundos\n");
        comparacion.append("- Elementos insertados: ").append(resultadoEncadenamiento.elementosInsertados).append("/").append(arrayClientes.length).append("\n");
        comparacion.append("- Factor de carga: ").append(String.format("%.2f", tablaEncadenamiento.factorCarga())).append("\n\n");
        
        comparacion.append("=== ANÁLISIS DE COMPLEJIDAD ASINTÓTICA ===\n\n");
        comparacion.append("REASIGNACIÓN LINEAL:\n");
        comparacion.append("- Inserción: O(1) caso promedio, O(n) caso peor\n");
        comparacion.append("- Búsqueda: O(1) caso promedio, O(n) caso peor\n");
        comparacion.append("- Ventajas: Uso eficiente de memoria, implementación simple\n");
        comparacion.append("- Desventajas: Degradación con alta carga, clustering primario\n\n");
        
        comparacion.append("ENCADENAMIENTO CON ÁRBOLES BINARIOS:\n");
        comparacion.append("- Inserción: O(1) caso promedio, O(log n) por colisión\n");
        comparacion.append("- Búsqueda: O(1) caso promedio, O(log n) por colisión\n");
        comparacion.append("- Ventajas: Mejor rendimiento con colisiones, sin clustering\n");
        comparacion.append("- Desventajas: Mayor uso de memoria, complejidad de implementación\n\n");
        
        // Determinar el método más eficiente
        long tiempoTotalLineal = resultadoLineal.tiempoInsercion + resultadoLineal.tiempoBusqueda;
        long tiempoTotalEncadenamiento = resultadoEncadenamiento.tiempoInsercion + resultadoEncadenamiento.tiempoBusqueda;
        
        comparacion.append("=== CONCLUSIÓN ===\n");
        if (tiempoTotalLineal < tiempoTotalEncadenamiento) {
            comparacion.append("La reasignación lineal fue más rápida en esta prueba.\n");
        } else {
            comparacion.append("El encadenamiento con árboles fue más rápido en esta prueba.\n");
        }
        comparacion.append("Diferencia: ").append(Math.abs(tiempoTotalLineal - tiempoTotalEncadenamiento) / 1000.0).append(" microsegundos");
        
        txtComparacion.setText(comparacion.toString());
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
               UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new InterfazGrafica().setVisible(true);
        });
    }
}