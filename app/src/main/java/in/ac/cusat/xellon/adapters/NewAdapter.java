package in.ac.cusat.xellon.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.ac.cusat.xellon.R;
import in.ac.cusat.xellon.models.Items;

public class  NewAdapter extends RecyclerView.Adapter<NewAdapter.MyViewHolder>{

    private ArrayList<Items>itemslist;

    public NewAdapter(ArrayList<Items>itemslist)
    {
        this.itemslist  = itemslist;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(itemslist.get(position).getTitle());
        holder.price.setText(itemslist.get(position).getPrice());



        Picasso.get().load(itemslist.get(position).getImage()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return itemslist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView price;
        public TextView des;
        public ImageView image;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.bname);
            price = itemView.findViewById(R.id.rate);
            image = itemView.findViewById(R.id.rimage);

        }
    }
}