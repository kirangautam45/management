package com.example.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import  android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String>arrayList;
    private ArrayAdapter<String>adapter;
    private EditText txtinput;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = (ListView) findViewById(R.id.listview);
        String[] items = {"mango", "apple", "graph"};
        arrayList = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, arrayList);
        listView.setAdapter(adapter);
        txtinput = findViewById(R.id.addtext);
        Button btAdd = findViewById(R.id.addbutton);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newItem = txtinput.getText().toString();
                arrayList.add(newItem);
                adapter.notifyDataSetChanged();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                SparseBooleanArray positiomchecker=listView.getCheckedItemPositions();
                int count=listView.getCount();
                for (int item=count-1;item>0;item--)
                    if (positiomchecker.get(item)) adapter.remove(arrayList.get(item));
                positiomchecker.clear();
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}
