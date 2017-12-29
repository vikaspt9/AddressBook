package com.example.adminibm.addressbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adminibm.addressbook.bean.Contacts;

public class ViewEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entry);

        Intent intent = getIntent();
        String contactId = intent.getStringExtra("contact_id");
        Log.d("clicked contaci ID===>", contactId);

        DataBaseHelper db = new DataBaseHelper(this);
        Contacts c = db.getContact(Integer.parseInt(contactId));

        TextView txtName = (TextView) findViewById(R.id.labelName);
        TextView txtPhone = (TextView) findViewById(R.id.labelPhone);
        TextView txtEmail = (TextView) findViewById(R.id.labelEmail);

        txtName.setText(c.getName());
        txtEmail.setText(c.getEmail());
        txtPhone.setText(c.getPhone());
    }
}
