package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Usuario extends ConexionBD{
    
    private String login;
    private String name;
    private String password;
    private ArrayList arrayUsuario;

    public Usuario() {
        arrayUsuario = new ArrayList();
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public ArrayList getUserBD() throws SQLException {

        this.abrirConexion();
        
        String query = "SELECT * FROM usuario";

        Statement stmt = null;

        ResultSet rs = null;

        try {

            stmt = this.conn.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {

                Usuario usuario = new Usuario();
                usuario.setLogin(rs.getString("login"));
                usuario.setName(rs.getString("name"));
                usuario.setPassword(rs.getString("password"));

                this.arrayUsuario.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        } finally {
            stmt.close();
            rs.close();
            this.cerrarConexion();
        }

        return arrayUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" + "login=" + login + ", name=" + name + ", password=" + password + '}';
    }
    
}
