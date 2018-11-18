package com.hm.groupchat.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hm.groupchat.R;

public class SignUpActivity extends BaseActivity {

    private static final String TAG = "SignUpActivity";

    EditText emailEditText;
    EditText passwordEditText;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        emailEditText = (EditText) findViewById(R.id.email_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);

        Button signUpButton = (Button) findViewById(R.id.sign_up_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if(email.equals("") || password.equals("")) {

                    Toast.makeText(SignUpActivity.this, "Please enter your email and password.",
                            Toast.LENGTH_LONG).show();
                }

                else {

                    signUp(email, password);
                }
            }
        });

        mAuth = FirebaseAuth.getInstance();
    }

    private void signUp(String email, String password) {

        displayLoadingIndicator("Signing Up...");

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        hideLoadingIndicator();

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signUp:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Log.d(TAG, "User = "+user.toString());

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signUp:failure", task.getException());

                            goToChatScreen();
                        }

                        // ...
                    }
                });
    }

    private void goToChatScreen() {

        Intent intent = new Intent(SignUpActivity.this, ChatActivity.class);
        startActivity(intent);
    }
}
