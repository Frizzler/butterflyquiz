package com.jimdo.raupenzoo.schmetterlingsquiz;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Frage006Activity extends AppCompatActivity {

    ImageView imageviewenlargeoben;

    ImageView   imageviewlinksoben;
    ImageView   imageviewrechtsoben;
    ImageView   imageviewlinksunten;
    ImageView   imageviewrechtsunten;

    TextView loesung;

    /**
     * Zähler für die falsch ausgewählten Antworten.
     * Wird in onCreate() aus dem Intent geholt.
     * Für jeden falschen Klick um 1 erhöhen und am Ende weitergeben.
     */
    private int fehler;

    private boolean soundistaktiviert = false;

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
        super.onCreate(savedInstanceState);
        // das verwendete Layout setzen
        setContentView(R.layout.zweimalzweibildermitbuttonstouchimage);
        // Frage und Bilder setzen
        TextView frage = (TextView) findViewById(R.id.frage);
        frage.setText(R.string.frage006);
        imageviewenlargeoben = (ImageView) findViewById(R.id.imageviewenlargeoben);
        imageviewlinksoben = (ImageView) findViewById(R.id.imageviewlinksoben);
        imageviewlinksoben.setImageResource(R.drawable.frage006_1);
        imageviewrechtsoben = (ImageView) findViewById(R.id.imageviewrechtsoben);
        imageviewrechtsoben.setImageResource(R.drawable.frage006_2);
        imageviewlinksunten = (ImageView) findViewById(R.id.imageviewlinksunten);
        imageviewlinksunten.setImageResource(R.drawable.frage006_3);
        imageviewrechtsunten = (ImageView) findViewById(R.id.imageviewrechtsunten);
        imageviewrechtsunten.setImageResource(R.drawable.frage006_4);
        addenlargelisteneronimages();
        loesung = (TextView) findViewById(R.id.loesung);
        // Intent holen, der diese Aktivität gestartet hat:
        Intent intent = getIntent();
        // daraus die bisherige Fehleranzahl holen:
        fehler = intent.getExtras().getInt("fehler");
        // daraus die Info holen, ob Sounds aktiviert sind:
        soundistaktiviert = intent.getExtras().getBoolean("soundistaktiviert");
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
    }



    /*
     * Damit jede Form der Berührung der Bilder
     * zu deren Vergrößerung führt (auch Pinch-Zoom-Versuch).
     */
    public void addenlargelisteneronimages() {
        imageviewlinksoben.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent event) {
                enlargeimagea(arg0);
                return true;
            }
        });
        imageviewrechtsoben.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent event) {
                enlargeimageb(arg0);
                return true;
            }
        });
        imageviewlinksunten.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent event) {
                enlargeimagec(arg0);
                return true;
            }
        });
        imageviewrechtsunten.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent event) {
                enlargeimaged(arg0);
                return true;
            }
        });
    }


    /**
     * Wenn auf ein Bild geklickt wird, dieses vergrößern.
     * @param view
     */
    public void enlargeimagea(View view) {
        imageviewenlargeoben.setImageResource(R.drawable.frage006_1);
        imageviewenlargeoben.setVisibility(View.VISIBLE);
    }
    public void enlargeimageb(View view) {
        imageviewenlargeoben.setImageResource(R.drawable.frage006_2);
        imageviewenlargeoben.setVisibility(View.VISIBLE);
    }
    public void enlargeimagec(View view) {
        imageviewenlargeoben.setImageResource(R.drawable.frage006_3);
        imageviewenlargeoben.setVisibility(View.VISIBLE);
    }
    public void enlargeimaged(View view) {
        imageviewenlargeoben.setImageResource(R.drawable.frage006_4);
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
            if (soundistaktiviert) {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.richerlandtvbadbeepincorrect);
                mp.start();
            }
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
            if (soundistaktiviert) {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.richerlandtvbadbeepincorrect);
                mp.start();
            }
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
            Button weiterbutton = (Button) findViewById(R.id.weiterbutton);
            weiterbutton.setVisibility(View.VISIBLE);
            if (soundistaktiviert) {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.littlerainyseasonscorrect);
                mp.start();
            }
        }
        else {
            loesung.setText("Leider nicht richtig.");
            fehler++;
            imageviewlinksunten.setImageResource(R.drawable.frage006_3_falsch);
            if (soundistaktiviert) {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.richerlandtvbadbeepincorrect);
                mp.start();
            }
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


    /*
     * Je nach Bildschirmformat ist der Lösungstext und der Weiterbutton abgeschnitten.
     * Nach dem Klick auf einen der Buttons A, B, C oder D muss daher
     * vorsichtshalber nach unten gescrollt werden.
     */
    private void scrolldown(int verzoegerung) {
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
     * Wird aufgerufen wenn der Benutzer auf "weiter zur nächsten Frage" klickt
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, Frage007Activity.class);
        // Frage-3-Activity starten und fehler-Anzahl übergeben:
        intent.putExtra("fehler", fehler);
        // Die Info durchschleifen, ob Sounds aktiviert sind:
        intent.putExtra("soundistaktiviert", soundistaktiviert);
        startActivity(intent);
    }
}