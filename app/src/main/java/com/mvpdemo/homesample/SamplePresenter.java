package com.mvpdemo.homesample;

import com.mvpdemo.base.BasePresenter;
import com.mvpdemo.datamodel.College;

import java.util.ArrayList;

public class SamplePresenter extends BasePresenter<SampleView> implements SampleModelListener {

    private SampleModel model;
   // final CharSequence[] items = {"Delete College", "Update College", "Cancel"};

    public SamplePresenter(SampleView view) {
        super(view);
    }

    @Override
    protected void setModel() {
        model = new SampleModel(this);
    }

    @Override
    protected void destroy() {
        model.detachListener();
        model = null;
    }

    @Override
    protected void initView() {

    }

    public void onFabClicked() {
        getView().showNewCollegeScreen();
    }

    public void getCollegeList() {
        model.getList();
    }

    @Override
    public void onCollegeRetrieved(ArrayList<College> list) {
        if (list != null) {
            getView().showColleges(list);
        } else
            getView().noSuchCollege();
    }

    public void onRowClick(int collegeId) {
        getView().showDialogOption(collegeId);
    }

    public void onUpdateCollege(int collegeId) {
        getView().showUpdateCollegeScreen(collegeId);
    }

    public void onDeleteCollege(int collegeId) {
        // keep position here
        model.deleteFromDatabase(collegeId);
    }

    @Override
    public void onCollegeDeleted(int position) {
        getView().notifyItemRemovedFromAdapter(position);
    }

    /*public void onItemSelected(int item, int collegeId, DialogInterface) {
        if (items[item].equals(items[0])) {
           // presenter.onDeleteCollege(collegeId);
            model.deleteFromDatabase(collegeId);
        } else if (items[item].equals(items[1])) {
            //presenter.onUpdateCollege(collegeId);
            getView().showUpdateCollegeScreen(collegeId);
        } else if (items[item].equals(items[2])) {
            dialog.dismiss();
        }
    }*/
}
