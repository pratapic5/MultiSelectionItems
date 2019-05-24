package com.multiselectedproject;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("All")
public class ParentModel implements Serializable {

    private String name;
    private int  isType;
    private ArrayList<ChildOption> optionArrayList;
    private ArrayList<Integer> listInt = new ArrayList<>();

    public int getIsType() {
        return isType;
    }

    public void setIsType(int isType) {
        this.isType = isType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ChildOption> getOptionArrayList() {
        return optionArrayList;
    }

    public void setOptionArrayList(ArrayList<ChildOption> optionArrayList) {
        this.optionArrayList = optionArrayList;
    }

    public ArrayList<Integer> getListInt() {
        return listInt;
    }

    public void setListInt(ArrayList<Integer> listInt) {
        this.listInt = listInt;
    }

    @Override
    public String toString() {
        return "ParentModel{" +
                "name='" + name + '\'' +
                ", isType=" + isType +
                ", optionArrayList=" + optionArrayList +
                '}';
    }
}
