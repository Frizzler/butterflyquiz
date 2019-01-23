package com.jimdo.raupenzoo.schmetterlingsquiz;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Frage009Activity extends AppCompatActivity {

    ImageView   imageView1;
    ImageView   imageView2;
    ImageView   imageView3;
    ImageView   imageView4;

    /**
     * Zähler für die falsch ausgewählten Antworten.
     * Wird in onCreate() aus dem Intent geholt.
     * Für jeden falschen Klick um 1 erhöhen und am Ende weitergeben.
     */
    private int fehler;

    private boolean soundistaktiviert = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // das verwendete Layout setzen
        setContentView(R.layout.zweimalzweibilder);
        // Frage und Bilder setzen
        TextView frage = (TextView) findViewById(R.id.frage);
        frage.setText(R.string.frage009);
        imageView1 = (ImageView) findViewById(R.id.imageviewlinksoben);
        imageView1.setImageResource(R.drawable.frage009_1);
        imageView2 = (ImageView) findViewById(R.id.imageviewrechtsoben);
        imageView2.setImageResource(R.drawable.frage009_2);
        imageView3 = (ImageView) findViewById(R.id.imageviewlinksunten);
        imageView3.setImageResource(R.drawable.frage009_3);
        imageView4 = (ImageView) findViewById(R.id.imageviewrechtsunten);
        imageView4.setImageResource(R.drawable.frage009_4);
        // Intent holen, der diese Aktivität gestartet hat:
        Intent intent = getIntent();
        // daraus die bisherige Fehleranzahl holen:
        fehler = intent.getExtras().getInt("fehler");
        // daraus die Info holen, ob Sounds aktiviert sind:
        soundistaktiviert = intent.getExtras().getBoolean("soundistaktiviert");
        addListenerOnImage1();
        addListenerOnImage2();
        addListenerOnImage3();
        addListenerOnImage4();
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

    /**
     * Wenn auf Bild 1 (links oben) geklickt wird,
     * dieses ausgrauen und Fehler hochzählen.
     */
    public void addListenerOnImage1() {
        imageView1 = (ImageView) findViewById(R.id.imageviewlinksoben);
        imageView1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                TextView loesung = (TextView) findViewById(R.id.loesung);
                loesung.setText("Leider nicht richtig. Das Kleine Nachtpfauenauge hat gar keinen Saugrüssel und lebt nur von den Reserven der Raupe.");
                imageView1.setImageResource(R.drawable.frage009_1_checked);
                fehler++;
                if (soundistaktiviert) {
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.richerlandtvbadbeepincorrect);
                    mp.start();
                }
            }
        });
    }

    /**
     * Wenn auf Bild 2 (rechts oben) geklickt wird
     */
    public void addListenerOnImage2() {
        imageView2 = (ImageView) findViewById(R.id.imageviewrechtsoben);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                TextView loesung = (TextView) findViewById(R.id.loesung);
                loesung.setText("Richtig! Der Saugrüssel des Taubenschwänzchens ist 26 mm lang.");
                Button weiterbutton = (Button) findViewById(R.id.weiterbutton);
                weiterbutton.setVisibility(View.VISIBLE);
                imageView2.setImageResource(R.drawable.frage009_2_checked);
                if (soundistaktiviert) {
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.littlerainyseasonscorrect);
                    mp.start();
                }
            }
        });
    }


    /**
     * Klick auf Bild 3 (links unten) geklickt wird,
     * dieses ausgrauen und Fehler hochzählen.
     */
    public void addListenerOnImage3() {
        imageView3 = (ImageView) findViewById(R.id.imageviewlinksunten);
        imageView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                TextView loesung = (TextView) findViewById(R.id.loesung);
                loesung.setText("Leider nicht richtig. Der Prachtwickler ist ein sehr kleiner Schmetterling mit entsprechend kurzem Saugrüssel.");
                imageView3.setImageResource(R.drawable.frage009_3_checked);
                fehler++;
                if (soundistaktiviert) {
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.richerlandtvbadbeepincorrect);
                    mp.start();
                }
            }
        });
    }

    /**
     * Klick auf Bild 4 (rechts unten) geklickt wird,
     * dieses ausgrauen und Fehler hochzählen.
     */
    public void addListenerOnImage4() {
        imageView4 = (ImageView) findViewById(R.id.imageviewrechtsunten);
        imageView4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                TextView loesung = (TextView) findViewById(R.id.loesung);
                loesung.setText("Leider nicht richtig. Die Urmotten haben keinen Saugrüssel, sondern Kauwerkzeuge.");
                imageView4.setImageResource(R.drawable.frage009_4_checked);
                fehler++;
                if (soundistaktiviert) {
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.richerlandtvbadbeepincorrect);
                    mp.start();
                }
            }
        });
    }

    /**
     * Wird aufgerufen wenn der Benutzer auf "weiter zur nächsten Frage" klickt
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, Frage010Activity.class);
        // Frage-9-Activity starten und fehler-Anzahl übergeben:
        intent.putExtra("fehler", fehler);
        // Die Info durchschleifen, ob Sounds aktiviert sind:
        intent.putExtra("soundistaktiviert", soundistaktiviert);
        startActivity(intent);
    }

}