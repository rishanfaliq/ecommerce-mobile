package com.example.rishan.rishan.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.rishan.rishan.Models.ProductTags;
import com.example.rishan.rishan.Models.Products;
import com.example.rishan.rishan.R;
import com.example.rishan.rishan.Models.SIngleItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rishan on 5/4/2018.
 */

public class HomeFragment extends Fragment {
    Context context;
    String name;
    double price;
    String image;
    int quantity;
    String selectedProduct;
    Long uid;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.activity_home, container, false);

        List<Products> productsList=new ArrayList<>();
        GridView gridView = view.findViewById(R.id.gridView);
        SIngleItem omegaAdapter;
//        Button searchButton=view.findViewById(R.id.searchButton);
//
//        final EditText searchText=view.findViewById(R.id.searchText);
        Bundle bundle=getArguments();
        String type=bundle.getString("type");
        getActivity().setTitle(type);

        if(type.equals("Men")){
            List<ProductTags> productsTagsList=ProductTags.find(ProductTags.class,  "t = ?",  "Men");
            productsList= new ArrayList<>();
            for(ProductTags pt:productsTagsList)
            {
                productsList.add(pt.getP());
            }
            omegaAdapter = new SIngleItem(getContext(), productsList);
            gridView.setAdapter(omegaAdapter);

        }else if(type.equals("Women"))
        {
            List<ProductTags> womenList=ProductTags.find(ProductTags.class,  "t = ?",  "Women");
             productsList= new ArrayList<>();
            for(ProductTags pt:womenList)
            {
                productsList.add(pt.getP());
            }
            omegaAdapter = new SIngleItem(getContext(), productsList);
            gridView.setAdapter(omegaAdapter);

        }else if(type.equals("Kid"))
        {

            List<ProductTags> kidsList=ProductTags.find(ProductTags.class,  "t = ?",  "Kid");
            productsList= new ArrayList<>();
            for(ProductTags pt:kidsList)
            {
                productsList.add(pt.getP());
            }
         omegaAdapter = new SIngleItem(getContext(), productsList);
            gridView.setAdapter(omegaAdapter);
        }else if(type.equals("Item")){

            List<ProductTags> itemList=ProductTags.find(ProductTags.class,  "t = ?",  "Item");
            productsList= new ArrayList<>();
            for(ProductTags pt:itemList)
            {
                productsList.add(pt.getP());
            }
             omegaAdapter = new SIngleItem(getContext(), productsList);
            gridView.setAdapter(omegaAdapter);
        }
        else{

              productsList=Products.findWithQuery(Products.class, "SELECT * FROM Products WHERE Name like '%"+type+"%'");
            List<ProductTags> itemList=ProductTags.find(ProductTags.class,  "t = ?",  type);
            for(ProductTags pt:itemList)
            {
                productsList.add(pt.getP());

            }

            omegaAdapter = new SIngleItem(getContext(), productsList);
            gridView.setAdapter(omegaAdapter);

        }



        final List<Products> finalProductsList = productsList;
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GridviewItemFragment f = new GridviewItemFragment();
                Products product= finalProductsList.get(position);

                uid=product.getId();
                Bundle bdl = new Bundle(4);
                bdl.putLong("uid", uid);

                f.setArguments(bdl);

                FragmentTransaction ft= getActivity().getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                ft.replace(R.id.fragment_container, f);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return view;

    }
}
