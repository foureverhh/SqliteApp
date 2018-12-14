package com.example.zfgg04.sqliteapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName,editSurname,editMarks;
    Button btn_getData;
    Button btn_viewAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
        editName = findViewById(R.id.editText_name);
        editSurname = findViewById(R.id.editText_surname);
        editMarks = findViewById(R.id.editText_marks);
        btn_getData = findViewById(R.id.button_addData);
        btn_viewAll = findViewById(R.id.button_viewAll);

        btn_getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inInserted = myDb.insertDate(editName.getText().toString(),
                        editSurname.getText().toString(),
                        editMarks.getText().toString());
                if(inInserted)
                    Toast.makeText(MainActivity.this,
                            "Data inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,
                            "Data not inserted",Toast.LENGTH_LONG).show();
            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if(res.getCount() == 0){
                    showMessage("Error","No data in database!");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Id： "+res.getString(0)+"\n");
                    buffer.append("Name： "+res.getString(1)+"\n");
                    buffer.append("Surname： "+res.getString(2)+"\n");
                    buffer.append("Marks： "+res.getString(3)+"\n\n");
                }
                showMessage("Data",buffer.toString());
            }
        });

    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
