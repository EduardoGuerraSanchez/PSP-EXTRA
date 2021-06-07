package hebras;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import vistas.VistaLogin;
import vistas.VistaWords;
import vistas.VistaWords_ESP;

public class ThreadClient implements Runnable {

    private final Socket socket;
    private final Thread thread;
    private VistaLogin vistaLogin;
    private PrintWriter out;
    private BufferedReader in;
    private String login;
    private String token;
    private String cadena[];
    private VistaWords_ESP vistaWords_ESP;
    File fich;
    FileOutputStream salida;
    private String nameMultimedia, format;
    private int totalBytes, sizeMultimedia;
    private int contadorFoto = 0;
    private boolean doneMultimedia;

    public ThreadClient(Socket socket) throws IOException {
        this.socket = socket;
        this.thread = new Thread(this);
    }

    public Thread getThread() {
        return thread;
    }

    @Override
    public void run() {

        try {
            vistaLogin = new VistaLogin();

            String inputLine = null;
            String outputLine = null;

            //CON EL OUT MANDO A ESCRIBIR
            this.out = new PrintWriter(this.socket.getOutputStream(), true);
            //CON EL IN LEO LO QUE ME HAN MANDADO
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            try {
                while ((inputLine = in.readLine()) != null) {

                    cadena = inputLine.split("#");
                    System.out.println("CLIENTE");

                    System.out.println(inputLine);

                    if (cadena.length > 1) {
                        switch (cadena[1]) {
                            case "WELLCOME":
                                System.out.println("ENTRAMOS EN EL WELLCOME");
                                procesInput(inputLine);

                                this.vistaLogin.setVisible(false);

                                this.out.println("#GET_WORD");
                                System.out.println("SALIMOS DEL WELLCOME");
                                break;

                            case "AVAIBLE_WORDS":
                                System.out.println("ENTRAMOS EN AVAIBLE");
                                VistaWords vistaWords = new VistaWords(this.socket, inputLine, this.login, this.token);

                                vistaWords.setVisible(true);
                                System.out.println("SALIMOS DE AVAIBLE");
                                break;

                            case "GET_SPECIFIC_WORD":
                                System.out.println("ESTO CONTIENE: " + inputLine);
                                this.cadena = inputLine.split("#");
//                            this.cadena = inputLine.split("@");

                                System.out.println("COMENSEMO A VE");
                                System.out.println(this.cadena[0]);//PROTOCOLCRISTONARY1.0

                                System.out.println(this.cadena[1]);//GET_SPECIFIC_WORD
                                System.out.println(this.cadena[2]);//1@arbol@edeuve@Planta perenne, de tronco leñoso y elevado, que se ramifica a cierta altura del suelo.

                                System.out.println(this.cadena[3]);//png
                                this.format = cadena[3];
                                System.out.println("A VER QUE CANTIDAD TIENE ESTO: " + this.cadena[4]);//0
                                this.sizeMultimedia = Integer.parseInt(this.cadena[4]);

                                this.vistaWords_ESP = new VistaWords_ESP(this.socket, this.cadena[2], this.login, this.token, this.cadena[3], Integer.parseInt(this.cadena[4]), this);

                                this.vistaWords_ESP.setVisible(true);
                                break;
                                
                            case "ADIOSXULO":
                                System.out.println("AQUI NUNCA ENTRAMOS???");
                                this.out.println("BYE");
                                break;

                            default:
                                procesMultimedia(cadena);
                        }
                    }
                }
                this.socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);

            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public String getNameMultimedia() {
        return nameMultimedia;
    }

    public void setNameMultimedia(String nameMultimedia) {
        this.nameMultimedia = nameMultimedia;
    }

    public void procesInput(String message) {

        this.cadena = message.split("#");

        this.login = cadena[2];
        this.token = cadena[4];

    }

    public void procesMultimedia(String[] message) throws FileNotFoundException, IOException {

        System.out.println(nameMultimedia + format);

        if (this.doneMultimedia == false) {
            fich = new File(nameMultimedia + format);
            salida = new FileOutputStream(fich);
            this.doneMultimedia = true;
        }

        byte[] fragment = Base64.getDecoder().decode(this.cadena[3]);
        salida.write(fragment);

        this.totalBytes += 512;
        System.out.println(sizeMultimedia);
        System.out.println(totalBytes);
        if (totalBytes >= sizeMultimedia) {
            System.out.println("----------------------------------------------------------------------------------");
            this.vistaWords_ESP.addMultimedia(fich.getAbsolutePath());
            this.totalBytes = 0;
            this.doneMultimedia = false;
        }
    }
}
