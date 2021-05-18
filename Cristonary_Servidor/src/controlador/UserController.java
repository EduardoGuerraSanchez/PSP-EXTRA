package controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Usuario;

public class UserController {

    private ArrayList<Usuario> arrayControllerUsers;

    public UserController() {
        arrayControllerUsers = new ArrayList<Usuario>();
    }

    public ArrayList<Usuario> getArrayControllerUsers() {
        return arrayControllerUsers;
    }

    public void setArrayControllerUsers(ArrayList<Usuario> arrayControllerUsers) {
        this.arrayControllerUsers = arrayControllerUsers;
    }
    
    public ArrayList<Usuario> getTableUsers() throws SQLException{
        
        Usuario usuario = new Usuario();
        
        this.setArrayControllerUsers(usuario.getUserBD());

        return this.arrayControllerUsers;
    }
    
}
