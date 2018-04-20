package com.mvpdemo.homesample;

import com.mvpdemo.base.BaseModelListener;
import com.mvpdemo.datamodel.College;

import java.util.ArrayList;

public interface SampleModelListener extends BaseModelListener {

    void onCollegeRetrieved(ArrayList<College> list);
    void onCollegeDeleted(int position);
}
