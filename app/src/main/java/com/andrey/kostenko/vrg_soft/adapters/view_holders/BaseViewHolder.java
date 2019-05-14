package com.andrey.kostenko.vrg_soft.adapters.view_holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private int currentPosition;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void onBind(int position) {
        currentPosition = position;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }
}
