package dikdikmaulana48.gmail.belajarservice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editWaktu;
    Button tombolMain, tombolStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editWaktu = findViewById(R.id.et_waktu);
        tombolMain = findViewById(R.id.bt_play);
        tombolStop = findViewById(R.id.bt_stop);

        tombolMain.setOnClickListener(this);
        tombolStop.setOnClickListener(this);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_play:
                callPlay();
                break;
            case R.id.bt_stop:
                stopPlay();
                break;
        }
    }
    public void callPlay(){
        int detik = Integer.parseInt(editWaktu.getText().toString());

        Intent intent = new Intent(MainActivity.this,MyService.class);

        PendingIntent pendingIntent = PendingIntent.getService(MainActivity.this,0, intent,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        if(alarmManager !=null){
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+(detik*1000), pendingIntent);
            Toast.makeText(getApplicationContext(),"Song Play After "+detik+" second!", Toast.LENGTH_LONG).show();
        }
    }
    public  void  stopPlay(){
        stopService(new Intent(MainActivity.this, MyService.class));
    }
}

