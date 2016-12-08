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
public class TransactionData {
    int transaction_id;
    int ad_id;
    int seller_id;
    int consumer_id;
    String transaction_date;
    int number_of_units;
    int account_number;

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public void setAd_id(int ad_id) {
        this.ad_id = ad_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public void setConsumer_id(int consumer_id) {
        this.consumer_id = consumer_id;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public void setNumber_of_units(int number_of_units) {
        this.number_of_units = number_of_units;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public int getAd_id() {
        return ad_id;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public int getConsumer_id() {
        return consumer_id;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public int getNumber_of_units() {
        return number_of_units;
    }

    public int getAccount_number() {
        return account_number;
    }

    public TransactionData(int transaction_id, int ad_id, int seller_id, int consumer_id, String transaction_date, int number_of_units, int account_number) {
        this.transaction_id = transaction_id;
        this.ad_id = ad_id;
        this.seller_id = seller_id;
        this.consumer_id = consumer_id;
        this.transaction_date = transaction_date;
        this.number_of_units = number_of_units;
        this.account_number = account_number;
    }
}
