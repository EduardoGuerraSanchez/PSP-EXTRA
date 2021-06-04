package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Word;

public class WordsController {
    
    private ArrayList<Word> arrayWordsController;
    
    public WordsController(){
        arrayWordsController = new ArrayList<Word>();
    }

    public ArrayList<Word> getArrayWordsController() {
        return arrayWordsController;
    }

    public void setArrayWordsController(ArrayList<Word> arrayWordsController) {
        this.arrayWordsController = arrayWordsController;
    }
    
    public ArrayList<Word> getTableWords() throws SQLException, IOException{
        
        Word word = new Word();
        
        this.setArrayWordsController(word.getWordBD());

        return this.arrayWordsController;
    }
    
    
}
