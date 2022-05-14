package com.server.lib;

import com.server.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Random;

public class KlijentskaNit extends Thread{
    public LinkedList<Soba> listaSoba = new LinkedList<Soba>();
    public LinkedList<KlijentskaNit> listaKlijenata = new LinkedList<KlijentskaNit>();
    private Socket socket;
    private boolean vrti = true;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Soba trenutnaSoba;

    public KlijentskaNit(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            while(vrti){
                int poruka = (int) in.readObject();
                switch (poruka){
                    case 1:
                        kreirajSobu();
                        break;
                    case 2:
                        pridruziSeSobi();
                        break;
                    case 3:
                        izlaz();
                        break;
                }
            }

        } catch(IOException e){

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void izlaz() {
        //TODO: METODA ZA IZLAZAK
    }

    private void pridruziSeSobi() {
        //TODO: NAPRAVITI METODU ZA PRIDRUZIVANJE SOBI
        try{
            int brojSobe = (int) in.readObject();
            for(Soba soba : listaSoba){
                if(soba.brojSobe == brojSobe){
                    for(int i = 1; i < 4; i++){
                        if(soba.igraci[i] == null){
                            soba.igraci[i] = this;
                            trenutnaSoba = soba;
                            Main.azurirajListe(listaKlijenata, listaSoba);
                            //TODO: poslati azuriranje sobe, saigracima iz sobe
                            out.writeObject(true);
                            //TODO: otkloniti io exception koji se ovde ispod stvara
                            for(int j = 0; j < 4; j++){
                                out.writeObject(trenutnaSoba.podaciOIgracima[j]);
                            }
                            return;
                        }
                    }
                    out.writeObject(false);
                    return;
                }
            }
            out.writeObject(false);
        }catch (IOException e){
            //TODO: dodati izlaz
        }catch(ClassNotFoundException e){
            System.out.println("Nije poslat pravi podatak");
        }
    }

    private void kreirajSobu() {
        Random random = new Random();
        Soba soba = new Soba(this);
        soba.brojSobe = random.nextInt(10000);
        while(listaSoba.contains(soba) == true){
            soba.brojSobe = random.nextInt(10000);
        }
        trenutnaSoba = soba;
        listaSoba.add(soba);
        Main.azurirajListe(listaKlijenata, listaSoba);
        try{
            out.writeObject(soba.brojSobe);
        } catch (IOException e){
            //TODO: dodati izlaz
        }
    }
}
