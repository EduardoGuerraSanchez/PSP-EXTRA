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
import map.TimeDate;
import protocol.ClientProtocol;
import vistas.VistaLogin;
import vistas.VistaWords;
import vistas.VistaWords_ESP;
import vistas.VistaWords_ING;

public class ThreadClient implements Runnable {

    private final Socket socket;
    private final Thread thread;
    private VistaLogin vistaLogin;
    private PrintWriter out;
    private BufferedReader in;
    private String login;
    private String token;
    private String cadena[];
    private String languaje;
    private String aux[];
    private VistaWords_ESP vistaWords_ESP;
    private VistaWords_ING vistaWords_ING;
    File fich;
    FileOutputStream salida;
    private String nameMultimedia, format;
    private int totalBytes, sizeMultimedia;
    private int contadorFoto = 0;
    private boolean doneMultimedia;
    private TimeDate timedate;
    
    
    
    
    
//    private Socket socket = null;
//    private Thread hebra;
//    private PrintWriter out;
//    private BufferedReader in;
    private ClientProtocol clientProtocol;

    public ThreadClient(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.thread = new Thread(this);
    }

    public Thread getThread() {
        return thread;
    }

    @Override
    public void run() {

        try {
            vistaLogin = new VistaLogin();
            clientProtocol = new ClientProtocol(this.socket,this.vistaLogin);

            String inputLine;
            
            try {
                
                while(true){
                while ((inputLine = in.readLine()) != null) {
                    
                    String[] cadena = inputLine.split("#");
                    
                    System.out.println("THREAD CLIENTE");
                    
                    System.out.println("EL THREAD CLIENTE CONTIENE: " + inputLine);
                    
                    this.clientProtocol.procesInput(inputLine, this.socket);
                }
            }
//                while ((inputLine = in.readLine()) != null) {
//
//                    cadena = inputLine.split("#");
//                    System.out.println("CLIENTE");
//
//                    System.out.println(inputLine);
//
//                    if (cadena.length > 1) {
//                        switch (cadena[1]) {
//                            case "WELLCOME":
//                                System.out.println("ENTRAMOS EN EL WELLCOME");
//                                procesInput(inputLine);
//
//                                this.vistaLogin.setVisible(false);
//
//                                this.out.println("#GET_WORD");
//                                System.out.println("SALIMOS DEL WELLCOME");
//                                break;
//
//                            case "AVAIBLE_WORDS":
//                                System.out.println("ENTRAMOS EN AVAIBLE");
//                                VistaWords vistaWords = new VistaWords(this.socket, inputLine, this.login, this.token);
//
//                                vistaWords.setVisible(true);
//                                System.out.println("SALIMOS DE AVAIBLE");
//                                break;
//
//                            case "GET_SPECIFIC_WORD":
//                                System.out.println("ESTO CONTIENE: " + inputLine);
//                                this.cadena = inputLine.split("#");
//                                this.aux = inputLine.split("#");
//                                this.aux = inputLine.split("@");
//
//                                System.out.println("COMENSEMO A VE");
//                                System.out.println(this.cadena[0]);//PROTOCOLCRISTONARY1.0
//
//                                System.out.println(this.cadena[1]);//GET_SPECIFIC_WORD
//                                System.out.println(this.cadena[2]);//1@arbol@edeuve@Planta perenne, de tronco leñoso y elevado, que se ramifica a cierta altura del suelo.
//
//                                System.out.println(this.cadena[3]);//png
//                                this.format = cadena[3];
//                                System.out.println("A VER QUE CANTIDAD TIENE ESTO: " + this.cadena[4]);
//                                this.sizeMultimedia = Integer.parseInt(this.cadena[4]);
//                                System.out.println(this.cadena[5]);
//                                this.languaje = this.cadena[5];
//
//                                if ("ESP".equals(this.cadena[5])) {
//                                    this.vistaWords_ESP = new VistaWords_ESP(this.socket, this.cadena[2], this.login, this.token, this.cadena[3], Integer.parseInt(this.cadena[4]), this.aux[2], this);
//
//                                    this.vistaWords_ESP.setVisible(true);
//                                }
//
//                                if ("ING".equals(this.cadena[5])) {
//                                    this.vistaWords_ING = new VistaWords_ING(this.socket, this.cadena[2], this.login, this.token, this.cadena[3], Integer.parseInt(this.cadena[4]), this.aux[2], this);
//
//                                    this.vistaWords_ING.setVisible(true);
//                                }
//
//                                break;
//
//                            case "ADIOSXULO":
//                                System.out.println("AQUI NUNCA ENTRAMOS???");
//                                this.out.println("BYE");
//                                break;
//
//                            default:
//                                procesMultimedia(cadena,this.languaje);
//                        }
//                    }
//                }
            } catch (IOException ex) {
                Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);

            } catch (InterruptedException ex) {
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

    public void procesMultimedia(String[] message,String languaje) throws FileNotFoundException, IOException {

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
        
        if("ESP".equals(languaje)){
           if (totalBytes >= sizeMultimedia) {
            System.out.println("----------------------------------------------------------------------------------");
            this.vistaWords_ESP.addMultimedia(fich.getAbsolutePath());
            this.totalBytes = 0;
            this.doneMultimedia = false;
        } 
        }
        
        if("ING".equals(languaje)){
            if (totalBytes >= sizeMultimedia) {
            System.out.println("----------------------------------------------------------------------------------");
            this.vistaWords_ING.addMultimedia(fich.getAbsolutePath());
            this.totalBytes = 0;
            this.doneMultimedia = false;
        }
        }
        
        
    }
}
