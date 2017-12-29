package com.example.adminibm.addressbook;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.adminibm.addressbook.bean.Contacts;

import java.util.List;

import static android.support.design.widget.Snackbar.make;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_CONTACT_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DataBaseHelper db = new DataBaseHelper(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*make(view, "Add Entry", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(MainActivity.this, AddEntry.class);
                startActivity(intent);
                //startActivityForResult(intent,ADD_CONTACT_REQUEST);
            }
        });

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(AddEntry.SAVE_MESSAGE);

        if(message!=null && message.equalsIgnoreCase("OK")) {
            showToast();
        }

        Log.d("Reading: ", "Reading all contacts..");
        final Cursor cursor = db.getAllContactsCursor();
        String[] fromColumns = {"name"};
        int[] toViews = {R.id.label};
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.contact_list, cursor,fromColumns,toViews);

        final ListView listView = (ListView) findViewById(R.id.contactList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long rowId) {

                Intent viewEntryIntent = new Intent(MainActivity.this, ViewEntry.class);
                Cursor c = (Cursor) adapter.getItem(pos);
                viewEntryIntent.putExtra("contact_id", c.getString(c.getColumnIndex("_id")));
                startActivity(viewEntryIntent);
            }
        });


        /*for (Contacts cn : contacts) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getPhone();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }*/
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showToast() {
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_LONG);
        toast.show();
    }
}
