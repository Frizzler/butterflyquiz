package com.example.butterflyquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

/**
 * Die ImpressumActivity ist die Startseite des Quiz.
 */
public class ImpressumActivity extends AppCompatActivity {


    /**
     * Vor der Anzeige der Impressumactivity wird diese Methode automatisch aufgerufen.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        // Verknüpfung mit impressum_activity.xml im Ordner res/layout
        setContentView ( R.layout.impressum_activity );
        // Die Sound-Checkbox defaultmäßig checken:
        CheckBox checkbox = (CheckBox) findViewById ( R.id.soundcheckbox );
        checkbox.setChecked ( true );
    }


    /**
     * Wird aufgerufen wenn der Benutzer auf "Ok. Quiz starten." klickt
     */
    public void sendMessage(View view) {
        Intent intent = new Intent ( this, Frage001Activity.class );
        // Frage001Activity starten:
        CheckBox checkbox = (CheckBox) findViewById ( R.id.soundcheckbox );
        intent.putExtra ( "soundistaktiviert", checkbox.isChecked () );
        intent.putExtra ( "fehler", 0 );
        startActivity ( intent );
    }
}