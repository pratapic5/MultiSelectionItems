package com.multiselectedproject;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

@SuppressWarnings("All")
public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<ParentModel> imageModelArrayList;
    private Context ctx;

    public ParentAdapter(Context ctx, ArrayList<ParentModel> imageModelArrayList) {
        inflater = LayoutInflater.from(ctx);
        this.imageModelArrayList = imageModelArrayList;
        this.ctx = ctx;
    }

    @Override
    public ParentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.parent_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    public void setPosition(int pos) {
        notifyItemChanged(pos);
    }

    @Override
    public void onBindViewHolder(final ParentAdapter.MyViewHolder holder, final int position) {

        holder.tvAnimal.setText(imageModelArrayList.get(position).getName() + " - " + imageModelArrayList.get(position).getIsType());

        final ChildAdapter customAdapter = new ChildAdapter(ctx, imageModelArrayList.get(position).getIsType(),
                imageModelArrayList.get(position).getOptionArrayList(), position, imageModelArrayList);
        holder.rvItem.setLayoutManager(new LinearLayoutManager(ctx));
        holder.rvItem.setAdapter(customAdapter);
    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvAnimal;
        private RecyclerView rvItem;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvAnimal = (TextView) itemView.findViewById(R.id.name);
            rvItem = (RecyclerView) itemView.findViewById(R.id.rvItem);
        }

    }
}


