package com.example.jorkon.jkl;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        Button btnProducts = (Button)v.findViewById(R.id.btnProducts);
        Button btnGoLive = (Button)v.findViewById(R.id.btnGoLive);

        btnProducts.setOnClickListener(this);

        return v;

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnProducts:
                // Create new fragment and transaction

                Fragment newFragment = new ProductsFragment();
                // consider using Java coding conventions (upper first char class names!!!)
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.frame_container, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
        }
    }
}
