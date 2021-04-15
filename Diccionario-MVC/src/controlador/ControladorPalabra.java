package controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import modelo.Palabra;

public class ControladorPalabra {

    private ArrayList<Palabra> arrayControladorPalabra;

    public ControladorPalabra() {
        arrayControladorPalabra = new ArrayList<Palabra>();
    }

    public ArrayList<Palabra> getArrayControladorPalabra() {
        return arrayControladorPalabra;
    }

    public void setArrayControladorPalabra(ArrayList<Palabra> arrayControladorPalabra) {
        this.arrayControladorPalabra = arrayControladorPalabra;
    }

    public ArrayList<Palabra> getTablaPalabra() throws SQLException {

        Palabra palabra = new Palabra();

        this.setArrayControladorPalabra(palabra.getPalabraBD());

        return this.arrayControladorPalabra;
    }

    public int traerUnaPalabra(String nombre) throws SQLException {
        
        Palabra palabra = new Palabra();
        
        int numero = 0;
        
        this.setArrayControladorPalabra(palabra.getPalabraBD());

        for (int contador = 0; contador < this.arrayControladorPalabra.size(); contador++) {

            if (this.arrayControladorPalabra.get(contador).getNombre().contains(nombre)) {
                numero = contador;
            }
        }
        return numero;
    }

    public ArrayList insertarPalabra(String nombre,String definicion) throws SQLException {
        
        Palabra palabra = new Palabra();
        
        this.setArrayControladorPalabra(palabra.getPalabraBD());
        
        palabra.crearPalabra(nombre,definicion);
        
        this.arrayControladorPalabra.add(palabra);

        return this.arrayControladorPalabra;
    }
    
    public boolean comprobarSiExiste(String nombre) throws SQLException{
        
        boolean encontrado = false;
        
        Palabra palabra = new Palabra();
        
        this.setArrayControladorPalabra(palabra.getPalabraBD());
        
        for (int contador = 0; contador < this.arrayControladorPalabra.size(); contador++) {

            if (this.arrayControladorPalabra.get(contador).getNombre().contains(nombre)) {
                encontrado = true;
            }
        }
        
        return encontrado;
    }
    
    public ArrayList actualizarPalabra(String nombre,String definicion) throws SQLException{
        
        Palabra palabra = new Palabra();
        
        this.setArrayControladorPalabra(palabra.getPalabraBD());
        
//        palabra.modificarPalabra(nombre, definicion);

        for (int contador = 0; contador < this.arrayControladorPalabra.size(); contador++) {

            if (this.arrayControladorPalabra.get(contador).getNombre().contains(nombre)) {
                palabra.modificarPalabra(nombre, definicion);
            }
        }
        
        return this.arrayControladorPalabra;
        
    }
    

    public ArrayList borrarPalabra(String nombre) throws SQLException {
        
        Palabra palabra = new Palabra();
        
        this.setArrayControladorPalabra(palabra.getPalabraBD());

        palabra.eliminarPalabra(nombre);

        for (int contador = 0; contador < this.arrayControladorPalabra.size(); contador++) {
            if (this.arrayControladorPalabra.get(contador).getNombre().contains(nombre)) {
                this.arrayControladorPalabra.remove(contador);
            }
        }
        return this.arrayControladorPalabra;
    }
}