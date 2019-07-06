package in.ac.cusat.xellon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionActivity extends AppCompatActivity {
    public Button but1;
    public Button but2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        but1 = findViewById(R.id.add);
        but2 = findViewById(R.id.view);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OptionActivity.this,AddActivity.class);
                startActivity(i);
                finish();
            }
        });
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(OptionActivity.this,RecycActivity.class);
                startActivity(j);
                finish();
            }
        });

    }


}
