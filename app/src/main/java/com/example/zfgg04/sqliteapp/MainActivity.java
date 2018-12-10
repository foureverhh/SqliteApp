package com.example.zfgg04.sqliteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName,editSurname,editMarks;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
        editName = findViewById(R.id.editText_name);
        editSurname = findViewById(R.id.editText_surname);
        editMarks = findViewById(R.id.editText_marks);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
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

    }
}
