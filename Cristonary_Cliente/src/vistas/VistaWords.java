package vistas;

import controller.VistaWordsController;
import controller.VistaWords_ING_Controller;
import java.awt.TextArea;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import map.Word;
import map.Word_ESP;
import map.Word_ING;

public class VistaWords extends javax.swing.JFrame {

    private Socket socket;
    private String[] mensaje;
    private String login;
    private String token;
    private ArrayList<Word_ESP> arrayWord_ESP;
    private ArrayList<Word_ING> arrayWord_ING;
    private String words[];
    private PrintWriter out;
    private int contador;
    private int tamanio;
    public int totalESP, totalING;
    private VistaCreateWord_ESP vistaCreateWord_ESP;
    private VistaCreateWord_ING vistaCreateWord_ING;

    public VistaWords(Socket socket, String[] mensaje, String login, String token) throws IOException {
        initComponents();
        System.out.println("AQUI NO ENTRAMOS O QUE????????????");
        this.socket = socket;
        this.mensaje = mensaje;

        this.login = login;
        this.token = token;
        this.out = new PrintWriter(this.socket.getOutputStream(), true);

        soliciteWords();
        System.out.println("aaaa");
        System.out.println(this.mensaje);

//        this.arrayWord_ESP = loadArray();
//        this.arrayWord_ING = loadArray_ING();
//
//        VistaWordsController vistaWordsController = new VistaWordsController(this.mensaje, this.arrayWord_ESP);
//        this.tabla = vistaWordsController.showTable();
//        tableWords.setModel(new javax.swing.table.DefaultTableModel(this.tabla, new String[]{"Nombre"}));
//
//        VistaWords_ING_Controller vistaWords_ING_Controller = new VistaWords_ING_Controller(this.mensaje, this.arrayWord_ING);
//        this.tabla_ING = vistaWords_ING_Controller.showTable();
//        tableWordsENGLISH.setModel(new javax.swing.table.DefaultTableModel(this.tabla_ING, new String[]{"Name"}));
    }

    private VistaWords() {
        initComponents();
    }

    public ArrayList<Word_ESP> getArrayWord_ESP() {
        return arrayWord_ESP;
    }

    public void setArrayWord_ESP(ArrayList<Word_ESP> arrayWord_ESP) {
        this.arrayWord_ESP = arrayWord_ESP;
    }

    public ArrayList<Word_ING> getArrayWord_ING() {
        return arrayWord_ING;
    }

    public void setArrayWord_ING(ArrayList<Word_ING> arrayWord_ING) {
        this.arrayWord_ING = arrayWord_ING;
    }

