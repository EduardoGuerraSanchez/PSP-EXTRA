package protocol;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientProtocol implements Runnable{
    
    private Socket socket;
    private Thread hebra;
    private PrintWriter out;
    private BufferedReader in;

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
