package ru.nlcodeteam.typicode.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import ru.nlcodeteam.typicode.GlideApp;
import ru.nlcodeteam.typicode.R;
import ru.nlcodeteam.typicode.data.local.Photo;


public class PhotosAdapter extends BaseAdapter  {


    private WeakReference<Context> context;
    private ArrayList<Photo> photos;

    public PhotosAdapter(Context context,ArrayList<Photo> photos){
        this.context = new WeakReference<Context>(context);
        this.photos = photos;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public Object getItem(int i) {
        return photos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(context.get());
        ViewHolder holder = new ViewHolder();

        if (view == null) {
            view = inflater.inflate(R.layout.photo_list_item, viewGroup, false);
            holder.img = (ImageView) view.findViewById(R.id.photo_item_img);
            holder.description = (TextView) view.findViewById(R.id.photo_item_title_tw);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        holder.description.setText(photos.get(i).getTitle());


        Log.d(context.get().getPackageName(),photos.get(i).getUrl());
        GlideApp.with(context.get())
                .load(photos.get(i).getUrl())
                .into(holder.img);


        return view;
    }





    static class ViewHolder {
        TextView description;
        ImageView img;
    }
}

