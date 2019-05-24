package com.multiselectedproject;

import java.io.Serializable;
import java.util.ArrayList;

public class ChildOption implements Serializable {

    private String name;

    private int isType;
    private boolean selected = false;
    private ArrayList<SubChildOption> subOptions;
    public ArrayList<Integer> listSubInt = new ArrayList<>();

    public int getIsType() {
        return isType;
    }

    public void setIsType(int isType) {
        this.isType = isType;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SubChildOption> getSubOptions() {
        return subOptions;
    }

    public void setSubOptions(ArrayList<SubChildOption> subOptions) {
        this.subOptions = subOptions;
    }

    public ArrayList<Integer> getListSubInt() {
        return listSubInt;
    }

    public void setListSubInt(ArrayList<Integer> listSubInt) {
        this.listSubInt = listSubInt;
    }

    @Override
    public String toString() {
        return "Option{" +
                "name='" + name + '\'' +
                ", isType=" + isType +
                ", selected=" + selected +
                ", SunChildData=" + subOptions +
                '}';
    }
}
