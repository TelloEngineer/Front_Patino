package Visual;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import Logic.Analyzer.Lexico.GrammarV1_0;
import Logic.Analyzer.Lexico.Token.Token;
import Logic.Analyzer.Semantico.Semantic;
import Logic.Analyzer.Sintactico.Syntatic;
import Logic.Analyzer.Sintactico.ejemplo_sintactico_AST;
import Logic.Behavior.Analyzer;
import Logic.Behavior.Grammar;
import Logic.Formats.StringAnalyzer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class Menu extends JFrame implements ActionListener {
    private JTextArea entradaField;
    private JButton analizarButton;
    private JButton analizarSintacticaButton;
    private JButton traerCodigoFuenteButton;
    private JButton limpiarAnalizadorLexicoButton;
    private JTable tabla;
    private DefaultTableModel tablaModel;

    public Menu() {
        setTitle("Analizador Léxico y Sintáctico");
        setSize(800, 630);
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

        analizarSintacticaButton = new JButton("Analizar Sintácticamente");
        analizarSintacticaButton.addActionListener(this);

        traerCodigoFuenteButton = new JButton("Código fuente");
        traerCodigoFuenteButton.addActionListener(this);

        limpiarAnalizadorLexicoButton = new JButton("Limpiar");
        limpiarAnalizadorLexicoButton.addActionListener(this);

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

        //acomodo lexico
        // colocamos en dos bloques en horizontal y en vertical los componenetes
        // agregando los espacios para que estén donde queramos
        // panelPrincipal.setHorizontalGroup(
        //         panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        //                 .addGroup(panelPrincipal.createSequentialGroup()
        //                         .addGap(136, 136, 136)
        //                         .addComponent(analizarButton)
        //                         .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        //                 .addGroup(panelPrincipal.createSequentialGroup()
        //                         .addGap(35, 35, 35)
        //                         .addComponent(panelCaptura, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
        //                         .addGap(18, 18, 18)
        //                         .addComponent(scrollPaneTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 384,
        //                                 javax.swing.GroupLayout.PREFERRED_SIZE)
        //                         .addGap(29, 29, 29)));
        // panelPrincipal.setVerticalGroup(
        //         panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        //                 .addGroup(panelPrincipal.createSequentialGroup()
        //                         .addGap(17, 17, 17)
        //                         .addGroup(panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        //                                 .addComponent(scrollPaneTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 338,
        //                                         javax.swing.GroupLayout.PREFERRED_SIZE)
        //                                 .addComponent(panelCaptura))
        //                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        //                         .addComponent(analizarButton)
        //                         .addGap(10, 10, 10)));

        //acomodo léxico y sintáctico
        panelPrincipal.setHorizontalGroup(
            panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelPrincipal.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addGroup(panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(traerCodigoFuenteButton, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                        .addComponent(panelCaptura))
                    .addGap(18, 37, Short.MAX_VALUE)
                    .addGroup(panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(scrollPaneTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipal.createSequentialGroup()
                            .addComponent(analizarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(limpiarAnalizadorLexicoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(analizarSintacticaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(22, 22, 22))
        );
        panelPrincipal.setVerticalGroup(
            panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipal.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(traerCodigoFuenteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(analizarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(limpiarAnalizadorLexicoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipal.createSequentialGroup()
                        .addComponent(scrollPaneTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 500,javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(analizarSintacticaButton,  javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelCaptura, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

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
        String command = e.getActionCommand();
        List<Token> listaTokens = grammar.getLexycal();

        System.out.println(command);
        if(command.equalsIgnoreCase("Limpiar") || command.equalsIgnoreCase("Analizar léxicamente")){
            deleteColumns();
        }
        
        
        if (command.equalsIgnoreCase("Analizar léxicamente")) {
            for (Token index : grammar.getLexycal()) {
                tablaModel.addRow(new Object[] { index.getLexema().getSubString(), index.getId().getDescription(), index.getId().getId() });
                //System.out.println(index);
            }
        }
        
        if(command.equalsIgnoreCase("Código fuente")){
            JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto (.txt)", "txt"));

                int returnValue = fileChooser.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                        String line;
                        entradaField.setText(""); // Limpia el área de texto antes de cargar el nuevo archivo.
                        while ((line = reader.readLine()) != null) {
                            entradaField.append(line + "\n"); // Agrega el contenido del archivo al área de texto.
                        }
                        reader.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
        }

        if(command.equalsIgnoreCase("Analizar Sintácticamente")){
            System.out.println("clic en analizador sintactico");
            // int rowCount = tablaModel.getRowCount();
            // Object firstToken = tablaModel.getValueAt(0, 0);
            // int contTokens = 1;
            // System.out.println(firstToken);
            // Object prevToken = firstToken;
            // Pattern identiPattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");
            // if("int".equals(firstToken)){
            //     for(int row = 1; row < rowCount; row++){
            //         Object token = tablaModel.getValueAt(row, 0);
            //         System.out.println(token);
            //         if("main".equals(token)){
            //             contTokens += 1;
            //             prevToken = token;
            //             token = tablaModel.getValueAt(contTokens, 0);
            //             if("(".equals(token)){
            //                 contTokens += 1;
            //                 prevToken = token;
            //                 token = tablaModel.getValueAt(contTokens, 0);
            //                 if(")".equals(token)){
            //                     contTokens += 1;
            //                     prevToken = token;
            //                     token = tablaModel.getValueAt(contTokens, 0);
            //                     if("{".equals(token)){
            //                         contTokens += 1;
            //                         prevToken = token;
            //                         token = tablaModel.getValueAt(contTokens, 0);
                                    
            //                         //empieza el bloque
            //                         for(int rowB = contTokens; rowB < rowCount; rowB++){
            //                             //validamos si es identificador
            //                             String valueToken = token.toString();
            //                             Matcher identificadorMatcher = identiPattern.matcher(valueToken);
            //                             if(identificadorMatcher.matches()){
            //                                 System.out.println("identificador");
            //                             }
            //                         }
            //                         if("}".equals(token)){
            //                             JOptionPane.showMessageDialog(null, "Analizado sinácticamente", "Éxito", JOptionPane.ERROR_MESSAGE);
            //                             break;
            //                         }
            //                         else{
            //                             showErrorDialog("Error cerca de: '"+prevToken+"', token no reconocido: "+ token);
            //                             break;
            //                         }
            //                     }
            //                     else{
            //                         showErrorDialog("Error cerca de: '"+prevToken+"', token no reconocido: "+ token);
            //                         break;
            //                     }
            //                 }
            //                 else{
            //                     showErrorDialog("Error cerca de: '"+prevToken+"', token no reconocido: "+ token);
            //                     break;
            //                 }
            //             }
            //             else{
            //                 showErrorDialog("Error cerca de: '"+prevToken+"', token no reconocido: "+ token);
            //                 break;
            //             }
            //         }
            //         else{
            //             showErrorDialog("Error cerca de: '"+prevToken+"', token no reconocido: "+ token);
            //             break;
            //         }
            //     }
            // }
            // else{
            //     showErrorDialog("Error en linea 1, token no reconocido: "+ firstToken);
            // }

            ejemplo_sintactico_AST analizarSintact = new ejemplo_sintactico_AST();
            if(!analizarSintact.isValid(listaTokens)){
                JOptionPane.showMessageDialog(null, analizarSintact.getStateMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, analizarSintact.getStateMessage(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
            
            System.out.println(new Semantic().semanticAnalize(analizarSintact.getArbol_Sintactico()));
        }
    }

    private void deleteColumns(){
        while((tablaModel.getRowCount()>0)){
            tablaModel.removeRow(tablaModel.getRowCount()-1);
        }
            
    }

    public void showErrorDialog(String msg) {
        String errorMessage = msg;
        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
