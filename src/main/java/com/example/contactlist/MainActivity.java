package com.example.contactlist;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    private Button tambahKontak, button_sbmt;
    private TextView textView;
    private EditText textNama, textNomor;
    private ArrayList<ContactModel> contactList = new ArrayList<>();
    private ContactAdapter contactAdapter;
//    private LinearLayout layoutAdd;

    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result != null && result.getResultCode() == RESULT_OK){
                contactList.add(new ContactModel(result.getData().getStringExtra("nama"), result.getData().getStringExtra("nomor")));
                contactAdapter = new ContactAdapter(contactList, MainActivity.this);
                recyclerView.setAdapter(contactAdapter);
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        tambahKontak = findViewById(R.id.tambahKontak);
        textView = findViewById(R.id.daftarKontak);
        button_sbmt = findViewById(R.id.submitBtn);
        textNama = findViewById(R.id.inputNama);
        textNomor = findViewById(R.id.inputNomor);

        tambahKontak.setOnClickListener(view -> {
//            if(recyclerView.getVisibility() == View.VISIBLE) {
//                recyclerView.setVisibility(View.GONE);
//                layoutAdd.setVisibility(View.VISIBLE);
//            }
            if(view.getId() == tambahKontak.getId()){
                Intent intent = new Intent(this, FormActivity.class);
                startForResult.launch(intent);
            }
        });
//        button_sbmt.setOnClickListener(view -> {
//            if(textNama.getText().toString().equals("") || textNim.getText().toString().equals("")){
//                Toast.makeText(this, "Please Fill The Entire Here", Toast.LENGTH_SHORT).show();
//            } else {
//                contactList.add(new ContactModel(textNama.getText().toString(), textNim.getText().toString()));
//                contactAdapter = new ContactAdapter(contactList, this);
//                recyclerView.setAdapter(contactAdapter);
//                recyclerView.setVisibility(View.VISIBLE);
//                layoutAdd.setVisibility(View.GONE);
//            }
//        });
        contactAdapter = new ContactAdapter(contactList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        contactAdapter.setOnItemClickListener((position, v) -> {
            contactList.remove(position);
            contactAdapter = new ContactAdapter(contactList, this);
            recyclerView.setAdapter(contactAdapter);
        });
        recyclerView.setAdapter(contactAdapter);
    }

}