package vistas;

import controller.VistaWordsController;
import hebras.ThreadClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import map.Word_ESP;

public class VistaWords extends javax.swing.JFrame {

    private Socket socket;
    String mensaje;
    String login;
    String token;
    private ArrayList<Word_ESP> word_ESP;
    private String words[];
    private String tabla[][];
    private boolean buttonTable;
    private boolean close;
    private ThreadClient client;
    private PrintWriter out;
    private BufferedReader in;

    public VistaWords(Socket socket, String mensaje, String login, String token) throws IOException {
        initComponents();
        this.socket = socket;
        this.buttonTable = false;
        this.close = false;
        this.mensaje = mensaje;
        this.word_ESP = loadArray();
        this.login = login;
        this.token = token;
        System.out.println("aaaa");
        System.out.println(this.mensaje);

        this.out = new PrintWriter(this.socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

//        showTableWords();

        VistaWordsController vistaWordsController = new VistaWordsController(this.mensaje,this.word_ESP);
        this.tabla = vistaWordsController.showTable();
        tableWords.setModel(new javax.swing.table.DefaultTableModel(this.tabla, new String[]{"Nombre"}));
    }

    private VistaWords() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        textWord = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        buttonTableWords = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableWords = new javax.swing.JTable();
        textDescription = new javax.swing.JTextField();

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

        jLabel1.setText("Escribe la palabra que quieres buscar en el diccionario");

        jLabel3.setText("Write the world that you want to search in the diccionary");

        buttonTableWords.setText("Tabla Palabras");
        buttonTableWords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTableWordsActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textWord, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(71, 357, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonTableWords)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTableWords)
                    .addComponent(textDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonTableWordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTableWordsActionPerformed
//        this.buttonTable = true;
//        showTableWords();
    }//GEN-LAST:event_buttonTableWordsActionPerformed

    
    public ArrayList<Word_ESP> loadArray() {

        ArrayList<Word_ESP> array = new ArrayList();
        String[] a;

        this.words = this.mensaje.split("#");
        int totalWords = Integer.parseInt(this.words[2]);

        this.words = transformWords(this.words, totalWords);

        for (int contador = 0; contador < this.words.length; contador++) {
            Word_ESP word_ESP = new Word_ESP();

            a = this.words[contador].split("@");
            word_ESP.setCod_palabra(a[0]);
            word_ESP.setWord_ESP(a[1]);
            word_ESP.setDefinition_ESP(a[2]);
            array.add(word_ESP);
        }

        return array;
    }
    
    private void tableWordsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableWordsMouseClicked

        int row = tableWords.getSelectedRow();
        
        System.out.println("AQUII: " + this.mensaje);
        
//      this.out.println("GET_SPECIFIC_WORD");

        this.out.println("PROTOCOLCRISTONARY1.0#GET_SPECIFIC_WORD#" + login + "#" + token + "#" + word_ESP.get(row).getCod_palabra());
    }//GEN-LAST:event_tableWordsMouseClicked

    public String[] transformWords(String[] words, int nWords) {

        String[] array = new String[nWords];
        int i = 0;
        for (int contador = 3; contador < words.length; contador++) {
            array[i] = words[contador];
            i++;
        }
        return array;
    }
//
//    public final void showTableWords() {
//
//        String[] cadena = this.mensaje.split("#");
//        int totalWords = Integer.parseInt(cadena[2]);
//
//        this.words = transformWords(cadena, totalWords);
//        this.tabla = new String[words.length][1];
//
//        for (int contador = 0; contador < words.length; contador++) {
//            this.tabla[contador][0] = words[contador];
//        }
//
////        this.out.println("MIS_GORDOS");
//        tableWords.setModel(new javax.swing.table.DefaultTableModel(tabla, new String[]{"Nombre"}));
//    }

//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(VistaWords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(VistaWords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(VistaWords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(VistaWords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new VistaWords().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonTableWords;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableWords;
    private javax.swing.JTextField textDescription;
    private javax.swing.JTextField textWord;
    // End of variables declaration//GEN-END:variables

}
