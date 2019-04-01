package com.jimdo.raupenzoo.schmetterlingsquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Frage007Activity extends AbstractFrageActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * Frage und Bilder setzen.
         * Die Werte werden in der Oberklasse gesetzt,
         * damit sie dort verfügbar sind für Vergrößerungen.
         */
        imagelinksoben   = R.drawable.frage007_1;
        imagerechtsoben  = R.drawable.frage007_2;
        imagelinksunten  = R.drawable.frage007_3;
        imagerechtsunten = R.drawable.frage007_4;
        fragetext = R.string.frage007;
        super.onCreate(savedInstanceState);
    }


    /**
     * Wenn auf Button A (unter Bild 1) geklickt wird,
     * dieses ausgrauen, dem Benutzer mitteilen,
     * dass das leider nicht stimmt und falls der Sound aktiviert ist Mööp machen.
     */
    public void sendbuttona(View view) {
        loesung.setText("Leider nicht richtig.\r\nDas ist die Raupe der Ahorn-Rindeneule.");
        fehler++;
        imageviewlinksoben.setImageResource(R.drawable.frage007_1_checked);
        moeoep(soundistaktiviert);
        scrolldown(5);
    }

    /**
     * Wenn auf Button B (unter Bild 2) geklickt wird,
     * dieses ausgrauen, dem Benutzer mitteilen,
     * dass das leider nicht stimmt und falls der Sound aktiviert ist Mööp machen.
     */
    public void sendbuttonb(View view) {
        loesung.setText("Leider nicht richtig.\r\nDas ist die Raupe vom Kleinen Fuchs.");
        fehler++;
        imageviewrechtsoben.setImageResource(R.drawable.frage007_2_checked);
        moeoep(soundistaktiviert);
        scrolldown(5);
    }

    /**
     * Wenn auf Button C (unter Bild 3) geklickt wird,
     * dieses ausgrauen, dem Benutzer mitteilen,
     * dass das leider nicht stimmt und falls der Sound aktiviert ist Mööp machen.
     */
    public void sendbuttonc(View view) {
        loesung.setText("Leider nicht richtig.\r\nDas ist die Raupe des Mittelrhein-Widderchens.");
        fehler++;
        imageviewlinksunten.setImageResource(R.drawable.frage007_3_checked);
        moeoep(soundistaktiviert);
        scrolldown(5);
    }

    /**
     * Wird aufgerufen wenn der Benutzer auf Button D klickt.
     * Bild 4 gelbgrün umrahmen, den Weiter-Button einblenden
     * und falls der Sound aktiviert ist Bling machen.
     */
    public void sendbuttond(View view) {
        loesung.setText("Richtig!\r\nSpannerraupen haben weniger Bauchbeinpaare und machen beim Laufen daher einen Bogen.");
        weiterbutton.setVisibility(View.VISIBLE);
        imageviewrechtsunten.setImageResource(R.drawable.frage007_4_checked);
        bling(soundistaktiviert);
        scrolldown(100);
    }

    /**
     * Wird aufgerufen wenn der Benutzer auf "weiter zur nächsten Frage" klickt
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, Frage008Activity.class);
        // Frage-8-Activity starten und fehler-Anzahl übergeben:
        intent.putExtra("fehler", fehler);
        // Die Info durchschleifen, ob Sounds aktiviert sind:
        intent.putExtra("soundistaktiviert", soundistaktiviert);
        startActivity(intent);
    }

}