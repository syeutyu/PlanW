package com.example.user.junier;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.user.junier.TabFragment2.version;

public class Signin extends AppCompatActivity {
    public EditText login;
    public EditText password;
    public Button loginbtn;
    Datebase helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        helper = new Datebase(getApplicationContext(), Datebase.Schema, null, version);

        setting();
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = login.getText().toString();
                String pw = password.getText().toString();
                Log.d(id + "," + pw, " 을 이용해서 정보 검색");
                JSONObject user;
                String name = null;
                user = helper.getSign(id, pw);
                Log.d("찾은데이터",String.valueOf(user));

                if (user.length() == 0) {
                    Snackbar.make(view, "입력하신 정보를 찾을 수가 없습니다", Snackbar.LENGTH_SHORT).show();
                } else {

                    try {
                        name = (String) user.get("name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Signin.this, "어서오세요 " + name + "님 환영합니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainShow.class);
                    intent.putExtra("name",name);
                    startActivity(intent);
                    }
            }
        });
    }

    public void setting() {
        login = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);
        loginbtn = (Button) findViewById(R.id.login);
    }

    public void SignUp(View v) {
        Log.d("회원가입창", "이동");
        Intent intent = new Intent(getApplicationContext(), Signup.class);
        startActivity(intent);
    }

}
