package dikdikmaulana48.gmail.belajarservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
//TODO 1 : Membuat class MyService dengan extends Service
public class MyService extends Service {
    //TODO 2 : Memanggil import MediaPlayer
    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate(){
        mediaPlayer = MediaPlayer.create(this, R.raw.perfect);
        mediaPlayer.setLooping(true);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        mediaPlayer.start();
        return START_STICKY;
    }
    @Override
    public void onDestroy(){
        mediaPlayer.stop();
    }
}
