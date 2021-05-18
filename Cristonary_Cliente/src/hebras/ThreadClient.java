package hebras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import vistas.VistaLogin;
import vistas.VistaWords;

public class ThreadClient implements Runnable {

    private Socket socket;
    private Thread thread;
    private String name;
    private ArrayList arrayWords;
    private VistaWords vistaWords;
    private VistaLogin vistaLogin;

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
            vistaWords = new VistaWords(this.socket);
            vistaLogin = new VistaLogin();
            String inputLine = null;
            String outputLine = null;
            String aux = null;
            arrayWords = new ArrayList();
            int contador = 0;
            boolean entrado = false;
            //CON EL OUT MANDO A ESCRIBIR
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            //CON EL IN LEO LO QUE ME HAN MANDADO
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String a = null;
            try {
                while ((inputLine = in.readLine()) != null) {

                    System.out.println("CLIENTE");

                    if (inputLine.contains("#WELLCOME")) {
                        vistaLogin.setVisible(false);

                        vistaWords.setVisible(true);

                        this.vistaWords.getThread().start();

//                        a = vistaWords.boton();
//                        
//                        out.println(a);
                    }

                    System.out.println(inputLine);

                }
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);

            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
