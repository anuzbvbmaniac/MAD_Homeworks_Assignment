package com.example.android.todoapp.introview;

public class ScreenItem {

    String introTitle, introDesc;
    int introImages;

    public ScreenItem(String introTitle, String introDesc, int introImages) {
        this.introTitle = introTitle;
        this.introDesc = introDesc;
        this.introImages = introImages;
    }

    public String getIntroTitle() {
        return introTitle;
    }

    public void setIntroTitle(String introTitle) {
        this.introTitle = introTitle;
    }

    public String getIntroDesc() {
        return introDesc;
    }

    public void setIntroDesc(String introDesc) {
        this.introDesc = introDesc;
    }

    public int getIntroImages() {
        return introImages;
    }

    public void setIntroImages(int introImages) {
        this.introImages = introImages;
    }
}
