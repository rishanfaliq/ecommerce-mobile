package com.example.rishan.rishan.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.Toolbar;

import com.example.rishan.rishan.Models.Cart;
import com.example.rishan.rishan.Models.Favourites;
import com.example.rishan.rishan.Models.Inquiry;
import com.example.rishan.rishan.Models.InquiryAdapter;
import com.example.rishan.rishan.Models.ProductTags;
import com.example.rishan.rishan.Models.Products;
import com.example.rishan.rishan.Models.Tags;
import com.example.rishan.rishan.Models.User;
import com.example.rishan.rishan.R;
import com.orm.SugarRecord;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import me.gujun.android.taggroup.TagGroup;

/**
 * Created by Rishan on 5/7/2018.
 */

public class GridviewItemFragment extends Fragment {

    Long position;
    String id;
    Favourites fav = new Favourites();


    Tags tagRef = new Tags();
    List<Tags> ptags = new ArrayList<>();
    Tags t;
    int quantity;
    String name;
    double price;
    String image;
    String description;
    Cart cart;
    int cartItem = 0;
    List<Cart> singleItemCart;
    CartFragment cartFragment;
    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Products pSelected = new Products();
        final List<Favourites> favList = Favourites.listAll(Favourites.class);


        final Bundle bdl = getArguments();
        position = bdl.getLong("uid");
        List<Products> productsLists = Products.listAll(Products.class);
        for (Products p : productsLists) {
            if (p.getId().equals(position)) {
                pSelected = p;
                quantity = pSelected.getQuantity();
                name = pSelected.getName();
                price = pSelected.getPrice();
                image = pSelected.getScaledImage();
                description = pSelected.getLongDescription();

            }
        }


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("uid", "");
        final User userData = SugarRecord.findById(User.class, Long.parseLong(id));

        inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.gridview_item, container, false);
        // Attaching the layout to the toolbar object

        TextView priceText = view.findViewById(R.id.productPrice);
        TextView productText = view.findViewById(R.id.productTexts);
        ImageView productImage = view.findViewById(R.id.productImage);
        //Button cartButton = view.findViewById(R.id.cartButton);
        Button buyButton = view.findViewById(R.id.buyButton);
        Button shareButton = view.findViewById(R.id.shareButton);
        TextView productQuantity = view.findViewById(R.id.quantity);
        TextView descriptionText = view.findViewById(R.id.productDesciptionText);
        final TextView sendText = view.findViewById(R.id.edittext_chatbox);
        Button sendButton = view.findViewById(R.id.button_chatbox_send);
        final ListView inquiryList = view.findViewById(R.id.inquiryList);
        final ToggleButton favourite = view.findViewById(R.id.favourites);
        final RatingBar ratingbar = view.findViewById(R.id.ratingBar);
        for (Favourites f : favList) {
            if (f.getP().getId().equals(position)) {
                favourite.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ye1));
                favourite.setChecked(false);
                break;
            } else {
                favourite.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.wh));
                favourite.setChecked(false);
            }
        }

        priceText.setText("Price: $" + String.valueOf(price));
        productText.setText(name);
        try {
            descriptionText.setText(description);
        } catch (Exception e) {
            e.printStackTrace();
        }
        productQuantity.setText("Quantity Available :" + String.valueOf(quantity));

        Picasso.get()
                .load(image)
                .placeholder(R.drawable.dress)

                .into(productImage);

        favourite.setText(null);
        favourite.setTextOn(null);
        favourite.setTextOff(null);
        favourite.setChecked(false);
        favourite.setTextSize(0);
        final Products finalPSelected = pSelected;
        favourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    favourite.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ye1));
                    for (Favourites f : favList) {
                        if (f.getP().getId().equals(position))
                        {
                            fav = f;
                            break;

                        } else
                        {
                            fav = new Favourites();
                        }
                    }
                    fav.setP(finalPSelected);
                    fav.setU(userData);
                    fav.save();
                    Toast.makeText(getContext(), "Added to Favourites!", Toast.LENGTH_SHORT).show();

                } else {
                    favourite.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.wh));
                    List<Favourites> favList = Favourites.listAll(Favourites.class);
                    for (Favourites f : favList) {
                        if (f.getP().getId().equals(position)) {
                            f.delete();
                        }

                    }

                }
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartFragment = new CartFragment();
                Bundle bundle = new Bundle();
                bundle.putLong("pid", position);
                cartFragment.setArguments(bundle);
                cartFragment.show(getFragmentManager(), "buyFragment");
            }
        });


        final List<Inquiry> inquiries =  Inquiry.find(Inquiry.class, "p = ?", String.valueOf(pSelected.getId()));
        InquiryAdapter omegaAdapter = new InquiryAdapter(getContext(), inquiries);
        inquiryList.setAdapter(omegaAdapter);
        //Number picker implementation

        try {
            //Setting Tags

            TagGroup mTagGroup = view.findViewById(R.id.tag_group);
            //external library i used for tags
            List<Tags> tagsList = Tags.listAll(Tags.class);


            List<ProductTags> pstags = ProductTags.listAll(ProductTags.class);


            String tag = "";


            String s = String.valueOf(pSelected.getId());
            //pSelected is current item in gridview obtained from position
            List<ProductTags> ptag = ProductTags.find(ProductTags.class, "p = ?", String.valueOf(position));
            // obtaining all the tags for this product
            List<String> tagLists = new ArrayList<String>();
            // new String list to save my tag names
            for (ProductTags pts : ptag)
            {
                tagLists.add(pts.getT());
            }
            mTagGroup.setTags(tagLists);

            mTagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
                @Override
                public void onTagClick(String tag) {
                    Bundle bundle=new Bundle();
                    bundle.putString("type", tag);
                    Fragment fragment = new HomeFragment();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

                    fragment.setArguments(bundle);
                    ft.replace(R.id.fragment_container, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            });

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        inquiryList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        final Products finalPSelected1 = pSelected;
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date c = Calendar.getInstance().getTime();

                Inquiry i = new Inquiry();

                i.setMessage(sendText.getText().toString());
                i.setSender(userData);
                i.setTime(c.toString());
                i.setRating(ratingbar.getRating());
                i.setP(finalPSelected1);
                i.save();

                final List<Inquiry> inquiries = Inquiry.find(Inquiry.class, "p = ?", String.valueOf(finalPSelected1.getId()));
                InquiryAdapter omegaAdapter = new InquiryAdapter(getContext(), inquiries);
                inquiryList.setAdapter(omegaAdapter);
            }
        });


