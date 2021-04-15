package modelo;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Palabra extends ConexionBD {

    private int codigo_palabra;//PK
    private String nombre;
    private String definicion;
    private ArrayList<Palabra> arrayPalabra;

    public Palabra() {
        arrayPalabra = new ArrayList<Palabra>();
    }

    public int getCodigo_palabra() {
        return codigo_palabra;
    }

    public void setCodigo_palabra(int codigo_palabra) {
        this.codigo_palabra = codigo_palabra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDefinicion() {
        return definicion;
    }

    public void setDefinicion(String definicion) {
        this.definicion = definicion;
    }


    public ArrayList<Palabra> getPalabraBD() throws SQLException {

        this.abrirConexion();
        
        String query = "SELECT * FROM palabra";

        Statement stmt = null;

        ResultSet rs = null;

        try {

            stmt = this.conn.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {

                Palabra palabra = new Palabra();
                palabra.setCodigo_palabra(rs.getInt("codigo_palabra"));
                palabra.setNombre(rs.getString("nombre"));
                palabra.setDefinicion(rs.getString("definicion"));

                this.arrayPalabra.add(palabra);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        } finally {
            stmt.close();
            rs.close();
            this.cerrarConexion();
        }

        return arrayPalabra;
    }

    public void crearPalabra(String nombre,String definicion) throws SQLException {

        PreparedStatement ps;
        
        String query = "INSERT INTO palabra(nombre,definicion) VALUES (?,?)";

        this.abrirConexion();

        try {
            ps = this.conn.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, definicion);
            ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        } finally {
            this.cerrarConexion();
        }
    }
    
    public void modificarPalabra(String nombre,String definicion) throws SQLException{
        
        String query = "UPDATE palabra SET definicion=? WHERE nombre=?";
        
        PreparedStatement ps;

        this.abrirConexion();
        System.out.println("TAMOS EN LA BD");
        System.out.println(nombre);
        System.out.println(definicion);
        try {
            
            ps = this.conn.prepareStatement(query);
            
            ps.setString(1, definicion);       
            ps.setString(2, nombre);

            
            ps.executeUpdate();
            
            System.out.println("LO HEMOS INTENTAO");
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        } finally {
            this.cerrarConexion();
        }
    }
    
    public void eliminarPalabra(String nombre) throws SQLException{
        
        PreparedStatement ps;
        
        String query = "DELETE FROM palabra WHERE nombre=?";
        
        this.abrirConexion();
        
        try{
            ps = this.conn.prepareStatement(query);
            
            ps.setString(1, nombre);
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        } finally {
            this.cerrarConexion();
        }
        
    }

    @Override
    public String toString() {
        return "Palabra{" + "codigo_palabra=" + codigo_palabra + ", nombre=" + nombre + ", definicion=" + definicion + '}';
    }

}