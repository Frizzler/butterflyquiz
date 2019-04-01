package com.jimdo.raupenzoo.schmetterlingsquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Frage002Activity extends AbstractFrageActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * Frage und Bilder setzen.
         * Die Werte werden in der Oberklasse gesetzt,
         * damit sie dort verfügbar sind für Vergrößerungen.
         */
        imagelinksoben   = R.drawable.frage002_1;
        imagerechtsoben  = R.drawable.frage002_2;
        imagelinksunten  = R.drawable.frage002_3;
        imagerechtsunten = R.drawable.frage002_4;
        fragetext        = R.string.frage002;
        super.onCreate(savedInstanceState);
    }


    /**
     * Wenn auf Button A (unter Bild 1) geklickt wird,
     * dieses ausgrauen, dem Benutzer mitteilen,
     * dass das leider nicht stimmt und falls der Sound aktiviert ist Mööp machen.
     */
    public void sendbuttona(View view) {
        loesung.setText("Leider nicht richtig.\r\nDas ist das Blaukernauge.");
        fehler++;
        imageviewlinksoben.setImageResource(R.drawable.frage002_1_checked);
        moeoep(soundistaktiviert);
        scrolldown(5);
    }

    /**
     * Wird aufgerufen wenn der Benutzer auf Button B klickt.
     * Bild 2 gelbgrün umrahmen, den Weiter-Button einblenden
     * und falls der Sound aktiviert ist Bling machen.
     */
    public void sendbuttonb(View view) {
        loesung.setText("Richtig!\r\n Der Kleine Fuchs ist erkennbar an der orangeroten Grundfarbe und den blauen Randflecken.");
        weiterbutton.setVisibility(View.VISIBLE);
        imageviewrechtsoben.setImageResource(R.drawable.frage002_2_checked);
        bling(soundistaktiviert);
        scrolldown(100);
    }

    /**
     * Wenn auf Button C (unter Bild 3) geklickt wird,
     * dieses ausgrauen und dem Benutzer mitteilen,
     * dass das leider nicht stimmt.
     */
    public void sendbuttonc(View view) {
        loesung.setText("Leider nicht richtig.\r\nDas ist der Apollo.");
        fehler++;
        imageviewlinksunten.setImageResource(R.drawable.frage002_3_checked);
        moeoep(soundistaktiviert);
        scrolldown(5);
    }

    /**
     * Wenn auf Button D (unter Bild 4) geklickt wird,
     * dieses ausgrauen, dem Benutzer mitteilen,
     * dass das leider nicht stimmt und falls der Sound aktiviert ist Mööp machen.
     */
    public void sendbuttond(View view) {
        loesung.setText("Leider nicht richtig.\r\nDas ist der Blaue Eichenzipfelfalter.");
        fehler++;
        imageviewrechtsunten.setImageResource(R.drawable.frage002_4_checked);
        moeoep(soundistaktiviert);
        scrolldown(5);
    }

    /**
     * Wird aufgerufen wenn der Benutzer auf "weiter zur nächsten Frage" klickt
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, Frage003Activity.class);
        // Frage-3-Activity starten und fehler-Anzahl übergeben:
        intent.putExtra("fehler", fehler);
        // Die Info durchschleifen, ob Sounds aktiviert sind:
        intent.putExtra("soundistaktiviert", soundistaktiviert);
        startActivity(intent);
    }

}