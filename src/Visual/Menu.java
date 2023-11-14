package Visual;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import Logic.Analyzer.Lexico.GrammarV1_0;
import Logic.Analyzer.Lexico.Token.Token;
import Logic.Analyzer.Semantico.Semantic;
import Logic.Analyzer.Sintactico.Exp;
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
    private JButton analizarSemanticaButton;
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

        analizarSemanticaButton = new JButton("Analizar Semánticamente");
        analizarSemanticaButton.addActionListener(this);

        // Tabla en la esquina inferior izquierda
        tablaModel = new DefaultTableModel();
        tablaModel.addColumn("Lexema");
        tablaModel.addColumn("Token");
        tablaModel.addColumn("Número");
        tabla = new JTable(tablaModel);
        tabla.getTableHeader().setBackground(new Color(168, 169, 240));
        tabla.setSelectionBackground(Color.red);
        JScrollPane scrollPaneTabla = new JScrollPane(tabla);

       

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
        // panelPrincipal.setHorizontalGroup(
        //     panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        //         .addGroup(panelPrincipal.createSequentialGroup()
        //             .addGap(31, 31, 31)
        //             .addGroup(panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
        //                 .addComponent(traerCodigoFuenteButton, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
        //                 .addComponent(panelCaptura))
        //             .addGap(18, 37, Short.MAX_VALUE)
        //             .addGroup(panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
        //                 .addComponent(scrollPaneTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        //                 .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipal.createSequentialGroup()
        //                     .addComponent(analizarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
        //                     .addGap(18, 18, 18)
        //                     .addComponent(limpiarAnalizadorLexicoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
        //                 .addComponent(analizarSintacticaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        //                 .addGap(22, 22, 22))
        // );
        // panelPrincipal.setVerticalGroup(
        //     panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        //     .addGroup(panelPrincipal.createSequentialGroup()
        //         .addContainerGap()
        //         .addGroup(panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
        //             .addGroup(panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
        //                 .addComponent(traerCodigoFuenteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
        //                 .addComponent(analizarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        //             .addComponent(limpiarAnalizadorLexicoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        //         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        //         .addGroup(panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        //             .addGroup(panelPrincipal.createSequentialGroup()
        //                 .addComponent(scrollPaneTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 500,javax.swing.GroupLayout.PREFERRED_SIZE)
        //                 .addGap(18, 18, 18)
        //                 .addComponent(analizarSintacticaButton,  javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        //             .addComponent(panelCaptura, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE))
        //         .addContainerGap(12, Short.MAX_VALUE))
        // );

        // add(panelPrincipal);

        //sintáctico, léxico y semántico
        panelPrincipal.setHorizontalGroup(
            panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipal.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(traerCodigoFuenteButton, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                        .addComponent(panelCaptura)
                        .addComponent(analizarSemanticaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE )
                    .addGroup(panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(analizarSintacticaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(scrollPaneTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipal.createSequentialGroup()
                                .addComponent(analizarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(limpiarAnalizadorLexicoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            )
                        )
                    )
                .addContainerGap()
            )
        );

        panelPrincipal.setVerticalGroup(
            panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                panelPrincipal.createSequentialGroup()
                .addContainerGap()
                .addGroup(
                    panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(
                        panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(traerCodigoFuenteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(analizarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    )
                    .addComponent(limpiarAnalizadorLexicoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(
                    panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelCaptura)
                    .addComponent(scrollPaneTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                )
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(
                    panelPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(analizarSintacticaButton,  javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(analizarSemanticaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            )
        );

        pack();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Analyzer analyzer;
        analyzer = new StringAnalyzer();
        GrammarV1_0 grammar = new GrammarV1_0();
        Grammar grammarToAnalize = grammar;
        Syntatic analizarSintact = new Syntatic();

        String entrada = entradaField.getText();
        analyzer.analyze(entrada, grammarToAnalize);
        String command = e.getActionCommand();
        List<Token> listaTokens = grammar.getLexycal();
        boolean isSyntaticCorrect = analizarSintact.isValid(listaTokens);

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
            
            if(!isSyntaticCorrect){
                JOptionPane.showMessageDialog(null, analizarSintact.getStateMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, analizarSintact.getStateMessage(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if(command.equalsIgnoreCase("Analizar Semánticamente")){
            //Exp Ast_toAnalize = analizarSintact.getArbol_Sintactico();
            //System.out.println(new Semantic().semanticAnalize(Ast_toAnalize)); 
            System.out.println(analizarSintact.getErrorSemantic().toString());
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
