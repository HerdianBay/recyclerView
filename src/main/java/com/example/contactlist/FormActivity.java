package com.example.contactlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class FormActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button_sbmt;
    private EditText textNama, textNomor;
    private ArrayList<ContactModel> contactList = new ArrayList<>();
    private ContactAdapter contactAdapter;
    private MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_layout);

        Intent intent = getIntent();
        button_sbmt = findViewById(R.id.submitBtn);
        textNama = findViewById(R.id.inputNama);
        textNomor = findViewById(R.id.inputNomor);

        button_sbmt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == button_sbmt.getId()){
            if(textNama.getText().toString().equals("") || textNomor.getText().toString().equals("")){
                Toast.makeText(this, "Please Fill The Entire Form", Toast.LENGTH_SHORT).show();
            } else {
                String nama = textNama.getText().toString();
                String nomor = textNomor.getText().toString();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("nama", nama);
                resultIntent.putExtra("nomor", nomor);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        }
    }
}