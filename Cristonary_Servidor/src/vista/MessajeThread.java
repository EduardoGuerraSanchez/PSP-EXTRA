package vista;

public class MessajeThread implements Runnable {

    private String messaje;
    private Thread text;

    public MessajeThread(String messaje) {
        this.messaje = messaje;
        text = new Thread(this);
    }

    public synchronized void run() {
        VistaServer.insertText(messaje + "\n");
    }

    public String getMessaje() {
        return messaje;
    }

    public void setMessaje(String mensaje) {
        this.messaje = mensaje;
    }

    public Thread getText() {
        return text;
    }

    public void setText(Thread text) {
        this.text = text;
    }
}
