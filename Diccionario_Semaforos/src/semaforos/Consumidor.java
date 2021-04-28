package semaforos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor implements Runnable {

    public Thread thread;
    private int contador;
    private Buffer buffer;
    private int numeroHebra;

    public Consumidor(String nombreHebra, Buffer buffer, int contador, int numero) {
        thread = new Thread(this, nombreHebra);
        this.buffer = buffer;
        this.contador = contador;
        this.numeroHebra = numero;
    }

    @Override
    public void run() {

        if (this.contador == 0) {
            try {
                this.buffer.consumir();
                System.out.println("LA HEBRA CONSUMIDORA NUMERO: " + this.numeroHebra);

            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (this.contador == 1) {
            try {
                while (true) {
                   // Thread.sleep(10000);

                    //System.out.println("LA HEBRA CONSUMIDORA NUMERO: " + this.numeroHebra);

                    this.buffer.consumir();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
