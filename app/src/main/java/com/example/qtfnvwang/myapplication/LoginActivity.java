package com.example.qtfnvwang.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    EditText uname;
    EditText upwd;
    CheckBox rememberpwd;
    CheckBox showpwd;
    Button login;
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setActionBar();
        init();
    }
    public void init(){
        uname=(EditText)findViewById(R.id.uname);
        upwd=(EditText)findViewById(R.id.upwd);
        rememberpwd=(CheckBox) findViewById(R.id.rememberpwd);
        showpwd=(CheckBox) findViewById(R.id.showpwd);
        login=(Button) findViewById(R.id.btn_login);
        register=(Button) findViewById(R.id.register);
        SharedPreferences loginStatus = getSharedPreferences("loginStatus",MODE_PRIVATE);
        String temp_uname = loginStatus.getString("uname","");
        String temp_upwd = loginStatus.getString("upwd","");
        int temp_status=loginStatus.getInt("status",0);
        uname.setText(temp_uname);
        upwd.setText(temp_upwd);
        if(temp_status == 1){
            rememberpwd.setChecked(true);
        }else{
            rememberpwd.setChecked(false);
        }
        showpwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    upwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else
                {
                    upwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_uname = uname.getText().toString();
                String str_upwd = upwd.getText().toString();
                if(str_uname.length()==0)
                {
                    //三个参数，自己的类名，字符串，时间长短
                    Toast.makeText(LoginActivity.this,"用户名不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                if(str_upwd.length()==0)
                {
                    //三个参数，自己的类名，字符串，时间长短
                    Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                 if(str_upwd.length()<6)
                {
                    //三个参数，自己的类名，字符串，时间长短
                    Toast.makeText(LoginActivity.this,"密码长度不能低于6位",Toast.LENGTH_LONG).show();
                    return;
                }
                //硬编码
                if(str_uname.equals("admin")&&str_upwd.equals("123456")){

                    SharedPreferences loginStatus = getSharedPreferences("loginStatus",MODE_PRIVATE);
                    SharedPreferences.Editor editor = loginStatus.edit();
                    if (rememberpwd.isChecked()) {
                        editor.putString("uname",str_uname);
                        editor.putString("upwd",str_upwd);
                        editor.putInt("status",1);
                    }else
                    {
                        editor.clear();
                    }
                    editor.commit();
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this,"登录失败，请重试",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void setActionBar() {
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("航班查询系统");
        actionBar.setSubtitle("login");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.mipmap.background));
        actionBar.setIcon(getResources().getDrawable(R.mipmap.login));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu,menu);
        if (menu!=null){
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")){
                try{
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible",Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu,true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        getMenuInflater().inflate(R.menu.login_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //后续完成
        if(item.getItemId()==R.id.menu_exit){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("是否真的退出系统");
            builder.setCancelable(true);
            builder.setIcon(R.mipmap.logo);
            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            });
            builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        }
        if(item.getItemId()==R.id.menu_register){

        }
        if(item.getItemId()==android.R.id.home){

        }
        return super.onOptionsItemSelected(item);
    }

}

