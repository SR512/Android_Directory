package com.srktechnology.directory.Adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.srktechnology.directory.R;

public class UserViewHolder extends RecyclerView.ViewHolder {

    public ImageView imgProfile;
    public TextView txtUserName,txtUserMobile,txtUserAddress,txtUserJob;

    public UserViewHolder(View itemView) {
        super(itemView);

        imgProfile = (ImageView)itemView.findViewById(R.id.imgAvtar);
        txtUserName = (TextView)itemView.findViewById(R.id.txtUserName);
        txtUserMobile = (TextView)itemView.findViewById(R.id.txtUserMobile);
        txtUserJob = (TextView)itemView.findViewById(R.id.txtUserJob);
        txtUserAddress = (TextView)itemView.findViewById(R.id.txtUserAddress);

    }
}
