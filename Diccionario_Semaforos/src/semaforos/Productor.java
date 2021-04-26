package semaforos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Productor implements Runnable {

    public Thread thread;
    private int contador;
    private Buffer buffer;

    public Productor(String nombreHebra, Buffer buffer, int contador) {
        thread = new Thread(this, nombreHebra);
        this.buffer = buffer;
        this.contador = contador;
    }

    @Override
    public void run() {

        if (this.contador == 0) {
            try {
                this.buffer.producir();
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (this.contador == 1) {
            try {
                while (true) {
                    this.buffer.producir();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
}
