package com.multiselectedproject;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

@SuppressWarnings("All")
public class SubChildAdapter extends RecyclerView.Adapter<SubChildAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<SubChildOption> listChild;
    private Context ctx;

    private ArrayList<Integer> listCHildInt;
    private int isType;

    interface onChildClick {
        public void onChildItemClick(int pos, int type);
    }

    int mainPos;

    public SubChildAdapter(Context ctx, int isType, ArrayList<SubChildOption> listChild, ArrayList<Integer> listCHildInt) {

        inflater = LayoutInflater.from(ctx);
        this.listChild = listChild;
        this.ctx = ctx;

        this.mainPos = mainPos;
        this.isType = isType;

        this.listCHildInt = listCHildInt;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.child_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    public SubChildOption getItem(int pos) {
        return listChild.get(pos);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.checkBox.setText(listChild.get(position).getName());

        if (listChild.get(position).isSelected()) {
            holder.checkBox.setTextColor(Color.parseColor("#FF0000"));
        } else {
            holder.checkBox.setTextColor(Color.parseColor("#000000"));
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(position, isType);
            }
        });
    }

    public void setData(int pos, int type) {
        if (type != 0) {
            if (listChild.get(pos).isSelected()) {
                listChild.get(pos).setSelected(false);
                if (listCHildInt.size() > 0) {
                    for (int i = 0; i < listCHildInt.size(); i++) {
                        if (listCHildInt.get(i) == pos) {
                            listCHildInt.remove(i);
                        }
                    }
                }
            } else {

                if (listCHildInt.size() > 0) {
                    for (int i = 0; i < listCHildInt.size(); i++) {
                        if (listCHildInt.get(i) == pos) {
                            listCHildInt.remove(i);
                        }
                    }
                }

                listCHildInt.add(pos);
                listChild.get(pos).setSelected(true);

                if (listCHildInt.size() > type) {
                    listChild.get(listCHildInt.get(0)).setSelected(false);
                    listCHildInt.remove(0);
                }
            }
        } else {
            if (listChild.get(pos).isSelected()) {
                listChild.get(pos).setSelected(false);
            } else {
                listChild.get(pos).setSelected(true);
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listChild.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        protected TextView checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);

            checkBox = (TextView) itemView.findViewById(R.id.cb);
        }

    }
}



