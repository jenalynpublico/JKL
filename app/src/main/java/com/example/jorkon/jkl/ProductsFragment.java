package com.example.jorkon.jkl;


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
public class ProductsFragment extends Fragment implements View.OnClickListener{


    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Products");
        View v = inflater.inflate(R.layout.fragment_products, container, false);

        Button btnLivingRoom = (Button) v.findViewById(R.id.btnLivingRoom);

        btnLivingRoom.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnLivingRoom:
                // Create new fragment and transaction

                Fragment newFragment = new ViewProdFragment();
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
