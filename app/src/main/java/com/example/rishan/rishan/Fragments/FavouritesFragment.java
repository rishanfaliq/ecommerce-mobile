package com.example.rishan.rishan.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rishan.rishan.Models.Cart;
import com.example.rishan.rishan.Models.Favourites;
import com.example.rishan.rishan.Models.Products;
import com.example.rishan.rishan.Models.PurchasedItems;
import com.example.rishan.rishan.R;

import java.util.List;

/**
 * Created by Rishan on 6/6/2018.
 */

public class FavouritesFragment extends Fragment {

        String id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.favourites, container, false);
        getActivity().setTitle("Favourites");

        ListView listView = view.findViewById(R.id.favouritesList);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("uid", "");

        final List<Favourites> fList = Favourites.find(Favourites.class, "u = ? ", id);
        FavouritesAdapter favouritesAdapter = new FavouritesAdapter(getContext(), fList);
        listView.setAdapter(favouritesAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GridviewItemFragment f = new GridviewItemFragment();

                Favourites fav= fList.get(position);
                long  uid=fav.getP().getId();
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
