package vistas;

import java.awt.Image;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.ImageIcon;
import protocol.ClientProtocol;

public class VistaWords_ING extends javax.swing.JFrame {
    
    private Socket socket;
    private String nameWord;
    private String login;
    private String key;
    private String cod_word;
    private String cadena[];
    private String format;
    private int nBytes;
    private ClientProtocol clientProtocol;
    private String creator;
    private PrintWriter out;

    public VistaWords_ING() {
        initComponents();
    }
    
    public VistaWords_ING(Socket socket,String aux,String login,String key,String format,int nBytes,String creator,ClientProtocol clientProtocol) throws IOException {
        initComponents();
        this.clientProtocol = clientProtocol;
        this.socket = socket;
        this.cadena = aux.split("@");
        this.cod_word = cadena[0];
        this.login = login;
        this.key = key;
        this.format = format;
        this.nBytes = nBytes;
        this.nameWord = this.cadena[1];
        this.clientProtocol.setNameMultimedia(cod_word);
        this.creator = creator;
        for(int contador = 0; contador < cadena.length;contador++){
            System.out.println("NUMERO: " + contador + " " + this.cadena[contador]);
        }
        this.textAreaDescription.append(this.cadena[3]);
        this.labelName.setText(this.nameWord);
        this.labelPropietario.setText("Esta palabra ha sido aportada por: " + this.creator);
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

        labelName = new java.awt.Label();
        textAreaDescription = new java.awt.TextArea();
        jButton1 = new javax.swing.JButton();
        labelPropietario = new java.awt.Label();
        labelMultimedia = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelName.setText("label1");

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        labelPropietario.setText("label2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(labelMultimedia, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addComponent(textAreaDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(392, 392, 392)
                        .addComponent(jButton1)
                        .addGap(152, 152, 152)
                        .addComponent(labelPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(labelName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(labelName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(labelMultimedia, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addComponent(labelPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(textAreaDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(46, 46, 46))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(VistaWords_ING.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaWords_ING.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaWords_ING.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaWords_ING.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaWords_ING().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel labelMultimedia;
    private java.awt.Label labelName;
    private java.awt.Label labelPropietario;
    private java.awt.TextArea textAreaDescription;
    // End of variables declaration//GEN-END:variables
}
