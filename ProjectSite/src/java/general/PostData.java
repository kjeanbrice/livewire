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
public class PostData implements Serializable {

    private int postID;
    private UserData user;
    private String postDate;
    private String postContent;
    private ArrayList<CommentData> commentData;

    public PostData() {
        commentData = new ArrayList<CommentData>();
    }

    public PostData(int postID, UserData user, String postDate, String postContent, ArrayList<CommentData> commentData) {
        this.postID = postID;
        this.user = user;
        this.postDate = postDate;
        this.postContent = postContent;
        this.commentData = commentData;

    }

    /**
     * @return the postID
     */
    public int getPostID() {
        return postID;
    }

    /**
     * @param postID the postID to set
     */
    public void setPostID(int postID) {
        this.postID = postID;
    }

    /**
     * @return the user
     */
    public UserData getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserData user) {
        this.user = user;
    }

    /**
     * @return the postDate
     */
    public String getPostDate() {
        return postDate;
    }

    /**
     * @param postDate the postDate to set
     */
    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    /**
     * @return the postContent
     */
    public String getPostContent() {
        return postContent;
    }

    /**
     * @param postContent the postContent to set
     */
    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    /**
     * @return the commentData
     */
    public ArrayList<CommentData> getCommentData() {
        return commentData;
    }

    /**
     * @param commentData the commentData to set
     */
    public void setCommentData(ArrayList<CommentData> commentData) {
        this.commentData = commentData;
    }

    public String generateJSON() {
        String outputString = "{\"postID\":" + postID + ","
                + "\"postContent\":\"" + postContent + "\","
                + "\"postDate\":\"" + postDate + "\","
                + "\"ownerFirstName\":\"" + user.getFirstname() + "\","
                + "\"ownerLastName\":\"" + user.getLastname() + "\","
                + "\"Comments\":[";
        for (int i = 0; i < commentData.size(); i++) {
            if (i == commentData.size() - 1) {
                outputString += commentData.get(i).generateJSON() + "]";
            } else {
                outputString += commentData.get(i).generateJSON() + ",";
            }
        }
        
        if(commentData.isEmpty()){
            outputString+="{}]}";
        }else{
        outputString += "}";
        }
        return outputString;

    }

}
