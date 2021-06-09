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
import modelo.Word_ING;

public class MultimediaWordController {

    private ArrayList<Word_ESP> arrayWord_ESP;
    private ArrayList<Word_ING> arrayWord_ING;
    private PrintWriter out;
    private Words_ESP_Controller words_ESP_Controller;
    private Words_ING_Controller words_ING_Controller;

    private int i;
    private ArrayList<Word> array;
    private WordsController wordsController;

    public MultimediaWordController(Socket socket) throws IOException {
        arrayWord_ESP = new ArrayList<>();
        arrayWord_ING = new ArrayList<>();
        array = new ArrayList<>();
        words_ESP_Controller = new Words_ESP_Controller();
        words_ING_Controller = new Words_ING_Controller();
        wordsController = new WordsController();
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public String getTamanioMultimedia() throws SQLException, IOException {
        String a = null;

        arrayWord_ESP = words_ESP_Controller.getTableWords_ESP();
        array = wordsController.getTableWords();

        a = String.valueOf(array.get(i).getMultimediaWord().getMultimediaSize());
        return a;
    }

    public void sendMultimedia(String pk, String languaje) throws IOException, SQLException {
        boolean find = false;

        arrayWord_ESP = words_ESP_Controller.getTableWords_ESP();
        arrayWord_ING = words_ING_Controller.getTableWords_ING();

        array = wordsController.getTableWords();

        if ("ESP".equals(languaje)) {
            for (int contador = 0; contador < array.size() && find == false; contador++) {
                if (pk.equals(array.get(contador).getCod_word())) {
                    find = true;
                    this.i = contador;
                }
            }
            array.get(this.i).getMultimediaWord().initializeMultimedia(array.get(this.i).getMultimedia());
            System.out.println("NO ES EL PROTOCOLO espppp,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,, " + this.i);

            sendFragmentByte(this.i, languaje);
        }

        if ("ING".equals(languaje)) {
            for (int contador = 0; contador < array.size() && find == false; contador++) {
                if (pk.equals(array.get(contador).getCod_word())) {
                    find = true;
                    this.i = contador;
                }
            }
            array.get(this.i).getMultimediaWord().initializeMultimedia(array.get(this.i).getMultimedia());
            System.out.println("NO ES EL PROTOCOLO ingggg,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,, " + this.i);

            sendFragmentByte(this.i, languaje);
        }

    }

    public void sendFragmentByte(int id, String languaje) throws IOException {
        int size = 512;
        int totalSize = 0;
        int fragment = 0;
        String codificar;

        if ("ESP".equals(languaje)) {
            while (totalSize < array.get(id).getMultimediaWord().getMultimediaSize()) {

                array.get(id).getMultimediaWord().bytsMultimedia = new byte[size];
                fragment = array.get(id).getMultimediaWord().dataIS.read(array.get(id).getMultimediaWord().bytsMultimedia, 0, 512);

                totalSize += 512;

                System.out.println(Arrays.toString(array.get(id).getMultimediaWord().bytsMultimedia));

                System.out.println("YA VAN: " + totalSize + "de: " + array.get(id).getMultimediaWord().getMultimediaSize());

                codificar = Base64.getEncoder().encodeToString(array.get(id).getMultimediaWord().bytsMultimedia);

                String message = "PROTOCOLOCRISTONARY1.0" + "#" + array.get(id).getCod_word() + "#" + size + "#" + codificar;
                this.out.println(message);
            }
        }

        if ("ING".equals(languaje)) {
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
}
