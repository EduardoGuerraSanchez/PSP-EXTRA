package protocolo;

import controlador.ProtocolController;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Word;
import modelo.Word_ESP;

public class Protocol {

    private final char SIGN = '#';
    private final String PROTOCOL = "PROTOCOLOCRISTONARY1.0";
    private final String LOGIN = "LOGIN";
    private final String AVAIBLE = "AVAIBLE_WORDS";
    private final String WELLCOME = "WELLCOME";
    private final String ERROR = "ERROR";
    private final String GET_WORD = "GET_WORD";
    private ProtocolController protocolController;

    public String processInput(String theInput) throws SQLException {

        protocolController = new ProtocolController();

        String theOutput = null;
        this.protocolController.setLoginProtocol(theInput);

        if (theInput.contains(PROTOCOL + SIGN + LOGIN)) {
            if (this.protocolController.checkUser() == true) {
                theOutput = this.protocolController.OK(theOutput);
                theOutput = this.protocolController.insertSecretWord(theOutput);
            } else {
                theOutput = this.protocolController.ERROR(theOutput);
            }
        }
        if (theInput.contains(GET_WORD)) {
            theOutput = this.getWords_ESP();
            theOutput = "PROTOCOLOCRISTONARY1.0#AVAIBLE_WORDS#" + theOutput;
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
