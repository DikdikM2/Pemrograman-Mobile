package dikdikmaulana48gmail.storagetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nama, telepon;
    TextView dataTelepon;
    Button tombolInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nama = (EditText) findViewById(R.id.editNama);
        telepon = (EditText) findViewById(R.id.editTelepon);
        dataTelepon = (TextView) findViewById(R.id.textDataTelp);
        tombolInput = (Button) findViewById(R.id.buttonInput);

        tombolInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
//menyiapkan buffer
                byte[] bufferNama = new byte[30];
                byte[] bufferTelepon = new byte[15];

                //menyalin data ke buffer
                salinData(bufferNama, nama.getText().toString());
                salinData(bufferTelepon, telepon.getText().toString());
//proses menyimpan file ke internal memori
                try {
//menyiapkan file di memori internal
                    FileOutputStream dataFile = openFileOutput("telepon.dat", MODE_APPEND);
                    DataOutputStream output = new DataOutputStream(dataFile);

//menyimpan data
                    output.write(bufferNama);
                    output.write(bufferTelepon);

//menutup file
                    dataFile.close();

//menampilkan pesan jika data tersimpan
                    Toast.makeText(getBaseContext(), "Data telah disimpan",
                            Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(getBaseContext(), "Kesalahan: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                tampilkanData();
            }
        });
        tampilkanData();
    }

    public void salinData(byte[] buffer, String data) {
//mengosongkan buffer
        for (int i = 0; i < buffer.length; i++)
            buffer[i] = 0;

//menyalin data ke buffer
        for (int i = 0; i < data.length(); i++)
            buffer[i] = (byte) data.charAt(i);
    }

    public void tampilkanData() {
        try {
            // menyiapkan file untuk dibaca
            FileInputStream dataFile = openFileInput("telepon.dat");
            DataInputStream input = new DataInputStream(dataFile);

            //menyiapkan buffer
            byte[] bufNama = new byte[30];
            byte[] bufTelepon = new byte[15];

            String infoData = "Data Telepon:\n";

            //proses membaca data
            while (input.available() > 0) {
                input.read(bufNama);
                input.read(bufTelepon);

                String dataNama = "";
                for (int i = 0; i < bufNama.length; i++)
                    dataNama = dataNama + (char) bufNama[i];

                String dataTelepon = "";
                for (int i = 0; i < bufTelepon.length; i++)
                    dataTelepon = dataTelepon + (char) bufTelepon[i];

                //format menampilkan data
                infoData = infoData + " > " + dataNama + " - " +
                        dataTelepon + "\n";
                output.write(bufferTelepon);

//menutup file
                dataFile.close();

//menampilkan pesan jika data tersimpan
                Toast.makeText(getBaseContext(), "Data telah disimpan",
                        Toast.LENGTH_LONG).show();
            }
            catch(IOException e){
                Toast.makeText(getBaseContext(), "Kesalahan: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
            tampilkanData();
        }
    }

    );

    tampilkanData();
}

    public void salinData(byte[] buffer, String data) {
//mengosongkan buffer
        for (int i = 0; i < buffer.length; i++)
            buffer[i] = 0;

//menyalin data ke buffer
        for (int i = 0; i < data.length(); i++)
            buffer[i] = (byte) data.charAt(i);
    }

    public void tampilkanData() {
        try {
            // menyiapkan file untuk dibaca
            FileInputStream dataFile = openFileInput("telepon.dat");
            DataInputStream input = new DataInputStream(dataFile);

            //menyiapkan buffer
            byte[] bufNama = new byte[30];
            byte[] bufTelepon = new byte[15];

            String infoData = "Data Telepon:\n";

            //proses membaca data
            while (input.available() > 0) {
                input.read(bufNama);
                input.read(bufTelepon);

                String dataNama = "";
                for (int i = 0; i < bufNama.length; i++)
                    dataNama = dataNama + (char) bufNama[i];

                String dataTelepon = "";
                for (int i = 0; i < bufTelepon.length; i++)
                    dataTelepon = dataTelepon + (char) bufTelepon[i];

                //format menampilkan data
                infoData = infoData + " > " + dataNama + " - " +
                        dataTelepon + "\n";
            }
        }
    }
