/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAINT2;



import PAINT2.Constantes;
import PAINT2.Texto;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

public class VentanaTexto extends javax.swing.JDialog {
    
    private GraphicsEnvironment gEnv;
    private Texto texto;
    private boolean dibujaTexto;
    private VentanaColor ventanaColor;

    public VentanaTexto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e){}
        gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        initComponents();
        texto = null;
        ventanaColor = null;
        dibujaTexto = false;
        this.setTitle("" + Constantes.INCREMENTO_CANTIDAD_DE_ESPACIO_TITULO +
                "Introduzca un texto");         
        this.setResizable(false);               
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanelTextPanel = new javax.swing.JPanel();
        jLabelIntroduzcaTexto = new javax.swing.JLabel();
        jTextFieldTexto = new javax.swing.JTextField();
        jLabelTipolFuente = new javax.swing.JLabel();
        jLabelTamañoTexto = new javax.swing.JLabel();
        jSpinnerTamañoTexto = new javax.swing.JSpinner(new SpinnerNumberModel(Constantes.VALOR_PREDETERMINADO_SPINNER_NUMBER_MODEL, Constantes.VALOR_MINIMO_SPINNER_NUMBER_MODEL, Constantes.VALOR_MAXIMO_SPINNER_NUMBER_MODEL, Constantes.INCREMENTO_SPINNER_NUMBER_MODEL));
        jComboBoxTipoFuentes = new javax.swing.JComboBox();
        jButtonColor = new javax.swing.JButton();
        jLabelEstilo = new javax.swing.JLabel();
        jComboBoxTipoEstilo = new javax.swing.JComboBox();
        jButtonAceptar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabelIntroduzcaTexto.setText("Introduzca un texto:");

        jTextFieldTexto.setToolTipText("texto");

        javax.swing.GroupLayout jPanelTextPanelLayout = new javax.swing.GroupLayout(jPanelTextPanel);
        jPanelTextPanel.setLayout(jPanelTextPanelLayout);
        jPanelTextPanelLayout.setHorizontalGroup(
            jPanelTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTextPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelIntroduzcaTexto)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelTextPanelLayout.setVerticalGroup(
            jPanelTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTextPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIntroduzcaTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jTextFieldTexto)))
        );

        jLabelTipolFuente.setText("Fuentes:");
        jLabelTipolFuente.setToolTipText("Tipo de Fuentes");

        jLabelTamañoTexto.setText("Tamaño:");

        jSpinnerTamañoTexto.setToolTipText("Tamaño");

        jComboBoxTipoFuentes.setMaximumRowCount(5);
        jComboBoxTipoFuentes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Serif", "SansSerif", "Monospaced", "Dialog", "DialogInput" }));
        jComboBoxTipoFuentes.setSelectedItem(Constantes.TIPO_FUENTE_LETRA_PREDETERMINADO);
        jComboBoxTipoFuentes.setToolTipText("Tipos de Fuente");
        jComboBoxTipoFuentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoFuentesActionPerformed(evt);
            }
        });

        jButtonColor.setText("Color");
        jButtonColor.setToolTipText("Color");
        jButtonColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonColorActionPerformed(evt);
            }
        });

        jLabelEstilo.setText("Estilo:");

        jComboBoxTipoEstilo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Negrita", "Cursiva", "Negrita Cursiva" }));
        jComboBoxTipoEstilo.setToolTipText("Tipo de Estilos");

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelTextPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabelTipolFuente, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxTipoFuentes, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabelTamañoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jSpinnerTamañoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jButtonColor))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabelEstilo)
                        .addGap(41, 41, 41)
                        .addComponent(jComboBoxTipoEstilo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jButtonAceptar)
                        .addGap(27, 27, 27)
                        .addComponent(jButtonCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelTextPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTipolFuente, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jComboBoxTipoFuentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTamañoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerTamañoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jButtonColor)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabelEstilo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBoxTipoEstilo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAceptar)
                    .addComponent(jButtonCancelar))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }

    private void jComboBoxTipoFuentesActionPerformed(java.awt.event.ActionEvent evt) {
}

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {
        jTextFieldTexto.requestFocus();
        if(!jTextFieldTexto.getText().equals("")){
            int numeroEntero = Integer.parseInt(jSpinnerTamañoTexto.getValue().toString());
            Color colorTexto;
            if(ventanaColor != null){
                colorTexto = ventanaColor.getJColorChooserColores().getColor();
            }else{
                colorTexto = Color.BLACK; 
            }
            texto = new Texto(jTextFieldTexto.getText(), seleccionarTipoFuente(jComboBoxTipoFuentes.getSelectedIndex()),
                jComboBoxTipoEstilo.getSelectedIndex(), numeroEntero, colorTexto);
            this.setVisible(false);
            setDibujaTexto(true);
        }else {
            String mensaje = "Introduzca un texto!!!";
            JOptionPane.showMessageDialog(this, mensaje, "" + Constantes.INCREMENTO_CANTIDAD_DE_ESPACIO_TITULO +
                    Constantes.TITULO_PROGRAMA,JOptionPane.PLAIN_MESSAGE);
            jTextFieldTexto.requestFocus();
            setDibujaTexto(false);
        }
        jTextFieldTexto.setText("");
        repaint();
}

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        setVisible(false);
        setDibujaTexto(false);

        dispose();
        
}

    private void jButtonColorActionPerformed(java.awt.event.ActionEvent evt) {
        mostrarVentanaColor();
        ventanaColor.setLocationRelativeTo(this);

        
    }
    
    public Texto getTexto() {
        return texto;
    }

    public void setTexto(Texto texto) {
        this.texto = texto;
    }


    public boolean isDibujaTexto() {
        return dibujaTexto;
    }

    public void setDibujaTexto(boolean dibujaTexto) {
        this.dibujaTexto = dibujaTexto;
    }

    public void mostrarVentanaColor() {
        if (ventanaColor == null) {
            ventanaColor = new VentanaColor(null, rootPaneCheckingEnabled);
            ventanaColor.setLocationRelativeTo(this);
        }
        ventanaColor.setTitle("Color del texto");
        ventanaColor.setVisible(true);
    }

    
    public String seleccionarTipoFuente(int indice){
        String tipoFuente = "Dialog";

        switch(indice){
            case 1:
                tipoFuente = "Serif";
                break;
            case 2:
                tipoFuente = "SansSerif";
                break;
            case 3:
                tipoFuente = "Monospaced";
                break;
            case 4:
                tipoFuente = "Dialog";
                break;
            case 5:
                tipoFuente = "DialogInput";
                break;
        }
        return tipoFuente;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaTexto dialog = new VentanaTexto(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonColor;
    private javax.swing.JComboBox jComboBoxTipoEstilo;
    private javax.swing.JComboBox jComboBoxTipoFuentes;
    private javax.swing.JLabel jLabelEstilo;
    private javax.swing.JLabel jLabelIntroduzcaTexto;
    private javax.swing.JLabel jLabelTamañoTexto;
    private javax.swing.JLabel jLabelTipolFuente;
    private javax.swing.JPanel jPanelTextPanel;
    private javax.swing.JSpinner jSpinnerTamañoTexto;
    private javax.swing.JTextField jTextFieldTexto;

}