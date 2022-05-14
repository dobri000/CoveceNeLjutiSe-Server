package com.server.lib;

public class Soba {

    int brojSobe;
    KlijentskaNit[] igraci = new KlijentskaNit[4];
    PodaciOIgracima[] podaciOIgracima = new PodaciOIgracima[4];

    public Soba(KlijentskaNit klijentskaNit) {
        igraci[0] = klijentskaNit;
        for(int i = 0; i < 4; i++){
            podaciOIgracima[i] = new PodaciOIgracima();
            podaciOIgracima[i].ime += " " + i;
            switch(i){
                case 0:
                    podaciOIgracima[i].boja = Boja.plava;
                    break;
                case 1:
                    podaciOIgracima[i].boja = Boja.crvena;
                    break;
                case 2:
                    podaciOIgracima[i].boja = Boja.zelena;
                    break;
                case 3:
                    podaciOIgracima[i].boja = Boja.zuta;
                    break;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        Soba soba = (Soba) o;
        return soba.brojSobe == this.brojSobe;
    }


}
