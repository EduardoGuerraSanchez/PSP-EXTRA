package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {
    
    private String usuario = "root";
    private String contraseña = "contraseña";
    private String nombreBD = "Cristonary";
    private String hostBD = "localhost";
    private String databaseBD = "mysql";
    private String puertoBD = "3306";
    private String URL = ("jdbc:" + this.getDatabaseBD() + "://" + this.getHostBD() + ":" + this.getPuertoBD() + "/" + this.getNombreBD() + "?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true");
    protected Connection conn;
    
    public String getUsuario() {
        return usuario;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombreBD() {
        return nombreBD;
    }

    public void setNombreBD(String nombreBD) {
        this.nombreBD = nombreBD;
    }

    public String getHostBD() {
        return hostBD;
    }

    public void setHostBD(String hostBD) {
        this.hostBD = hostBD;
    }

    public String getDatabaseBD() {
        return databaseBD;
    }

    public void setDatabaseBD(String databaseBD) {
        this.databaseBD = databaseBD;
    }

    public String getPuertoBD() {
        return puertoBD;
    }

    public void setPuertoBD(String puertoDB) {
        this.puertoBD = puertoDB;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
    
    public void abrirConexion() {

        try {
            this.conn = DriverManager.getConnection(this.getURL(), this.getUsuario(), this.getContraseña());
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarConexion() throws SQLException {
        if (this.conn != null) {
            this.conn.close();
            this.conn = null;
        }
    }
    
}
