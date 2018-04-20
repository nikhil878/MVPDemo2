package com.mvpdemo.homesample;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mvpdemo.R;
import com.mvpdemo.adapters.CollegeAdapter;
import com.mvpdemo.addcollegesample.AddCollegeActivity;
import com.mvpdemo.base.BaseActivity;
import com.mvpdemo.datamodel.College;
import com.mvpdemo.editcollegesample.EditCollegeActivity;
import com.mvpdemo.interfaces.OnViewClick;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class HomePageActivity extends BaseActivity implements SampleView, OnViewClick {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.rv_colleges)
    RecyclerView rvColleges;
    private ArrayList<College> beanArrayList;
    private SamplePresenter presenter;
    private SearchView searchView;
    private CollegeAdapter collegeAdapter;
    private DialogInterface dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        presenter = new SamplePresenter(this);
        beanArrayList = new ArrayList<>();
       // presenter.getCollegeList();
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_sample;
    }

    @Override
    public void showColleges(ArrayList<College> list) {
        beanArrayList = list;
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvColleges.setLayoutManager(manager);
        collegeAdapter = new CollegeAdapter(beanArrayList, this);
        rvColleges.setAdapter(collegeAdapter);
    }

    @Override
    public void showNewCollegeScreen() {
        startActivity(new Intent(HomePageActivity.this, AddCollegeActivity.class));
    }

    @Override
    public void showUpdateCollegeScreen(int collegeId) {
        startActivity(new Intent(HomePageActivity.this, EditCollegeActivity.class).putExtra("collegeid", collegeId));
    }

    @Override
    public void noSuchCollege() {
        showToastLong(getString(R.string.msg_no_such_college));
    }

    @Override
    public void showDialogOption(final int collegeId) {
        final CharSequence[] items = {getString(R.string.msg_delete_college), getString(R.string.msg_update_college), getString(R.string.msg_cancel)};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(getString(R.string.msg_delete_college))) {
                    presenter.onDeleteCollege(collegeId);
                } else if (items[item].equals(getString(R.string.msg_update_college))) {
                    presenter.onUpdateCollege(collegeId);
                } else if (items[item].equals(getString(R.string.msg_cancel))) {
                    dialog.dismiss();
                }
               // presenter.onItemSelected(item, collegeId, dialog);
            }
        });
        builder.show();
    }

    @Override
    public void dismissDialog() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        presenter.onFabClicked();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                collegeAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                collegeAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        presenter.getCollegeList();
    }

    /**
     * College id instead of position
     * */
    @Override
    public void onItemClick(View view, int collegeId) {
        //presenter.onRowClick(beanArrayList.get(position).getId());
        presenter.onRowClick(collegeId);
    }

    @Override
    public void notifyItemRemovedFromAdapter(int position) {
        collegeAdapter.removeCollegeItem(position);
       // collegeAdapter.notifyItemRangeChanged(position, getItemCount());
    }


    private int getItemCount() {
       return beanArrayList.size();
    }
}
