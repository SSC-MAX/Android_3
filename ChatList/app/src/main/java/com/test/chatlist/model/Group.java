package com.test.chatlist.model;

public class Group {
    private String name;
    private String creator;
    private int iconId;

    public Group() {
    }

    public Group(String name, String creator, int iconId) {
        this.name = name;
        this.creator = creator;
        this.iconId = iconId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
