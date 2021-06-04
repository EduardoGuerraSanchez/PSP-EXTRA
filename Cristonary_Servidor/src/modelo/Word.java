package modelo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Word extends ConexionBD {
    
    private String cod_word;
    private String multimedia;
    private String login;
    private MultimediaWord multimediaWord;
    private ArrayList arrayWord;
    
    public Word() throws IOException {
        multimediaWord = new MultimediaWord();
        arrayWord = new ArrayList();
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

    public String getCod_word() {
        return cod_word;
    }

    public void setCod_word(String cod_word) {
        this.cod_word = cod_word;
    }

    public MultimediaWord getMultimediaWord() {
        return multimediaWord;
    }

    public void setMultimediaWord(MultimediaWord multimediaWord) {
        this.multimediaWord = multimediaWord;
    }
    
    public ArrayList getWordBD() throws SQLException, IOException {

        this.abrirConexion();
        
        String query = "SELECT * FROM palabra";

        Statement stmt = null;

        ResultSet rs = null;

        try {

            stmt = this.conn.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {

                Word word = new Word();
                word.setCod_word(rs.getString("cod_palabra"));
                word.setMultimedia(rs.getString("multimedia"));
                word.setLogin(rs.getString("login"));


                this.arrayWord.add(word);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        } finally {
            stmt.close();
            rs.close();
            this.cerrarConexion();
        }

        return arrayWord;
    }
    
    
    
}
