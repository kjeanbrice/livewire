$(document).ready(function () {

    

$("#btn_messages").click(function() {var link = "http://" + window.location.hostname + ":" + window.location.port + "/ProjectSite/" + "messagepage.jsp"
        window.location = link;});


$("#btn_CRL_Panel").click(function() {var link = "http://" + window.location.hostname + ":" + window.location.port + "/ProjectSite/" + "CRLpage.jsp"
        window.location = link;});
$("#bestsellers").click(function() {$("#best_seller_space").fadeIn("slow"); getBestSellers();});

$("#btn_CRL_Panel").click(function() {var link = "http://" + window.location.hostname + ":" + window.location.port + "/ProjectSite/" + "CRLpage.jsp"
        window.location = link;});


$("#user_groups_space a").click(function() {
var groupIdForLink = this.id.split("group")[1];
console.log(groupIdForLink);
var link = "http://" + window.location.hostname + ":" + window.location.port + "/ProjectSite/" + "grouppage.jsp?groupID=" + groupIdForLink;
 window.location = link;
});

var useremail = $("#guser_email").text().trim();
$("#history").click(function() {$("#history_space").fadeIn("slow"); getCustomerHistory(useremail);});
$("#suggest").click(function() {$("#suggest_space").fadeIn("slow"); getCustomerSuggestions(useremail);});
$("#user_groups").click(function() {$("#user_groups_space").fadeIn("slow"); getCustomerGroups(useremail);});
$("#btn_log_out").click(function() {
    var link = "http://" + window.location.hostname + ":" + window.location.port + "/ProjectSite/" + "loginpage.jsp";
 window.location = link;
    
});

});
 function getBestSellers() {
  var senderId = parseInt($("#guser_id").text());
    var $url = "/ProjectSite/ProcessCRLTransaction?transaction=BEST_SELLER";

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'application/json',
        success: function (response) {
         
        },
        error: function (e) {
             $("#best_seller_space").empty()
            var bestSellers = $.parseJSON( e.responseText);
            for(m in bestSellers.groups) {
              $("#best_seller_space").append("<h5>" + bestSellers.groups[m] +"<\/h5>")

        }
        $("#best_seller_space").fadeIn("slow");

        }

    });
    }
    
     function getCustomerSuggestions(email) {
  var senderId = parseInt($("#guser_id").text());
    var $url = "/ProjectSite/ProcessCRLTransaction?transaction=ITEM_SUGGESTION"
    +"&email=" + email;

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'application/json',
        success: function (response) {
         
        },
        error: function (e) {
             $("#suggest_space").empty()
            var suggestions = $.parseJSON( e.responseText);
            for(m in suggestions.suggestions) {
              $("#suggest_space").append("<h5>" + suggestions.suggestions[m] +"<\/h5>")

        }
        $("#suggest_space").fadeIn("slow");

        }

    });
    }
    
        
    function getCustomerHistory(email) {
  var senderId = parseInt($("#guser_id").text());
    var $url = "/ProjectSite/ProcessCRLTransaction?transaction=ITEM_SUGGESTION"
    +"&email=" + email;

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'application/json',
        success: function (response) {
         
        },
        error: function (e) {
             $("#history_space").empty()
            var suggestions = $.parseJSON( e.responseText);
            for(m in suggestions.suggestions) {
              $("#history_space").append("<h5>" + suggestions.suggestions[m] +"<\/h5>")

        }
        $("#user_history_space").fadeIn("slow");

        }

    });
    }
    
       function getCustomerGroups(email) {
  var senderId = parseInt($("#guser_id").text());
    var $url = "/ProjectSite/ProcessCRLTransaction?transaction=CUSTOMER_GROUPSWITH_ID"
    +"&email=" + email;

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'application/json',
        success: function (response) {
         
        },
        error: function (e) {
             $("#user_groups_space").empty()
            var groups = $.parseJSON( e.responseText);
            console.log(groups);
            for(m in groups.groups) {
              $("#user_groups_space").append("<a id=\"group" + groups.groups[m].split('~')[1] +"\">" + groups.groups[m].split('~')[0] +"<\/a>")

        }
        $("#user_group_space").fadeIn("slow");
        
$("#user_groups_space a").click(function() {
var groupIdForLink = this.id.split("group")[1];
console.log(groupIdForLink);
var link = "http://" + window.location.hostname + ":" + window.location.port + "/ProjectSite/" + "grouppage.jsp?groupID=" + groupIdForLink;
 window.location = link;
});

        }
        
        
        
        
        

    });
    }