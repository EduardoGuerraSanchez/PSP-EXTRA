package hebras;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import map.Word_ESP;
import map.Word_ING;
import vistas.VistaWords;

public class UpdateTable implements Runnable {

    private Socket socket;
    public Thread thread;
    String[] message_ESP;
    String[] message_ING;
    ArrayList<Word_ESP> arrayESP;
    ArrayList<Word_ING> arrayING;
    VistaWords vistaWords;

    public UpdateTable(Socket socket, String[] cadena_ESP, String[] cadena_ING, VistaWords vistaWords) {
        this.socket = socket;
        thread = new Thread(this);
        this.message_ESP = cadena_ESP;
        this.message_ING = cadena_ING;
        this.vistaWords = vistaWords;
        this.arrayESP = insertArrayInWord_ESP();
        this.arrayING = insertArrayInWord_ING();
    }

    public ArrayList<Word_ESP> insertArrayInWord_ESP() {

        String[] cadenaAux;

        ArrayList<Word_ESP> arrayAux = new ArrayList<>();

        for (int i = 0; i < message_ESP.length; i++) {

            cadenaAux = this.message_ESP[i].split("@");
            
            Word_ESP word_ESP = new Word_ESP();

            word_ESP.setCod_palabra(cadenaAux[0]);

            word_ESP.setWord_ESP(cadenaAux[1]);

            word_ESP.setDefinition_ESP(cadenaAux[2]);

            arrayAux.add(word_ESP);
        }
        return arrayAux;
    }

//    public String[][] updateTableWords_ESP() {
//        String table[][] = new String[arrayESP.size()][1];
//
//        for (int contador = 0; contador < this.arrayESP.size(); contador++) {
//            table[contador][0] = this.arrayESP.get(contador).getWord_ESP();
//        }
//
//        return table;
//    }

    public String[][] insertWordsTable_ESP() throws IOException, FileNotFoundException, ClassNotFoundException {

        String table[][] = new String[this.arrayESP.size()][1];

        for (int contador = 0; contador < this.arrayESP.size(); contador++) {
            table[contador][0] = this.arrayESP.get(contador).getWord_ESP();
        }
        return table;
    }

    public ArrayList<Word_ING> insertArrayInWord_ING() {
        String[] cadenaAux;
        ArrayList<Word_ING> arrayAux = new ArrayList<>();

        for (int i = 0; i < message_ING.length; i++) {

            cadenaAux = this.message_ING[i].split("@");

            Word_ING word_ING = new Word_ING();

            word_ING.setCod_palabra(cadenaAux[0]);

            word_ING.setWord_ING(cadenaAux[1]);

            word_ING.setDefinition_ING(cadenaAux[2]);

            arrayAux.add(word_ING);
        }
        return arrayAux;
    }

    public String[][] updateTableWords_ING() {
        String table[][] = new String[arrayING.size()][1];

        for (int contador = 0; contador < this.arrayING.size(); contador++) {
            table[contador][0] = this.arrayING.get(contador).getWord_ING();
        }

        return table;
    }

    public String[][] insertWordsTable_ING() throws IOException, FileNotFoundException, ClassNotFoundException {

        String table[][] = new String[this.arrayING.size()][1];

        for (int contador = 0; contador < this.arrayING.size(); contador++) {
            table[contador][0] = this.arrayING.get(contador).getWord_ING();
        }
        return table;
    }

    @Override
    public void run() {

        try {
            System.out.println("TOMALO NIÑOOOO");
            this.vistaWords.setArrayWord_ESP(arrayESP);
            this.vistaWords.setArrayWord_ING(arrayING);
            this.vistaWords.refreshTable_ESP(insertWordsTable_ESP());
            this.vistaWords.refreshTable_ING(insertWordsTable_ING());
            System.out.println("YA SE LO HA DAO");

        } catch (IOException ex) {
            Logger.getLogger(UpdateTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateTable.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
