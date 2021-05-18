package kk;

import java.sql.SQLException;
import modelo.Palabra;

public class KKProtocol {

    /*
    public String processInput(String theInput) throws SQLException {

        String theOutput = null;
        this.con = new ControladorProtocolo();
        theOutput ="PROTOCOLCRISTOPOP1.0#LOGIN";
        this.con.setProtocoloLogin(theInput);
        
        if(theOutput.contains("PROTOCOLCRISTOPOP1.0#LOGIN")){
            if(this.con.comprobarLoginBD() == true){
                theOutput = this.con.devolverOK(theOutput);  
            } 
            else{
                theOutput = this.con.devolverERROR(theOutput);
            }
        }
        return theOutput;
    }
     */
    public String processInput(String theInput) throws SQLException {
        Palabra palabra = new Palabra();
        String theOutput = null;
        boolean dentro = false;

//        if (theInput.contains("parraf0")) {
//            theOutput = "MUY BUENAS CABALLERO";
//            dentro = true;
//        }
//
//        if (theInput.contains("adios")) {
//            dentro = true;
//            theOutput = "ADIOS AMIGO";
//        }
//
//        if (theInput.contains("diselo")) {
//            theOutput = "TU VERAS";
//            dentro = true;
//        }
        if (theInput.contains("parrafo")) {
            theOutput = palabra.getPalabraBD().get(0).toString();
            dentro = true;
        }

        if (theInput.contains("diccionario")) {
            dentro = true;
            theOutput = palabra.getPalabraBD().get(1).toString();
        }

        if (theInput.contains("pais")) {
            theOutput = palabra.getPalabraBD().get(2).toString();
            dentro = true;
        }
        
        if (theInput.contains("libro")) {
            theOutput = palabra.getPalabraBD().get(3).toString();
            dentro = true;
        }

        if (dentro == false) {
            theOutput = "NO ME HAS DICHO NADA QUE ESTE DENTRO DEL PROTOCOLO";
        }

        return theOutput;
    }

}
