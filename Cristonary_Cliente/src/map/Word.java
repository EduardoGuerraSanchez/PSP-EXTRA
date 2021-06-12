package map;

import java.util.ArrayList;

public class Word {
    
    private int cod_word;
    private String multimedia;
    private String login;
    private ArrayList arrayWord;
    
    public Word() {
        arrayWord = new ArrayList();
    }
    
    public Word(String multimedia,String login){
        this.multimedia = multimedia;
        this.login = login;
    }

    public Word(String login){
        this.login = login;
    }
    
    public String getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(String multimedia) {
        this.multimedia = multimedia;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getCod_word() {
        return cod_word;
    }

    public void setCod_word(int cod_word) {
        this.cod_word = cod_word;
    }

    @Override
    public String toString() {
        return "Word{" + "cod_word=" + cod_word + ", multimedia=" + multimedia + ", login=" + login + '}';
    }
    
}
