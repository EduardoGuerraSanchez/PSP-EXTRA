package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Word extends ConexionBD {
    
    private int cod_word;
    private String multimedia;
    private String login;
    private ArrayList arrayWord;
    
    public Word() {
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

    public int getCod_word() {
        return cod_word;
    }

    public void setCod_word(int cod_word) {
        this.cod_word = cod_word;
    }
    
    
    
    public ArrayList getWordBD() throws SQLException {

        this.abrirConexion();
        
        String query = "SELECT * FROM palabra";

        Statement stmt = null;

        ResultSet rs = null;

        try {

            stmt = this.conn.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {

                Word word = new Word();
                word.setCod_word(rs.getInt("cod_palabra"));
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
