package rome.je;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    private Button mSignup;
    private EditText mEmail, mPassword;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mSignup = (Button) findViewById(R.id.regBtn);
        mEmail = (EditText) findViewById(R.id.mEmail);
        mPassword= (EditText) findViewById(R.id.mPassword);
        final Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
        mAuth = FirebaseAuth.getInstance();

//CHECKING of Input
//        mEmail.addTextChangedListener(loginTextWatcher);
//        mPassword.addTextChangedListener(loginTextWatcher);


        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult>task){
                        if(!task.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }else{
                            String user_id = mAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Custormers").child(user_id);
                            current_user_db.setValue(true);
                            Toast.makeText(SignupActivity.this, "Registered!", Toast.LENGTH_SHORT).show();

                            startActivity(intent);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        }
                    }
                });
            }
        });

    }

//    private TextWatcher loginTextWatcher = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            String usernameInput = mEmail.getText().toString().trim();
//            String passwordInput = mPassword.getText().toString().trim();
//
//            mSignup.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty());
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//
//        }
//    };



}






//    public void previous(View view){
//        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
//
//