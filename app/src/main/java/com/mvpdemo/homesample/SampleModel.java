package com.mvpdemo.homesample;

import android.util.Log;

import com.mvpdemo.base.BaseModel;
import com.mvpdemo.datamodel.College;

import java.util.ArrayList;
import java.util.List;

public class SampleModel extends BaseModel<SampleModelListener> {

    private ArrayList<College> colleges;
    private int position = -1;

    public SampleModel(SampleModelListener listener) {
        super(listener);
    }

    @Override
    public void init() {

    }

    public void getList() {
        colleges = getDataManager().retrieveCollege();
       getListener().onCollegeRetrieved(colleges);
    }

    public void deleteFromDatabase(int collegeId) {
        position = getItemPosition(collegeId);
        if (getDataManager().deleteCollege(collegeId)) {
            getListener().onCollegeDeleted(position);
            colleges.remove(position);
        } else {
            Log.e("deleteFromDatabase: ", "error");
        }

    }

    private int getItemPosition(int collegeId) {
        for (College c : colleges) {
            if (c.getId() == collegeId)
                return colleges.indexOf(c);
        }
        return -1;
    }
}
