package com.multiselectedproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

@SuppressWarnings("All")
public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMain;
    Activity mActivity;
    Context mContext;
    ArrayList<ParentModel> listParent = new ArrayList<>();
    ParentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;
        mContext = this;

        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setVisibility(View.GONE);


        init();
    }

    private void init() {
        rvMain = (RecyclerView) findViewById(R.id.recyclerViewMain);
        rvMain.setLayoutManager(new LinearLayoutManager(mActivity));

        listParent = getModel();
        Log.d("AppName", "List Parent :" + listParent.toString());
        adapter = new ParentAdapter(mContext, listParent);
        rvMain.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK && requestCode == 101) {
            if (intent != null) {
                ArrayList<SubChildOption> listCHildOptions = (ArrayList<SubChildOption>) intent.getSerializableExtra("subItemList");
                ArrayList<Integer> listParentInt = (ArrayList<Integer>) intent.getSerializableExtra("intList");
                ArrayList<Integer> listCHildInt = (ArrayList<Integer>) intent.getSerializableExtra("childIntList");

                int mainPos = intent.getIntExtra("mainPos", 0);
                int childPos = intent.getIntExtra("subPos", 0);
                int isType = intent.getIntExtra("isType", 0);

                if (isType == 0) {
                    boolean isAdded = false;
                    for (int i = 0; i < listCHildOptions.size(); i++) {
                        if (listCHildOptions.get(i).isSelected()) {
                            isAdded = true;
                        }
                    }

                    listParent.get(mainPos).getOptionArrayList().get(childPos).setSelected(isAdded);

                    listParent.get(mainPos).getOptionArrayList().get(childPos).setSubOptions(listCHildOptions);
                    adapter.notifyItemChanged(mainPos);
                } else {

                    boolean isAdded = false;
                    for (int i = 0; i < listCHildOptions.size(); i++) {
                        if (listCHildOptions.get(i).isSelected()) {
                            isAdded = true;
                        }
                    }

                    if (isAdded) {
                        if (listParentInt.size() > 0) {
                            for (int i = 0; i < listParentInt.size(); i++) {
                                if (listParentInt.get(i) == childPos) {
                                    ArrayList<Integer> listRemove = listParentInt;
                                    listRemove.remove(i);
                                    listParent.get(mainPos).setListInt(listRemove);
                                }
                            }
                        }

                        listParentInt.add(childPos);
                        listParent.get(mainPos).getOptionArrayList().get(childPos).setSelected(true);

                        if (listParentInt.size() > isType) {
                            listParent.get(mainPos).getOptionArrayList().get(0).setSelected(false);
                            listParentInt.remove(0);
                        }
                    }

                    updateResult(mainPos, childPos, listParentInt, listCHildOptions, listCHildInt);
                }
            }
        }
    }

    public void updateResult(int mainPos, int childPos, ArrayList<Integer> listInt, ArrayList<SubChildOption> listCHildOptions, ArrayList<Integer> listCHildInt) {

        Toast.makeText(mActivity, "" + listInt.toString(), Toast.LENGTH_SHORT).show();

        listParent.get(mainPos).getOptionArrayList().get(childPos).setListSubInt(listCHildInt);
        listParent.get(mainPos).getOptionArrayList().get(childPos).setSubOptions(listCHildOptions);
        listParent.get(mainPos).setListInt(listInt);

        Toast.makeText(mActivity, "" + listParent.get(mainPos).getListInt().toString(), Toast.LENGTH_SHORT).show();

        for (int i = 0; i < listParent.get(mainPos).getOptionArrayList().size(); i++) {

            boolean isSel = false;
            for (int j = 0; j < listInt.size(); j++) {
                if (listInt.get(j) == i) {
                    isSel = true;
                }
            }

            listParent.get(mainPos).getOptionArrayList().get(i).setSelected(isSel);

            if (!isSel) {
                for (int j = 0; j < listParent.get(mainPos).getOptionArrayList().get(i).getSubOptions().size(); j++) {
                    listParent.get(mainPos).getOptionArrayList().get(i).getSubOptions().get(j).setSelected(false);
                }
            }
        }

        adapter.notifyItemChanged(mainPos);
    }

    private ArrayList<ParentModel> getModel() {
        listParent = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ParentModel model = new ParentModel();
            model.setName("Item " + i);
            model.setIsType(i);

            ArrayList<ChildOption> optionArrayList = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                ChildOption option = new ChildOption();
                option.setIsType(j);
                option.setName("Item " + i + "." + j);
                option.setSelected(false);

                ArrayList<SubChildOption> childOptionsList = new ArrayList<>();
                for (int k = 0; k < 5; k++) {
                    if (j != 0 && j != 1) {
                        SubChildOption childOptions = new SubChildOption();
                        childOptions.setName("Sub Child : " + (k + 1));
                        childOptions.setSelected(false);
                        childOptionsList.add(childOptions);
                    }
                }

                option.setSubOptions(childOptionsList);
                optionArrayList.add(option);
            }
            model.setOptionArrayList(optionArrayList);
            listParent.add(model);

        }
        return listParent;
    }
}
