package com.multiselectedproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

@SuppressWarnings("All")
public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<ChildOption> listChild;
    private Context ctx;

    ArrayList<ParentModel> listParent;
    private int isType;

    interface onChildClick {
        public void onChildItemClick(int pos, int type);
    }

    int mainPos;

    public ChildAdapter(Context ctx, int isType, ArrayList<ChildOption> listChild, int mainPos, ArrayList<ParentModel> listParent) {

        inflater = LayoutInflater.from(ctx);
        this.listChild = listChild;
        this.ctx = ctx;
        this.mainPos = mainPos;
        this.isType = isType;
        this.listParent = listParent;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.child_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    public ChildOption getItem(int pos) {
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
                boolean isSel = listChild.get(position).isSelected();

                if (listChild.get(position).getSubOptions().size() > 0) {
                    Intent intent = new Intent(ctx, SubChildSelectorActivity.class);
                    intent.putExtra("mainPos", mainPos);
                    intent.putExtra("subPos", position);
                    intent.putExtra("isType", isType );
                    intent.putExtra("isTypeChild", listChild.get(position).getIsType());
                    intent.putExtra("childIntList", listChild.get(position).getListSubInt());
                    intent.putExtra("subItemList", listChild.get(position).getSubOptions());
                    intent.putExtra("intParentList", listParent.get(mainPos).getListInt());
                    ((MainActivity) ctx).startActivityForResult(intent, 101);
                } else {
                    setData(position, isType);
                }
            }
        });
    }


    public void setData(int pos, int type) {
        if (type == 0) {
            if (listChild.get(pos).isSelected()) {
                listChild.get(pos).setSelected(false);
            } else {
                listChild.get(pos).setSelected(true);
            }
        } else {
            if (listChild.get(pos).isSelected()) {
                listChild.get(pos).setSelected(false);
                if (listParent.get(mainPos).getListInt().size() > 0) {
                    for (int i = 0; i < listParent.get(mainPos).getListInt().size(); i++) {
                        if (listParent.get(mainPos).getListInt().get(i) == pos) {
                            ArrayList<Integer> listRemove = listParent.get(mainPos).getListInt();
                            listRemove.remove(i);
                            listParent.get(mainPos).setListInt(listRemove);
                        }
                    }
                }
            } else {

                if (listParent.get(mainPos).getListInt().size() > 0) {
                    for (int i = 0; i < listParent.get(mainPos).getListInt().size(); i++) {
                        if (listParent.get(mainPos).getListInt().get(i) == pos) {
                            ArrayList<Integer> listRemove = listParent.get(mainPos).getListInt();
                            listRemove.remove(i);
                            listParent.get(mainPos).setListInt(listRemove);
                        }
                    }
                }

                listParent.get(mainPos).getListInt().add(pos);
                listChild.get(pos).setSelected(true);

                if (listParent.get(mainPos).getListInt().size() > type) {
                    listChild.get(listParent.get(mainPos).getListInt().get(0)).setSelected(false);

                    ArrayList<Integer> listRemove = listParent.get(mainPos).getListInt();
                    listRemove.remove(0);
                    listParent.get(mainPos).setListInt(listRemove);
                }
            }

            for (int i = 0; i < listChild.size(); i++) {

                boolean isSel = false;
                for (int j = 0; j < listParent.get(mainPos).getListInt().size(); j++) {
                    if (listParent.get(mainPos).getListInt().get(j) == i) {
                        isSel = true;
                    }
                }

                listParent.get(mainPos).getOptionArrayList().get(i).setSelected(isSel);

                if (!isSel) {
                    for (int j = 0; j < listChild.get(i).getSubOptions().size(); j++) {
                        listChild.get(i).getSubOptions().get(j).setSelected(false);
                    }
                }
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



