package com.server;

import com.server.lib.KlijentskaNit;
import com.server.lib.Soba;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Main {

    static LinkedList<KlijentskaNit> listaKlijenata = new LinkedList<KlijentskaNit>();
    static LinkedList<Soba> listaSoba = new LinkedList<Soba>();

    public static void main(String[] args) {
        try {
            //PORT NA KOJI SERVER PRIMA ZAHTEVE JE 6666
            ServerSocket serverSocket = new ServerSocket(6666);
            //PETLJA ZA PRIMANJE SERVERA
            while(true){
                Socket socket = serverSocket.accept();
                KlijentskaNit klijentskaNit = new KlijentskaNit(socket);
                listaKlijenata.add(klijentskaNit);
                azurirajListe();
                klijentskaNit.start();
            }
        } catch (IOException e) {
            System.out.println("Doslo je do problema sa konekcijom");
        }
    }

    //AZURIRANJE LISTA NAKON IZMENA
    public static void azurirajListe(){
        for(KlijentskaNit klijentskaNit : listaKlijenata){
            klijentskaNit.listaSoba = listaSoba;
            klijentskaNit.listaKlijenata = listaKlijenata;
        }
    }
}
