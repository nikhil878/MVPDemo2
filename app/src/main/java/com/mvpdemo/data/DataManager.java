package com.mvpdemo.data;

import com.mvpdemo.data.database.DatabaseManager;
import com.mvpdemo.datamodel.College;

import java.util.ArrayList;

public class DataManager implements IDataManager {

    private DatabaseManager databaseManager;
    private static DataManager instance;

    private DataManager() {
        databaseManager = DatabaseManager.getInstance();
    }

    public static DataManager getInstance() {
        if (instance == null) {
            synchronized (DataManager.class) {
                if (instance == null)
                    instance = new DataManager();
            }
        }
        return instance;
    }

    @Override
    public int addCollege(College college) {
       return databaseManager.insert(college);
    }

    @Override
    public int updateCollege(College college) {
       return databaseManager.update(college);
    }

    @Override
    public boolean deleteCollege(int collegeId) {
        return databaseManager.del(collegeId);
    }

    @Override
    public ArrayList<College> retrieveCollege() {
        return databaseManager.retrieve();
    }

    @Override
    public College fetchParticularCollege(int collegeId) {
        return databaseManager.fetchSpecificCollege(collegeId);
    }
}
