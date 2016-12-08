/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

/**
 *
 * @author peterjasko
 */
public class AdvertisementData {
    int advertisement_id;
    int employee_id;
    String date_of_ad;
    String type_of_ad;
    String company;
    String item_name;
    String content;
    float unit_price;

    public AdvertisementData(int advertisement_id, int employee_id, String date_of_ad, String type_of_ad, String company, String item_name, String content, float unit_price, int num_available) {
        this.advertisement_id = advertisement_id;
        this.employee_id = employee_id;
        this.date_of_ad = date_of_ad;
        this.type_of_ad = type_of_ad;
        this.company = company;
        this.item_name = item_name;
        this.content = content;
        this.unit_price = unit_price;
        this.num_available = num_available;
    }

    public void setAdvertisement_id(int advertisement_id) {
        this.advertisement_id = advertisement_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public void setDate_of_ad(String date_of_ad) {
        this.date_of_ad = date_of_ad;
    }

    public void setType_of_ad(String type_of_ad) {
        this.type_of_ad = type_of_ad;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUnit_price(float unit_price) {
        this.unit_price = unit_price;
    }

    public void setNum_available(int num_available) {
        this.num_available = num_available;
    }

    public int getAdvertisement_id() {
        return advertisement_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public String getDate_of_ad() {
        return date_of_ad;
    }

    public String getType_of_ad() {
        return type_of_ad;
    }

    public String getCompany() {
        return company;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getContent() {
        return content;
    }

    public float getUnit_price() {
        return unit_price;
    }

    public int getNum_available() {
        return num_available;
    }
    int num_available;
    
    public String generateJSON() {
        String outputString = "{\"advertisement_id\":" + advertisement_id + ","
                + "\"employee_id\":\"" + employee_id + "\","
                + "\"date_of_ad\":\"" + date_of_ad + "\","
                + "\"type_of_ad\":\"" + type_of_ad + "\","
                + "\"company\":\"" + company + "\","
                 + "\"item_name\":\"" + item_name + "\","
                + "\"content\":\"" + content + "\","
                + "\"unit_price\":\"" + unit_price 
                + "\"}";
      return outputString;  
     }
}
