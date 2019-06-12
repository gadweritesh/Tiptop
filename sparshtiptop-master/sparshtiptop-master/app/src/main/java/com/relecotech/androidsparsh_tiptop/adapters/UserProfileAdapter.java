package com.relecotech.androidsparsh_tiptop.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.relecotech.androidsparsh_tiptop.R;
import com.relecotech.androidsparsh_tiptop.models.UserProfileListData;

import java.util.List;

/**
 * Created by Relecotech on 09-02-2018.
 */

public class UserProfileAdapter extends RecyclerView.Adapter<UserProfileAdapter.ViewHolder> {


    LayoutInflater inflater;
    Context context;
    List<UserProfileListData> profileListData;


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView userProfileImageView;
        private TextView userProfileDescriptionTextView;
        private TextView userProfileTitleTextView;

        public ViewHolder(View convertView) {
            super(convertView);
            userProfileImageView = (ImageView) convertView.findViewById(R.id.userProfileImageView);
            userProfileDescriptionTextView = (TextView) convertView.findViewById(R.id.userProfileDescriptionTextView);
            userProfileTitleTextView = (TextView) convertView.findViewById(R.id.userProfileTitleTextView);
        }
    }

    public UserProfileAdapter(Context context, List<UserProfileListData> profileListData) {
        this.context = context;
        this.profileListData = profileListData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_profile_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        UserProfileListData getAbsentListObject = profileListData.get(position);

        holder.userProfileDescriptionTextView.setText(getAbsentListObject.getDescription());
        holder.userProfileTitleTextView.setText(getAbsentListObject.getTitle());
        holder.userProfileImageView.setImageResource(getAbsentListObject.getTitleImage());

    }

    @Override
    public int getItemCount() {
        return profileListData.size();
    }

}