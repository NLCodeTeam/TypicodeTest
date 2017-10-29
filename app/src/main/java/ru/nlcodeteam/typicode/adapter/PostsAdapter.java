package ru.nlcodeteam.typicode.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.nlcodeteam.typicode.R;
import ru.nlcodeteam.typicode.adapter.OnItemClickListener;
import ru.nlcodeteam.typicode.adapter.holder.PostHolder;
import ru.nlcodeteam.typicode.data.local.Post;

/**
 * Created by eldar on 29.10.2017.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostHolder> {

    private List<Post> posts;
    private OnItemClickListener listener;

    public PostsAdapter(ArrayList<Post> posts, OnItemClickListener listener) {
        this.posts = posts;
        this.listener = listener;
    }

    @Override
    public PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        PostHolder holder = new  PostHolder(view);
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.post_item;
    }

    @Override
    public void onBindViewHolder(final PostHolder holder, final int position) {
        final Post post = posts.get(position);
        holder.mTitle.setText(post.getTitle());
        holder.mContent.setText(post.getInfo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onItemClick(post,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
