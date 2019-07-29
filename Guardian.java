package com.example.android.newsapp;

public class Guardian {
    private String mTitle;
    private String mDate;
    private String mSection;
    private String mUrl;
    private String mAuthor;

    public String getmTitle()
    { return mTitle;}
    public String getmDate()
    { return mDate;}
    public String getmSection()
    { return mSection;}
    public String getmUrl()
    { return mUrl;}
    public String getmAuthor()
    {
        return mAuthor;
    }

    public Guardian(String t,String d,String s,String u,String au)
    {
        mTitle=t;
        mDate=d;
        mSection=s;
        mUrl=u;
        mAuthor=au;
    }


}
