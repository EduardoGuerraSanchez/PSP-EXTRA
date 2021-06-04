package protocolo;

import controlador.MultimediaWordController;
import controlador.ProtocolController;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.MultimediaWord;
import modelo.Word;
import modelo.Word_ESP;

public class Protocol {

    private final String SIGN = "#";
    private final String PROTOCOL = "PROTOCOLOCRISTONARY1.0";
    private final String LOGIN = "LOGIN";
    private final String GET_WORD = "GET_WORD";
    private final String GET_SPECIFIC_WORD = "GET_SPECIFIC_WORD";
    private final String PREPARED_TO_RECEIVE = "PREPARED_TO_RECEIVE";
    private ProtocolController protocolController;
    private MultimediaWordController multimediaWordController;
    private String[] cadena;
    private String login;
    private String i;
    Socket socket;

    public Protocol(Socket socket) throws IOException {
        protocolController = new ProtocolController();
        multimediaWordController = new MultimediaWordController(socket);
    }

    public String processInput(String theInput) throws SQLException, IOException {

        String theOutput = null;
        this.protocolController.setLoginProtocol(theInput);

        if (theInput.contains(PROTOCOL + SIGN + LOGIN)) {
            if (this.protocolController.checkUser() == true) {
                theOutput = this.protocolController.OK(theOutput);
                this.cadena = theOutput.split(SIGN);
                this.login = this.cadena[2];
                theOutput = this.protocolController.insertSecretWord(theOutput);
            } else {
                theOutput = this.protocolController.ERROR(theOutput);
            }
        }

        if (theInput.contains(GET_WORD)) {
            theOutput = this.protocolController.getWords();
        }

        if (theInput.contains(GET_SPECIFIC_WORD)) {

            String name = null;
            
            this.i = protocolController.sizeMultimedia();

            System.out.println("ESTO VA A SER: " + theInput);
            cadena = theInput.split(SIGN);
            // cadena --> PROTOCOLCRISTONARY1.0#GET_SPECIFIC_WORD#edeuve#1772031544#1 SIN LAS #
            System.out.println(this.cadena[0]);//PROTOCOLCRISTONARY1.0
            System.out.println(this.cadena[1]);//GET_SPECIFIC_WORD
            System.out.println(this.cadena[2]);//edeuve
            System.out.println(this.cadena[3]);//-707877684
            System.out.println(this.cadena[4]);//1

            theOutput = this.protocolController.get_specific_word(cadena[4]);
            name = this.protocolController.getNameFromSpecificWord();
            theOutput = "PROTOCOLCRISTONARY1.0#GET_SPECIFIC_WORD#" + cadena[4] + "@" + name + "@" + this.login + "@" + theOutput + "#" + ".png" + "#" + 11868;
            System.out.println("CUANTO ES ESTO: " + this.i);
        }

        if (theInput.contains(PREPARED_TO_RECEIVE)) {
            System.out.println("DALEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
            System.out.println(cadena[4]);
            multimediaWordController.sendMultimedia(cadena[4]);
        }

        return theOutput;
    }

    public String getWords_ESP() throws SQLException {

        ArrayList<Word_ESP> array = new ArrayList<Word_ESP>();

        protocolController = new ProtocolController();

        array = protocolController.sendWords_ESP();

        String a = array.size() + "#" + array.get(0).getWord_ESP() + "#";

        for (int contador = 1; contador < array.size(); contador++) {
            a += array.get(contador).getWord_ESP() + "#";
        }

        return a;
    }

}
