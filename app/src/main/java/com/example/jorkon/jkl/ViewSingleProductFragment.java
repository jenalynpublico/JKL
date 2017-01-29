package com.example.jorkon.jkl;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewSingleProductFragment extends Fragment {

    private String key;

    private ImageView imageViewImg;

    private TextView txtName, txtColor, txtDesc;

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("productWithColor");

    public ViewSingleProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_single_product, container, false);

        Toast.makeText(getActivity().getApplicationContext(), key, Toast.LENGTH_LONG).show();

        txtName = (TextView) view.findViewById(R.id.txtName);

        txtColor = (TextView) view.findViewById(R.id.txtColor);

        txtDesc = (TextView) view.findViewById(R.id.txtDesc);

        imageViewImg = (ImageView) view.findViewById(R.id.imageViewImg);

        databaseReference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String productName = (String) dataSnapshot.child("name").getValue();

                String productDesc = (String) dataSnapshot.child("description").getValue();

                String productImg = (String) dataSnapshot.child("picUrl").getValue();

                String productColor = (String) dataSnapshot.child("color").getValue();

                txtName.setText(productName);

                txtDesc.setText(productDesc);

                txtColor.setText(productColor);

                Picasso.with(getActivity().getApplicationContext()).load(productImg).into(imageViewImg);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    public void setProductKey(String key) {
        this.key = key;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("key", key);
    }


}
