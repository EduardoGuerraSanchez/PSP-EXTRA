package map;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

public class TimeDate extends TimerTask{
    
    private Socket socket;
    private PrintWriter out;
    
    public TimeDate(Socket socket) throws IOException{
        this.socket = socket;
        this.out = new PrintWriter(this.socket.getOutputStream(),true);
    }
    
     public String getDate(){
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
        
        return formatoFecha.format(fecha);
    }
    
    public String getTime(){
        String tiempo;
        Calendar calendario = Calendar.getInstance();
        
        tiempo = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY) + ":" + calendario.get(Calendar.MINUTE));
        
        return tiempo;
    }
    
    public void refreshTable(){
        String date = getDate();
        String time = getTime();
        
        this.out.println("PROTOCOLOCRISTONARY1.0#REFRESH#" + date + "#" + time);
    }

    @Override
    public void run() {
        refreshTable();
    }
}