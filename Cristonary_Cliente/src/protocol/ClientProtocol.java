package protocol;

import hebras.UpdateTable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Timer;
import map.TimeDate;
import vistas.VistaLogin;
import vistas.VistaWords;
import vistas.VistaWords_ESP;
import vistas.VistaWords_ING;

public class ClientProtocol {

    private Socket socket;
    private Thread thread;
    private VistaLogin vistaLogin;
    private PrintWriter out;
    private BufferedReader in;
    private String login;
    private String token;
    private String cadena[];
    private String languaje;
    private String aux[];
    private String[] wordsESP;
    private String[] wordsING;
    private VistaWords_ESP vistaWords_ESP;
    private VistaWords_ING vistaWords_ING;
    private File fich;
    private ArrayList<String> fragments;
    private FileOutputStream salida;
    private String nameMultimedia, format;
    private int totalBytes, sizeMultimedia;
    private boolean doneMultimedia;
    private TimeDate timedate;
    private Timer timer;
    private int totalESP, totalING;
    private VistaWords vistaWords;

    public ClientProtocol(Socket socket, VistaLogin vistaLogin) throws FileNotFoundException, IOException {
        this.socket = socket;
        this.vistaLogin = vistaLogin;
        this.totalBytes = 0;
        this.fragments = new ArrayList<String>();
        this.timedate = new TimeDate(this.socket);
        this.timer = new Timer();
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void procesInput(String messaje, Socket socket) throws IOException, InterruptedException {
        this.cadena = messaje.split("#");

        for (int contador = 0; contador < cadena.length; contador++) {
            System.out.println("MENSAJE -->: " + this.cadena[contador]);
        }

        if (cadena.length > 1) {
            switch (this.cadena[1]) {
                case "WELLCOME":
                    System.out.println("ENTRAMOS EN EL WELLCOME");
                    loginKey(messaje);
//                    this.vistaLogin.setVisible(false);
                    this.vistaWords = new VistaWords(this.socket, this.cadena, this.login, this.token);
                    this.vistaWords.setVisible(true);
//                    this.out.println("#GET_WORD");
                    System.out.println("SALIMOS DEL WELLCOME");
                    break;

                case "AVAIBLE_WORDS":
                    System.out.println("ENTRAMOS EN AVAIBLE");
                    for (int contador = 0; contador < this.cadena.length; contador++) {
                        System.out.println("A VER QUE TAL QUEDA: " + this.cadena[contador]);
                    }
                    totalWords_ESP_ING();
                    System.out.println("EL ESPAÑOL: " + totalESP);
                    System.out.println("ingles: " + totalING);
                    transforWordsESP(this.cadena);
                    transforWordsING(this.cadena);
                    for (int contador = 0; contador < this.wordsESP.length; contador++) {
                        System.out.println("LAS PALABRAS EN ESPAÑOL: " + this.wordsESP[contador]);
                    }
                    for (int contador = 0; contador < this.wordsING.length; contador++) {
                        System.out.println("LAS PALABRAS EN INGLES: " + this.wordsING[contador]);
                    }
//                    update();
                    UpdateTable updateTable = new UpdateTable(this.socket, this.wordsESP, this.wordsING, this.vistaWords);
                    updateTable.thread.start();
//                    this.totalESP = vistaWords.totalESP;
//                    this.totalING = vistaWords.totalING;
//                    System.out.println("QUE TAMAÑOS TIENEN: " + totalESP + " Y TAMBIEN: " + totalING);
                    System.out.println("SALIMOS DE AVAIBLE");
                    break;

                case "GET_SPECIFIC_WORD":
                    System.out.println("ESTO CONTIENE: " + messaje);
                    this.cadena = messaje.split("#");
                    this.aux = messaje.split("#");
                    this.aux = messaje.split("@");

                    System.out.println("COMENSEMO A VE");
                    System.out.println(this.cadena[0]);//PROTOCOLCRISTONARY1.0

                    System.out.println(this.cadena[1]);//GET_SPECIFIC_WORD
                    System.out.println(this.cadena[2]);//1@arbol@edeuve@Planta perenne, de tronco leñoso y elevado, que se ramifica a cierta altura del suelo.

                    System.out.println(this.cadena[3]);//png
                    this.format = cadena[3];
                    System.out.println("A VER QUE CANTIDAD TIENE ESTO: " + this.cadena[4]);
                    this.sizeMultimedia = Integer.parseInt(this.cadena[4]);
                    System.out.println(this.cadena[5]);
                    this.languaje = this.cadena[5];

                    if ("ESP".equals(this.cadena[5])) {
                        this.vistaWords_ESP = new VistaWords_ESP(this.socket, this.cadena[2], this.login, this.token, this.cadena[3], Integer.parseInt(this.cadena[4]), this.aux[2], this);

                        this.vistaWords_ESP.setVisible(true);
                    }

                    if ("ING".equals(this.cadena[5])) {
                        this.vistaWords_ING = new VistaWords_ING(this.socket, this.cadena[2], this.login, this.token, this.cadena[3], Integer.parseInt(this.cadena[4]), this.aux[2], this);

                        this.vistaWords_ING.setVisible(true);
                    }

                    break;

                case "REFRESH_WORDS":
                    System.out.println("AMOS A RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRREFRESCAR");
                    
                    for(int contador = 0;contador < this.cadena.length;contador++){
                        System.out.println(this.cadena[contador]);
                    }
                    
                    transforWordsESP(this.cadena);
                    transforWordsING(this.cadena);
                    
                    for (int contador = 0; contador < this.wordsESP.length; contador++) {
                        System.out.println("ESPAÑOL-->: " + this.wordsESP[contador]);
                    }
                    for (int contador = 0; contador < this.wordsING.length; contador++) {
                        System.out.println("INGLES-->: " + this.wordsING[contador]);
                    }
                    UpdateTable updateTable2 = new UpdateTable(this.socket,this.wordsESP,this.wordsING,this.vistaWords);
                    updateTable2.thread.start();
                    System.out.println("CONTINUAMOS...");
                    break;
                    
                case "WORD_CREATED_ESP":
                    System.out.println("NOS DISPONEMOS A CREAR UNA PALABRA...");
                    this.totalESP++;
                    break;
                    
                case "WORD_CREATED_ING":
                    System.out.println("AHORA IN INGLISHHHH");
                    this.totalING++;
                    break;
                    
                case "ADIOSXULO":
                    System.out.println("AQUI NUNCA ENTRAMOS???");
                    this.out.println("BYE");
                    break;

                default:
                    System.out.println("QUE TIENE ESTO COMO PARA QUE ENTRES::::::::::::::::::::::::::::::::::::::::::::::::::::: " + this.cadena[1]);
                    procesMultimedia(cadena, this.languaje);
                    break;
            }
        }
    }

    public void loginKey(String message) {

        this.login = cadena[2];
        this.token = cadena[4];

    }

    public void procesMultimedia(String[] message, String languaje) throws FileNotFoundException, IOException {

        System.out.println(nameMultimedia + format);

        if (this.doneMultimedia == false) {
            this.fich = new File(this.nameMultimedia + this.format);
            this.salida = new FileOutputStream(this.fich);
            this.doneMultimedia = true;
        }

        byte[] fragment = Base64.getDecoder().decode(this.cadena[3]);
        this.salida.write(fragment);

        this.totalBytes += 512;
        System.out.println(sizeMultimedia);
        System.out.println(totalBytes);

        if ("ESP".equals(languaje)) {
            System.out.println("LA PALABRA ES EN ESPAÑOL");
            if (totalBytes >= sizeMultimedia) {
                System.out.println("----------------------------------------------------------------------------------");
                this.vistaWords_ESP.addMultimedia(fich.getAbsolutePath());
                this.totalBytes = 0;
                this.doneMultimedia = false;
            }
        }

        if ("ING".equals(languaje)) {
            System.out.println("LA PALABRA ES EN INGLES");
            if (totalBytes >= sizeMultimedia) {
                System.out.println("----------------------------------------------------------------------------------");
                this.vistaWords_ING.addMultimedia(fich.getAbsolutePath());
                this.totalBytes = 0;
                this.doneMultimedia = false;
            }
        }
    }

    public String getNameMultimedia() {
        return nameMultimedia;
    }

    public void setNameMultimedia(String nameMultimedia) {
        this.nameMultimedia = nameMultimedia;
    }

    public void update() {
        int tiempo = 10;
        this.timer.scheduleAtFixedRate(timedate, 0, 1000 * tiempo);
    }

    public void totalWords_ESP_ING() {
        boolean español = false;
        
        System.out.println("¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡CUANTO TIENE ESTO EN TOTAL: " + this.cadena.length);
        
        for (int contador = 3; contador < this.cadena.length; contador++) {

            if (español == false) {
                if ("ESP".equals(this.cadena[contador])) {
                    System.out.println("IF ESPAÑOL");
                    español = true;
                } else {
                    System.out.println("ELSE ESPAÑOL");
                    this.totalESP++;
                }
            }

            if (español == true) {
                if ("ING".equals(this.cadena[contador]) || "ESP".equals(this.cadena[contador])) {
                    System.out.println("IF ING");
                } else {
                    System.out.println("ELSE ING");
                    this.totalING++;
                }
            }
        }
    }

    public void transforWordsESP(String[] words) {

        this.wordsESP = new String[this.totalESP];
        System.out.println("ALO POLICHIA_ESP");
        System.out.println(this.totalESP);
        int j = 0;
        boolean done = false;
        for (int i = 3; i < words.length && done == false; i++) {
            if ("ESP".equals(words[i])) {
                done = true;
            } else {
                System.out.println("ESTO POR UN LADO; " + words[i]);
                this.wordsESP[j] = words[i];
                j++;
            }
        }
    }

    public void transforWordsING(String[] words) {

        this.wordsING = new String[this.totalING];
        System.out.println("ALO POLICHIA_ING");
        int j = 0;
        boolean done = false;
        boolean english = false;
        for (int i = 3; i < words.length && done == false; i++) {
            if ("ING".equals(words[i])) {
                done = true;

            } else {
                if (english == true) {
                    this.wordsING[j] = words[i];
                    j++;
                }
                if ("ESP".equals(words[i])) {
                    english = true;
                }
            }
        }
    }

    public ArrayList<String> getFragments() {
        return fragments;
    }

    public void setFragments(ArrayList<String> fragments) {
        this.fragments = fragments;
    }
}