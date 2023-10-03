package Visual;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Logic.Analyzer.Lexico.GrammarV1_0;
import Logic.Analyzer.Lexico.Token.Token;
import Logic.Behavior.Analyzer;
import Logic.Behavior.Grammar;
import Logic.Formats.StringAnalyzer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    private JTextArea entradaField;
    private JButton analizarButton;
    private JTable tabla;
    private DefaultTableModel tablaModel;

    public Menu() {
        setTitle("Analizador Léxico");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un panel principal con GridBagLayout
        // JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        // Panel de captura en la esquina superior izquierda
        JPanel panelCaptura = new JPanel(new BorderLayout());
        entradaField = new JTextArea(10, 30);
        entradaField.setLineWrap(true);
        entradaField.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(entradaField);
        panelCaptura.add(scrollPane, BorderLayout.CENTER);

        // Botón "Analizar léxicamente" en el centro
        analizarButton = new JButton("Analizar léxicamente");
        analizarButton.addActionListener(this);

        // Tabla en la esquina inferior izquierda
        tablaModel = new DefaultTableModel();
        tablaModel.addColumn("Lexema");
        tablaModel.addColumn("Token");
        tablaModel.addColumn("Número");
        tabla = new JTable(tablaModel);
        tabla.getTableHeader().setBackground(new Color(168, 169, 240));
        tabla.setSelectionBackground(Color.red);
        JScrollPane scrollPaneTabla = new JScrollPane(tabla);

        // constraints.gridx = 0;
        // constraints.gridy = 0;
        // constraints.gridwidth = 1;
        // constraints.gridheight = 1;
        // panelPrincipal.add(panelCaptura, constraints);

        // constraints.gridx = 1;
        // constraints.gridy = 0;
        // constraints.gridwidth = 1;
        // constraints.gridheight = 1;
        // constraints.fill = GridBagConstraints.NONE; // Establecer el relleno a NONE
        // panelPrincipal.add(analizarButton, constraints);

        // constraints.gridx = 2;
        // constraints.gridy = 0;
        // constraints.gridwidth = 1;
        // constraints.gridheight = 1;
        // constraints.fill = GridBagConstraints.BOTH; // Restaurar el relleno a BOTH

        // constraints.gridx = 0;
        // constraints.gridy = 1;
        // constraints.gridwidth = 3;
        // constraints.gridheight = 1;
        // panelPrincipal.add(scrollPaneTabla, constraints);

        javax.swing.GroupLayout panelPrincipal = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(panelPrincipal);

        // colocamos en dos bloques en horizontal y en vertical los componenetes
        // agregando los espacios para que estén donde queramos
        panelPrincipal.setHorizontalGroup(
                panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelPrincipal.createSequentialGroup()
                                .addGap(136, 136, 136)
                                .addComponent(analizarButton)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(panelPrincipal.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(panelCaptura, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(scrollPaneTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 384,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)));
        panelPrincipal.setVerticalGroup(
                panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelPrincipal.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(scrollPaneTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 338,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panelCaptura))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(analizarButton)
                                .addGap(10, 10, 10)));

        // add(panelPrincipal);
        pack();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Analyzer analyzer;
        analyzer = new StringAnalyzer();
        GrammarV1_0 grammar = new GrammarV1_0();
        Grammar grammarToAnalize = grammar;
        String entrada = entradaField.getText();
        analyzer.analyze(entrada, grammarToAnalize);

        deleteColumns();
        if (e.getSource() == analizarButton) {
            for (Token index : grammar.getLexycal()) {
                tablaModel.addRow(new Object[] { index.getLexema().getSubString(), index.getId().getDescription(), index.getId().getId() });
                //System.out.println(index);
            }
        }
    }
    private void deleteColumns(){
        while((tablaModel.getRowCount()>0)){
            tablaModel.removeRow(tablaModel.getRowCount()-1);
        }
            
    }
}
