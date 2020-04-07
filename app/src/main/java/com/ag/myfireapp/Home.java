package com.ag.myfireapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {
    private ListView mListView;
    private FirebaseListAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mListView = findViewById(R.id.mListView);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://my-test-project-990ee.firebaseio.com/Users");
        FirebaseListOptions<String> options = new FirebaseListOptions.Builder<String>()
                .setQuery(databaseReference, String.class)
                .setLayout(android.R.layout.simple_list_item_1)
                .build();
        adapter = new FirebaseListAdapter<String>(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull String model, int position) {
                TextView textView = v.findViewById(android.R.id.text1);
                textView.setText(model);
                Log.d("Heyy", "Here Model" + model);
            }
        };
        mListView.setAdapter(adapter);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("Heyy", "Here Model" + dataSnapshot.getValue());
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
