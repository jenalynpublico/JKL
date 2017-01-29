package com.example.jorkon.jkl;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ViewSingleProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_product);

        Intent intent = getIntent();
        String productKey = intent.getStringExtra("key");

        Bundle bundle = new Bundle();
        bundle.putString("key", productKey);
        //set Fragmentclass Arguments
        ViewSingleProductFragment myFrag = new ViewSingleProductFragment();
        myFrag.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.frame_container, myFrag);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }
}
