package ru.nlcodeteam.typicode.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.nlcodeteam.typicode.R;
import ru.nlcodeteam.typicode.adapter.OnItemClickListener;
import ru.nlcodeteam.typicode.adapter.holder.TextViewHolder;
import ru.nlcodeteam.typicode.data.local.Album;

/**
 * Created by eldar on 29.10.2017.
 */

public class AlbumsAdapter extends RecyclerView.Adapter<TextViewHolder> {

    private List<Album> albums;
    private OnItemClickListener listener;

    public AlbumsAdapter(ArrayList<Album> albums,OnItemClickListener listener) {
        this.albums = albums;
        this.listener = listener;
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        TextViewHolder holder = new  TextViewHolder(view);
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.album_item;
    }

    @Override
    public void onBindViewHolder(final TextViewHolder holder, final int position) {
        final Album album = albums.get(position);
        holder.mTitle.setText(album.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onItemClick(album,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }
}
