package com.sjsu.nguyen.alertsjsu;

/**
 * Created by nguyen on 6/30/17.
 */

public class AlertPOJO {

    private String uid;
    private String subject;
    private String content;
    private String location;
    //private int verifyCount;

    public AlertPOJO(String uid, String subject, String content, String location){
        this.uid = uid;
        this.subject = subject;
        this.content = content;
        this.location = location;
        //verifyCount = 0;
    }

    // Getter block
    public String getUid(){
        return uid;
    }
    public String getSubject(){
        return subject;
    }
    public String getContent(){
        return content;
    }
    public String getLocation(){ return location; }
    /*public int getVerifyCount(){
        return verifyCount;
    }
    */

    //Setter block
    public void setUid(String uid){
        this.uid = uid;
    }
    public void setSubject(String subject){
        this.subject = subject;
    }
    public void setContent(String content){
        this.content = content;
    }
    public void setLocation(String location) { this.location = location; }
    /*public void setVerifyCount(int verifyCount){
        this.verifyCount = verifyCount;
    }*/

}
