package in.ac.cusat.xellon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    public Button button;
    public TextView signup;
    private FirebaseAuth mAuth;
    public EditText email;
    public EditText password;
    public String em;
    public String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                em = email.getText().toString().trim();
                pass = password.getText().toString().trim();
                mAuth.signInWithEmailAndPassword(em,pass).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(LoginActivity.this,OptionActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(i);
                finish();
            }
        });
    }


}
