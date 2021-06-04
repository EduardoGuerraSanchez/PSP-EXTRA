package hebras;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadMultimedia implements Runnable{

    Thread thread;
    Socket socket;
    File fich;
    ArrayList<String> fragments;
    int totalBytes;
    String name;
    
    public ThreadMultimedia(Socket socket,String format, int byts){
        thread = new Thread(this);
        this.socket = socket;
        this.name = format;
        this.totalBytes = byts;
    }
    
    
    @Override
    public void run() {
        
        FileOutputStream salida = null;
        
        try{
            
            int paquetesEscritos = 0;
            byte[] bytesObtenidos;
            salida = new FileOutputStream(fich);
            int i = 0;
            boolean terminado = false;
            
            for(paquetesEscritos = 0; paquetesEscritos < fragments.size();paquetesEscritos++){
                
                bytesObtenidos = new byte[512];
                bytesObtenidos = Base64.getDecoder().decode(fragments.get(paquetesEscritos));
                salida.write(bytesObtenidos);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ThreadMultimedia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ThreadMultimedia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
}
