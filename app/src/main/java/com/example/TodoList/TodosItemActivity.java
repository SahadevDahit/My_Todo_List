package com.example.TodoList;

public class TodosItemActivity {
    private String id;
    private String title;
    private String subtitle;


    public TodosItemActivity(String id,String title, String subtitle) {
        this.id=id;
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
