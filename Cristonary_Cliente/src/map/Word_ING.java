package map;

import java.util.ArrayList;


public class Word_ING extends Word{
    
    private String word_ING;
    private String definition_ING;
    private String cod_palabra;
    private ArrayList arrayWord_ING;
    
    public Word_ING(){
        arrayWord_ING = new ArrayList();
    }

    public String getWord_ING() {
        return word_ING;
    }

    public void setWord_ING(String word_ING) {
        this.word_ING = word_ING;
    }

    public String getDefinition_ING() {
        return definition_ING;
    }

    public void setDefinition_ING(String definition_ING) {
        this.definition_ING = definition_ING;
    }

    public String getCod_palabra() {
        return cod_palabra;
    }

    public void setCod_palabra(String cod_palabra) {
        this.cod_palabra = cod_palabra;
    }
    
    @Override
    public String toString() {
        return "Word_ING{" + "word_ING=" + word_ING + ", definition_ING=" + definition_ING + ", cod_palabra=" + cod_palabra + '}';
    }
    
}
