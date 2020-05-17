package com.app.mobileproject;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Objects;

public class ImageListAdapter extends ArrayAdapter<uploadinfo> {
    private static final String TAG = "ImageListAdapter";
    private Context mContext;
    private int mResource;
    public ImageListAdapter(Context context, int resource, ArrayList<uploadinfo>  list) {
        super(context, resource, list);
        mContext = context;
        mResource = resource;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the image information



        String TempImagePrice = Objects.requireNonNull(getItem(position)).getImagePrice();
        String TempImageStorage = Objects.requireNonNull(getItem(position)).getImageStorage();
        String TempImageSpecification = Objects.requireNonNull(getItem(position)).getImageSpecification();
        String imageName = Objects.requireNonNull(getItem(position)).getImageName();
        String imageUrl = Objects.requireNonNull(getItem(position)).getImageURL();

        //Create the employee object with the information
        uploadinfo ImageInfo = new uploadinfo(imageName,TempImagePrice,TempImageStorage,TempImageSpecification,imageUrl);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(this.mResource, parent,false);
        TextView imgName = convertView.findViewById(R.id.image_name);
        TextView imgprice = convertView.findViewById(R.id.image_price);
        TextView imgstorage = convertView.findViewById(R.id.image_storage);
        TextView imgspecification = convertView.findViewById(R.id.image_specification);
        ImageView imgView= convertView.findViewById(R.id.image_View);
        imgName.setText(imageName);
        imgprice.setText(TempImagePrice);
        imgstorage.setText(TempImageStorage);
        imgspecification.setText(TempImageSpecification);

        //Loading image from Glide library.

        Glide.with(convertView.getContext()).load(imageUrl).dontAnimate().into(imgView);
        return convertView;
    }
}