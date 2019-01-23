package com.jimdo.raupenzoo.pinchexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.jimdo.raupenzoo.pinchexample.R.id.oberezweibilder;

public class PinchExampleActivity extends AppCompatActivity {

    ImageView   imageView1;
    ImageView   imageView2;
    ImageView   imageView3;
    ImageView   imageView4;

    TextView    loesung;

    /**
     * Zähler für die falsch ausgewählten Antworten.
     * Wird in onCreate() aus dem Intent geholt.
     * Für jeden falschen Klick um 1 erhöhen und am Ende weitergeben.
     */
    private int fehler;

    boolean soundistaktiviert = false;

    ScaleGestureDetector scaleGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zweimalzweibilder);

        loesung = (TextView)findViewById(R.id.loesung);

        scaleGestureDetector =
                new ScaleGestureDetector(this,
                        new MyOnScaleGestureListener());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        return true;
    }

    public class MyOnScaleGestureListener extends
            SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            float scaleFactor = detector.getScaleFactor();
            float xpinch = detector.getFocusX(); // rechts links
            float ypinch = detector.getFocusY(); // oben unten
            ImageView rechtsoben = (ImageView)findViewById(R.id.imageviewrechtsoben);
            int xrechtsoben = rechtsoben.getLeft();
            LinearLayout oberzweibilder = (LinearLayout)findViewById(oberezweibilder);
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
                    }
                    else {
                        loesung.setText("Rechts unten, Zooming In " + xpinch);
                    }
                }
               /*
                * Oben
                */
                else {
                    if (scaleFactor > 1) {
                        loesung.setText(yrechtsoben + " Rechts oben, Zooming Out " + ypinch);
                    }
                    else {
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
                    }
                    else {
                        loesung.setText("Links unten, Zooming In " + xpinch);
                    }
                }
               /*
                * Oben
                */
                else {
                    if (scaleFactor > 1) {
                        loesung.setText(yrechtsoben + " Links oben, Zooming Out " + ypinch);
                    }
                    else {
                        loesung.setText("Links oben, Zooming In " + xpinch);
                    }
                }
            }
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }

}
