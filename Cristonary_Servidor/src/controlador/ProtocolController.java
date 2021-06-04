package controlador;

import java.io.IOException;
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
    private int i;

    public ProtocolController() {
    }

    public String getLoginProtocol() {
        return loginProtocol;
    }

    public void setLoginProtocol(String loginProtocol) {
        this.loginProtocol = loginProtocol;
    }

    public String getLoginCheck() {
        return this.loginCheck;
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

    public boolean checkUser() throws SQLException, IOException {

        UserController userController = new UserController();
        String protocolLogin = this.getLoginProtocol();
        boolean exist = false;

        protocolLogin = protocolLogin.substring(protocolLogin.indexOf("#") + 1, protocolLogin.length());
        protocolLogin = protocolLogin.substring(protocolLogin.indexOf("#") + 1, protocolLogin.length());//LOGIN#<USER_LOGIN>#<PASS>
        this.loginCheck = protocolLogin.substring(0, protocolLogin.indexOf("#"));//<USER_LOGIN>#<PASS>
        protocolLogin = protocolLogin.substring(protocolLogin.indexOf("#") + 1, protocolLogin.length());//<PASS>
        this.passwordCheck = protocolLogin;

        for (int contador = 0; contador < userController.getTableUsers().size(); contador++) {

            if (this.loginCheck.contentEquals(userController.getTableUsers().get(contador).getLogin()) && this.passwordCheck.contentEquals(userController.getTableUsers().get(contador).getPassword())) {
                exist = true;
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
    
    public ArrayList sendWords() throws SQLException, IOException{
        
        ArrayList array = new ArrayList();
        
        WordsController wordsController = new WordsController();
        
        array = wordsController.getTableWords();
        
        return array;
    }
    
    public String getWords() throws SQLException{
        String a = null;
        
        ArrayList<Word_ESP> array = new ArrayList();

        Words_ESP_Controller words_ESP_Controller = new Words_ESP_Controller();

        array = words_ESP_Controller.getTableWords_ESP();
        
        String protocol = "PROTOCOLOCRISTONARY1.0#AVAIBLE_WORDS#" + array.size() + "#";
        
        for(int contador = 0;contador < array.size();contador++){
            
            a = array.get(contador).getCod_palabra() + "@" + array.get(contador).getWord_ESP() + "@" + array.get(contador).getDefinition_ESP();
        
            
            if (contador != array.size() - 1) {
                System.out.println("AQUI ENTRA??");
                    protocol = protocol + a + "#";
                } else {
                    protocol = protocol + a;
                }
            
        }
        System.out.println("COMO QUEDA: " + a);
        System.out.println(protocol);
        
        return protocol;
    }

    public String get_specific_word(String pk) throws SQLException, IOException {

        Words_ESP_Controller words_ESP_Controller = new Words_ESP_Controller();

        ArrayList<Word_ESP> array = new ArrayList<Word_ESP>();
        
        ArrayList<Word> arrayWord = new ArrayList<Word>();
        
        WordsController wordsController = new WordsController();
        
        arrayWord = wordsController.getTableWords();

        String description = null;

        array = sendWords_ESP();

        System.out.println("LA PK ES: " + pk);

        for (int contador = 0; contador < array.size(); contador++) {

            System.out.println("esto va a ser: " + array.get(contador).getCod_palabra());
            if (pk.equals(array.get(contador).getCod_palabra())) {

                System.out.println("Mandamos tu descripcion");
                description = array.get(contador).getDefinition_ESP();
                this.i = contador;
                array.get(this.i).getMultimediaWord().initializeMultimedia(arrayWord.get(this.i).getMultimedia());//Añadimos la ruta
            }
        }
        return description;
    }

    public String getNameFromSpecificWord() throws SQLException {

        String name = null;

        ArrayList<Word_ESP> array = new ArrayList<Word_ESP>();

        array = sendWords_ESP();

        name = array.get(i).getWord_ESP();

        return name;
    }
    
    public String sizeMultimedia() throws SQLException, IOException{
        String numero = null;
        
        ArrayList<Word_ESP> array = new ArrayList<Word_ESP>();
        
        ArrayList<Word> arrayWord = new ArrayList<Word>();

        array = sendWords_ESP();
        
        arrayWord = sendWords();
        
        numero = String.valueOf(array.get(this.i).getMultimediaWord().getMultimediaSize());
        
        System.out.println("AQUI EL NUMERO VA A SER: " + numero);
        
        return numero;
    }
}
