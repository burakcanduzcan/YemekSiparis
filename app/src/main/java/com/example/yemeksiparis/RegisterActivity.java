package com.example.yemeksiparis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {


    EditText etUsername;
    EditText etPassword;
    Button btnRegister;
    ListView lv;
    ArrayAdapter usersArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        lv = (ListView) findViewById(R.id.lv);
        dataBaseHelper = new DataBaseHelper(RegisterActivity.this);

        showUsersOnListView();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel userModel;
                try {
                    userModel = new UserModel(-1,
                            etUsername.getText().toString(),
                            etPassword.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(RegisterActivity.this, "Kullanıcı eklerken hata oluştu", Toast.LENGTH_SHORT).show();
                    userModel = new UserModel(-1, "hata", "hata");
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(RegisterActivity.this);
                boolean success = dataBaseHelper.addSingle(userModel);
                if (success) {
                    Toast.makeText(RegisterActivity.this, "Kullanıcı başarıyla eklendi", Toast.LENGTH_SHORT).show();
                }
                showUsersOnListView();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserModel clickedUser = (UserModel) parent.getItemAtPosition(position);
                dataBaseHelper.deleteSingle(clickedUser);
                showUsersOnListView();
                Toast.makeText(RegisterActivity.this, "Kullanıcı silindi", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showUsersOnListView() {
        usersArrayAdapter = new ArrayAdapter<UserModel>(RegisterActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getAll());
        lv.setAdapter(usersArrayAdapter);
    }
}