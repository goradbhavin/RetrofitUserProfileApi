package com.gb.myuserprofileapi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gb.myuserprofileapi.R;
import com.gb.myuserprofileapi.controller.Utils;
import com.gb.myuserprofileapi.fragment.LoginFragment;
import com.gb.myuserprofileapi.fragment.SignUpFragment;
import com.gb.myuserprofileapi.store.SharedPrefManager;

/**
 * Created by bhavin on 8/13/2018.
 */

public class LoginActivity extends AppCompatActivity implements
        LoginFragment.LoginFragmentListener,
        SignUpFragment.SignUpFragmentListener{


    private LoginFragment mLoginFragment;
    private SignUpFragment mSignUpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        onLoadLoginFragment();
    }

    private void onLoadLoginFragment() {

        if (mLoginFragment == null){
            mLoginFragment = new LoginFragment(this);
        }

        onFragmentLoad(mLoginFragment);
    }

    private void onLoadSignUpFragment(){

        if (mSignUpFragment == null){
            mSignUpFragment = new SignUpFragment(this);
        }
        onFragmentLoad(mSignUpFragment);
    }

    private void onFragmentLoad(Fragment fragment){

        String TAG = fragment.getClass().getSimpleName();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.container_activity_login, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (mLoginFragment.isVisible()){
            finish();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            onStartMainActivity();
        }
    }

    @Override
    public void onLoginSuccess(String message) {
        Utils.showToast(this,message);
        onStartMainActivity();
    }

    private void onStartMainActivity() {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFailed(String message) {
        Utils.showToast(this,message);
    }
    @Override
    public void onCreateNewAccountButtonClicked() {
        onLoadSignUpFragment();
    }
    @Override
    public void onAccountCreatedSuccess(String message) {
        onLoadLoginFragment();
    }
    @Override
    public void onAccountCreatedFailed(String message) {
        Utils.showToast(this,message);
    }
    @Override
    public void onSignUpResponseError(String message) {
        Utils.showToast(this,message);
    }
}
