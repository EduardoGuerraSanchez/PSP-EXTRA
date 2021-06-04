package hebras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Word;
import modelo.Word_ESP;
import protocolo.Protocol;

public class ThreadServer implements Runnable {

    private Socket socket;
    public Thread thread;
    private String name;
    private Protocol protocol;

    public ThreadServer(Socket socket) {
        this.socket = socket;
    }

    public ThreadServer(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
        this.thread = new Thread(this, name);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {

        try {
            String inputLine, outputLine;

            protocol = new Protocol(this.socket);
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            ArrayList<Word_ESP> array = new ArrayList<Word_ESP>();

            while ((inputLine = in.readLine()) != null) {

                System.out.println("SERVIDOR");
                
                System.out.println("EL SERVIDOR VA A LEER: " + inputLine);

                outputLine = protocol.processInput(inputLine);
                
                System.out.println("EL SERVIDOR HA PROCESADO: " + outputLine);
                
                out.println(outputLine);
            }
            System.out.println("FINALIZAMOS LA HEBRA DEL SERVIDOR");
            this.socket.close();

        } catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}