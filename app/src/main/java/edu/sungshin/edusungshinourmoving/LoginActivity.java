package edu.sungshin.edusungshinourmoving;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.sungshin.edusungshinourmoving.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editTextEmail; //이메일 입력
    private EditText editTextPassword; //비밀번호 입력

    private long backKeyPressedTime = 0;
    private Toast toast;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
            toast.cancel();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) findViewById(R.id.edit_email);
        editTextPassword = (EditText) findViewById(R.id.edit_pw);

        Button signinbutton = (Button) findViewById(R.id.join); //회원가입 버튼
        Button loginbutton = (Button) findViewById(R.id.login); //로그인 버튼

        signinbutton.setOnClickListener(new View.OnClickListener() { //회원가입 버튼
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() { //로그인버튼
            @Override
            public void onClick(View view) {
                login(editTextEmail.getText().toString(), editTextPassword.getText().toString());

            }
        });


    }

    private void login(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete( @NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(LoginActivity.this,MainActivity3.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(LoginActivity.this, "로그인 실패!", Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }



}