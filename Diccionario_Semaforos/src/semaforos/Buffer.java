package semaforos;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Buffer {

    private char arrayBuffer[];
    private char palabras[];
    private ArrayList array;
    private char arrayPalabras[];
    private String letraAleatoria;
    private Random random;
    private char nuevaLetra;
    private int i;
    private int totalLetras;
    private int turno = 1;
    private Semaphore mutex, semProductor, semConsumidor;

    private final String RED = "\033[31m";
    private final String GREEN = "\033[32m";
    private final String RESTORE = "\u001B[0m";

    public Buffer(int tamanioBuffer) {

        arrayBuffer = new char[tamanioBuffer];
        palabras = new char[tamanioBuffer];

        arrayPalabras = new char[tamanioBuffer];

        array = new ArrayList();

//        arrayPalabras[0] = 'a';
//        arrayPalabras[1] = 'm';
//        arrayPalabras[2] = 'i';
//        arrayPalabras[3] = 'g';
//        arrayPalabras[4] = 'o';
        letraAleatoria = "abcde";

        random = new Random();

        mutex = new Semaphore(1);
        semProductor = new Semaphore(tamanioBuffer);
        semConsumidor = new Semaphore(1);

        for (int contador = 0; contador < tamanioBuffer; contador++) {

            arrayBuffer[contador] = 0;

        }
    }

    public void introducirLetras() {

        boolean salir = false;
        Scanner sc = new Scanner(System.in);

        for (int contador = 0; contador < palabras.length && salir == false; contador++) {
            arrayPalabras[contador] = sc.next().charAt(0);
            if (arrayPalabras[contador] == '-') {
                salir = true;
                arrayBuffer[contador] = ' ';
            }
        }
    }

    public void producir() throws InterruptedException {

        this.semProductor.acquire();
        this.mutex.acquire();

        int posicionLibre = 0;

        if (turno == 1) {
            nuevaLetra = (char) (random.nextInt(5) + 'a');
            turno = 0;
        }

        palabras[0] = nuevaLetra;

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

        switch (palabras[0]) {
            case 'a':

                if (i == 0) {
                    palabras[1] = 'm';

                }
                if (i == 1) {
                    palabras[2] = 'i';

                }
                if (i == 2) {
                    palabras[3] = 'g';

                }
                if (i == 3) {
                    palabras[4] = 'o';

                }
                totalLetras = 5;
                i++;

                break;

            case 'b':

                if (i == 0) {
                    palabras[1] = 'a';

                }
                if (i == 1) {
                    palabras[2] = 'r';

                }
                if (i == 2) {
                    palabras[3] = 'c';

                }
                if (i == 3) {
                    palabras[4] = 'o';

                }
                totalLetras = 5;

                i++;

                break;

            case 'c':

                if (i == 0) {
                    palabras[1] = 'a';

                }
                if (i == 1) {
                    palabras[2] = 's';

                }
                if (i == 2) {
                    palabras[3] = 'c';

                }
                if (i == 3) {
                    palabras[4] = 'o';

                }
                totalLetras = 5;

                i++;

                break;

            case 'd':
                if (i == 0) {
                    palabras[1] = 'a';

                }
                if (i == 1) {
                    palabras[2] = 'r';

                }
                if (i == 2) {
                    palabras[3] = 'd';

                }
                if (i == 3) {
                    palabras[4] = 'o';

                }
                totalLetras = 5;

                i++;

                break;

            case 'e':

                if (i == 0) {
                    palabras[1] = 'r';

                }
                if (i == 1) {
                    palabras[2] = 'i';

                }
                if (i == 2) {
                    palabras[3] = 'z';

                }
                if (i == 3) {
                    palabras[4] = 'o';

                }
                totalLetras = 5;

                i++;

                break;
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

        if (i == totalLetras) {//Limpiar el buffer
            for (int contador = 0; contador < palabras.length; contador++) {
                palabras[contador] = ' ';
                arrayPalabras[contador] = ' ';
            }
            i = 0;
            turno = 1;
        }

        this.mutex.release();
        this.semProductor.release();

    }
}
