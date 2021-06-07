package controller;

import java.util.ArrayList;
import map.Word_ESP;

public class VistaWordsController {

    private final String mensaje;
    private ArrayList<Word_ESP> arrayWord_esp;
    private final ArrayList arrayAux;

    public VistaWordsController(String mensaje, ArrayList aux) {
        this.mensaje = mensaje;
        this.arrayAux = aux;
    }

    public String[] transformWords(String[] words, int nWords) {

        String[] array = new String[nWords];
        int i = 0;
        for (int contador = 3; contador < words.length; contador++) {
            array[i] = words[contador];
            i++;
        }
        return array;
    }

    public String[][] showTable() {

        this.arrayWord_esp = this.arrayAux;

        String tabla[][] = new String[arrayWord_esp.size()][1];

        for (int contador = 0; contador < arrayWord_esp.size(); contador++) {
            tabla[contador][0] = this.arrayWord_esp.get(contador).getWord_ESP();
        }

        return tabla;

    }

//    public ArrayList<Word_ESP> show() {
//
//        ArrayList<Word_ESP> array = new ArrayList();
//        String[] a;
//
//        this.words = this.mensaje.split("#");
//        int totalWords = Integer.parseInt(this.words[2]);
//
//        this.words = transformWords(this.words, totalWords);
//
//        for (int contador = 0; contador < this.words.length; contador++) {
//            Word_ESP word_ESP = new Word_ESP();
//
//            a = this.words[contador].split("@");
//            word_ESP.setCod_palabra(a[0]);
//            word_ESP.setWord_ESP(a[1]);
//            word_ESP.setDefinition_ESP(a[2]);
//            array.add(word_ESP);
//        }
//
//        return array;
//    }
//    public String[][] showTableWords() {
//
//        this.words = this.mensaje.split("#");//mensaje --> PROTOCOLOCRISTONARY1.0#AVAIBLE_WORDS#2#1@arbol@Planta perenne, de tronco leñoso y elevado...
//        int totalWords = Integer.parseInt(this.words[2]);
//
//        for (int contador = 0; contador < this.words.length; contador++) {
//            System.out.println("DE PRIMERAS TIENE: " + this.words[contador]);
//        }
//
//        this.words = transformWords(this.words, totalWords);//cadena -->  1@arbol@Planta perenne, de tronco leñoso y elevado, que se ramifica a cierta altura del suelo.
//
//        for (int contador = 0; contador < this.words.length; contador++) {
//            System.out.println("DESPUES: " + this.words[contador]);
//        }
//
//        String tabla[][] = new String[this.words.length][1];
//
//        for (int contador = 0; contador < this.words.length; contador++) {
//
//            tabla[contador][0] = this.words[contador];
//
//            System.out.println("EL DEFINITIVO: " + tabla[contador][0]);
//        }
//
////        this.out.println("MIS_GORDOS");
//        return tabla;
//
//    }
}
