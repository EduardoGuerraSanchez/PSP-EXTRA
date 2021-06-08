package controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Word_ING;

public class Words_ING_Controller {
    
    private ArrayList<Word_ING> arrayWords_ING_Controller;

    public Words_ING_Controller() {
        arrayWords_ING_Controller = new ArrayList<Word_ING>();
    }

    public ArrayList<Word_ING> getArrayWords_ESP_Controller() {
        return arrayWords_ING_Controller;
    }

    public void setArrayWords_ESP_Controller(ArrayList<Word_ING> arrayWordsController) {
        this.arrayWords_ING_Controller = arrayWordsController;
    }

    public ArrayList<Word_ING> getTableWords_ING() throws SQLException{

        Word_ING words_ING = new Word_ING();

        this.setArrayWords_ESP_Controller(words_ING.getWord_ING_BD());

        return this.arrayWords_ING_Controller;
    }
    
}
