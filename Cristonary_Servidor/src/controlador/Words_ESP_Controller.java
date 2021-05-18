package controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Word_ESP;

public class Words_ESP_Controller {

    private ArrayList<Word_ESP> arrayWords_ESP_Controller;

    public Words_ESP_Controller() {
        arrayWords_ESP_Controller = new ArrayList<Word_ESP>();
    }

    public ArrayList<Word_ESP> getArrayWords_ESP_Controller() {
        return arrayWords_ESP_Controller;
    }

    public void setArrayWords_ESP_Controller(ArrayList<Word_ESP> arrayWordsController) {
        this.arrayWords_ESP_Controller = arrayWordsController;
    }

    public ArrayList<Word_ESP> getTableWords_ESP() throws SQLException {

        Word_ESP words_ESP = new Word_ESP();

        this.setArrayWords_ESP_Controller(words_ESP.getWord_ESP_BD());

        return this.arrayWords_ESP_Controller;
    }

//    public static void main(String[] args) throws SQLException {
//        Words_ESP_Controller word_ESP = new Words_ESP_Controller();
//
//        ArrayList<Word_ESP> array = new ArrayList<Word_ESP>();
//
//        array = word_ESP.getTableWords_ESP();
//
//        for (int contador = 0; contador < array.size(); contador++) {
//            System.out.println(array.get(contador).getDefinition_ESP());
//        }
//
//    }

}
