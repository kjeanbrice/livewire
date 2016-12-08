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
public class GroupData implements Serializable {

    private int groupID;
    private String groupName;
    private String creationDate;
    private String groupType;
    private ArrayList<PostData> postData;
    private UserData user;

    public GroupData() {
        postData = new ArrayList<PostData>();
    }

    public GroupData(int groupID,String groupName, String creationDate,UserData user, ArrayList<PostData> postData) {
        this.groupID = groupID;
        
        this.groupName = groupName;

        this.creationDate = creationDate;
        this.postData = postData;
        this.user = user;
    }

    /**
     * @return the groupID
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * @param groupID the groupID to set
     */
    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @return the creationDate
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }


    /**
     * @return the postData
     */
    public ArrayList<PostData> getPostData() {
        return postData;
    }

    /**
     * @param postData the postData to set
     */
    public void setPostData(ArrayList<PostData> postData) {
        this.postData = postData;
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
     * @return the groupType
     */
    public String getGroupType() {
        return groupType;
    }

    /**
     * @param groupType the groupType to set
     */
    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }


    public String generateJSON() {
        String outputString = "{\"groupID\":" + groupID + ","
                + "\"firstName\":\"" + user.getFirstname() + "\","
                + "\"lastName\":\"" + user.getLastname() + "\","
                + "\"creationDate\":\"" + creationDate + "\","
                + "\"groupName\":\"" + groupName + "\","
                + "\"groupType\":\"" + groupType + "\","
                + "\"email\":\"" + user.getEmail() + "\","
                + "\"gUserID\":\"" + user.getUserid() + "\","
                + "\"Posts\":[";

        if (postData.isEmpty()) {
            outputString = "{\"groupID\":" + groupID + ","
                    + "\"firstName\":\"" + getUser().getFirstname() + "\","
                    + "\"lastName\":\"" + getUser().getLastname() + "\","
                    + "\"creationDate\":\"" + creationDate + "\","
                    + "\"groupName\":\"" + groupName + "\","
                    + "\"groupType\":\"" + groupType + "\","
                    + "\"email\":\"" + user.getEmail() + "\","
                    + "\"gUserID\":\"" + user.getUserid() + "\"}";
                    
            return outputString;
        }

        String test_post = "";
        for (int i = 0; i < postData.size(); i++) {
            if (i == postData.size() - 1) {
                test_post = postData.get(i).generateJSON() + "]";
                outputString += postData.get(i).generateJSON() + "]";
            } else {
                test_post = postData.get(i).generateJSON();
                outputString += postData.get(i).generateJSON() + ",";
            }
        }

        outputString += "}";

        return outputString;

    }
}
