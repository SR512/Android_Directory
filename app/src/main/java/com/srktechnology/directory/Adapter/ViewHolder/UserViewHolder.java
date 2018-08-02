package com.srktechnology.directory.Adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.srktechnology.directory.R;

import me.grantland.widget.AutofitTextView;

public class UserViewHolder extends RecyclerView.ViewHolder {

    public ImageView imgProfile;
    public TextView txtUserName,txtUserMobile,txtUserJob,txtCity;
    public AutofitTextView txtUserAddress;

    public UserViewHolder(View itemView) {
        super(itemView);

        imgProfile = (ImageView)itemView.findViewById(R.id.imgAvtar);
        txtUserName = (TextView)itemView.findViewById(R.id.txtUserName);
        txtUserMobile = (TextView)itemView.findViewById(R.id.txtUserMobile);
        txtUserJob = (TextView)itemView.findViewById(R.id.txtUserJob);
        txtUserAddress = (AutofitTextView) itemView.findViewById(R.id.txtUserAddress);
        txtCity = (TextView)itemView.findViewById(R.id.txtCity);

    }
}
