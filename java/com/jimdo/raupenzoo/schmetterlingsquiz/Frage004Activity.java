package com.jimdo.raupenzoo.schmetterlingsquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Frage004Activity extends AbstractFrageActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Frage und Bilder setzen
        // (im Layout-Template sind defaultmäßig die von Frage 1 hinterlegt):
        frage.setText(R.string.frage004);
        imageviewlinksoben.setImageResource(R.drawable.frage004_1);
        imageviewrechtsoben.setImageResource(R.drawable.frage004_2);
        imageviewlinksunten.setImageResource(R.drawable.frage004_3);
        imageviewrechtsunten.setImageResource(R.drawable.frage004_4);
    }

    /**
     * Wenn auf ein Bild geklickt wird, dieses oben vergrößert anzeigen.
     * @param view
     */
    public void enlargeimagea(View view) {
        imageviewenlargeoben.setImageResource(R.drawable.frage004_1);
        imageviewenlargeoben.setVisibility(View.VISIBLE);
    }
    public void enlargeimageb(View view) {
        imageviewenlargeoben.setImageResource(R.drawable.frage004_2);
        imageviewenlargeoben.setVisibility(View.VISIBLE);
    }
    public void enlargeimagec(View view) {
        imageviewenlargeoben.setImageResource(R.drawable.frage004_3);
        imageviewenlargeoben.setVisibility(View.VISIBLE);
    }
    public void enlargeimaged(View view) {
        imageviewenlargeoben.setImageResource(R.drawable.frage004_4);
        imageviewenlargeoben.setVisibility(View.VISIBLE);
    }

    /**
     * Wenn auf das vergrößerte Bild geklickt wird, die Vergrößerung wieder ausblenden.
     * @param view
     */
    public void removeenlargeviewoben(View view) {
        imageviewenlargeoben.setVisibility(View.GONE);
    }

    /**
     * Wenn auf Button A (unter Bild 1) geklickt wird,
     * dieses ausgrauen, dem Benutzer mitteilen,
     * dass das leider nicht stimmt und falls der Sound aktiviert ist Mööp machen.
     */
    public void sendbuttona(View view) {
        loesung.setText("Leider nicht richtig.\r\nDas ist die Pfeileule.");
        fehler++;
        imageviewlinksoben.setImageResource(R.drawable.frage004_1_checked);
        moeoep(soundistaktiviert);
        scrolldown(5);
    }

    /**
     * Wird aufgerufen wenn der Benutzer auf Button B klickt.
     * Bild 2 gelbgrün umrahmen, den Weiter-Button einblenden
     * und falls der Sound aktiviert ist Bling machen.
     */
    public void sendbuttonb(View view) {
        loesung.setText("Richtig!\r\nBei den Langhornmotten sind die Fühler länger als die Flügel.");
        weiterbutton.setVisibility(View.VISIBLE);
        imageviewrechtsoben.setImageResource(R.drawable.frage004_2_checked);
        bling(soundistaktiviert);
        scrolldown(100);
    }

    /**
     * Wenn auf Button C (unter Bild 3) geklickt wird,
     * dieses ausgrauen und dem Benutzer mitteilen,
     * dass das leider nicht stimmt.
     */
    public void sendbuttonc(View view) {
        loesung.setText("Leider nicht richtig.\r\nDas ist der Ahornwickler.");
        fehler++;
        imageviewlinksunten.setImageResource(R.drawable.frage004_3_checked);
        moeoep(soundistaktiviert);
        scrolldown(5);
    }


    /**
     * Wenn auf Button D (unter Bild 4) geklickt wird,
     * dieses ausgrauen, dem Benutzer mitteilen,
     * dass das leider nicht stimmt und falls der Sound aktiviert ist Mööp machen.
     */
    public void sendbuttond(View view) {
        loesung.setText("Leider nicht richtig.\r\nDas ist der Mittlere Weinschwärmer.");
        fehler++;
        imageviewrechtsunten.setImageResource(R.drawable.frage004_4_checked);
        moeoep(soundistaktiviert);
        scrolldown(5);
    }

    /**
     * Wird aufgerufen wenn der Benutzer auf "weiter zur nächsten Frage" klickt
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, Frage005Activity.class);
        // Frage-5-Activity starten und fehler-Anzahl übergeben:
        intent.putExtra("fehler", fehler);
        // Die Info durchschleifen, ob Sounds aktiviert sind:
        intent.putExtra("soundistaktiviert", soundistaktiviert);
        startActivity(intent);
    }

}