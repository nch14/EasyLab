package com.chenh.easylab.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chenh.easylab.R;
import com.chenh.easylab.util.Client;
import com.chenh.easylab.util.CurrentUser;
import com.chenh.easylab.util.ServerBackData;
import com.chenh.easylab.vo.UserVO;

import org.json.JSONException;
import org.json.JSONObject;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String message = msg.obj.toString();
                if (message.equals("student")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else if((message.equals("manager"))){
                    Intent intent = new Intent(LoginActivity.this, ManagerActivity.class);
                    startActivity(intent);
                    finish();
                } else
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
//                db.save(new User(username, password));
                showProgress(false);
            }
        };
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        Button signUpButton=(Button)findViewById(R.id.sign_up_button);
        assert signUpButton != null;
        signUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                //    设置Title的图标
                //builder.setIcon(R.drawable.ic_launcher);
                //    设置Title的内容
                builder.setTitle("注册");
                //    设置Content来显示一个信息

                View vv= LayoutInflater.from(LoginActivity.this).inflate(R.layout.sign_up, null);

                final EditText editTextUsername= (EditText) vv.findViewById(R.id.username_input_signup);
                final EditText editTextPassword= (EditText) vv.findViewById(R.id.password_input_signup);
                final EditText editTextSID= (EditText) vv.findViewById(R.id.sid_input_signup);
                final EditText editTextName= (EditText) vv.findViewById(R.id.name_input_signup);

                /*TextView sureButton=(TextView)vv.findViewById(R.id.sure_button_signup) ;
                sureButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Check for a valid password, if the user entered one.
                        final String username= editTextUsername.getText().toString();
                        final String password=editTextPassword.getText().toString();
                        final String sid= editTextSID.getText().toString();
                        final String name=editTextName.getText().toString();
                        boolean a=TextUtils.isEmpty(username);
                        boolean b=isValidUsername(username);
                        if (TextUtils.isEmpty(username) || (!isValidUsername(username))) {
                            editTextUsername.setError("用户名只能由密码和数字组成");
                            editTextUsername.requestFocus();
                            return;
                        }
                        if (TextUtils.isEmpty(password)|| (!isPasswordValid(password))) {
                            editTextPassword.setError("密码不能少于8位");
                            editTextPassword.requestFocus();
                            return;
                        }
                        if (TextUtils.isEmpty(sid) || (!isValidSID(sid))) {
                            editTextSID.setError("学号不能造假");
                            editTextSID.requestFocus();
                            return;
                        }
                        if (TextUtils.isEmpty(name) || (!isValidName(name))) {
                            editTextName.setError("必须是汉字");
                            editTextName.requestFocus();
                            return;
                        }
                        //-------------------------执行注册操作
                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                JSONObject map=new JSONObject();
                                try {
                                    map.put("op","1");
                                    map.put("username",username);
                                    map.put("password",password);
                                    map.put("sid",sid);
                                    map.put("name",name);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                ServerBackData serverBackData= Client.register(map);

                                Message m=null;
                                if (serverBackData.isResultState()){
                                    CurrentUser.newUser(new UserVO(username,password,sid,name,0));
                                    m=handler.obtainMessage(0,"ok");
                                }else{
                                    m=handler.obtainMessage(0,"fail");
                                    Toast.makeText(LoginActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                                }
                                handler.sendMessage(m);
                            }
                        }).start();
                    }
                });

                TextView cancelButton=(TextView)vv.findViewById(R.id.cancel_button_signup) ;
                cancelButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });*/

                builder.setView(vv);
                //builder.setMessage("确定删除吗？");
                //    设置一个PositiveButton
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        // Check for a valid password, if the user entered one.

                        final String username= editTextUsername.getText().toString();
                        final String password=editTextPassword.getText().toString();
                        final String sid= editTextSID.getText().toString();
                        final String name=editTextName.getText().toString();
                        boolean a=TextUtils.isEmpty(username);
                        boolean b=isValidUsername(username);
                        if (TextUtils.isEmpty(username) || (!isValidUsername(username))) {
                            editTextUsername.setError("用户名只能由密码和数字组成");
                            editTextUsername.requestFocus();
                            return;
                        }
                        if (TextUtils.isEmpty(password)|| (!isPasswordValid(password))) {
                            editTextPassword.setError("密码不能少于8位");
                            editTextPassword.requestFocus();
                            return;
                        }
                        if (TextUtils.isEmpty(sid) || (!isValidSID(sid))) {
                            editTextSID.setError("学号不能造假");
                            editTextSID.requestFocus();
                            return;
                        }
                        if (TextUtils.isEmpty(name) || (!isValidName(name))) {
                            editTextName.setError("必须是汉字");
                            editTextName.requestFocus();
                            return;
                        }
                        //-------------------------执行注册操作
                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                JSONObject map=new JSONObject();
                                try {
                                    map.put("op","1");
                                    map.put("username",username);
                                    map.put("password",password);
                                    map.put("sid",sid);
                                    map.put("name",name);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                ServerBackData serverBackData= Client.register(map);

                                Message m=null;
                                if (serverBackData.isResultState()){
                                    CurrentUser.newUser(new UserVO(username,password,sid,name,0));
                                    m=handler.obtainMessage(0,"ok");
                                }else{
                                    m=handler.obtainMessage(0,"fail");
                                }
                                handler.sendMessage(m);
                            }
                        }).start();
                    }
                });
                //    设置一个NegativeButton
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(LoginActivity.this, "注册已取消", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();

            }
        });


    }

    private boolean isValidUsername(String username){
        char[] chars=username.toCharArray();
        boolean is=true;
        for (int i=0;i<chars.length;i++){
            if (((chars[i]>='0'&&chars[i]<='9'))||((chars[i]>='A'&&chars[i]<='Z'))||((chars[i]>='a'&&chars[i]<='z')))
               System.out.println("对的");
            else{
                break;
            }
        }
        System.out.println(true);
        return true;
    }

    private boolean isValidName(String name){return true;}
    private boolean isValidSID(String sid){return true;}

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isIDValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

           new Thread(new Runnable() {
               @Override
               public void run() {
                   JSONObject map=new JSONObject();
                   try {
                       map.put("op","2");
                       map.put("username",email);
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
                   ServerBackData serverBackData= Client.register(map);

                   Message m=null;
                   if (serverBackData.isResultState()){
                       UserVO userVO= (UserVO) serverBackData.getObject();
                       if (userVO.password.equals(password)){
                           CurrentUser.newUser(userVO);
                           if (userVO.identify.equals("学生"))
                               m=handler.obtainMessage(0,"manager");
                               //m=handler.obtainMessage(0,"student");
                           if (userVO.identify.equals("管理员"))
                               m=handler.obtainMessage(0,"manager");
                       }else {
                           m=handler.obtainMessage(0,"密码错误");
                           //Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
                       }

                   }else{
                       m=handler.obtainMessage(0,"该用户不存在");
                       //Toast.makeText(LoginActivity.this, "该用户不存在", Toast.LENGTH_SHORT).show();
                   }
                   handler.sendMessage(m);
               }
           }).start();
        }
    }

    private boolean isIDValid(String email) {
        //TODO: Replace this with your own logic

        return true;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 8;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}

