package controlador;

import hebras.ThreadServer;
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
    private int sizeMultimedia;
    private String type;
    private ArrayList<Word> arrayWord;
    private ArrayList<Word_ESP> arrayESP;
    private ArrayList<ThreadServer> arrayThread;

    public ProtocolController(ArrayList<ThreadServer> array) throws SQLException, IOException {

        Words_ESP_Controller words_ESP_Controller = new Words_ESP_Controller();

        this.arrayESP = words_ESP_Controller.getTableWords_ESP();

        WordsController wordsController = new WordsController();

        this.arrayWord = wordsController.getTableWords();

        this.arrayThread = array;

        System.out.println("-------------------------------------------------------------------------------------------------- son en total: " + this.arrayThread.size());
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

    public int getSizeMultimedia() {
        return sizeMultimedia;
    }

    public void setSizeMultimedia(int sizeMultimedia) {
        this.sizeMultimedia = sizeMultimedia;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Word> getArrayWord() {
        return arrayWord;
    }

    public void setArrayWord(ArrayList<Word> arrayWord) {
        this.arrayWord = arrayWord;
    }

    public ArrayList<Word_ESP> getArrayESP() {
        return arrayESP;
    }

    public void setArrayESP(ArrayList<Word_ESP> arrayESP) {
        this.arrayESP = arrayESP;
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

    public String getWords() throws SQLException {
        String a = null;

        String protocol = "PROTOCOLOCRISTONARY1.0#AVAIBLE_WORDS#" + this.arrayESP.size() + "#";

        for (int contador = 0; contador < this.arrayESP.size(); contador++) {

            a = this.arrayESP.get(contador).getCod_palabra() + "@" + this.arrayESP.get(contador).getWord_ESP() + "@" + this.arrayESP.get(contador).getDefinition_ESP();

            if (contador != this.arrayESP.size() - 1) {
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

        String description = null;

        System.out.println("LA PK ES: " + pk);

        for (int contador = 0; contador < this.arrayESP.size(); contador++) {

            System.out.println("esto va a ser: " + this.arrayESP.get(contador).getCod_palabra());
            if (pk.equals(this.arrayESP.get(contador).getCod_palabra())) {

                System.out.println("Mandamos tu descripcion");
                description = this.arrayESP.get(contador).getDefinition_ESP();
                this.i = contador;
                this.arrayESP.get(this.i).getMultimediaWord().initializeMultimedia(this.arrayWord.get(this.i).getMultimedia());//Añadimos la ruta
                this.sizeMultimedia = this.arrayESP.get(this.i).getMultimediaWord().getMultimediaSize();
                this.type = this.arrayWord.get(i).getMultimedia().substring(this.arrayWord.get(i).getMultimedia().length() - 4, this.arrayWord.get(i).getMultimedia().length());
            }
        }
        return description;
    }

    public String getNameFromSpecificWord() throws SQLException {

        String name = null;

        name = this.arrayESP.get(i).getWord_ESP();

        return name;
    }

    public String sizeMultimedia() throws SQLException, IOException {

        String numero = null;

        numero = String.valueOf(this.arrayESP.get(this.i).getMultimediaWord().getMultimediaSize());

        System.out.println("AQUI EL NUMERO VA A SER: " + numero);

        return numero;
    }

    public String deleteUser(String login, String token) throws IOException {
        
        String message = null;

        for (int contador = 0; contador < this.arrayThread.size(); contador++) {
            String loginPrueba = this.arrayThread.get(contador).getProtocol().getLogin();

            if (loginPrueba.equals(login)) {
                message = "PROTOCOLOCRISTONARY1.0#ADIOSXULO#" + login + "#" + token;
                this.arrayThread.get(contador).getSocket().close();
                this.arrayThread.remove(contador);
            }

        }
        return message;
    }

}
