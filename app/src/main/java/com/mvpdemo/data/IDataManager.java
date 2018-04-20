package com.mvpdemo.data;

import com.mvpdemo.datamodel.College;

import java.util.ArrayList;

public interface IDataManager {

    int addCollege(College college);
    int updateCollege(College college);
    boolean deleteCollege(int collegeId);
    ArrayList<College> retrieveCollege();
    College fetchParticularCollege(int collegeId);
}
