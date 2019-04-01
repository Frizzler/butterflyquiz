package com.jimdo.raupenzoo.schmetterlingsquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Frage005Activity extends AbstractFrageActivity {

    /**
     * Merkt sich, ob der Benutzer bereits auf das Weibchen geklickt hat
     */
    private boolean weibchengeklickt = false;
    /**
     * Merkt sich, ob der Benutzer bereits auf das Männchen geklickt hat
     */
    private boolean maennchengeklickt = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * Frage und Bilder setzen.
         * Die Werte werden in der Oberklasse gesetzt,
         * damit sie dort verfügbar sind für Vergrößerungen.
         */
        imagelinksoben   = R.drawable.frage005_1;
        imagerechtsoben  = R.drawable.frage005_2;
        imagelinksunten  = R.drawable.frage005_3;
        imagerechtsunten = R.drawable.frage005_4;
        fragetext        = R.string.frage005;
        super.onCreate(savedInstanceState);
    }


    /**
     * Wenn auf Button A (unter Bild 1) geklickt wird,
     * dieses gelbgrün umrahmen und ggf. den Weiter-Button einblenden.
     */
    public void sendbuttona(View view) {
        maennchengeklickt = true;
        imageviewlinksoben.setImageResource(R.drawable.frage005_1_checked);
        if (weibchengeklickt) {
            loesung.setText("Richtig!\r\nDas Männchen vom Zitronenfalter ist knallgelb.");
            weiterbutton.setVisibility(View.VISIBLE);
            bling(soundistaktiviert);
        }
        else {
            loesung.setText("Richtig!\r\nDas Männchen vom Zitronenfalter ist knallgelb. Jetzt nur noch das Weibchen finden...");
        }
        scrolldown(100);
    }

    /**
     * Wenn auf Button B (unter Bild 1) geklickt wird,
     * dieses gelbgrün umrahmen und ggf. den Weiter-Button einblenden.
     */
    public void sendbuttonb(View view) {
        weibchengeklickt = true;
        imageviewrechtsoben.setImageResource(R.drawable.frage005_2_checked);
        if (maennchengeklickt) {
            loesung.setText("Richtig!\r\nDas Weibchen vom Zitronenfalter ist grünlich-weiß.");
            weiterbutton.setVisibility(View.VISIBLE);
            bling(soundistaktiviert);
        }
        else {
            loesung.setText("Richtig!\r\nDas Weibchen vom Zitronenfalter ist grünlich-weiß. Jetzt nur noch das Männchen finden...");
        }
        scrolldown(100);
    }

    /**
     * Wenn auf Button C (unter Bild 3) geklickt wird,
     * dieses ausgrauen und dem Benutzer mitteilen,
     * dass das leider nicht stimmt.
     */
    public void sendbuttonc(View view) {
        loesung.setText("Leider nicht richtig.\r\nDas ist der Postillon.");
        fehler++;
        imageviewlinksunten.setImageResource(R.drawable.frage005_3_checked);
        moeoep(soundistaktiviert);
        scrolldown(5);
    }

    /**
     * Wenn auf Button D (unter Bild 4) geklickt wird,
     * dieses ausgrauen, dem Benutzer mitteilen,
     * dass das leider nicht stimmt und falls der Sound aktiviert ist Mööp machen.
     */
    public void sendbuttond(View view) {
        loesung.setText("Leider nicht richtig.\r\nDas ist der Tintenfleck-Weißling.");
        fehler++;
        imageviewrechtsunten.setImageResource(R.drawable.frage005_4_checked);
        moeoep(soundistaktiviert);
        scrolldown(5);
    }

    /**
     * Wird aufgerufen wenn der Benutzer auf "weiter zur nächsten Frage" klickt
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, Frage006Activity.class);
        // Frage-6-Activity starten und fehler-Anzahl übergeben:
        intent.putExtra("fehler", fehler);
        // Die Info durchschleifen, ob Sounds aktiviert sind:
        intent.putExtra("soundistaktiviert", soundistaktiviert);
        startActivity(intent);
    }

}