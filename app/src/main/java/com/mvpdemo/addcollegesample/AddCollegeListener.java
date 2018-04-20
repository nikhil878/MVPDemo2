package com.mvpdemo.addcollegesample;

import com.mvpdemo.base.BaseModelListener;
import com.mvpdemo.datamodel.College;

public interface AddCollegeListener extends BaseModelListener {
    void onCollegeFetched(College college);
    void onTaskDone(int result);
}
