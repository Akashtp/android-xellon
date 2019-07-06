package in.ac.cusat.xellon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public ImageView img;
    public TextView tit;
    public TextView pri;
    public TextView sel;
    public TextView det;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        img = findViewById(R.id.image);
        img.setImageResource(getIntent().getIntExtra("image",0));
        tit = findViewById(R.id.name);
        tit.setText(getIntent().getStringExtra("title"));
        pri = findViewById(R.id.amount);
        pri.setText(getIntent().getStringExtra("prize"));

        det = findViewById(R.id.details);
        det.setText(getIntent().getStringExtra("details"));




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
