package ru.nlcodeteam.typicode.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.nlcodeteam.typicode.R;

/**
 * Created by eldar on 28.10.2017.
 */

public class UserHolder extends RecyclerView.ViewHolder {

    public TextView mName,mAddress,mEmail;
    public UserHolder(View itemView) {
        super(itemView);
        mName = itemView.findViewById(R.id.user_name_tw);
        mAddress = itemView.findViewById(R.id.address_tw);
        mEmail = itemView.findViewById(R.id.email_tw);

    }
}
