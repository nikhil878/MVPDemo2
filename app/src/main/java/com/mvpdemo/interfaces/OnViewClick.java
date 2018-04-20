package com.mvpdemo.interfaces;

import android.view.View;

public interface OnViewClick {
    /**
     * method that takes row and the position of the row in a Recycler View
     * @param view  row in the recycler view
     * @param collegeId position of that row
     */
    void onItemClick(View view, int collegeId);
}
