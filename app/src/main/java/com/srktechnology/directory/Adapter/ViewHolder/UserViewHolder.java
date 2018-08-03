package com.srktechnology.directory.Adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.srktechnology.directory.R;

import de.hdodenhof.circleimageview.CircleImageView;
import me.grantland.widget.AutofitTextView;

public class UserViewHolder extends RecyclerView.ViewHolder {

    public CircleImageView imgProfile;
    public TextView txtUserName,txtUserMobile,txtUserJob,txtCity;
    public AutofitTextView txtUserAddress;

    public UserViewHolder(View itemView) {
        super(itemView);

        imgProfile = itemView.findViewById(R.id.imgAvtar);
        txtUserName = itemView.findViewById(R.id.txtUserName);
        txtUserMobile = itemView.findViewById(R.id.txtUserMobile);
        txtUserJob = itemView.findViewById(R.id.txtUserJob);
        txtUserAddress = itemView.findViewById(R.id.txtUserAddress);
        txtCity = itemView.findViewById(R.id.txtCity);

    }
}
