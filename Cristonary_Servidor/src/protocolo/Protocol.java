package protocolo;

import controlador.MultimediaWordController;
import controlador.ProtocolController;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Word_ESP;

public class Protocol {

    private final String SIGN = "#";
    private final String PROTOCOL = "PROTOCOLOCRISTONARY1.0";
    private final String LOGIN = "LOGIN";
    private final String GET_WORD = "GET_WORD";
    private final String GET_SPECIFIC_WORD = "GET_SPECIFIC_WORD";
    private final String PREPARED_TO_RECEIVE = "PREPARED_TO_RECEIVE";
    private final String REFRESH = "REFRESH";
    private final String BYE = "BYE";
    private ProtocolController protocolController;
    private MultimediaWordController multimediaWordController;
    private String[] cadena;
    private String login;
    private String token;
    

    public Protocol(Socket socket,ArrayList array) throws IOException, SQLException {
        protocolController = new ProtocolController(array);
        multimediaWordController = new MultimediaWordController(socket);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
            int tamanio = 0;
            String type = null;
            String creator = null;
            String definition = null;

            System.out.println("ESTO VA A SER: " + theInput);
            cadena = theInput.split(SIGN);
            System.out.println(this.cadena[0]);//PROTOCOLCRISTONARY1.0
            System.out.println(this.cadena[1]);//GET_SPECIFIC_WORD
            System.out.println(this.cadena[2]);//edeuve
            System.out.println(this.cadena[3]);//-707877684
            this.token = this.cadena[3];
            System.out.println(this.cadena[4]);//1
            System.out.println(this.cadena[5]);//ESP / ING

            this.protocolController.inizializarMultimedia(this.cadena[4]);
            definition = this.protocolController.get_specific_definition(this.cadena[4],this.cadena[5]);
            name = this.protocolController.getNameFromSpecificWord(this.cadena[4],this.cadena[5]);
            tamanio = this.protocolController.getSizeMultimedia();
            type = this.protocolController.getType();
            creator = this.protocolController.getCreator(this.cadena[4]);
            
            theOutput = "PROTOCOLCRISTONARY1.0#GET_SPECIFIC_WORD#" + cadena[4] + "@" + name + "@" + creator + "@" + definition + "#" + type + "#" + tamanio + "#" + this.cadena[5];
        }

        if (theInput.contains(PREPARED_TO_RECEIVE)) {
            System.out.println(cadena[4]);
            multimediaWordController.sendMultimedia(cadena[4],this.cadena[5]);
        }
        
        if(theInput.contains(REFRESH)){
            System.out.println("onrrRRRRRREFFFFFFRRREEESSSCAAAR");
            theOutput = this.protocolController.refreshWords();
            System.out.println(theOutput);
        }
        
        if(theInput.contains(BYE)){
            System.out.println("OLE OLE OLE QUE ESTAMOS OUT");
            theOutput = protocolController.deleteUser(this.login, this.token);
        }
        
        
        
        System.out.println("EL SERVIDOR PROTOCOLO VA A DEVOLVER: " + theOutput);
        return theOutput;
    }

    public String getWords_ESP() throws SQLException, IOException {

        ArrayList<Word_ESP> array = new ArrayList<Word_ESP>();

        array = protocolController.getArrayESP();

        String a = array.size() + "#" + array.get(0).getWord_ESP() + "#";

        for (int contador = 1; contador < array.size(); contador++) {
            a += array.get(contador).getWord_ESP() + "#";
        }

        return a;
    }
}
