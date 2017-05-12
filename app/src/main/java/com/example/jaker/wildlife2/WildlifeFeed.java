package com.example.jaker.wildlife2;


public class WildlifeFeed {
    private String title;
    private String description;
    private String pubDate;
    private String link;

    public WildlifeFeed()
    {

    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }





    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
