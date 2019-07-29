package com.gmail.dikdikmaulana15.comgmaildikdikmaulana151;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    MySQLHelper dbHelper;
    private EditText ed_num;
    private EditText ed_name;
    protected Cursor cursor;
    private int id = -1;
    protected ListAdapter adapter;
    protected ListView numberList;

    private ImageView imageView;
    Button btn_cam;
    Uri file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.ed_num = (EditText) this.findViewById(R.id.editText_num);
        this.ed_name = (EditText) this.findViewById(R.id.editText_name);
        this.numberList = (ListView) this.findViewById(R.id.ListView01);
        dbHelper = new MySQLHelper(this);
        numberList.setSelected(true);
        numberList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                cursor = db.rawQuery("SELECT * FROM data", null);
                cursor.moveToPosition(arg2);
                ed_num.setText(cursor.getString(1));
                ed_name.setText(cursor.getString(2));
                id = cursor.getInt(0);
            }
        });
        view();
        btn_cam = findViewById(R.id.btup);
        imageView = findViewById(R.id.imageView);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            btn_cam.setEnabled(false);
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                btn_cam.setEnabled(true);
            }
        }
    }
    public void takePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = Uri.fromFile(getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
        startActivityForResult(intent, 0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                imageView.setImageURI(file);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Camera");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg");
    }
    private void addData(String num, String name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL("insert into " + MySQLHelper.TABLE + " values(null,'" + num + "','" + name + "')");
        } catch (Exception e) {
            ed_num.setText(e.toString());
        }
    }

    public void but_inClick(View v) {
        addData(ed_num.getText().toString(), ed_name.getText().toString());
        view();
    }

    public void but_DelClick(View v) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            if (id != -1) {
                db.execSQL("delete from " + MySQLHelper.TABLE + " where number='" + ed_num.getText().toString() + "'");
                view();
            }
        } catch (Exception e) {
            ed_num.setText(e.toString());
        }
    }

    public void but_EditClick(View v) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            if(id!=-1){
                db.execSQL("update data set number='"+
                        ed_num.getText().toString()+"',name='"+ed_name.getText().toString()+
                        "' where _id="+id);
                view();
            }
        } catch (Exception e) {
            ed_num.setText(e.toString());
        }
    }

    private void view() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            cursor = db.rawQuery("SELECT * FROM data", null);
            adapter = new SimpleCursorAdapter(
                    this, R.layout.view2,
                    cursor, new String[]{"number", "name"},
                    new int[]{R.id.number, R.id.name});

            numberList.setAdapter(adapter);} catch (Exception e) {
            ed_num.setText(e.toString());
        }
    }

}
