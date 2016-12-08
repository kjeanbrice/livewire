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
public class MessageData {
    
    private int messageId;
    private String message_date;
    private String message_subject;
    private String message_content;
    private int message_sender;
    private int message_receiver;
    private String message_sender_name = "";

    public String getMessage_sender_name() {
        return message_sender_name;
    }

    public String getMessage_receiver_name() {
        return message_receiver_name;
    }
    private String message_receiver_name = "";

    public void setMessage_sender_name(String message_sender_name) {
        this.message_sender_name = message_sender_name;
    }

    public void setMessage_receiver_name(String message_receiver_name) {
        this.message_receiver_name = message_receiver_name;
    }

    public MessageData(int messageId, String message_date, String message_subject, String message_content, int message_sender, int message_receiver) {
        this.messageId = messageId;
        this.message_date = message_date;
        this.message_subject = message_subject;
        this.message_content = message_content;
        this.message_sender = message_sender;
        this.message_receiver = message_receiver;
    }
    
     public MessageData(int messageId, String message_date, String message_subject, String message_content, int message_sender, int message_receiver, String sName, String rName) {
        this.messageId = messageId;
        this.message_date = message_date;
        this.message_subject = message_subject;
        this.message_content = message_content;
        this.message_sender = message_sender;
        this.message_receiver = message_receiver;
        this.message_sender_name = sName;
        this.message_receiver_name = rName;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public void setMessage_date(String message_date) {
        this.message_date = message_date;
    }

    public void setMessage_subject(String message_subject) {
        this.message_subject = message_subject;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public void setMessage_sender(int message_sender) {
        this.message_sender = message_sender;
    }

    public void setMessage_receiver(int message_receiver) {
        this.message_receiver = message_receiver;
    }
   

    public int getMessageId() {
        return messageId;
    }

    public String getMessage_date() {
        return message_date;
    }

    public String getMessage_subject() {
        return message_subject;
    }

    public String getMessage_content() {
        return message_content;
    }

    public int getMessage_sender() {
        return message_sender;
    }

    public int getMessage_receiver() {
        return message_receiver;
    }
   
    
     public String generateJSON() {
        String outputString = "{\"message_id\":" + messageId + ","
                + "\"message_date\":\"" + message_date + "\","
                + "\"message_subject\":\"" + message_subject + "\","
                + "\"message_content\":\"" + message_content + "\","
                + "\"message_sender\":\"" + message_sender + "\","
                 + "\"message_sender_name\":\"" + message_sender_name + "\","
                + "\"message_receiver\":\"" + message_receiver + "\","
                + "\"message_receiver_name\":\"" + message_receiver_name 
                + "\"}";
      return outputString;  
     }

}
