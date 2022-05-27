package com.example.listaspesav2;

public class ListElement {
    private String name;
    private int num;
    private boolean isSelected = false;

    public ListElement(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public String getName() { return name; }

    public int getNum() { return num; }

    public void toggleSelect() { isSelected = !isSelected; }

    public boolean isSelected() { return isSelected; }
}
