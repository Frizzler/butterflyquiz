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

import static com.jimdo.raupenzoo.schmetterlingsquiz.R.id.oberezweibilder;

/**
 * Enthält Code, der bei allen Fragen gleich ist.
 * Created by Frizzler on 10.12.2018.
 */
public class AbstractFrageActivity extends AppCompatActivity {

    TextView frage;

    ImageView imageviewenlargeoben;
    ImageView imageviewlinksoben;
    ImageView imageviewrechtsoben;
    ImageView imageviewlinksunten;
    ImageView imageviewrechtsunten;

    TextView  loesung;

    Button    weiterbutton;

    boolean soundistaktiviert = false;

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Das verwendete Layout-Template setzen:
        setContentView(R.layout.zweimalzweibildermitbuttons);
        // Klassenvariablen vorbelegen:
        frage = (TextView) findViewById(R.id.frage);
        imageviewenlargeoben = (ImageView) findViewById(R.id.imageviewenlargeoben);
        imageviewlinksoben   = (ImageView) findViewById(R.id.imageviewlinksoben);
        imageviewrechtsoben  = (ImageView) findViewById(R.id.imageviewrechtsoben);
        imageviewlinksunten  = (ImageView) findViewById(R.id.imageviewlinksunten);
        imageviewrechtsunten = (ImageView) findViewById(R.id.imageviewrechtsunten);
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
        LinearLayout linearlayoutoben = (LinearLayout) findViewById(oberezweibilder);
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


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        loesung.setText("onTouchEvent");
        scaleGestureDetector.onTouchEvent(event);
        return true;
    }

    public class MyOnScaleGestureListener extends SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getScaleFactor();
            float xpinch = detector.getFocusX(); // rechts links
            float ypinch = detector.getFocusY(); // oben unten
            ImageView rechtsoben = (ImageView) findViewById(R.id.imageviewrechtsoben);
            int xrechtsoben = rechtsoben.getLeft();
            LinearLayout oberzweibilder = (LinearLayout) findViewById(oberezweibilder);
            int yrechtsoben = oberzweibilder.getBottom();

            /*
             * Rechts
             */
            if (xpinch >= xrechtsoben) {
               /*
                * Unten
                */
                if (ypinch >= yrechtsoben) {
                    if (scaleFactor > 1) {
                        loesung.setText(yrechtsoben + " Rechts unten, Zooming Out " + ypinch);
                    } else {
                        loesung.setText("Rechts unten, Zooming In " + xpinch);
                    }
                }
               /*
                * Oben
                */
                else {
                    if (scaleFactor > 1) {
                        loesung.setText(yrechtsoben + " Rechts oben, Zooming Out " + ypinch);
                    } else {
                        loesung.setText("Rechts oben, Zooming In " + xpinch);
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
                    if (scaleFactor > 1) {
                        loesung.setText(yrechtsoben + " Links unten, Zooming Out " + ypinch);
                    } else {
                        loesung.setText("Links unten, Zooming In " + xpinch);
                    }
                }
               /*
                * Oben
                */
                else {
                    if (scaleFactor > 1) {
                        loesung.setText(yrechtsoben + " Links oben, Zooming Out " + ypinch);
                    } else {
                        loesung.setText("Links oben, Zooming In " + xpinch);
                    }
                }
            }
            return true;
        }// end onScale()


        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector detector) {
        }
    }// end class declaration of MyOnScaleGestureListener

}