/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Karl
 */
public class CommentData implements Serializable {

    private int commentID;
    private UserData user;
    private String commentContent;
    private String creationDate;
    private int commentLikes;

    /**
     * @return the commentID
     */
    public CommentData() {

    }

    public CommentData(int commentID, UserData user, String commentContent, int commentLikes, String creationDate) {
        this.commentID = commentID;
        this.user = user;
        this.commentContent = commentContent;
        this.creationDate = creationDate;
        this.commentLikes = commentLikes;
    }

    public int getCommentID() {
        return commentID;
    }

    /**
     * @param commentID the commentID to set
     */
    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    /**
     * @return the userData
     */
    public UserData getUser() {
        return user;
    }

    /**
     * @param userData the userData to set
     */
    public void setUser(UserData user) {
        this.user = user;
    }

    /**
     * @return the commentContent
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * @param commentContent the commentContent to set
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    /**
     * @return the creationData
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationData the creationData to set
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

     /**
     * @return the commentLikes
     */
    public int getCommentLikes() {
        return commentLikes;
    }

    /**
     * @param commentLikes the commentLikes to set
     */
    public void setCommentLikes(int commentLikes) {
        this.commentLikes = commentLikes;
    }
    
    
    public String generateJSON() {

        String outputString = "{\"commentID\":" + commentID + ","
                + "\"commentContent\":\"" + commentContent + "\","
                + "\"creationDate\":\"" + creationDate + "\",";

        outputString += "\"cFirstName\":\"" + user.getFirstname() + "\","
                +"\"cUserID\":\"" + user.getUserid() + "\","
                +"\"cLikes\":\"" + commentLikes + "\","
                + "\"cLastName\":\"" + user.getLastname() + "\"";

        outputString += "}";

        return outputString;
    }

   

 

}
