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

    private final Socket socket;
    private final Thread thread;
    private String name;
    private ArrayList arrayWords;
    private VistaLogin vistaLogin;
    private PrintWriter out;
    private BufferedReader in;

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
            arrayWords = new ArrayList();
            //CON EL OUT MANDO A ESCRIBIR
            this.out = new PrintWriter(this.socket.getOutputStream(), true);
            //CON EL IN LEO LO QUE ME HAN MANDADO
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            try {
                while ((inputLine = in.readLine()) != null) {

                    System.out.println("CLIENTE");

                    if (inputLine.contains("#WELLCOME")) {
                        this.vistaLogin.setVisible(false);
                        
                        this.out.println("#GET_WORD");
                    }
                    
                    if(inputLine.contains("#AVAIBLE")){
                        VistaWords vistaWords = new VistaWords(this.socket,inputLine);

                        vistaWords.setVisible(true);
                    }
                    
                    System.out.println("SI VES ESTO ES PORQUE NO HA ENTRADO EN NINGUN IF: " + inputLine);
//                    thread.wait();
//                    this.thread.join();

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
