package semaforos;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Buffer {

    private char palabras[];
    private Random random;
    private char nuevaLetra;
    private Semaphore mutex, semProductor, semConsumidor;
    private int bufferCircular;

    private final String RED = "\033[31m";
    private final String GREEN = "\033[32m";
    private final String RESTORE = "\u001B[0m";

    public Buffer(int tamanioBuffer) {

        palabras = new char[tamanioBuffer];
        
        bufferCircular = 0;


        for(int contador = 0;contador < palabras.length;contador++){
            palabras[contador] = ' ';
        }


        random = new Random();

        mutex = new Semaphore(1);
        semProductor = new Semaphore(tamanioBuffer);
        semConsumidor = new Semaphore(1);

    }

    public void producir() throws InterruptedException {

        this.semProductor.acquire();
        this.mutex.acquire();

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

        this.mutex.release();
        this.semConsumidor.release();

    }

    public void consumir() throws InterruptedException {

        semConsumidor.acquire();
        mutex.acquire();

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

        this.mutex.release();
        this.semProductor.release();

    }
}
