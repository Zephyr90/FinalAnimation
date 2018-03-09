package com.example.zephyr.finalanimation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

import java.util.List;

/**
 * Created by zephyr on 2017/3/30.
 */

public class AnimationTypeAdapter extends RecyclerView.Adapter<AnimationTypeAdapter.AnimationViewHolder>{


    private List<String> mList;

    private Context mContext;

    public AnimationTypeAdapter(List<String> mList) {
        this.mList = mList;
    }

    @Override
    public AnimationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_animation_type, parent, false);
        AnimationViewHolder viewHolder = new AnimationViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AnimationViewHolder holder, int position) {
        final String s = mList.get(position);
        holder.mButton.setText(s);
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class AnimationViewHolder extends RecyclerView.ViewHolder {

        private final Button mButton;

        public AnimationViewHolder(View itemView) {
            super(itemView);
            mButton = ((Button) itemView.findViewById(R.id.btnType));
        }
    }
}
