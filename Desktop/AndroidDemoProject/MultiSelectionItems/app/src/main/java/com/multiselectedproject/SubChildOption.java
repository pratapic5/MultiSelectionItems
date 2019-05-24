package com.multiselectedproject;

import java.io.Serializable;

public class SubChildOption implements Serializable {

    private String name;

    private boolean selected=false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    @Override
    public String toString() {
        return "SubChildOption{" +
                "name='" + name + '\'' +
                ", selected=" + selected +
                '}';
    }
}
