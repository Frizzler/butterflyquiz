package com.example.butterflyquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


/**
 * Enthält die Logik für die erste Frage des ersten Quiz.
 * <p>
 * Das Quiz ist derzeit programmiert für minSdkVersion 15 (siehe build.gradle).
 * Das entspricht Android 4.0.3. vom 16. Dezember 2011.
 * <p>
 * Das Xiaomi Redmi 3S hat eine Auflösung von 720 x 1280 Pixeln.
 * <p>
 * DONE Frage 1 von Anfang an vergrößert und mit Scrollbar
 * DONE Frage 2 mit extra buttons, Vergrößerung durch Bild-Antippen
 * DONE Frage 3 Vergrößerung durch long click
 * DONE Frage 4 Erkennung des Pinch-Zoom, darauf reagieren aber die Bilder nicht
 * DONE Frage 5 Verwendung TouchImageView, dadurch Erkennung des Pinch-Zoom auch auf Bildern,
 * diese bleiben aber in ihrem zu kleinen Rahmen
 * DONE Frage 6 (mit extra-buttons aus Frage 2 kombiniert):
 * Pinch-Zoom innerhalb eines Bilds erkennen durch onTouchListener,
 * dieses dann oben vergrößert anzeigen (somit Vergrößerung möglich durch Klick/LongKlick, Doppelklick und Pinch-Zoom-Versuch)
 * (TODO oben dann nochmal TouchImageView verwenden)
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
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private ImageView ImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );

        // initialize the view and the gesture detector
        ImageView = findViewById ( R.id.imageviewenlargeoben );
        mScaleGestureDetector = new ScaleGestureDetector ( this, new ScaleListener () );

    }


    /**
     * Wenn auf ein Bild geklickt wird, dieses oben vergrößert anzeigen.
     *
     * @param view
     */
    public void enlargeimagea(View view) {
        imageviewenlargeoben.setImageResource ( R.drawable.frage001_1 );
        imageviewenlargeoben.setVisibility ( View.VISIBLE );
    }

    public void enlargeimageb(View view) {
        imageviewenlargeoben.setImageResource ( R.drawable.frage001_2 );
        imageviewenlargeoben.setVisibility ( View.VISIBLE );
    }

    public void enlargeimagec(View view) {
        imageviewenlargeoben.setImageResource ( R.drawable.frage001_3 );
        imageviewenlargeoben.setVisibility ( View.VISIBLE );
    }

    public void enlargeimaged(View view) {
        imageviewenlargeoben.setImageResource ( R.drawable.frage001_4 );
        imageviewenlargeoben.setVisibility ( View.VISIBLE );
    }

    /**
     * Wenn auf das vergrößerte Bild geklickt wird, die Vergrößerung wieder ausblenden.
     *
     * @param view
     */
    public void removeenlargeviewoben(View view) {
        imageviewenlargeoben.setVisibility ( View.GONE );
    }

    /**
     * Wenn auf Bild 1 geklickt wird, dieses grün umrahmen, den Weiter-Button einblenden
     * und falls der Sound aktiviert ist Bling machen.
     */
    public void sendbuttona(View view) {
        loesung.setText ( "Richtig!\r\n Die Augenzeichnung dient der Abschreckung von Fressfeinden." );
        weiterbutton.setVisibility ( View.VISIBLE );
        imageviewlinksoben.setImageResource ( R.drawable.frage001_1_checked );
        bling ( soundistaktiviert );
        scrolldown ( 100 );
    }

    /**
     * Wenn auf Bild 2 geklickt wird, dieses ausgrauen, dem Benutzer mitteilen,
     * dass das leider nicht stimmt und falls der Sound aktiviert ist Mööp machen.
     */
    public void sendbuttonb(View view) {
        loesung.setText ( "Leider nicht richtig.\r\nDas ist der Aurorafalter." );
        fehler++;
        imageviewrechtsoben.setImageResource ( R.drawable.frage001_2_checked );
        moeoep ( soundistaktiviert );
        scrolldown ( 5 );
    }

    public void sendbuttonc(View view) {
        loesung.setText ( "Leider nicht richtig.\r\nDas ist der Himmelblaue Bläuling." );
        fehler++;
        imageviewlinksunten.setImageResource ( R.drawable.frage001_3_checked );
        moeoep ( soundistaktiviert );
        scrolldown ( 5 );
    }

    public void sendbuttond(View view) {
        loesung.setText ( "Leider nicht richtig.\r\nDas ist der Schwalbenschwanz." );
        fehler++;
        imageviewrechtsunten.setImageResource ( R.drawable.frage001_4_checked );
        moeoep ( soundistaktiviert );
        scrolldown ( 5 );
    }

    /**
     * Wird aufgerufen wenn der Benutzer auf "weiter zur nächsten Frage" klickt
     */
    public void sendMessage(View view) {
        Intent intent = new Intent ( this, Frage002Activity.class );
        // Frage-2-Activity starten und fehler-Anzahl übergeben:
        intent.putExtra ( "fehler", fehler );
        // Die Info durchschleifen, ob Sounds aktiviert sind:
        intent.putExtra ( "soundistaktiviert", soundistaktiviert );
        startActivity ( intent );
    }

    // this redirects all touch events in the activity to the gesture detector
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        super.dispatchTouchEvent ( event );

        mScaleGestureDetector.onTouchEvent ( event );
        return mScaleGestureDetector.onTouchEvent ( event );

    }


    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor ();
            mScaleFactor = Math.max ( 0.1f,
                    Math.min ( mScaleFactor, 10.0f ) );
            ImageView.setScaleX ( mScaleFactor );
            ImageView.setScaleY ( mScaleFactor );
            return true;
        }
    }

}