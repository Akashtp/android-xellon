package in.ac.cusat.xellon;

import android.content.Intent;
import android.graphics.drawable.RippleDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class SignupActivity extends AppCompatActivity {
    public EditText email;
    public EditText password;
    public EditText rpassword;
    public Button signup;
    private FirebaseAuth mAuth;
    public String em;
    public String pass;
    public String rpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth =FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        rpassword = findViewById(R.id.rpassword);
        signup = findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.length()>0 && password.length()>0 && rpassword.length()>0)
                {
                    em = email.getText().toString().trim();
                    pass = password.getText().toString().trim();
                    rpass = rpassword.getText().toString().trim();
                    if(pass.equals(rpass) )
                    {
                        mAuth.createUserWithEmailAndPassword(em,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Toast.makeText(SignupActivity.this, "successfully signed in", Toast.LENGTH_SHORT).show();
                                    Intent i= new Intent(SignupActivity.this,OptionActivity.class);
                                    startActivity(i);

                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(SignupActivity.this, "Passwords dont match", Toast.LENGTH_SHORT).show();
                    }

                    }
                    else
                        {
                            Toast.makeText(SignupActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                        }





                }
            });
        }
    }

