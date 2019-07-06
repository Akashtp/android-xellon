package in.ac.cusat.xellon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import in.ac.cusat.xellon.adapters.NewAdapter;
import in.ac.cusat.xellon.helper.RecyclerItemClickListener;
import in.ac.cusat.xellon.models.Items;

public class RecycActivity extends AppCompatActivity {
    private RecyclerView mRecyclerview;
    private NewAdapter newadapter;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference databaseReference;
    private ArrayList<Items>itemsArrayList = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyc);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("posts");
        mRecyclerview = findViewById(R.id.my_recycler_view);
        mRecyclerview.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(linearLayoutManager);
        newadapter = new NewAdapter(itemsArrayList);
        mRecyclerview.setAdapter(newadapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) ;
                itemsArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Items items = snapshot.getValue(Items.class);
                    itemsArrayList.add(items);
                }

                newadapter.notifyDataSetChanged();
                String a = String.valueOf(itemsArrayList.size());
                Toast.makeText(RecycActivity.this, a, Toast.LENGTH_SHORT).show();

                }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

        mRecyclerview.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String a=itemsArrayList.get(position).getTitle();
                        String b=itemsArrayList.get(position).getPrice();
                        String d=itemsArrayList.get(position).getDec();
                        //String f=itemsArrayList.get(position).getSeller();



                        Intent i=new Intent(RecycActivity.this,DetailActivity.class);
                        i.putExtra("user",a);
                        i.putExtra("name",d);
                        i.putExtra("price",b);
                       // i.putExtra("detail",f);
                        startActivity(i);


                    }
                })
        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(RecycActivity.this,OptionActivity.class);
        startActivity(i);
    }
}


