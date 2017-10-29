package ru.nlcodeteam.typicode.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.nlcodeteam.typicode.R;
import ru.nlcodeteam.typicode.adapter.holder.UserHolder;
import ru.nlcodeteam.typicode.data.local.User;

public class UserAdapter extends RecyclerView.Adapter<UserHolder> {

    private List<User> list;
    private OnItemClickListener mListener;

    private List<UserHolder> holders;

    public UserAdapter(ArrayList<User> list, OnItemClickListener mListener) {
        this.list = list;
        this.mListener = mListener;
        holders = new ArrayList<>();
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        UserHolder holder = new  UserHolder(view);
        holders.add(holder);
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.user_item;
    }

    @Override
    public void onBindViewHolder(final UserHolder holder, final int position) {
       final User user = list.get(position);
       holder.mName.setText(user.getName());
       holder.mAddress.setText(user.getAddress());
       holder.mEmail.setText(user.getEmail());
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (mListener != null)
                   mListener.onItemClick(user,position);
           }
       });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
