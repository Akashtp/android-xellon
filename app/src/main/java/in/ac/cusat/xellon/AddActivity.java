package in.ac.cusat.xellon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Random;

public class AddActivity extends AppCompatActivity {
    public EditText title;
    public EditText author;
    public EditText price;
    public EditText cat;
    public EditText des;
    public EditText cno;
    public Button button;
    public Button image;
    public Uri selectimage;
    private DatabaseReference mDatabase;
    private StorageReference storageReference;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        title = findViewById(R.id.name);
        author = findViewById(R.id.author);
        price = findViewById(R.id.price);
        cat = findViewById(R.id.category);
        des = findViewById(R.id.des);
        cno = findViewById(R.id.cno);
        image = findViewById(R.id.butt1);
        button = findViewById(R.id.butt);
        dialog = new ProgressDialog(AddActivity.this);
        dialog.setMessage("Loading");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("posts");
        storageReference = FirebaseStorage.getInstance().getReference();
        final HashMap<String, String> myData = new HashMap<>();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Random rand = new Random();
                int n = rand.nextInt(99999) + 1;
                String s = String.valueOf(n);
                StorageReference filepath = storageReference.child("photos").child(s);
                filepath.putFile(selectimage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri image_url = taskSnapshot.getDownloadUrl();
                        addDataToFirebase(image_url.toString());

                    }
                });
            }
        });


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent r = new Intent(Intent.ACTION_PICK);
                r.setType("image/*");
                startActivityForResult(r, 2);

                dialog.show();

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK) ;
        {

            selectimage = data.getData();



        }
    }


    private void addDataToFirebase(String img) {


        String tit = title.getText().toString().trim();
        String desc = des.getText().toString().trim();
        String auth = author.getText().toString().trim();
        String cont = cno.getText().toString().trim();
        String cate = cat.getText().toString().trim();
        String pri = price.getText().toString().trim();

        HashMap<String, String> dataPush = new HashMap<String, String>();
        dataPush.put("title", tit);
        dataPush.put("price", pri);
        dataPush.put("img", img);
        dataPush.put("deccription",desc);
        dataPush.put("author",auth);
        dataPush.put("contact no",cont);
        dataPush.put("category",cate);


        if (tit.length() > 0 && pri.length() > 0) {
            mDatabase.push().setValue(dataPush).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    dialog.hide();

                    if (task.isSuccessful()) {
                        Toast.makeText(AddActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i =new Intent(AddActivity.this,OptionActivity.class);
        startActivity(i);
    }
}