    public void soliciteWords() {
        this.out.println("PROTOCOLOCRISTONARY1.0#GET_WORD#" + this.login + "#" + this.token);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableWords = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableWordsENGLISH = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        textMessage = new java.awt.TextArea();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        textoEliminar = new javax.swing.JTextField();
        textDelete = new javax.swing.JTextField();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Escoge la palabra que quieres buscar en el diccionario");

        jLabel3.setText("Select the word that you want to search in the dictionary");

        tableWords.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Nombre"
            }
        ));
        tableWords.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableWordsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableWords);

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tableWordsENGLISH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Name"
            }
        ));
        tableWordsENGLISH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableWordsENGLISHMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableWordsENGLISH);

        jButton2.setText("Crear Palabra");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Create Word");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Eliminar Palabra");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Delete Word");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(195, 195, 195)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addGap(41, 41, 41)
                                .addComponent(textoEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(112, 112, 112)
                                .addComponent(jButton5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textDelete))
                        .addGap(149, 149, 149))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(textoEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textMessage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(87, 87, 87))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    public ArrayList<Word_ESP> loadArray() {
//
//        ArrayList<Word_ESP> array = new ArrayList();
//        String[] a;
// 
//        for (int contador = 0; contador < this.words.length; contador++) {
//            System.out.println(this.words[contador]);
//        }
//
//        int totalWords = Integer.parseInt(this.words[2]);
//        System.out.println(totalWords);
//        this.words = transformWords(this.words, totalWords);
//        System.out.println("VAMOS A DEMOSTRAR QUE...");
//        for (int contador = 0; contador < this.words.length; contador++) {
//            System.out.println(this.words[contador]);
//        }
//
//        for (int contador = 0; contador < this.words.length; contador++) {
//            Word_ESP word_ESP = new Word_ESP();
//
//            a = this.words[contador].split("@");
//            word_ESP.setCod_palabra(a[0]);
//            word_ESP.setWord_ESP(a[1]);
//            word_ESP.setDefinition_ESP(a[2]);
//            array.add(word_ESP);
//            System.out.println(array.get(contador));
//        }
//
//        return array;
//    }
//    public ArrayList<Word_ING> loadArray_ING() {
//
//        ArrayList<Word_ING> array = new ArrayList<Word_ING>();
//        String[] a;
//
//        int totalWords = Integer.parseInt(this.words[2]);
//
//        this.words = transformWords_ING(this.words, totalWords);
//
//        for (int contador = 0; contador < words.length; contador++) {
//            System.out.println(this.words[contador]);
//        }
//
//        for (int contador = 0; contador < this.words.length; contador++) {
//            Word_ING word_ING = new Word_ING();
//
//            a = this.words[contador].split("@");
//            word_ING.setCod_palabra(a[0]);
//            word_ING.setWord_ING(a[1]);
//            word_ING.setDefinition_ING(a[2]);
//            array.add(word_ING);
//        }
//        return array;
//    }

    private void tableWordsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableWordsMouseClicked

        int row = tableWords.getSelectedRow();

        this.out.println("PROTOCOLCRISTONARY1.0#GET_SPECIFIC_WORD#" + login + "#" + token + "#" + arrayWord_ESP.get(row).getCod_palabra() + "#" + "ESP");
    }//GEN-LAST:event_tableWordsMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        this.out.println("BYE");
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tableWordsENGLISHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableWordsENGLISHMouseClicked

        int row = tableWordsENGLISH.getSelectedRow();

        this.out.println("PROTOCOLCRISTONARY1.0#GET_SPECIFIC_WORD#" + login + "#" + token + "#" + arrayWord_ING.get(row).getCod_palabra() + "#" + "ING");
    }//GEN-LAST:event_tableWordsENGLISHMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            this.vistaCreateWord_ESP = new VistaCreateWord_ESP(this.socket, this.login);
            this.vistaCreateWord_ESP.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(VistaWords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            this.vistaCreateWord_ING = new VistaCreateWord_ING(this.socket, this.login);
            this.vistaCreateWord_ING.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(VistaWords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        String nombre = this.textoEliminar.getText();
        boolean existe = false;
        System.out.println("AQUI ENTRAMOS??");
        System.out.println(nombre);
        int i = 0;
        for (i = 0; contador < this.arrayWord_ESP.size() && existe == false; i++) {
            System.out.println("Esto es lo tuyo " + this.arrayWord_ESP.get(i).getWord_ESP());
            if (nombre.equals(this.arrayWord_ESP.get(i).getWord_ESP())) {

                System.out.println("DISELO OLE OLEOEE: " + this.arrayWord_ESP.get(i).getCod_palabra());
                existe = true;
                System.out.println("ESTO ES LO QUE MANDA: " + this.arrayWord_ESP.get(i).getCod_palabra());
                this.out.println("PROTOCOLCRISTONARY1.0#DELETE_WORD_ESP#" + this.arrayWord_ESP.get(i).getCod_palabra() + "#" + this.arrayWord_ESP.get(i).getWord_ESP() + "#" + this.login + "#" + "ESP");
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String nombre = this.textDelete.getText();
        boolean existe = false;
        System.out.println("AQUI ENTRAMOS??");
        System.out.println(nombre);
        int i = 0;
        for (i = 0; contador < this.arrayWord_ING.size() && existe == false; i++) {
            if (nombre.equals(this.arrayWord_ING.get(i).getWord_ING())) {
                existe = true;
                this.out.println("PROTOCOLCRISTONARY1.0#DELETE_WORD_ING#" + this.arrayWord_ING.get(i).getCod_palabra() + "#" + this.arrayWord_ING.get(i).getWord_ING() + "#" + this.login + "#" + "ING");
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    public String[] transformWords(String[] words, int nWords) {

        this.tamanio = nWords - 1;
        String[] array = new String[tamanio];
        this.totalESP = tamanio;

        int i = 0;
        boolean done = false;
        System.out.println("las palabras tiene: " + words.length);
        System.out.println("el array tiene: " + array.length);

        for (this.contador = 3; this.contador < words.length && done == false; this.contador++) {

            if ("ESP".equals(words[this.contador])) {
                done = true;
            } else {
                System.out.println("ESTO YA LLEVA: " + words[this.contador]);
                System.out.println("EL ARRAY QUEDA: " + array[i]);
                array[i] = words[this.contador];
                i++;
            }
        }

        return array;
    }

    public String[] transformWords_ING(String[] words, int nWords) {

        int newTamanio = contador - nWords;

        System.out.println(newTamanio);
        System.out.println("tamanio: " + tamanio);
        this.tamanio = this.tamanio - newTamanio;
        String[] array = new String[this.tamanio];
        boolean done = false;
        int j = 0;
        this.totalING = this.tamanio;

        for (int a = contador; contador < words.length && done == false; a++) {

            System.out.println("LO QUE SE VIENE: " + words[a]);
            if ("ING".equals(words[a])) {
                done = true;
                System.out.println("to ta aut");
            } else {
                System.out.println("JUSTO ANTES: " + words[a]);

                array[j] = words[a];
                System.out.println("POR UN LADO: " + words[a]);
                j++;
            }

        }
        System.out.println("EN FIN...");
        for (int contador = 0; contador < array.length; contador++) {
            System.out.println(array[contador]);
        }

        return array;
    }

    public void refreshTable_ESP(String tabla[][]) {
        this.tableWords.setModel(new javax.swing.table.DefaultTableModel(
                tabla,
                new String[]{
                    "Nombre"
                }
        ));
    }

    public void refreshTable_ING(String tabla[][]) {
        this.tableWordsENGLISH.setModel(new javax.swing.table.DefaultTableModel(
                tabla,
                new String[]{
                    "Name"
                }
        ));
    }

    public void updateMessaje(String mensaje, String login) {
        this.textMessage.append("\nLa palabra: " + mensaje + " ha sido introducida por el usuario: " + login);
    }

    public void uptadeMessajeDelete(String mensaje, String login) {
        this.textMessage.append("\nLa palabra: " + mensaje + " ha sido eliminada por el usuario: " + login);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableWords;
    private javax.swing.JTable tableWordsENGLISH;
    private javax.swing.JTextField textDelete;
    private java.awt.TextArea textMessage;
    private javax.swing.JTextField textoEliminar;
    // End of variables declaration//GEN-END:variables

}
