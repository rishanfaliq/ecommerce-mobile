package com.example.rishan.rishan.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.rishan.rishan.R;

/**
 * Created by Rishan on 6/3/2018.
 */

public class TabMain extends Fragment {

    Bundle bundle=new Bundle();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.tab_main, container, false);

        final TextView searchBar= view.findViewById(R.id.mainSearch);
        Button searchButton= view.findViewById(R.id.mainSearchButton);

        getActivity().setTitle("Home");
        Button men= view.findViewById(R.id.mensClothing);
        Button women= view.findViewById(R.id.womensClothing);
        Button kids= view.findViewById(R.id.kidsClothing);
        Button items= view.findViewById(R.id.accesories);


        TextView menT= view.findViewById(R.id.menText);
        TextView womenT= view.findViewById(R.id.womenText);
        TextView kidT= view.findViewById(R.id.kidText);
        TextView itemT= view.findViewById(R.id.itemText);





        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchResult=searchBar.getText().toString();
                Fragment fragment = new HomeFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Bundle bundle=new Bundle();
                bundle.putString("type", searchResult);
                fragment.setArguments(bundle);
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category="Men";
                Bundle bundle=new Bundle();
                bundle.putString("type", category);
                Fragment fragment = new HomeFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

                fragment.setArguments(bundle);
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        kids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category="Kid";
                Bundle bundle=new Bundle();
                bundle.putString("type", category);

                Fragment fragment = new HomeFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

                fragment.setArguments(bundle);
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category="Item";
                Bundle bundle=new Bundle();
                bundle.putString("type", category);

                Fragment fragment = new HomeFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

                fragment.setArguments(bundle);
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        women.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    String category="Women";
                    Bundle bundle=new Bundle();
                    bundle.putString("type", category);
                    Fragment fragment = new HomeFragment();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

                    fragment.setArguments(bundle);
                    ft.replace(R.id.fragment_container, fragment);
                    ft.addToBackStack(null);
                    ft.commit();


            }
        });

        return view;
    }
}
