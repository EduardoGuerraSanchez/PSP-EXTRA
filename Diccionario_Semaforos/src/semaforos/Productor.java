package semaforos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Productor implements Runnable {

    public Thread thread;
    private int contador;
    private Buffer buffer;
    private int numeroHebra;

    public Productor(String nombreHebra, Buffer buffer, int contador, int numero) {
        thread = new Thread(this, nombreHebra);
        this.buffer = buffer;
        this.contador = contador;
        this.numeroHebra = numero;
        this.numeroHebra++;
    }

    public int getContador() {
        return contador;
    }

    @Override
    public void run() {

        if (this.contador == 0) {
            try {
                this.buffer.producir();
                System.out.println("LA HEBRA PRODUCTORA NUMERO: " + this.numeroHebra);
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (this.contador == 1) {
            try {
                while (true) {
                   // Thread.sleep(10000);
                    //System.out.println("LA HEBRA PRODUCTORA NUMERO: " + this.numeroHebra);
                    this.buffer.producir();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
