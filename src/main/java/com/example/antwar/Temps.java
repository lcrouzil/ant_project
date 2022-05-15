package com.example.antwar;

import java.nio.file.FileSystems;
import java.util.TimerTask;
import java.util.Timer;

public class Temps extends TimerTask {

    int cpt = 0;
    int TempsRefresh = 50;
    int retour = 2345;

    private static Timer chrono;

    /**
     * appel le run
     */
    public void DepartChrono() {
        chrono = new Timer();
        chrono.scheduleAtFixedRate(new Temps(), 0, this.TempsRefresh);
    }

    @Override
    public void run() {
        //2 min de jeu donc 120000ms
        //120000÷50 nbr max de temps = 2400ms
        //je part sur 2345ms pour signal retour (retour et pose)
        this.cpt++;
        if (cpt == retour) {
            Constants.RetournerMaison = true;
        }
        if (cpt == 2400) {
            Constants.FinGame = true;
            chrono.cancel();
        }
    }
}
