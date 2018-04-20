package com.mvpdemo.addcollegesample;

import android.util.Patterns;
import com.mvpdemo.base.BasePresenter;
import com.mvpdemo.datamodel.College;

public class AddCollegePresenter extends BasePresenter<AddCollegeView> implements AddCollegeListener {

    private AddCollegeModel addCollegeModel;
    private College college;

    public AddCollegePresenter(AddCollegeView view) {
        super(view);
    }

    @Override
    protected void setModel() {
        addCollegeModel = new AddCollegeModel(this);
    }

    @Override
    protected void destroy() {
        addCollegeModel.detachListener();
        addCollegeModel = null;
    }

    @Override
    protected void initView() {

    }

    /**
     * method to insert new college entry into the database
     */
    public void onSubmit() {
        college = getView().getCollegeDetails();
        if (validateInput(college)) {
            addCollegeModel.addToDatabase(college);
        }
    }


    /**
     * method to check for valid input
     */
    private boolean validateInput(College college) {
        if (college.getName().equals("")) {
            getView().errorName();
            return false;
        } else if (college.getAddress().equals("")) {
            getView().errorAddress();
            return false;
        } else if (college.getDescription().equals("")) {
            getView().errorDescription();
            return false;
        } else if (college.getCoursesAvailable().equals("")) {
            getView().errorCourses();
            return false;
        } else if (college.getPhoneNumber().equals("")) {
            getView().errorPhone();
            return false;
        } else if (college.getEmail().equals("")) {
            getView().errorEmail();
            return false;
        }
        /*else if(!Patterns.EMAIL_ADDRESS.matcher(college.getEmail()).matches())
        {
            getView().errorEmail();
            return false;
        }*/
        return true;
    }

    /**
     * method to update the records of colleges
     */
    public void onUpdatePressed() {
        college = getView().getCollegeDetails();
        if(validateInput(college))
        {
            addCollegeModel.updateDatabase(college);
        }
    }

    public void onImageViewClicked() {
        getView().pickImage();
    }

    /**
     * fetch college for the corresponding id
     * @param collegeId id of the college
     */
    public void fetchData(int collegeId) {
        addCollegeModel.getCollegeData(collegeId);
    }

    @Override
    public void onCollegeFetched(College college) {
        getView().showCollegeDetails(college);
    }

    @Override
    public void onTaskDone(int result) {
        if(result > 0)
            getView().finishCurrentActivity();
    }
}
