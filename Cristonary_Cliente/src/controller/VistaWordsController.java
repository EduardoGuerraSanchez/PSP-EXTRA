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
            System.out.println("VEAMOS: -->: " + words[contador]);
            System.out.println("klk: -->: " + array[i]);

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
    
    
}
