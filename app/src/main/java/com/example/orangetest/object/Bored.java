package com.example.orangetest.object;

public class Bored {

    private String activity;
    private String type;
    private String participants;
    private String price;
    private String link;
    private String key;
    private String accessibility;

public Bored(){}

    public Bored(String key,String activity, String type, String participants, String price, String link,  String accessibility){

    this.activity= activity;
    this.type=type;
    this.participants=participants;
    this.price=price;
    this.link=link;
    this.key=key;
    this.accessibility=accessibility;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }
}
