package com.mvpdemo.homesample;

import com.mvpdemo.base.BaseView;
import com.mvpdemo.datamodel.College;

import java.util.ArrayList;

public interface SampleView extends BaseView{
   void showColleges(ArrayList<College> list);
   void showNewCollegeScreen();
   void showUpdateCollegeScreen(int collegeId);
   void noSuchCollege();
   void showDialogOption(int collegeId);
   void dismissDialog();
   void notifyItemRemovedFromAdapter(int position);
}
