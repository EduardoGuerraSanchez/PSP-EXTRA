
package cerrojos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor implements Runnable {

    public Thread thr;
    private int contador;
    private Buffer buffer;
    private int numeroHebra;


    public Consumidor(String nombreHebra, Buffer buffer, int contador,int numero) {
        thr = new Thread(this, nombreHebra);
        this.buffer = buffer;
        this.contador = contador;
        this.numeroHebra = numero;
    }

    @Override
    public void run() {

        if (this.contador == 0) {
            try {
                System.out.println("LA HEBRA CONSUMIDORA NUMERO: " + this.numeroHebra);
                this.buffer.consumir();
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (this.contador == 1) {
            try {
                while (true) {
                    System.out.println("LA HEBRA CONSUMIDORA NUMERO: " + this.numeroHebra);
                    this.buffer.consumir();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}