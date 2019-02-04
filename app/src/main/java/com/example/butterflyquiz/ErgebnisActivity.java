package com.example.butterflyquiz;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ErgebnisActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.ergebnis_activity );
        // Intent holen, der diese Aktivität gestartet hat:
        Intent intent = getIntent ();
        // daraus die bisherige Fehleranzahl holen:
        int fehler = intent.getExtras ().getInt ( "fehler" );
        TextView ergebnistext = (TextView) findViewById ( R.id.ergebnistext );
        ImageView ergebnisbild = (ImageView) findViewById ( R.id.ergebnisbild );
        if (fehler == 0) {
            ergebnistext.setText ( "Alter Falter! Sie haben alle Fragen auf Anhieb richtig beantwortet! Dafür gibt es volle fünf Sterne!" );
            ergebnisbild.setImageResource ( R.drawable.stern5 );
        } else if (fehler <= 2) {
            String fehlerstring = new Integer ( fehler ).toString ();
            ergebnistext.setText ( "Sehr gut! Sie haben alle Fragen richtig beantwortet und dabei nur " + fehlerstring + " Fehler gemacht! Dafür gibt es vier von fünf Sternen!" );
            ergebnisbild.setImageResource ( R.drawable.stern4 );
        } else if (fehler <= 5) {
            String fehlerstring = new Integer ( fehler ).toString ();
            ergebnistext.setText ( "Gut. Sie haben alle Fragen richtig beantwortet und dabei nur " + fehlerstring + " Fehler gemacht. Dafür gibt es drei von fünf Sternen." );
            ergebnisbild.setImageResource ( R.drawable.stern3 );
        } else if (fehler <= 9) {
            String fehlerstring = new Integer ( fehler ).toString ();
            ergebnistext.setText ( "Geschafft. Sie haben die Fragen richtig beantwortet und dabei " + fehlerstring + " Fehler gemacht. Dafür gibt es zwei von fünf Sternen." );
            ergebnisbild.setImageResource ( R.drawable.stern2 );
        } else if (fehler <= 15) {
            String fehlerstring = new Integer ( fehler ).toString ();
            ergebnistext.setText ( "Geschafft. Sie haben die Fragen richtig beantwortet und dabei " + fehlerstring + " Fehler gemacht. Dafür gibt es einen von fünf Sternen. Das geht aber besser." );
            ergebnisbild.setImageResource ( R.drawable.stern1 );
        } else {
            String fehlerstring = new Integer ( fehler ).toString ();
            ergebnistext.setText ( "Uiuiui. Sie haben " + fehlerstring + " Fehler gemacht. Das ist ganz schön viel. Dafür gibt es einen von fünf Sternen als Trostpreis. Bestimmt waren die vielen Fehler Absicht und Sie wollten nur testen, wie die App reagiert." );
            ergebnisbild.setImageResource ( R.drawable.stern1 );
        }
        /*
         * Das Bild mit den Sternen braucht zu viel Platz,
         * egal ob man wrap_content, fill_parent oder match_parent wählt.
         * Deshalb korrigieren wir jetzt die Höhe des Bilds.
         * Die neue Höhe ist ein Fünftel der Bildschirmhöhe:
         */
        int adjustedheight = Resources.getSystem ().getDisplayMetrics ().heightPixels / 5;
        ImageView imageview = (ImageView) findViewById ( R.id.ergebnisbild );
        imageview.setAdjustViewBounds ( true );
        imageview.setMaxHeight ( adjustedheight );
    }


    /**
     * Wird aufgerufen wenn der Benutzer auf "Quiz erneut spielen." klickt
     */
    public void sendMessage(View view) {
        Intent intent = new Intent ( this, ImpressumActivity.class );
        // ImpressumActivity starten:
        startActivity ( intent );
    }

    /**
     * Wird aufgerufen wenn der Benutzer auf "Quiz beenden." klickt.
     * Mit System.exit(0) klappt es nicht, dann startet die Activity 10 neu.
     */
    public void beenden(View view) {
        Intent homeIntent = new Intent ( Intent.ACTION_MAIN );
        homeIntent.addCategory ( Intent.CATEGORY_HOME );
        homeIntent.setFlags ( Intent.FLAG_ACTIVITY_CLEAR_TOP );
        startActivity ( homeIntent );
        // ab API-Level 21 geht es dann sauberer so:
        //this.finishAndRemoveTask();
    }
}