//Details


        /*cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Added to Cart!",
                        Toast.LENGTH_LONG).show();

            }
        });*/


        //Button OnCliclListnerers
/*
       cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {/*
                List<Cart> cartList=Cart.listAll(Cart.class);
                for(Cart c: cartList){
                    if(c.getP().equals(p)){
                        cart=c;

                    }
                }

                Products p = Products.findById(Products.class, position);
                // singleItemCart = Cart.find(Cart.class, "p = ? and status = ?",  String.valueOf(p), "Pending");
                List<Cart> carts = Cart.listAll(Cart.class);
                for (Cart c : carts) {
                    if (c.getP().getId().equals(position)) {
                        cart = c;

                    } else {
                        cart = new Cart();
                    }
                }

                if (cart.getNumberOfItems() >= 1) {
                    cartItem = cart.getNumberOfItems();
                    cartItem = cartItem + 1;
                    //Cart updateCart=singleItemCart.get(1);
                    cart.setNumberOfItems(cartItem);
                    cart.setTotal(cartItem * p.getPrice());
                    cart.save();


                } else {
                    cart.setStatus("Pending");
                    cart.setP(p);
                    cart.setTotal(p.getPrice());
                    cartItem = cartItem + 1;
                    cart.setNumberOfItems(cartItem);
                    cart.setU(userData);
                    cart.save();
                }
                Toast.makeText(getActivity(), "Added to Cart!", Toast.LENGTH_LONG).show();

            }
        }); */

        final String finalName = name;
        final Double finalPrice = price;
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Style Omega Product:" + finalName + "+ Price: " + finalPrice + " Quantity Available" +quantity+ "";// INSERT DESCIRIPTON HERE
                String shareSub = "Style Omega Sub";
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, " Share using"));

            }
        });


        return view;


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Product Details");

    }

}
