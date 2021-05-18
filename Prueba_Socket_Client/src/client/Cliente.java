package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    private final String IP = "192.168.43.51";
    private int puerto = 4444;
    private Socket socket;
    private PrintWriter out;

    public int getPuerto() {
        return puerto;
    }

    private void abrirPuerto() {

        //Creo el socket con la IP y el puerto
        try {
            this.socket = new Socket(IP, this.getPuerto());
        } catch (IOException ex) {
        }
    }

    private void enviarMensaje() {

        try {
            this.out = new PrintWriter(this.socket.getOutputStream(), true);
            
            System.out.println("Vamos a proceder a enviar el mensaje desde el cliente con el out.println()");

            this.out.println("hola");

            HebraCliente hebraCliente = new HebraCliente(this.socket);
            hebraCliente.getHebra().start();

        } catch (IOException ex) {
        }
    }

    public void iniciar() {
        abrirPuerto();
        enviarMensaje();
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.iniciar();
    }

}
