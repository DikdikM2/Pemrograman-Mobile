package dikdikmaulana48.gmail.belajarkamera;
//TODO 1 : mengimport class yang dibutuhkan dalam program
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //TODO 2 : Mendeklarasikan variabel imageView, btn_cam dan file
    private ImageView imageView;
    Button btn_cam;
    Uri file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO 3 : memberikan nilai pada variabel btn_cam dan imageView berdasarkan id pallete
        btn_cam = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
        //TODO 4 : Mengecek Izin Kamera
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //TODO 5 : Kamera di disable
            btn_cam.setEnabled(false);
            //TODO 6 : merequest izin kamera
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
        /*btn_cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, 0);
            }
        });*/
    }

    @Override
    //TODO 7 : Method untuk mengetahui izin kamera di izinkan atau tidak, jika diizinkan maka kamera di enable
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                btn_cam.setEnabled(true);
            }
        }
    }

    public void takePicture(View view) {
        //TODO 8 : Membuat intent untuk membuka kamera
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //TODO 9 : Memberikan nilai pada file dari file gambar dari method getOutputMediaFile
        file = Uri.fromFile(getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
        //TODO 10 : Menjalankan intent dengan request code 0
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //TODO 11 : Mengecek jika hasilnya benar maka akan disimpan di file
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                imageView.setImageURI(file);
                //TODO 12 : Jika user membatalkan maka akan memunculkan toast
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private static File getOutputMediaFile() {
        //TODO 13 : Mengambil directory picture
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Camera");
        //TODO 14 : Mengecek directory picture
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        //TODO 15 : Membuat variabel timeStamp untuk hasil foto
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //TODO 16 : Menyimpan hasil foto
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg");
    }
}
