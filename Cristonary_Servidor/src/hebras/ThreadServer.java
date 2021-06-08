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
import protocolo.Protocol;

public class ThreadServer implements Runnable {

    private Socket socket;
    private Thread thread;
    private String name;
    private Protocol protocol;
    private ArrayList<ThreadServer> arrayThread;
    private int idThread;
    private PrintWriter out;
    private BufferedReader in;

    public ThreadServer(Socket socket, int idThread, ArrayList<ThreadServer> hebras) throws IOException, SQLException {
        this.socket = socket;
        thread = new Thread(this);
        this.idThread = idThread;
        this.arrayThread = hebras;
        this.protocol = new Protocol(this.socket, this.arrayThread);
    }

    public ThreadServer(Socket socket, String name, ArrayList array) {
        this.socket = socket;
        this.name = name;
        this.thread = new Thread(this, name);
        this.arrayThread = array;
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

    public ArrayList getArrayThread() {
        return arrayThread;
    }

    public void setArrayThread(ArrayList arrayThread) {
        this.arrayThread = arrayThread;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }
    
    @Override
    public void run() {

        try {
            String inputLine, outputLine;
            this.out = new PrintWriter(this.socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            while ((inputLine = in.readLine()) != null) {

                System.out.println("SERVIDOR");

                System.out.println("EL SERVIDOR VA A LEER: " + inputLine);

                outputLine = protocol.processInput(inputLine);
                
                System.out.println("EL SERVIDOR HA PROCESADO: " + outputLine);

                System.out.println("LLEVAMOS UN TOTAL DE: " + this.getArrayThread().size() + " USUARIOS DENTRO");

                this.out.println(outputLine);
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
