package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Word_ING extends Word {

    private String word_ING;
    private String definition_ING;
    private String cod_palabra;
    private ArrayList arrayWord_ING;

    public Word_ING() {
        arrayWord_ING = new ArrayList();
    }
    
    public Word_ING(String nombre,String definition,String cod) {
        this.word_ING = nombre;
        this.definition_ING = definition;
        this.cod_palabra = cod;
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

    public ArrayList getWord_ING_BD() throws SQLException {

        this.abrirConexion();

        String query = "SELECT * FROM palabra_ing";

        Statement stmt = null;

        ResultSet rs = null;

        try {

            stmt = this.conn.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {

                Word_ING word_ING = new Word_ING();

                word_ING.setWord_ING(rs.getString("palabra_ing"));
                word_ING.setDefinition_ING(rs.getString("definicion_ing"));
                word_ING.setCod_palabra(rs.getString("cod_palabra"));

                this.arrayWord_ING.add(word_ING);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        } finally {
            stmt.close();
            rs.close();
            this.cerrarConexion();
        }

        return arrayWord_ING;
    }

    public void createWord_ING(String nombre, String description, int cod) throws SQLException {

        this.abrirConexion();

        PreparedStatement ps;

        String query = "INSERT INTO palabra_ing VALUES (?,?,?)";

        try {
            ps = this.conn.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, description);
            ps.setInt(3, cod);
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        } finally {
            this.cerrarConexion();
        }
    }

    public void deleteWord_ING(String cod) throws SQLException{
        
        PreparedStatement ps;
        
        String query = "DELETE FROM palabra_ing WHERE cod_palabra=?";
        
        this.abrirConexion();
        
        try{
            ps = this.conn.prepareStatement(query);
            
            ps.setString(1, cod);
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public String toString() {
        return "Word_ING{" + "word_ING=" + word_ING + ", definition_ING=" + definition_ING + ", cod_palabra=" + cod_palabra + '}';
    }
}
