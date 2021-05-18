package kk;

import java.net.*;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KKMultiThread extends Thread {

    private Socket socket = null;
    
    private final String GREEN = "\033[32m";
    private final String RESTORE = "\u001B[0m";

    public KKMultiThread(Socket socket) {
        super("KKMultiServerThread");
        this.socket = socket;
    }

    public void run() {

        //Flujo de salida de texto out
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                //Flujo de entrada de texto in
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            String inputLine, outputLine, cadena;
            KKProtocol kkp = new KKProtocol();
            //Procesa lo que le han pasado a traves del protocolo para verificar que esta correcto

//            Scanner sc = new Scanner(System.in);
//
//            cadena = sc.next();
//
//            outputLine = kkp.processInput(cadena);
            //A traves del flujo de salida out y se lo pasa al cliente
//            outputLine = "hola";
//            out.println(outputLine);
            while ((inputLine = in.readLine()) != null) {
                
                System.out.println("El usuario ma decio: " + inputLine);

                outputLine = kkp.processInput(inputLine);
                out.println(GREEN + outputLine + RESTORE);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(KKMultiThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
