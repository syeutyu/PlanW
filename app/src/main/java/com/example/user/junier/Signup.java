package com.example.user.junier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import static com.example.user.junier.TabFragment2.Result_Code;
import static com.example.user.junier.TabFragment2.version;

public class Signup extends AppCompatActivity {
    private static LoginButton loginButton;
    private ImageButton back;
    private CallbackManager callbackManager;
    private  Context context= this;
    private EditText id,password,name;
    private Button signup;
    private Datebase helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_signup);

        helper = new Datebase(context,Datebase.Schema,null,version);
        back = (ImageButton) findViewById(R.id.back);
        id = (EditText)findViewById(R.id.input_email);
        password = (EditText)findViewById(R.id.input_password);
        name = (EditText)findViewById(R.id.input_name);
        signup = (Button)findViewById(R.id.signup);

        Log.d("signup",String.valueOf(signup));
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uid = id.getText().toString();
                String upassword = password.getText().toString();
                String uname = name.getText().toString();
                Log.d("화원가입","완료");

                helper.insertSign(uid,upassword,uname);

                Toast.makeText(getApplicationContext(),"회원가입 완료",Toast.LENGTH_SHORT).show();
                //Snackbar.make(view,"회원 가입 완료",Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(context,Signin.class);
                setResult(Result_Code,intent);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Signin.class);
                startActivity(intent);
                finish();
            }
        });


        callbackManager = CallbackManager.Factory.create();  //로그인 응답을 처리할 콜백 관리자
        loginButton = (LoginButton)findViewById(R.id.login_button); //페이스북 로그인 버튼
        //유저 정보, 친구정보, 이메일 정보등을 수집하기 위해서는 허가(퍼미션)를 받아야 합니다.
        loginButton.setReadPermissions("public_profile", "user_friends","email");
        //버튼에 바로 콜백을 등록하는 경우 LoginManager에 콜백을 등록하지 않아도됩니다.
        //반면에 커스텀으로 만든 버튼을 사용할 경우 아래보면 CustomloginButton OnClickListener안에 LoginManager를 이용해서
        //로그인 처리를 해주어야 합니다.
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) { //로그인 성공시 호출되는 메소드
                Log.e("토큰",loginResult.getAccessToken().getToken());
                Log.e("유저아이디",loginResult.getAccessToken().getUserId());
                Log.e("퍼미션 리스트",loginResult.getAccessToken().getPermissions()+"");

                //loginResult.getAccessToken() 정보를 가지고 유저 정보를 가져올수 있습니다.
                GraphRequest request =GraphRequest.newMeRequest(loginResult.getAccessToken() ,
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    Log.e("user profile",object.toString());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                request.executeAsync();
            }

            @Override
            public void onError(FacebookException error) { }

            @Override
            public void onCancel() { }
        });

//        CustomloginButton = (Button)findViewById(R.id.loginBtn);
//        CustomloginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //LoginManager - 요청된 읽기 또는 게시 권한으로 로그인 절차를 시작합니다.
//                LoginManager.getInstance().logInWithReadPermissions(Signup.this,
//                        Arrays.asList("public_profile", "user_friends"));
//                LoginManager.getInstance().registerCallback(callbackManager,
//                        new FacebookCallback<LoginResult>() {
//                            @Override
//                            public void onSuccess(LoginResult loginResult) {
//                                Log.e("onSuccess", "onSuccess");
//                            }
//
//                            @Override
//                            public void onCancel() {
//                                Log.e("onCancel", "onCancel");
//                            }
//
//                            @Override
//                            public void onError(FacebookException exception) {
//                                Log.e("onError", "onError " + exception.getLocalizedMessage());
//                            }
//                        });
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
