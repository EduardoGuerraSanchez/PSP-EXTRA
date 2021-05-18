package controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import modelo.Word;
import modelo.Word_ESP;

public class ProtocolController {

    private String loginProtocol;
    private final String PROTOCOL = "PROTOCOLOCRISTONARY1.0";
    private final char SIGN = '#';
    private String loginCheck;
    private String passwordCheck;
    private final String RED = "\033[31m";
    private final String GREEN = "\033[32m";
    private final String RESTORE = "\u001B[0m";

    public String getLoginProtocol() {
        return loginProtocol;
    }

    public void setLoginProtocol(String loginProtocol) {
        this.loginProtocol = loginProtocol;
    }

    public String getLoginCheck() {
        return loginCheck;
    }

    public void setLoginCheck(String loginCheck) {
        this.loginCheck = loginCheck;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public boolean checkUser() throws SQLException {

        UserController userController = new UserController();
        String protocolLogin = this.getLoginProtocol();
        String login = null;
        String pass = null;
        boolean exist = false;

        protocolLogin = protocolLogin.substring(protocolLogin.indexOf("#") + 1, protocolLogin.length());
        protocolLogin = protocolLogin.substring(protocolLogin.indexOf("#") + 1, protocolLogin.length());//LOGIN#<USER_LOGIN>#<PASS>
        login = protocolLogin.substring(0, protocolLogin.indexOf("#"));//<USER_LOGIN>#<PASS>
        protocolLogin = protocolLogin.substring(protocolLogin.indexOf("#") + 1, protocolLogin.length());//<PASS>
        pass = protocolLogin;

        for (int contador = 0; contador < userController.getTableUsers().size(); contador++) {

            if (login.contentEquals(userController.getTableUsers().get(contador).getLogin()) && pass.contentEquals(userController.getTableUsers().get(contador).getPassword())) {
                exist = true;
                this.setLoginCheck(login);
                this.setPasswordCheck(pass);
            }

        }

        return exist;
    }

    public String OK(String s) throws SQLException {

        s = PROTOCOL + SIGN + "WELLCOME" + SIGN + this.getLoginCheck() + SIGN + "WITH_TOKEN" + SIGN;

        return s;
    }

    public String ERROR(String s) throws SQLException {

        s = RED + PROTOCOL + SIGN + "ERROR" + SIGN + "BAD_LOGIN" + RESTORE;

        return s;
    }

    public String generateSecretWord() {

        Random random = new Random();

        String n;

        n = String.valueOf(random.nextInt());

        return n;

    }

    public String insertSecretWord(String outputLine) {

        String s, word = null;
        s = outputLine.substring(outputLine.indexOf("#") + 1, outputLine.length());
        s = s.substring(s.indexOf("#") + 1, s.length());
        word = generateSecretWord();

        s = GREEN + outputLine + word + RESTORE;

        return s;
    }
    
    public ArrayList sendWords_ESP() throws SQLException {

        ArrayList array = new ArrayList();

        Words_ESP_Controller words_ESP_Controller = new Words_ESP_Controller();

        array = words_ESP_Controller.getTableWords_ESP();

        return array;
    }

}
