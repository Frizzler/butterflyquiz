package com.jimdo.raupenzoo.schmetterlingsquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;



/**
 * Enthält die Logik für die erste Frage des ersten Quiz.
 *
 * Das Quiz ist derzeit programmiert für minSdkVersion 15 (siehe build.gradle).
 * Das entspricht Android 4.0.3. vom 16. Dezember 2011.
 *
 * Das Xiaomi Redmi 3S hat eine Auflösung von 720 x 1280 Pixeln.
 *
 * DONE Frage 1 von Anfang an vergrößert und mit Scrollbar
 * DONE Frage 2 mit extra buttons, Vergrößerung durch Bild-Antippen
 * DONE Frage 3 Vergrößerung durch long click
 * DONE Frage 4 Erkennung des Pinch-Zoom, darauf reagieren aber die Bilder nicht
 * DONE Frage 5 Verwendung TouchImageView, dadurch Erkennung des Pinch-Zoom auch auf Bildern,
 *      diese bleiben aber in ihrem zu kleinen Rahmen
 * DONE Frage 6 (mit extra-buttons aus Frage 2 kombiniert):
 *      Pinch-Zoom innerhalb eines Bilds erkennen durch onTouchListener,
 *      dieses dann oben vergrößert anzeigen (somit Vergrößerung möglich durch Klick/LongKlick, Doppelklick und Pinch-Zoom-Versuch)
 *      (TODO oben dann nochmal TouchImageView verwenden)
 * TODO Bilder und Fragen der Oberklasse bekannt machen wie bis Frage 7 geschehen
 * TODO Pinch Zoom inside scrollview https://stackoverflow.com/questions/19615697/how-to-make-zoomable-scrollview
 * TODO Buttons schön machen wie in Frage 2; bei richtiger Antwort Button grün machen, bei falscher rot
 * TODO Bilder bereits onTouch vergrößern, damit Vergrößerung auch bei Pinch-Versuch triggert
 * TODO Kippen führt zu anderem Layout (kombiniert mit Lösungen aus Frage 6)
 * TODO Webview mit Zoom ==> geht nicht, Webview kann nur HTML vergrößern
 * TODO Sound extract method in Klasse Soundmanager
 * TODO auf anderen Smartphones testen (mit kleinerem Bildschirm und mit breiterem Bildschirm)
 * TODO Bilder mit höherer Auflösung aber kleiner (jpg) 400 x 267 Qualität 9
 * TODO dowdowdow bei mehr als 9 Fehlern
 * TODO tätätätärä bei 0 Fehlern
 * TODO Link zur erweiterten Version einbauen
 * erweiterte Version:
 * TODO mit Raupenzuchtanleitung
 * TODO 3 x Artengeiermodus mit Falterfotos
 * TODO 1 x Artengeiermodus mit Raupenfotos
 * TODO weiteres 10er-Quiz
 * Marketing:
 * TODO in Hugo Stalders App einbauen
 * TODO 10 Cent pro erweiterte Version an Naturefund / DJN
 * TODO Facebook, Lepiforum
 */
public class Frage001Activity extends AbstractFrageActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * Frage und Bilder setzen.
         * Die Werte werden in der Oberklasse gesetzt,
         * damit sie dort verfügbar sind für Vergrößerungen.
         */
        imagelinksoben   = R.drawable.frage001_1;
        imagerechtsoben  = R.drawable.frage001_2;
        imagelinksunten  = R.drawable.frage001_3;
        imagerechtsunten = R.drawable.frage001_4;
        fragetext        = R.string.frage001;
        super.onCreate(savedInstanceState);
    }


    /**
     * Wenn auf Bild 1 geklickt wird, dieses grün umrahmen, den Weiter-Button einblenden
     * und falls der Sound aktiviert ist Bling machen.
     */
    public void sendbuttona(View view) {
        loesung.setText("Richtig!\r\n Die Augenzeichnung dient der Abschreckung von Fressfeinden.");
        weiterbutton.setVisibility(View.VISIBLE);
        imageviewlinksoben.setImageResource(R.drawable.frage001_1_checked);
        bling(soundistaktiviert);
        scrolldown(100);
    }

    /**
     * Wenn auf Bild 2 geklickt wird, dieses ausgrauen, dem Benutzer mitteilen,
     * dass das leider nicht stimmt und falls der Sound aktiviert ist Mööp machen.
     */
    public void sendbuttonb(View view) {
        loesung.setText("Leider nicht richtig.\r\nDas ist der Aurorafalter.");
        fehler++;
        imageviewrechtsoben.setImageResource(R.drawable.frage001_2_checked);
        moeoep(soundistaktiviert);
        scrolldown(5);
    }

    public void sendbuttonc(View view) {
        loesung.setText("Leider nicht richtig.\r\nDas ist der Himmelblaue Bläuling.");
        fehler++;
        imageviewlinksunten.setImageResource(R.drawable.frage001_3_checked);
        moeoep(soundistaktiviert);
        scrolldown(5);
    }

    public void sendbuttond(View view) {
        loesung.setText("Leider nicht richtig.\r\nDas ist der Schwalbenschwanz.");
        fehler++;
        imageviewrechtsunten.setImageResource(R.drawable.frage001_4_checked);
        moeoep(soundistaktiviert);
        scrolldown(5);
    }

    /**
     * Wird aufgerufen wenn der Benutzer auf "weiter zur nächsten Frage" klickt
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, Frage002Activity.class);
        // Frage-2-Activity starten und fehler-Anzahl übergeben:
        intent.putExtra("fehler", fehler);
        // Die Info durchschleifen, ob Sounds aktiviert sind:
        intent.putExtra("soundistaktiviert", soundistaktiviert);
        startActivity(intent);
    }

}