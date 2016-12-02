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
    private String ownerFirstname;
    private String ownerLastname;
    private String groupName;
    private String creationDate;
    private ArrayList<PostData> postData;
    private boolean groupOwner;

    public GroupData() {
        postData = new ArrayList<PostData>();
    }

    public GroupData(int groupID, String ownerFirstName, String ownerLastname, String groupName, String creationDate, ArrayList<PostData> postData) {
        this.groupID = groupID;
        this.ownerFirstname = ownerFirstName;
        this.ownerLastname = ownerLastname;
        this.groupName = groupName;
        this.creationDate = creationDate;
        this.postData = postData;
        groupOwner = false;

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
     * @return the ownerFirstname
     */
    public String getOwnerFirstname() {
        return ownerFirstname;
    }

    /**
     * @param ownerFirstname the ownerFirstname to set
     */
    public void setOwnerFirstname(String ownerFirstname) {
        this.ownerFirstname = ownerFirstname;
    }

    /**
     * @return the ownerLastname
     */
    public String getOwnerLastname() {
        return ownerLastname;
    }

    /**
     * @param ownerLastname the ownerLastname to set
     */
    public void setOwnerLastname(String ownerLastname) {
        this.ownerLastname = ownerLastname;
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

    public String generateJSON() {
        String outputString = "{\"groupID\":" + groupID + ","
                + "\"firstName\":\"" + ownerFirstname + "\","
                + "\"lastName\":\"" + ownerLastname + "\","
                + "\"creationDate\":\"" + creationDate + "\","
                + "\"groupName\":\"" + groupName + "\","
                + "\"Posts\":[";
        for (int i = 0; i < postData.size(); i++) {
            if (i == postData.size() - 1) {
                outputString += postData.get(i).generateJSON() + "]";
            } else {
                outputString += postData.get(i).generateJSON() + ",";
            }
        }

        outputString += "}";
        return outputString;

    }

    /**
     * @return the groupOwner
     */
    public boolean isGroupOwner() {
        return groupOwner;
    }

    /**
     * @param groupOwner the groupOwner to set
     */
    public void setGroupOwner(boolean groupOwner) {
        this.groupOwner = groupOwner;
    }
}
