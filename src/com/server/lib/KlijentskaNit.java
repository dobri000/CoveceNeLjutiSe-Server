package com.server.lib;

import java.net.Socket;
import java.util.LinkedList;

public class KlijentskaNit extends Thread{
    public LinkedList<Soba> listaSoba = new LinkedList<Soba>();
    public LinkedList<KlijentskaNit> listaKlijenata = new LinkedList<KlijentskaNit>();
    Socket socket;

    public KlijentskaNit(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        
    }
}
