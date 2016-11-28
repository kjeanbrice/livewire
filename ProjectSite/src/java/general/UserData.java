/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.io.Serializable;


public class UserData implements Serializable {
    
    private int userid;
    private String lastname;
    private String firstname;
    private String address;
    private String city;
    private String state;
    private int zipcode;
    private long telephone;
    private String email;
    private long accountnumber;
    private long creditcard;
    private int rating;
    private int acounttype;
    private String password;

    
    public UserData(){
        
    }
    
    public UserData(int userid, String lastname, String firstname,String address, String city, 
            String state, int zipcode, long telephone, String email, long accountnumber,
            long creditcard, int rating, int acounttype,String password){
        this.userid = userid;
        this.lastname = lastname;
        this.firstname = firstname;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.telephone = telephone;
        this.email = email;
        this.accountnumber = accountnumber;
        this.creditcard = creditcard;
        this.rating = rating;
        this.acounttype = acounttype;
        this.password = password;
    }
    /**
     * @return the userid
     */
    public int getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the zipcode
     */
    public int getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * @return the telephone
     */
    public long getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(long telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the accountnumber
     */
    public long getAccountnumber() {
        return accountnumber;
    }

    /**
     * @param accountnumber the accountnumber to set
     */
    public void setAccountnumber(long accountnumber) {
        this.accountnumber = accountnumber;
    }

    /**
     * @return the creditcard
     */
    public long getCreditcard() {
        return creditcard;
    }

    /**
     * @param creditcard the creditcard to set
     */
    public void setCreditcard(long creditcard) {
        this.creditcard = creditcard;
    }

    /**
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    @Override
    public String toString(){
        return "User Id: " + userid + "First name:" + firstname + "Email: " + email;
    }

    /**
     * @return the usertype
     */
    public int getAccounttype() {
        return acounttype;
    }

    /**
     * @param usertype the usertype to set
     */
    public void setUsertype(int accounttype) {
        this.acounttype = accounttype;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
