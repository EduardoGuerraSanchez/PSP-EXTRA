package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public class MultimediaWord implements Serializable {

    private String route;
    public File fich;
    public FileInputStream dataIS;
    private int multimediaSize;
    public byte[] bytsMultimedia;

    public MultimediaWord() throws FileNotFoundException, IOException {
        this.route = "";
        fich = null;
        dataIS = null;
        multimediaSize = 0;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getMultimediaSize() {
        return multimediaSize;
    }

    public void setMultimediaSize(long multimediaSize) {
        this.multimediaSize = (int) multimediaSize;
    }

    public void initializeMultimedia(String route) throws FileNotFoundException, IOException {
        this.setRoute(route);
        System.out.println("-------------------------------->" + route);
        fich = new File(route);
        dataIS = new FileInputStream(fich);
        multimediaSize = (int) fich.length();
        System.out.println("------------------------------><>><<<<<><<: " + this.multimediaSize);
    }
}