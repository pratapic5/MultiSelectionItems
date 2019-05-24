package com.multiselectedproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

@SuppressWarnings("All")
public class SubChildSelectorActivity extends AppCompatActivity {

    private RecyclerView rvMain;
    Activity mActivity;
    Context mContext;
    ArrayList<SubChildOption> listCHildOptions = new ArrayList<>();
    ArrayList<Integer> listParentInt = new ArrayList<>();
    ArrayList<Integer> listCHildInt = new ArrayList<>();
    int mainPos, childPos, isType, isTypeChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivity = this;
        mContext = this;

        Intent intent = getIntent();
        if (intent != null) {
            listCHildOptions = (ArrayList<SubChildOption>) intent.getSerializableExtra("subItemList");
            listParentInt = (ArrayList<Integer>) intent.getSerializableExtra("intParentList");
            listCHildInt = (ArrayList<Integer>) intent.getSerializableExtra("childIntList");
            mainPos = intent.getIntExtra("mainPos", 0);
            childPos = intent.getIntExtra("subPos", 0);
            isType = intent.getIntExtra("isType", 0);
            isTypeChild = intent.getIntExtra("isTypeChild", 0);
        }


        String stSelect = "";
        for (int i = 0; i < listCHildOptions.size(); i++) {
            if (listCHildOptions.get(i).isSelected()) {
                stSelect = stSelect + i + ",";
            }
        }

        Toast.makeText(mActivity, "" + isTypeChild, Toast.LENGTH_SHORT).show();

        init();
    }

    private void init() {
        rvMain = (RecyclerView) findViewById(R.id.recyclerViewMain);
        rvMain.setLayoutManager(new LinearLayoutManager(mActivity));

        SubChildAdapter adapter = new SubChildAdapter(mContext, isTypeChild, listCHildOptions, listCHildInt);
        rvMain.setAdapter(adapter);

        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<Integer> listChildIntSub = new ArrayList<>();
                for (int i = 0; i < listCHildOptions.size(); i++) {
                    if (listCHildOptions.get(i).isSelected()) {
                        listChildIntSub.add(i);
                    }
                }


                Intent intent = new Intent();
                intent.putExtra("mainPos", mainPos);
                intent.putExtra("subPos", childPos);
                intent.putExtra("isType", isType);
                intent.putExtra("subItemList", listCHildOptions);
                intent.putExtra("intList", listParentInt);
                intent.putExtra("childIntList", listChildIntSub);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
