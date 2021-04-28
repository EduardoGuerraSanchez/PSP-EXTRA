package cerrojos;


import java.util.ArrayList;

public class Main {
    
     public static void main(String[] args) throws InterruptedException {

        int tamanioBuffer = Integer.parseInt(args[0]);
        int tamanioHebraProductora = Integer.parseInt(args[1]);
        int hebrasProductorasInfinitas = Integer.parseInt(args[2]);
        int tamanioHebraConsumidoras = Integer.parseInt(args[3]);
        int hebrasConsumidorasInfinitas = Integer.parseInt(args[4]);


        Buffer bufferArrayCompartido = new Buffer(tamanioBuffer);

        ArrayList<Productor> arrayHebrasProductoras = new ArrayList<Productor>();
        System.out.println(hebrasProductorasInfinitas);

        for (int contador = 0; contador < tamanioHebraProductora; contador++) {
            Productor productor = new Productor("La hebra Productora",bufferArrayCompartido,hebrasProductorasInfinitas);
            arrayHebrasProductoras.add(productor);
            arrayHebrasProductoras.get(contador).thr.start();
        }

        ArrayList<Consumidor> arrayHebrasConsumidor = new ArrayList<Consumidor>();
        for (int contador = 0; contador < tamanioHebraConsumidoras; contador++) {

            Consumidor consumidor = new Consumidor("La hebra Consumidora", bufferArrayCompartido,hebrasConsumidorasInfinitas);
            arrayHebrasConsumidor.add(consumidor);
            arrayHebrasConsumidor.get(contador).thr.start();
        
        }
     }
    
}
