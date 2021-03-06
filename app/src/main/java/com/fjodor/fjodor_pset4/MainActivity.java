package com.fjodor.fjodor_pset4;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    private long _id;

    private EditText edit_text;

    final String[] from = new String[] { DatabaseHelper._ID, DatabaseHelper.TODO };

    final int[] to = new int[] {R.id.id, R.id.todo };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DBManager(this);
        dbManager.open();

        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);

        adapter = new SimpleCursorAdapter(this, R.layout.row_layout, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                TextView idTextView = (TextView) view.findViewById(R.id.id);
                String temp = idTextView.getText().toString();
                Log.d("bug", temp);

                _id = Long.parseLong(temp);

                dbManager.delete(_id);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    public void onClickAddDatabase(View view) {
        switch (view.getId()){
            case R.id.addBtn:

                edit_text = (EditText) findViewById(R.id.edit_text);

                final String input = edit_text.getText().toString();

                dbManager.insert(input);

                Cursor cursor = dbManager.fetch();

                cursor.close();
                adapter.notifyDataSetChanged();
        }
    }
}
