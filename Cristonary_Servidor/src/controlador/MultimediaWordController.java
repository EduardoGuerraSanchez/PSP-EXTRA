package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import modelo.Word;
import modelo.Word_ESP;

public class MultimediaWordController {

    private ArrayList<Word_ESP> arrayWord_ESP;
    private PrintWriter out;
    private Words_ESP_Controller words_ESP_Controller;
    private int i;
    private ArrayList<Word> array;
    private WordsController wordsController;

    public MultimediaWordController(Socket socket) throws IOException {
        arrayWord_ESP = new ArrayList<>();
        array = new ArrayList<>();
        words_ESP_Controller = new Words_ESP_Controller();
        wordsController = new WordsController();
        out = new PrintWriter(socket.getOutputStream(), true);
    }
    
    public String getTamanioMultimedia() throws SQLException, IOException{
        String a = null;
        
        arrayWord_ESP = words_ESP_Controller.getTableWords_ESP();
        array = wordsController.getTableWords();
        
        a = String.valueOf(array.get(i).getMultimediaWord().getMultimediaSize());
        return a;
    }

    public void sendMultimedia(String pk) throws IOException, SQLException {
        boolean find = false;

        arrayWord_ESP = words_ESP_Controller.getTableWords_ESP();
        array = wordsController.getTableWords();

        for (int contador = 0; contador < arrayWord_ESP.size() && find == false; contador++) {
            if (pk.equals(arrayWord_ESP.get(contador).getCod_palabra())) {
                find = true;
                this.i = contador;
            }
        }
        arrayWord_ESP.get(this.i).getMultimediaWord().initializeMultimedia(array.get(this.i).getMultimedia());
        sendFragmentByte(this.i);
    }

    public void sendFragmentByte(int id) throws IOException {
        int size = 512;
        int totalSize = 0;
        int fragment = 0;
        String codificar;

        while (totalSize < arrayWord_ESP.get(id).getMultimediaWord().getMultimediaSize()) {
            
            arrayWord_ESP.get(id).getMultimediaWord().bytsMultimedia = new byte[size];
            fragment = arrayWord_ESP.get(id).getMultimediaWord().dataIS.read(arrayWord_ESP.get(id).getMultimediaWord().bytsMultimedia, 0, 512);
            
            totalSize += 512;

            System.out.println(Arrays.toString(arrayWord_ESP.get(id).getMultimediaWord().bytsMultimedia));

            System.out.println("YA VAN: " + totalSize + "de: " + arrayWord_ESP.get(id).getMultimediaWord().getMultimediaSize());

            codificar = Base64.getEncoder().encodeToString(arrayWord_ESP.get(id).getMultimediaWord().bytsMultimedia);

            String message = "PROTOCOLOCRISTONARY1.0" + "#" + arrayWord_ESP.get(id).getCod_palabra() + "#" + size + "#" + codificar;
            this.out.println(message);
        }
    }
}