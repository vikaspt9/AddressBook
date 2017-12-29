package com.example.adminibm.addressbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.adminibm.addressbook.bean.Contacts;

public class AddEntry extends AppCompatActivity {
    public final static String SAVE_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

       /* Intent intent = getIntent();
        String contactId =  intent.getStringExtra("contact_id");
        Log.d("clicked contaci ID===>",contactId);*/
    }

    public void saveData(View view) {
        DataBaseHelper db = new DataBaseHelper(this);
        Intent intent = new Intent(this, MainActivity.class);
        EditText txtName = (EditText) findViewById(R.id.txtName);
        EditText txtPhone = (EditText) findViewById(R.id.txtPhone);
        EditText txtEmail = (EditText) findViewById(R.id.txtEmail);

        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contacts(txtName.getText().toString(), txtPhone.getText().toString(), txtEmail.getText().toString()));

        intent.putExtra(SAVE_MESSAGE,"OK");
        startActivity(intent);
    }
}
