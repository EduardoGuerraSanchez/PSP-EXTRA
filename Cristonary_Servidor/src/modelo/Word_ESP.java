package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Word_ESP extends Word{
    
    private String word_ESP;
    private String definition_ESP;
    private String cod_palabra;
    private ArrayList arrayWord_ESP;
    
    public Word_ESP() {
        arrayWord_ESP = new ArrayList();
    }
    
    public Word_ESP(String name,String definition,String cod) {
        this.word_ESP = name;
        this.definition_ESP = definition;
        this.cod_palabra = cod;
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
    
    public ArrayList getWord_ESP_BD() throws SQLException {

        this.abrirConexion();
        
        String query = "SELECT * FROM palabra_esp";

        Statement stmt = null;

        ResultSet rs = null;

        try {

            stmt = this.conn.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {

                Word_ESP word_ESP = new Word_ESP();
                
                word_ESP.setWord_ESP(rs.getString("word_esp"));
                word_ESP.setDefinition_ESP(rs.getString("description_esp"));
                word_ESP.setCod_palabra(rs.getString("cod_word"));
                

                this.arrayWord_ESP.add(word_ESP);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        } finally {
            stmt.close();
            rs.close();
            this.cerrarConexion();
        }

        return arrayWord_ESP;
    }

    public ArrayList getArrayWord_ESP() {
        return arrayWord_ESP;
    }

    public void setArrayWord_ESP(ArrayList arrayWord_ESP) {
        this.arrayWord_ESP = arrayWord_ESP;
    }
    
    public void createWord_ESP(String nombre,String description, int cod) throws SQLException {

        this.abrirConexion();

        PreparedStatement ps;

        String query = "INSERT INTO palabra_esp VALUES (?,?,?)";

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

    @Override
    public String toString() {
        return "Word_ESP{" + "word_ESP=" + word_ESP + ", definition_ESP=" + definition_ESP + ", cod_palabra=" + cod_palabra + '}';
    }
    
    
}