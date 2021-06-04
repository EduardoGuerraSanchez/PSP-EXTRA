package vistas;

import hebras.ThreadClient;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.ImageIcon;

public class VistaWords_ESP extends javax.swing.JFrame {

    private String description;
    private Socket socket;
    private String nameWord;
    private String login;
    private String key;
    private String cod_word;
    private String aux;
    private String cadena[];
    private String format;
    private int nBytes;
    private ThreadClient threadClient;

    private PrintWriter out;

    public VistaWords_ESP() {
        initComponents();
    }

    public VistaWords_ESP(Socket socket,String aux,String login,String key,String format,int nBytes,ThreadClient threadClient) throws IOException {
        initComponents();
        this.threadClient = threadClient;
        this.socket = socket;
        this.aux = aux;
        this.cadena = aux.split("@");
        this.cod_word = cadena[0];
        this.login = login;
        this.key = key;
        this.format = format;
        this.nBytes = nBytes;
        this.nameWord = this.cadena[1];
        threadClient.setNameMultimedia(cod_word);
        for(int contador = 0; contador < cadena.length;contador++){
            System.out.println("NUMERO: " + contador + " " + this.cadena[contador]);
        }
        this.textAreaDescription.append(this.cadena[3]);
        this.labelName.setText(this.nameWord);
        solicitarMultimedia(this.socket);
    }

    public void solicitarMultimedia(Socket s) throws IOException {
        this.out = new PrintWriter(this.socket.getOutputStream(), true);
        System.out.println("-------------------------------------->: " + cadena[0]);
        System.out.println(cadena[1]);
        System.out.println(cadena[2]);
        System.out.println(cadena[3]);
        this.out.println("PROTOCOLCRISTONARY1.0#PREPARED_TO_RECEIVE#" + this.login + "#" + this.key + "#" + cadena[0] + "#" + "SIZE_PACKAGE" + 512);
    }
    
    public void addMultimedia(String route){
        ImageIcon imageWord = new ImageIcon(route); 
        ImageIcon icon = new ImageIcon(imageWord.getImage().getScaledInstance(labelMultimedia.getWidth(), labelMultimedia.getHeight(), Image.SCALE_DEFAULT));
        labelMultimedia.setIcon(icon);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        textAreaDescription = new java.awt.TextArea();
        labelName = new java.awt.Label();
        labelMultimedia = new javax.swing.JLabel();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelName.setText("label1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(labelName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(labelMultimedia, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(textAreaDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(labelName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(textAreaDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(labelMultimedia, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaWords_ESP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaWords_ESP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaWords_ESP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaWords_ESP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaWords_ESP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel labelMultimedia;
    private java.awt.Label labelName;
    private java.awt.TextArea textAreaDescription;
    // End of variables declaration//GEN-END:variables
}
