package cerrojos;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private char palabras[];
    private Random random;
    private char nuevaLetra;
    private int bufferCircular;
    private int i;
    private Lock lock = new ReentrantLock();
    private Condition condicionProductor;
    private Condition condicionConsumidor;

    private final String RED = "\033[31m";
    private final String GREEN = "\033[32m";
    private final String RESTORE = "\u001B[0m";

    public Buffer(int tamanioBuffer) {

        palabras = new char[tamanioBuffer];

        bufferCircular = 0;

        condicionConsumidor = lock.newCondition();
        condicionProductor = lock.newCondition();

        for (int contador = 0; contador < palabras.length; contador++) {
            palabras[contador] = ' ';
        }

        random = new Random();

    }

    public void producir() throws InterruptedException {

        lock.lock();
        while (i >= palabras.length) {
            condicionProductor.await();
        }
        
        i = i + 1;

        int posicionLibre = 0;
        boolean salir = false;

        for (int contador = bufferCircular; contador < palabras.length && salir == false; contador++) {
            if (palabras[contador] == ' ') {
                posicionLibre = contador;
                salir = true;
            }
        }

        if (bufferCircular == (palabras.length - 1)) {

            bufferCircular = 0;
        } else {

            bufferCircular = bufferCircular + 1;
        }

        

        nuevaLetra = (char) (random.nextInt(5) + 'a');

        palabras[posicionLibre] = nuevaLetra;

        System.out.println("La hebra esta produciendo en la posicion: " + posicionLibre + " del buffer");

        for (int contador = 0; contador < palabras.length; contador++) {
            if (contador == posicionLibre) {
                System.out.print(GREEN + "[" + palabras[contador] + "]" + RESTORE);
            } else {
                System.out.print("[" + palabras[contador] + "]");
            }
        }
        System.out.println("");

        condicionConsumidor.signalAll();
        lock.unlock();

    }

    public void consumir() throws InterruptedException {

        lock.lock();
        while (i == 0) {
            condicionConsumidor.await();
        }
        
        i = i - 1;

        int posicionOcupada = 0;
        boolean salir = false;

        for (int contador = 0; contador < palabras.length && !salir; contador++) {
            if (palabras[contador] != ' ') {
                posicionOcupada = contador;
                salir = true;
            }
        }

        System.out.println("La hebra esta consumiendo en la posicion: " + posicionOcupada + " del buffer");

        for (int contador = 0; contador < palabras.length; contador++) {
            if (contador == posicionOcupada) {
                System.out.print(RED + "[" + palabras[contador] + "]" + RESTORE);
            } else {
                System.out.print("[" + palabras[contador] + "]");
            }
        }
        System.out.println("");

        switch (palabras[posicionOcupada]) {
            case 'a':

                System.out.println(GREEN + "TU PALABRA ES ARBOL" + RESTORE);

                break;

            case 'b':

                System.out.println(GREEN + "TU PALABRA ES BARCO" + RESTORE);

                break;

            case 'c':

                System.out.println(GREEN + "TU PALABRA ES CASCO" + RESTORE);

                break;

            case 'd':

                System.out.println(GREEN + "TU PALABRA ES DARDO" + RESTORE);

                break;

            case 'e':

                System.out.println(GREEN + "TU PALABRA ES ERIZO" + RESTORE);

                break;
        }
        palabras[posicionOcupada] = ' ';

        condicionProductor.signalAll();
        lock.unlock();

    }
}
