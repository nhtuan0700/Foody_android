package com.example.foody.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foody.MainActivity.MainActivity;
import com.example.foody.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.UserProfileChangeRequest;

public class activity_signup extends AppCompatActivity {
    ImageView btnBack;
    Button btnSignUp;
    EditText edName, edEmail, edPw1, edPw2;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_signup);
        addControls();
    }

    public void addControls() {
        edName = findViewById(R.id.ed_name);
        edEmail = findViewById(R.id.ed_email);
        edPw1 = findViewById(R.id.ed_pw1);
        edPw2 = findViewById(R.id.ed_pw2);
        btnSignUp = findViewById(R.id.btn_signup);
        btnBack = findViewById(R.id.btn_back);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void signUp() {
        final String name = edName.getText().toString().trim();
        String email = edEmail.getText().toString();
        String pw1 = edPw1.getText().toString();
        String pw2 = edPw2.getText().toString();

        if (name.isEmpty() || email.isEmpty() || pw1.isEmpty() || pw2.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ các thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!pw1.equals(pw2)) {
            edPw2.setError("Mật khẩu không trùng khớp!");
            edPw2.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, pw1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(activity_signup.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build();

                            mAuth.getCurrentUser().updateProfile(profileUpdates);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            try
                            {
                                throw task.getException();
                            }
                            // if user enters wrong password.
                            catch (FirebaseAuthWeakPasswordException e){
                                edPw1.setError("Mật khẩu phải 6 kí tự trở lên!");
                                edPw1.requestFocus();
                            }
                            catch (FirebaseAuthInvalidCredentialsException malformedEmail)
                            {
                                edEmail.setError("Email không hợp lệ!");
                                edEmail.requestFocus();
                            }
                            catch (FirebaseAuthUserCollisionException existEmail)
                            {
                                edEmail.setError("Email đã tồn tại!");
                                edEmail.requestFocus();
                            }
                            catch (Exception e)
                            {
                                Toast.makeText(activity_signup.this, "Đăng ký không thành công!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}