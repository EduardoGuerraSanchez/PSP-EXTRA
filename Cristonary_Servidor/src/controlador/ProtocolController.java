package controlador;

import hebras.ThreadServer;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import modelo.Word;
import modelo.Word_ESP;
import modelo.Word_ING;

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
    private ArrayList<Word_ING> arrayING;
    private ArrayList<ThreadServer> arrayThread;
    private String token;
    private Word word;
    private Word_ESP word_ESP;
    private Word_ING word_ING;

    WordsController wordsController;
    Words_ESP_Controller words_ESP_Controller;
    Words_ING_Controller words_ING_Controller;

    public ProtocolController(ArrayList<ThreadServer> array) throws SQLException, IOException {

        this.wordsController = new WordsController();
        this.arrayWord = wordsController.getTableWords();

        this.words_ESP_Controller = new Words_ESP_Controller();
        this.arrayESP = words_ESP_Controller.getTableWords_ESP();

        this.words_ING_Controller = new Words_ING_Controller();
        this.arrayING = words_ING_Controller.getTableWords_ING();

        this.arrayThread = array;
    }

    public ProtocolController() throws SQLException, IOException {
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

    public String getToken() {
        return this.token;
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

        this.token = n;

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

        String protocol = "PROTOCOLOCRISTONARY1.0#AVAIBLE_WORDS#" + (this.arrayESP.size() + this.arrayING.size()) + "#";

        for (int contador = 0; contador < this.arrayESP.size(); contador++) {

            a = this.arrayESP.get(contador).getCod_palabra() + "@" + this.arrayESP.get(contador).getWord_ESP() + "@" + this.arrayESP.get(contador).getDefinition_ESP();

            if (contador != this.arrayESP.size() - 1) {
                System.out.println("AQUI ENTRA??");
                protocol = protocol + a + "#";
            } else {
                protocol = protocol + a;
            }

        }
        protocol = protocol + "#" + "ESP" + "#";

        for (int contador = 0; contador < this.arrayING.size(); contador++) {

            a = this.arrayING.get(contador).getCod_palabra() + "@" + this.arrayING.get(contador).getWord_ING() + "@" + this.arrayING.get(contador).getDefinition_ING();

            if (contador != this.arrayING.size() - 1) {
                System.out.println("AQUI ENTRA??");
                protocol = protocol + a + "#";
            } else {
                protocol = protocol + a;
            }

        }
        protocol = protocol + "#" + "ING";

        System.out.println("COMO QUEDA: " + a);
        System.out.println(this.arrayING.size());
        System.out.println(protocol);

        return protocol;
    }

    public String get_specific_definition(String pk, String languaje) throws SQLException, IOException {

        String description = null;

        System.out.println("LA PK ES: " + pk);

        if ("ESP".equals(languaje)) {
            for (int contador = 0; contador < this.arrayESP.size(); contador++) {

                System.out.println("esto va a ser: " + this.arrayESP.get(contador).getCod_palabra());
                if (pk.equals(this.arrayESP.get(contador).getCod_palabra())) {

                    System.out.println("Mandamos tu descripcion");
                    description = this.arrayESP.get(contador).getDefinition_ESP();
                    this.i = contador;
                    this.arrayWord.get(this.i).getMultimediaWord().initializeMultimedia(this.arrayWord.get(this.i).getMultimedia());//Añadimos la ruta
                    System.out.println("NO ES EL PROTOCOLOESPPPPP,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,, " + this.i);

                    this.sizeMultimedia = this.arrayWord.get(this.i).getMultimediaWord().getMultimediaSize();
                    this.type = this.arrayWord.get(i).getMultimedia().substring(this.arrayWord.get(i).getMultimedia().length() - 4, this.arrayWord.get(i).getMultimedia().length());
                }
            }
        }

        if ("ING".equals(languaje)) {

            for (int contador = 0; contador < this.arrayING.size(); contador++) {

                System.out.println("esto va a ser: " + this.arrayING.get(contador).getCod_palabra());
                if (pk.equals(this.arrayING.get(contador).getCod_palabra())) {

                    System.out.println("Mandamos tu descripcion");
                    description = this.arrayING.get(contador).getDefinition_ING();
                    this.i = contador;
                }
            }
        }

        return description;
    }

    public void inizializarMultimedia(String pk) throws IOException {
        boolean find = false;
        for (int contador = 0; contador < arrayWord.size() && find == false; contador++) {
            if (pk.equals(arrayWord.get(contador).getCod_word())) {
                find = true;
                this.i = contador;
            }
        }
        arrayWord.get(this.i).getMultimediaWord().initializeMultimedia(arrayWord.get(this.i).getMultimedia());

        this.sizeMultimedia = this.arrayWord.get(this.i).getMultimediaWord().getMultimediaSize();

        this.type = this.arrayWord.get(i).getMultimedia().substring(this.arrayWord.get(i).getMultimedia().length() - 4, this.arrayWord.get(i).getMultimedia().length());

    }

    public String getNameFromSpecificWord(String pk, String languaje) throws SQLException {

        String name = null;
        boolean done = false;

        if ("ING".equals(languaje)) {

            for (int contador = 0; contador < this.arrayING.size() && done == false; contador++) {

                if (pk.equals(this.arrayING.get(contador).getCod_palabra())) {
                    name = this.arrayING.get(contador).getWord_ING();
                    done = true;
                }
            }
        }
        if ("ESP".equals(languaje)) {

            for (int contador = 0; contador < this.arrayESP.size() && done == false; contador++) {

                if (pk.equals(this.arrayESP.get(contador).getCod_palabra())) {
                    name = this.arrayESP.get(contador).getWord_ESP();
                }
            }

        }

        return name;
    }

    public String getCreator(String pk) {
        String a = null;

        System.out.println("ESTA ES LA PK: " + pk);
        boolean done = false;

        for (int contador = 0; contador < arrayWord.size() && done == false; contador++) {
            System.out.println(arrayWord.get(contador).getLogin());
            System.out.println(arrayWord.get(contador).getCod_word());

            if (this.arrayWord.get(contador).getCod_word().equals(pk)) {
                System.out.println("DISELO MI PUTISIMO GORDO");
                a = this.arrayWord.get(contador).getLogin();
                done = true;
            }

        }

        System.out.println("TAMBIEN ESTO: " + a);

        return a;
    }

    public String sizeMultimedia(String languaje) throws SQLException, IOException {

        String numero = null;

        if ("ESP".equals(languaje)) {
            numero = String.valueOf(this.arrayESP.get(this.i).getMultimediaWord().getMultimediaSize());
        }

        if ("ING".equals(languaje)) {
            numero = String.valueOf(this.arrayING.get(this.i).getMultimediaWord().getMultimediaSize());
        }

        return numero;
    }

    public String deleteUser(String login, String token) throws IOException {

        String message = null;

        for (int contador = 0; contador < this.arrayThread.size(); contador++) {
            String loginPrueba = this.arrayThread.get(contador).getProtocol().getLogin();

            if (loginPrueba.equals(login)) {
                message = "PROTOCOLOCRISTONARY1.0#ADIOSXULO#" + login + "#" + token;
                this.arrayThread.get(contador).getSocket().close();
                this.arrayThread.get(contador).getIn().close();
                this.arrayThread.get(contador).getOut().close();
                this.arrayThread.remove(contador);
            }
        }
        return message;
    }

    public String refreshWords() throws SQLException {

        int total = arrayESP.size() + arrayING.size();
        System.out.println("NOS DISPONEMOS A REALISZAR EL METODO PARA REFRESCAR EN EL SERVIDOR");

        String messaje = "PROTOCOLCRISTOPOP1.0#REFRESH_WORDS#" + total + "#";
        String aux = null;
        
        this.arrayESP = this.words_ESP_Controller.getTableWords_ESP();
        this.arrayING = this.words_ING_Controller.getTableWords_ING();

        for (int contador = 0; contador < arrayESP.size(); contador++) {

            aux = arrayESP.get(contador).getCod_palabra() + "@" + arrayESP.get(contador).getWord_ESP() + "@" + arrayESP.get(contador).getDefinition_ESP();

            if (contador != arrayESP.size() - 1) {
                messaje = messaje + aux + "#";
            } else {
                messaje = messaje + aux;
            }
        }

        messaje = messaje + "#";
        messaje = messaje + "ESP" + "#";

        for (int contador = 0; contador < arrayING.size(); contador++) {

            aux = arrayING.get(contador).getCod_palabra() + "@" + arrayING.get(contador).getWord_ING() + "@" + arrayING.get(contador).getDefinition_ING();

            if (contador != arrayING.size() - 1) {
                messaje = messaje + aux + "#";
            } else {
                messaje = messaje + aux;
            }
        }
        messaje = messaje + "#";
        messaje = messaje + "ING";

        return messaje;
    }

    public String createRoute(String nombre, String login) {

        String route = null;

        route = "C:/Users/eduar/Desktop/cristonary/" + login + "/" + nombre + "/" + nombre + ".png";

        return route;
    }

    public synchronized String createWord_ESP(String nombre, String description, String login) throws IOException, SQLException {

        String route = createRoute(nombre, login);
        String cod = null;
        String message = null;

        this.word = new Word();
        this.word.createWord(route, login);
        WordsController wordsController = new WordsController();

        this.arrayWord = wordsController.getTableWords();

        for (int contador = 0; contador < this.arrayWord.size(); contador++) {
            System.out.println("ESTO TIENE: " + this.arrayWord.get(contador).getCod_word());
            if (contador == this.arrayWord.size() - 1) {
                cod = this.arrayWord.get(contador).getCod_word();
            }
        }

        this.word_ESP = new Word_ESP();
        this.word_ESP.createWord_ESP(nombre, description, Integer.valueOf(cod));

        message = "PROTOCOLCRISTONARY1.0#WORD_CREATED_ESP#" + "#" + nombre + "#" + description + "#" + login;

        return message;
    }

    public synchronized String createWord_ING(String nombre, String description, String login) throws IOException, SQLException {

        String route = createRoute(nombre, login);
        String cod = null;
        String message = null;

        this.word = new Word();
        this.word.createWord(route, login);
        wordsController = new WordsController();

        this.arrayWord = wordsController.getTableWords();

        for (int contador = 0; contador < this.arrayWord.size(); contador++) {
            System.out.println("ESTO TIENE: " + this.arrayWord.get(contador).getCod_word());
            if (contador == this.arrayWord.size() - 1) {
                cod = this.arrayWord.get(contador).getCod_word();
            }
        }

        this.word_ING = new Word_ING();
        this.word_ING.createWord_ING(nombre, description, Integer.valueOf(cod));

        message = "PROTOCOLCRISTONARY1.0#WORD_CREATED_ING#" + "#" + nombre + "#" + description + "#" + login;

        return message;
    }
}
