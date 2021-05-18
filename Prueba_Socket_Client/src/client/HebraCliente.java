package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduar
 */
public class HebraCliente implements Runnable {

    private Socket socket;
    private Thread hebra;
    
    private final String RED = "\033[31m";
    private final String RESTORE = "\u001B[0m";

    public HebraCliente(Socket socket) throws IOException {
        this.socket = socket;
        this.hebra = new Thread(this);
    }

    public Thread getHebra() {
        return this.hebra;
    }

    @Override
    public void run() {

        try {
            String inputLine = null;

            String outputLine = null;

            String cadena = null;

            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);

            //Creo un flujo de entrada
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));


//            Scanner sc = new Scanner(System.in);
//
//            cadena = sc.next();
//
//            out.println(cadena);
//
            try {
                while ((inputLine = in.readLine()) != null) {

                    System.out.println("Lo que ma decio el server: " + inputLine);

                    Scanner sc = new Scanner(System.in);

                    cadena = sc.next();

                    out.println(RED + cadena + RESTORE);
                    
                }
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(HebraCliente.class.getName()).log(Level.SEVERE, null, ex);

            }
        } catch (IOException ex) {
            Logger.getLogger(HebraCliente.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
