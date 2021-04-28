/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cerrojos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor implements Runnable {

    public Thread thr;
    private int contador;
    private Buffer buffer;

    public Consumidor(String nombreHebra, Buffer buffer, int contador) {
        thr = new Thread(this, nombreHebra);
        this.buffer = buffer;
        this.contador = contador;
    }

    @Override
    public void run() {

        if (this.contador == 0) {
            try {
                this.buffer.consumir();
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (this.contador == 1) {
            try {
                while (true) {
                    this.buffer.consumir();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}