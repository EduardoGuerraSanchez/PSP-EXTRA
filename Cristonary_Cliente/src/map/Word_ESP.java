package map;

import java.util.ArrayList;

public class Word_ESP extends Word{
    
    private String word_ESP;
    private String definition_ESP;
    private String cod_palabra;
    private ArrayList arrayWord_ESP;
    
    public Word_ESP() {
        arrayWord_ESP = new ArrayList();
    }

    public String getWord_ESP() {
        return word_ESP;
    }

    public void setWord_ESP(String word_ESP) {
        this.word_ESP = word_ESP;
    }

    public String getDefinition_ESP() {
        return definition_ESP;
    }

    public void setDefinition_ESP(String definition_ESP) {
        this.definition_ESP = definition_ESP;
    }

    public String getCod_palabra() {
        return cod_palabra;
    }

    public void setCod_palabra(String cod_palabra) {
        this.cod_palabra = cod_palabra;
    }

    @Override
    public String toString() {
        return "Word_ESP{" + "word_ESP=" + word_ESP + ", definition_ESP=" + definition_ESP + ", cod_palabra=" + cod_palabra + '}';
    }
}
