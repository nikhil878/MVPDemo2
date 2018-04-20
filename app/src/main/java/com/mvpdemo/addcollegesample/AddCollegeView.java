package com.mvpdemo.addcollegesample;

import com.mvpdemo.base.BaseView;
import com.mvpdemo.datamodel.College;

public interface AddCollegeView extends BaseView {
    void errorName();
    void errorAddress();
    void errorDescription();
    void errorCourses();
    void errorPhone();
    void errorEmail();
    void pickImage();
    void finishCurrentActivity();
    College getCollegeDetails();
    void showCollegeDetails(College college);
}
