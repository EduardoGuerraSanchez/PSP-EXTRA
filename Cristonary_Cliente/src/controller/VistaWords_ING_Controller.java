package controller;

import java.util.ArrayList;
import map.Word_ING;

public class VistaWords_ING_Controller {

    private String mensaje;
    private ArrayList<Word_ING> arrayWord_ING;
    private ArrayList arrayAux;

    public VistaWords_ING_Controller(String mensaje, ArrayList aux) {
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

        this.arrayWord_ING = this.arrayAux;

        String tabla[][] = new String[arrayWord_ING.size()][1];

        for (int contador = 0; contador < arrayWord_ING.size(); contador++) {
            System.out.println(arrayWord_ING.get(contador));
        }

        for (int contador = 0; contador < arrayWord_ING.size(); contador++) {
            tabla[contador][0] = this.arrayWord_ING.get(contador).getWord_ING();
        }
        return tabla;
    }

}
