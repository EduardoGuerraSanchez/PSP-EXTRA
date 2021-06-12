package hebras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import protocol.ClientProtocol;
import vistas.VistaLogin;

public class ThreadClient implements Runnable {

    private final Socket socket;
    private final Thread thread;
    private VistaLogin vistaLogin;
//    private PrintWriter out;
    private BufferedReader in;
    private ClientProtocol clientProtocol;

    public ThreadClient(Socket socket) throws IOException {
        this.socket = socket;
//        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.thread = new Thread(this);
        this.vistaLogin = new VistaLogin();

    }

    public Thread getThread() {
        return thread;
    }

    @Override
    public void run() {

        try {
            this.clientProtocol = new ClientProtocol(this.socket, this.vistaLogin);

            String inputLine;

            try {

                while (true) {
                    while ((inputLine = in.readLine()) != null) {

                        String[] cadena = inputLine.split("#");

                        System.out.println("THREAD CLIENTE");

                        System.out.println("EL THREAD CLIENTE CONTIENE: " + inputLine);

                        this.clientProtocol.procesInput(inputLine, this.socket);
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);

            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
