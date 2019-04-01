package com.jimdo.raupenzoo.schmetterlingsquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Frage003Activity extends AbstractFrageActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * Frage und Bilder setzen.
         * Die Werte werden in der Oberklasse gesetzt,
         * damit sie dort verfügbar sind für Vergrößerungen.
         */
        imagelinksoben   = R.drawable.frage003_1;
        imagerechtsoben  = R.drawable.frage003_2;
        imagelinksunten  = R.drawable.frage003_3;
        imagerechtsunten = R.drawable.frage003_4;
        fragetext        = R.string.frage003;
        super.onCreate(savedInstanceState);
    }

    /**
     * Wenn auf Button A (unter Bild 1) geklickt wird,
     * dieses ausgrauen, dem Benutzer mitteilen,
     * dass das leider nicht stimmt und falls der Sound aktiviert ist Mööp machen.
     */
    public void sendbuttona(View view) {
        loesung.setText("Leider nicht richtig.\r\nDas ist das Landkärtchen.");
        fehler++;
        imageviewlinksoben.setImageResource(R.drawable.frage003_1_checked);
        moeoep(soundistaktiviert);
        scrolldown(5);
    }

    /**
     * Wenn auf Button B (unter Bild 2) geklickt wird,
     * dieses ausgrauen, dem Benutzer mitteilen,
     * dass das leider nicht stimmt und falls der Sound aktiviert ist Mööp machen.
     */
    public void sendbuttonb(View view) {
        loesung.setText("Leider nicht richtig.\r\nDas ist der Silbrige Perlmuttfalter.");
        fehler++;
        imageviewrechtsoben.setImageResource(R.drawable.frage003_2_checked);
        moeoep(soundistaktiviert);
        scrolldown(5);
    }

    /**
     * Wird aufgerufen wenn der Benutzer auf Button C klickt.
     * Bild 3 gelbgrün umrahmen, den Weiter-Button einblenden
     * und falls der Sound aktiviert ist Bling machen.
     */
    public void sendbuttonc(View view) {
        loesung.setText("Richtig!\r\nDas Tagpfauenauge ist auf der Unterseite schwarz wie verkohlte Rinde.");
        weiterbutton.setVisibility(View.VISIBLE);
        imageviewlinksunten.setImageResource(R.drawable.frage003_3_checked);
        bling(soundistaktiviert);
        scrolldown(100);
    }

    /**
     * Wenn auf Button D (unter Bild 4) geklickt wird,
     * dieses ausgrauen, dem Benutzer mitteilen,
     * dass das leider nicht stimmt und falls der Sound aktiviert ist Mööp machen.
     */
    public void sendbuttond(View view) {
        loesung.setText("Leider nicht richtig.\r\nDas ist das Rotbraune Ochsenauge.");
        fehler++;
        imageviewrechtsunten.setImageResource(R.drawable.frage003_4_checked);
        moeoep(soundistaktiviert);
        scrolldown(5);
    }

    /**
     * Wird aufgerufen wenn der Benutzer auf "weiter zur nächsten Frage" klickt
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, Frage004Activity.class);
        // Frage-4-Activity starten und fehler-Anzahl übergeben:
        intent.putExtra("fehler", fehler);
        // Die Info durchschleifen, ob Sounds aktiviert sind:
        intent.putExtra("soundistaktiviert", soundistaktiviert);
        startActivity(intent);
    }

}