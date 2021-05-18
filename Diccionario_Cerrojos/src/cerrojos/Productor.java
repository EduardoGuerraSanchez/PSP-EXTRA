package cerrojos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Productor implements Runnable {

    public Thread thr;
    private int contador;
    private Buffer buffer;
    private int numeroHebra;


    public Productor(String nombreHebra, Buffer buffer, int contador,int numero) {
        thr = new Thread(this, nombreHebra);
        this.buffer = buffer;
        this.contador = contador;
        this.numeroHebra = numero;

    }

    @Override
    public void run() {

        if (this.contador == 0) {
            try {
                System.out.println("LA HEBRA PRODUCTORA NUMERO: " + this.numeroHebra);
                this.buffer.producir();
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (this.contador == 1) {
            try {
                while (true) {
                    System.out.println("LA HEBRA PRODUCTORA NUMERO: " + this.numeroHebra);
                    this.buffer.producir();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
