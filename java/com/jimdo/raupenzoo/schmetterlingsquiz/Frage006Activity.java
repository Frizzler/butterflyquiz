package com.jimdo.raupenzoo.schmetterlingsquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Frage006Activity extends AbstractFrageActivity {

    /**
     * Merkt sich, ob der Benutzer bereits auf das Ei geklickt hat
     */
    private boolean eigeklickt = false;
    /**
     * Merkt sich, ob der Benutzer bereits auf die Raupe geklickt hat
     * und dieser Klick nach dem Klick auf das Ei erfolgte
     */
    private boolean raupegeklickt = false;
    /**
     * Merkt sich, ob der Benutzer bereits auf die Puppe geklickt hat
     * und dieser Klick nach dem Klick auf die Raupe erfolgte
     */
    private boolean puppegeklickt = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * Frage und Bilder setzen.
         * Die Werte werden in der Oberklasse gesetzt,
         * damit sie dort verfügbar sind für Vergrößerungen.
         */
        imagelinksoben   = R.drawable.frage006_1;
        imagerechtsoben  = R.drawable.frage006_2;
        imagelinksunten  = R.drawable.frage006_3;
        imagerechtsunten = R.drawable.frage006_4;
        fragetext        = R.string.frage006;
        super.onCreate(savedInstanceState);
    }


    /**
     * Wenn auf Button A (unter Bild 1, Raupe) geklickt wird.
     */
    public void sendbuttona(View view) {
        if (puppegeklickt) {
            loesung.setText("Die Raupe hatten wir schon.");
        }
        else if (eigeklickt) {
            loesung.setText("Richtig! Aus dem Ei schlüpft eine gefräßige Raupe.");
            imageviewlinksoben.setImageResource(R.drawable.frage006_1_richtig);
            raupegeklickt = true;
            /*
             * Die zuvor evtl. ausgegrauten anderen Bilder wieder zurücksetzen:
             */
            imageviewrechtsoben.setImageResource(R.drawable.frage006_2);
            imageviewlinksunten.setImageResource(R.drawable.frage006_3);
        }
        else {
            loesung.setText("Leider nicht richtig.");
            fehler++;
            imageviewlinksoben.setImageResource(R.drawable.frage006_1_falsch);
            moeoep(soundistaktiviert);
        }
        scrolldown(5);
    }

    /**
     * Wird aufgerufen wenn der Benutzer auf Button B klickt.
     */
    public void sendbuttonb(View view) {
        if (raupegeklickt) {
            loesung.setText("Richtig! Beim Aurorafalter dauert die Puppenruhe neun Monate.");
            imageviewrechtsoben.setImageResource(R.drawable.frage006_2_richtig);
            puppegeklickt = true;
            /*
             * Das zuvor evtl. ausgegraute Falterbild wieder zurücksetzen:
             */
            imageviewlinksunten.setImageResource(R.drawable.frage006_3);
        }
        else {
            loesung.setText("Leider nicht richtig.");
            fehler++;
            imageviewrechtsoben.setImageResource(R.drawable.frage006_2_falsch);
            moeoep(soundistaktiviert);
        }
        scrolldown(100);
    }

    /**
     * Wenn auf Button C (unter Bild 3, Falter) geklickt wird.
     */
    public void sendbuttonc(View view) {
        if (puppegeklickt) {
            loesung.setText("Richtig! Der Schmetterling legt dann wieder Eier, übrigens über 100 Stück pro Weibchen.");
            imageviewlinksunten.setImageResource(R.drawable.frage006_3_richtig);
            weiterbutton.setVisibility(View.VISIBLE);
            bling(soundistaktiviert);
        }
        else {
            loesung.setText("Leider nicht richtig.");
            fehler++;
            imageviewlinksunten.setImageResource(R.drawable.frage006_3_falsch);
            moeoep(soundistaktiviert);
        }
        scrolldown(5);
    }

    /**
     * Wenn auf Button D (unter Bild 4, Ei) geklickt wird.
     */
    public void sendbuttond(View view) {
        eigeklickt = true;
        if (!raupegeklickt) {
            loesung.setText("Richtig! Das Leben eines Schmetterlings beginnt als winziges Ei.");
            imageviewrechtsunten.setImageResource(R.drawable.frage006_4_richtig);
            /*
             * Die zuvor evtl. ausgegrauten anderen Bilder wieder zurücksetzen:
             */
            imageviewrechtsoben.setImageResource(R.drawable.frage006_2);
            imageviewlinksunten.setImageResource(R.drawable.frage006_3);
            imageviewlinksoben.setImageResource(R.drawable.frage006_1);
        }
        else {
            loesung.setText("Das Ei hatten wir schon.");
        }
        scrolldown(5);
    }


    /**
     * Wird aufgerufen wenn der Benutzer auf "weiter zur nächsten Frage" klickt
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, Frage007Activity.class);
        // Frage-7-Activity starten und fehler-Anzahl übergeben:
        intent.putExtra("fehler", fehler);
        // Die Info durchschleifen, ob Sounds aktiviert sind:
        intent.putExtra("soundistaktiviert", soundistaktiviert);
        startActivity(intent);
    }
}