package com.example.nathapong.app67_firelistadapterusinglistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    ListView listView ;

    DatabaseReference databaseReference;

    FirebaseListAdapter<Boxer>firebaseListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listGetData);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference();
        databaseReference.keepSynced(true);

        FirebaseListOptions<Boxer> options = new FirebaseListOptions.Builder<Boxer>()
                .setLayout(android.R.layout.two_line_list_item)
                .setQuery(databaseReference, Boxer.class)
                .build();

        firebaseListAdapter = new FirebaseListAdapter<Boxer>(options) {
            @Override
            protected void populateView(View view, Boxer model, int position) {

                ((TextView)view.findViewById(android.R.id.text1)).setText(model.getBoxerName());

            }
        };

        listView.setAdapter(firebaseListAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseListAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseListAdapter.stopListening();
    }
}
