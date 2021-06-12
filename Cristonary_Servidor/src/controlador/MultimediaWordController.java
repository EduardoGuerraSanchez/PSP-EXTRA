package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import modelo.Word;

public class MultimediaWordController {

    private PrintWriter out;

    private ArrayList<Word> array;
    private WordsController wordsController;

    public MultimediaWordController(Socket socket) throws IOException {
        array = new ArrayList<>();
        wordsController = new WordsController();
        out = new PrintWriter(socket.getOutputStream(), true);
    }

//    public String getTamanioMultimedia() throws SQLException, IOException {
//        String a = null;
//
//        arrayWord_ESP = words_ESP_Controller.getTableWords_ESP();
//        array = wordsController.getTableWords();
//
//        a = String.valueOf(this.array.get(i).getMultimediaWord().getMultimediaSize());
//        return a;
//    }

    public void sendMultimedia(String pk, String languaje) throws IOException, SQLException {
        boolean find = false;

        this.array = this.wordsController.getTableWords();

        int contador;
        for (contador = 0; contador < this.array.size() && find == false; contador++) {
            if (pk.equals(this.array.get(contador).getCod_word())) {
                find = true;
            }
        }
        this.array.get(contador - 1).getMultimediaWord().initializeMultimedia(this.array.get(contador - 1).getMultimedia());

        sendFragmentByte(contador - 1, languaje);

    }

    public void sendFragmentByte(int id, String languaje) throws IOException {
        int size = 512;
        int totalSize = 0;
        int fragment = 0;
        String codificar;

        while (totalSize < array.get(id).getMultimediaWord().getMultimediaSize()) {

            array.get(id).getMultimediaWord().bytsMultimedia = new byte[size];
            fragment = array.get(id).getMultimediaWord().dataIS.read(array.get(id).getMultimediaWord().bytsMultimedia, 0, 512);

            totalSize += 512;

            System.out.println(Arrays.toString(array.get(id).getMultimediaWord().bytsMultimedia));

            System.out.println("YA VAN: " + totalSize + "de: " + array.get(id).getMultimediaWord().getMultimediaSize());

            codificar = Base64.getEncoder().encodeToString(array.get(id).getMultimediaWord().bytsMultimedia);

            System.out.println(array.get(id).getCod_word());
            String message = "PROTOCOLOCRISTONARY1.0" + "#" + array.get(id).getCod_word() + "#" + size + "#" + codificar;
            this.out.println(message);
        }
    }
}