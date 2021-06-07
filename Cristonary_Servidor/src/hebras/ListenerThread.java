package hebras;

import hebras.ThreadServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import vista.VistaServer;

public class ListenerThread implements Runnable {

    ServerSocket servidor;
    int idHebra;
    boolean listening;
    VistaServer vista;
    ArrayList<ThreadServer> hebras;
    public Thread listener;

    public ListenerThread(ServerSocket servidor, boolean listening, VistaServer vista, ArrayList<ThreadServer> hebras) {
        this.servidor = servidor;
        this.listening = listening;
        this.vista = vista;
        this.hebras = hebras;
        this.listener = new Thread(this);
    }

    @Override
    public void run() {

        while (listening) {
            try {
                ThreadServer threadServer = new ThreadServer(servidor.accept(), idHebra, hebras);
                idHebra++;
                hebras.add(threadServer);
                System.out.println("ESTO SON: " + hebras.size());
                hebras.get(hebras.size() - 1).getThread().start();
                System.out.println("AQUI NO LLEGAMOS");
            } catch (IOException ex) {
                Logger.getLogger(ListenerThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ListenerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    public void closeServer(){
        this.listening = false;
    }

}
