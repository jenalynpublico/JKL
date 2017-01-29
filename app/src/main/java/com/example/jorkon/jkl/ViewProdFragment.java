package com.example.jorkon.jkl;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewProdFragment extends Fragment {

    private ArrayList<String> prodArray;

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://jkl-thesisit.firebaseio.com/productWithColor");

    private RecyclerView productsList;

    private ViewProdFragmentInterface viewProdFragmentInterfaceRef;

    public ViewProdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_view_prod, container, false);

        productsList = (RecyclerView) v.findViewById(R.id.productsList);
       // productsList.setHasFixedSize(true);
        productsList.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Products, ProductViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(
                Products.class,
                R.layout.product_row,
                ProductViewHolder.class,
                databaseReference
        ) {
            @Override
            protected void populateViewHolder(ProductViewHolder viewHolder, Products model, int position) {

                final String productKey = getRef(position).getKey();

                viewHolder.setName(model.getName());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setImage(getActivity().getApplicationContext(), model.getPicUrl());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(viewProdFragmentInterfaceRef != null) {
                            viewProdFragmentInterfaceRef.callProductKey(productKey);
                        }
                    }
                });
            }
        };

        productsList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setName(String name) {
            TextView productName = (TextView) mView.findViewById(R.id.prodName);
            productName.setText(name);
        }

        public void setDescription(String description) {
            TextView productDescription = (TextView) mView.findViewById(R.id.prodDesc);
            productDescription.setText(description);
        }

        public void setImage(Context ctx, String image) {
            ImageView prodImg = (ImageView) mView.findViewById(R.id.prodImg);
            Picasso.with(ctx).load(image).into(prodImg);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ViewProdFragmentInterface) {
            viewProdFragmentInterfaceRef = (ViewProdFragmentInterface) context;
        } else {
            throw new RuntimeException(context.toString()+" must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        viewProdFragmentInterfaceRef = null;
    }

    public interface ViewProdFragmentInterface {
        void callProductKey(String key);
    }

}
