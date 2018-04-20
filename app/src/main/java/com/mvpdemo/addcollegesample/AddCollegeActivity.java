package com.mvpdemo.addcollegesample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mvpdemo.R;
import com.mvpdemo.base.BaseActivity;
import com.mvpdemo.datamodel.College;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class AddCollegeActivity extends BaseActivity implements AddCollegeView {


    @BindView(R.id.tv_heading)
    TextView tvHeading;
    @BindView(R.id.tiet_college_name)
    TextInputEditText tietCollegeName;
    @BindView(R.id.tiet_college_address)
    TextInputEditText tietCollegeAddress;
    @BindView(R.id.tiet_college_description)
    TextInputEditText tietCollegeDescription;
    @BindView(R.id.tiet_college_course)
    TextInputEditText tietCollegeCourse;
    @BindView(R.id.tiet_college_phone)
    TextInputEditText tietCollegePhone;
    @BindView(R.id.tiet_college_email)
    TextInputEditText tietCollegeEmail;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.iv_college)
    ImageView ivCollege;
    private String imageUri;
    //private String collegeName, collegeAddress, collegeDescription, collegeCourses, collegePhone, collegeEmail, collegeImage;
    private AddCollegePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AddCollegePresenter(this);
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_add_college;
    }

    @OnClick({R.id.btn_submit, R.id.iv_college})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
//                String cName = tietCollegeName.getText().toString();
//                String cAddress = tietCollegeAddress.getText().toString();
//                String cDescription = tietCollegeDescription.getText().toString();
//                String cCourses = tietCollegeCourse.getText().toString();
//                String cPhone = tietCollegePhone.getText().toString();
//                String cEmail = tietCollegeEmail.getText().toString();
//                String cImage = presenter.getUri();
                presenter.onSubmit();
                break;
            case R.id.iv_college:
                presenter.onImageViewClicked();
        }
    }

    @Override
    public College getCollegeDetails() {
        College college = new College();
        college.setName(tietCollegeName.getText().toString());
        college.setAddress(tietCollegeAddress.getText().toString());
        college.setDescription(tietCollegeDescription.getText().toString());
        college.setCoursesAvailable(tietCollegeCourse.getText().toString());
        college.setPhoneNumber(tietCollegePhone.getText().toString());
        college.setEmail(tietCollegeEmail.getText().toString());
        college.setImages(imageUri);
        return college;
    }

    @Override
    public void showCollegeDetails(College college) {

    }

    @Override
    public void errorName() {
        tietCollegeName.setError(getString(R.string.msg_name_empty));
    }

    @Override
    public void errorAddress() {
        tietCollegeAddress.setError(getString(R.string.msg_address_empty));
    }

    @Override
    public void errorDescription() {
        tietCollegeDescription.setError(getString(R.string.msg_description_empty));
    }

    @Override
    public void errorCourses() {
        tietCollegeCourse.setError(getString(R.string.msg_course_empty));
    }

    @Override
    public void errorPhone() {
        tietCollegePhone.setError(getString(R.string.msg_phone_empty));
    }

    @Override
    public void errorEmail() {
        tietCollegeEmail.setError(getString(R.string.msg_mail_empty_invalid));
    }

    @Override
    public void pickImage() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(AddCollegeActivity.this);
    }

    @Override
    public void finishCurrentActivity() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                imageUri = resultUri.toString();
                Glide.with(this).load(imageUri).into(ivCollege);
                File file = new File(resultUri.getPath());
            }
            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}

