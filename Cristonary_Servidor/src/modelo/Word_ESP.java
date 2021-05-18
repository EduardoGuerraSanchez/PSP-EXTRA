package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Word_ESP extends Word{
    
    private String word_ESP;
    private String definition_ESP;
    private int cod_palabra;
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

    public int getCod_palabra() {
        return cod_palabra;
    }

    public void setCod_palabra(int cod_palabra) {
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
                
                word_ESP.setWord_ESP(rs.getString("palabra_esp"));
                word_ESP.setDefinition_ESP(rs.getString("definicion_esp"));
                word_ESP.setCod_palabra(rs.getInt("cod_palabra"));
                

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
    
    
    
//    public static void main(String[] args) throws SQLException {
//        Word_ESP word_ESP = new Word_ESP();
//        
//        ArrayList<Word_ESP> array = new ArrayList<Word_ESP>();
//        
//        array = word_ESP.getWord_ESP_BD();
//        
//        for(int contador = 0;contador < array.size();contador++){
//            System.out.println(array.get(contador).getDefinition_ESP());
//        }
//        
//    }

    @Override
    public String toString() {
        return "Word_ESP{" + "word_ESP=" + word_ESP + ", definition_ESP=" + definition_ESP + ", cod_palabra=" + cod_palabra + '}';
    }
    
}
