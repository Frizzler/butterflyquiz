package com.jimdo.raupenzoo.schmetterlingsquiz;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Enthält Code, der bei allen Fragen gleich ist.
 * Created by Frizzler on 10.12.2018.
 */
public class AbstractFrageActivity extends AppCompatActivity {

    TextView  frage;
    int       fragetext;

    ImageView imageviewenlargeoben;
    ImageView imageviewlinksoben;
    int       imagelinksoben;
    ImageView imageviewrechtsoben;
    int       imagerechtsoben;
    ImageView imageviewlinksunten;
    int       imagelinksunten;
    ImageView imageviewrechtsunten;
    int       imagerechtsunten;

    TextView  loesung;

    Button    weiterbutton;

    boolean   soundistaktiviert = false;

    /**
     * Zähler für die falsch ausgewählten Antworten.
     * Wird in onCreate() aus dem Intent geholt.
     * Für jeden falschen Klick um 1 erhöhen und am Ende weitergeben.
     */
    int fehler;

    /**
     * Damit die Zoombewegung auf einem Bild erkannt werden kann
     */
    ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Das verwendete Layout-Template setzen:
        setContentView(R.layout.zweimalzweibildermitbuttons);
        // Klassenvariablen vorbelegen:
        frage = (TextView) findViewById(R.id.frage);
        frage.setText(fragetext);
        imageviewenlargeoben = (ImageView) findViewById(R.id.imageviewenlargeoben);
        imageviewlinksoben   = (ImageView) findViewById(R.id.imageviewlinksoben);
        imageviewlinksoben.setImageResource(imagelinksoben);
        imageviewrechtsoben  = (ImageView) findViewById(R.id.imageviewrechtsoben);
        imageviewrechtsoben.setImageResource(imagerechtsoben);
        imageviewlinksunten  = (ImageView) findViewById(R.id.imageviewlinksunten);
        imageviewlinksunten.setImageResource(imagelinksunten);
        imageviewrechtsunten = (ImageView) findViewById(R.id.imageviewrechtsunten);
        imageviewrechtsunten.setImageResource(imagerechtsunten);
        loesung = (TextView)findViewById(R.id.loesung);
        weiterbutton = (Button) findViewById(R.id.weiterbutton);
        // Intent holen, der diese Aktivität gestartet hat:
        Intent intent = getIntent();
        // daraus die Info holen, ob Sounds aktiviert sind:
        soundistaktiviert = intent.getExtras().getBoolean("soundistaktiviert");
        // daraus die bisherige Fehleranzahl holen:
        fehler = intent.getExtras().getInt("fehler");
        // Die AbstractFrageActivity macht jetzt das Layout schön:
        adjustlayout();
        // Den ScaleGestureDetector initialisieren:
        scaleGestureDetector =
                new ScaleGestureDetector(this,
                        new MyOnScaleGestureListener());
    }

    /**
     * Das Layout anpassen / schön machen.
     * Zugrunde gelegt wird die Breite des jeweiligen Bildschirms.
     * Dies wird voll ausgenutzt, die Höhe der Elemente wird dann so eingestellt,
     * dass sie zur Breite passt.
     */
    public void adjustlayout() {
        /*
         * Zwischen den Bildern ist defaultmäßig zu viel Platz,
         * egal ob man wrap_content, fill_parent oder match_parent wählt.
         * Deshalb korrigieren wir jetzt die Höhe der beiden Bildzeilen.
         * Die neue Höhe ist ein Drittel der Bildschirmbreite:
         */
        int adjustedheight = Resources.getSystem().getDisplayMetrics().widthPixels / 3;
        LinearLayout linearlayoutoben = (LinearLayout) findViewById(R.id.oberezweibilder);
        ViewGroup.LayoutParams paramsoben = linearlayoutoben.getLayoutParams();
        paramsoben.height = adjustedheight;
        linearlayoutoben.setLayoutParams(paramsoben);
        LinearLayout linearlayoutunten = (LinearLayout) findViewById(R.id.unterezweibilder);
        ViewGroup.LayoutParams paramsunten = linearlayoutunten.getLayoutParams();
        paramsunten.height = adjustedheight;
        linearlayoutunten.setLayoutParams(paramsunten);
        /*
         * Die Buttons sind defaultmäßig zu groß / klobig,
         * egal ob man wrap_content, fill_parent oder match_parent wählt.
         * Deshalb korrigieren wir jetzt die Höhe der beiden Buttonzeilen.
         * Die neue Höhe ist ein Achtel der Bildschirmbreite:
         */
        int adjustedheightbuttons = Resources.getSystem().getDisplayMetrics().widthPixels / 8;
        LinearLayout linearlayoutbuttonsoben = (LinearLayout) findViewById(R.id.oberezweibuttons);
        ViewGroup.LayoutParams buttonparamsoben = linearlayoutbuttonsoben.getLayoutParams();
        buttonparamsoben.height = adjustedheightbuttons;
        linearlayoutbuttonsoben.setLayoutParams(buttonparamsoben);
        LinearLayout linearlayoutbuttonsunten = (LinearLayout) findViewById(R.id.unterezweibuttons);
        ViewGroup.LayoutParams buttonparamsunten = linearlayoutbuttonsunten.getLayoutParams();
        buttonparamsunten.height = adjustedheightbuttons;
        linearlayoutbuttonsunten.setLayoutParams(buttonparamsunten);
    }

    // Momentan funktioniert die Skalierung der Bilder automatisch korrekt.
    // Falls irgendwann nicht mehr, dann folgenden Code verwenden:
    //int adjustedheight = (2 * imageview4.getWidth()) / 3;
    //imageview4.setAdjustViewBounds(true);
    //imageview4.setMaxHeight(adjustedheight);

    /**
     * Wenn auf ein Bild geklickt wird, dieses oben vergrößert anzeigen.
     * @param view
     */
    public void enlargeimagea(View view) {
        imageviewenlargeoben.setImageResource(imagelinksoben);
        imageviewenlargeoben.setVisibility(View.VISIBLE);
    }
    public void enlargeimageb(View view) {
        imageviewenlargeoben.setImageResource(imagerechtsoben);
        imageviewenlargeoben.setVisibility(View.VISIBLE);
    }
    public void enlargeimagec(View view) {
        imageviewenlargeoben.setImageResource(imagelinksunten);
        imageviewenlargeoben.setVisibility(View.VISIBLE);
    }
    public void enlargeimaged(View view) {
        imageviewenlargeoben.setImageResource(imagerechtsunten);
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
     * Der Mööp-Sound wird abgespielt, wenn soundistaktiviert
     *
     * @param soundistaktiviert
     */
    public void moeoep(boolean soundistaktiviert) {
        if (soundistaktiviert) {
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.richerlandtvbadbeepincorrect);
            mp.start();
        }
    }

    /**
     * Der Bling-Sound wird abgespielt, wenn soundistaktiviert
     *
     * @param soundistaktiviert
     */
    public void bling(boolean soundistaktiviert) {
        if (soundistaktiviert) {
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.littlerainyseasonscorrect);
            mp.start();
        }
    }

    /*
     * Je nach Bildschirmformat ist der Lösungstext und der Weiterbutton abgeschnitten.
     * Nach dem Klick auf einen der Buttons A, B, C oder D muss daher
     * vorsichtshalber nach unten gescrollt werden.
     */
    public void scrolldown(int verzoegerung) {
        try {
            Thread.sleep(verzoegerung);
        }
        catch (InterruptedException e) {
        }
        final View scrollview = findViewById(R.id.scrollview);
        scrollview.post(new Runnable() {
            public void run() {
                scrollview.scrollTo(0, scrollview.getBottom());
            }
        });
    }

    /**
     * Normalerweise wird zum Erkennen von Gestures onTouchEvent() verwendet.
     * Das funktioniert jedoch innerhalb von Scrollviews nicht.
     * Daher Verwendung von dispatchTouchEvent().
     * Details siehe
     * https://stackoverflow.com/questions/19615697/how-to-make-zoomable-scrollview
     *
     * @param  event z.B. ein Klick oder ein pinch zoom
     * @return true, d.h. das Event wird danach noch weiter ausgewertet
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        super.dispatchTouchEvent (event);
        //loesung.setText("dispatchTouchEvent");
        return scaleGestureDetector.onTouchEvent(event);
    }

    public class MyOnScaleGestureListener extends SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            mScaleFactor *= scaleGestureDetector.getScaleFactor ();
            mScaleFactor = Math.max ( 0.5f, Math.min ( mScaleFactor, 5.0f ) );
            //imageviewenlargeoben.setScaleX ( mScaleFactor );
            //imageviewenlargeoben.setScaleY ( mScaleFactor );
            LinearLayout gesamt = (LinearLayout) findViewById(R.id.activity_main);
            gesamt.setScaleX(mScaleFactor);
            gesamt.setScaleY(mScaleFactor);
            if (true) return true;

            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max ( 0.1f, Math.min ( mScaleFactor, 10.0f ) );
            float xpinch = scaleGestureDetector.getFocusX(); // rechts links
            loesung.setText("xpinch: " + xpinch);
            float ypinch = scaleGestureDetector.getFocusY(); // oben unten
            ImageView rechtsoben = (ImageView) findViewById(R.id.imageviewrechtsoben);
            int xrechtsoben = rechtsoben.getLeft();
            LinearLayout oberezweibilder = (LinearLayout) findViewById(R.id.oberezweibilder);
            int yrechtsoben = oberezweibilder.getBottom();

            /*
             * Rechts
             */
            if (xpinch >= xrechtsoben) {
               /*
                * Unten
                */
                if (ypinch >= yrechtsoben) {
                    if (mScaleFactor > 1) {
                        imageviewenlargeoben.setVisibility(View.GONE);
                        loesung.setText("rechts unten verkleinert");
                    }
                    else {
                        imageviewenlargeoben.setImageResource(imagerechtsunten);
                        imageviewenlargeoben.setVisibility(View.VISIBLE);
                        loesung.setText("rechts unten vergrößert");
                    }
                }
               /*
                * Oben
                */
                else {
                    if (mScaleFactor > 1) {
                        imageviewenlargeoben.setVisibility(View.GONE);
                        loesung.setText("rechts oben verkleinert");
                    }
                    else {
                        imageviewenlargeoben.setImageResource(imagerechtsoben);
                        imageviewenlargeoben.setVisibility(View.VISIBLE);
                        loesung.setText("rechts oben vergrößert");
                    }
                }
            }
            /*
             * Links
             */
            else {
               /*
                * Unten
                */
                if (ypinch >= yrechtsoben) {
                    if (mScaleFactor > 1) {
                        imageviewenlargeoben.setVisibility(View.GONE);
                        loesung.setText("Links unten verkleinert");
                    }
                    else {
                        imageviewenlargeoben.setImageResource(imagelinksunten);
                        imageviewenlargeoben.setVisibility(View.VISIBLE);
                        loesung.setText("links unten vergrößert");
                    }
                }
               /*
                * Oben
                */
                else {
                    if (mScaleFactor > 1) {
                        imageviewenlargeoben.setVisibility(View.GONE);
                        loesung.setText("Links oben verkleinert");
                    }
                    else {
                        imageviewenlargeoben.setImageResource(imagelinksoben);
                        imageviewenlargeoben.setVisibility(View.VISIBLE);
                        loesung.setText("links oben vergrößert");
                    }
                }
            }
            return true;
        }// end onScale()


        public boolean onScaleBegin(ScaleGestureDetector detector) {
            //loesung.setText("onScaleBegin");
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector detector) {
            //loesung.setText("onScaleEnd");
        }
    }// end class declaration of MyOnScaleGestureListener

}