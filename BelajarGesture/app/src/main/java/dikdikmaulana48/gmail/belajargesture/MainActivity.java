package dikdikmaulana48.gmail.belajargesture;

import android.graphics.Color;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    private TextView gestureText;
    private GestureDetectorCompat gDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestureText = (TextView)findViewById(R.id.gestureStatusText);

        this.gDetector = new GestureDetectorCompat(this,this);
        gDetector.setOnDoubleTapListener(this);
    }
    @Override
    public boolean onDown(MotionEvent event) {
        gestureText.setText ("onDown");
        RelativeLayout layout=(RelativeLayout)findViewById(R.id.activity_main);
        layout.setBackgroundColor(Color.BLUE);
        return true;
    }
    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY){
        gestureText.setText("onFling");
        RelativeLayout layout=(RelativeLayout)findViewById(R.id.activity_main);
        layout.setBackgroundColor(Color.RED);
        return true;
    }
    @Override
    public void onLongPress(MotionEvent event){
        gestureText.setText("onLongPress");
        RelativeLayout layout=(RelativeLayout)findViewById(R.id.activity_main);
        layout.setBackgroundColor(Color.BLACK);
    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY){
        gestureText.setText("onScroll");
        RelativeLayout layout=(RelativeLayout)findViewById(R.id.activity_main);
        layout.setBackgroundColor(Color.YELLOW);
        return true;
    }
    @Override
    public void onShowPress(MotionEvent event){
        gestureText.setText("onShowPress");
        RelativeLayout layout=(RelativeLayout)findViewById(R.id.activity_main);
        layout.setBackgroundColor(Color.GRAY);
    }
    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        gestureText.setText("onSingleTapUp");
        RelativeLayout layout=(RelativeLayout)findViewById(R.id.activity_main);
        layout.setBackgroundColor(Color.GREEN);
        return true;
    }
    @Override
    public boolean onDoubleTap(MotionEvent event) {
        gestureText.setText("onDoubleTap");
        RelativeLayout layout=(RelativeLayout)findViewById(R.id.activity_main);
        layout.setBackgroundColor(Color.MAGENTA);
        return true;
    }
    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        gestureText.setText("onDoubleTapEvent");
        RelativeLayout layout=(RelativeLayout)findViewById(R.id.activity_main);
        layout.setBackgroundColor(Color.DKGRAY);
        return true;
    }
    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        gestureText.setText("onSingleTapConfirmed");
        RelativeLayout layout=(RelativeLayout)findViewById(R.id.activity_main);
        layout.setBackgroundColor(Color.LTGRAY);
        return true;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gDetector.onTouchEvent(event);
        return super.onTouchEvent(event); }
}
