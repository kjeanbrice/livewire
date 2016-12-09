var register = true;
var currentUserId = 0;
$(document).ready(function () {



$("#btn_getemailList").click(function() {getEmailList();});
$("#btn_refresh").click(function() {getMessages();});
$("#create_ad").click(function() {$("#ad_creation").fadeIn("slow");});
$("#list_ads").click(function() {getAds();});
$("#btn_create_ad").click(function() {
    createAdvertisement(
            1, "date",$("#type").val(),$("#company").val(),$("#item").val(),$("#content").val(),$("#price").val(),$("#num_available").val());
});

$("#btn_get_items").click(function() {getItems()});

$("#item_suggestions").click(function() {$("#suggest_space").fadeIn("slow");});
$("#company_list").click(function() {$("#company_list_space").fadeIn("slow"); });
$("#people_item_list").click(function() {$("#people_item_list_space").fadeIn("slow"); });
$("#btn_get_names").click(function() {getNamesWhoBought()});

$("#transaction_user_list").click(function() {$("#transaction_user_list_space").fadeIn("slow")});

$("#btn_get_transactions_by_user").click(function() {getTransactionsByEmail()});

$("#btn_getgroups").click(function() {getCustomerGroups($(" #user_group_space #user_email").val())});;

$("#user_groups").click(function() {$("#user_group_space").fadeIn("slow");});
$("#user_history").click(function() {$("#user_history_space").fadeIn("slow");});
$("#btn_gethistory").click(function() {getCustomerHistory($("#user_history_space #user_email").val())});
$("#create_transaction").click(function() {$("#create_transaction_space").fadeIn("slow");});
$("#account_alter").click(function() {$("#account_alter_space").fadeIn("slow");
    $("#alter-form").fadeIn("slow");
    
    getUsers();});


$("#register-submit").click(function() {
    if(register) {
        register() ;
    } else {
        alert("updating");
        updateUser();
    }
});

$("#btn_make_transaction").click(function(){createTransaction($("#ad_id").val(), $("#seller_id").val(),$("#consumer_id").val(),$("#create_transaction_space #number_of_units").val(),
$("#create_transaction_space #account_number").val());
});

    
    
    function getItems() {
    var $url = "/ProjectSite/ProcessManagerTransactions?transaction=GET_COMPANY_ITEMS&company=" + $("#item_company_name").val();

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'application/json',
        success: function (response) {
         
        },
        error: function (e) {
          
              var items = $.parseJSON( e.responseText);
              var listString = ""
              $("#company_list_space h5").empty()
            for (i in items.items) {
              $("#company_list_space").append("<h5>" + items.items[i] +"<\/h5>")

            }

        }

    })
    }


    function getNamesWhoBought() {
    var $url = "/ProjectSite/ProcessManagerTransactions?transaction=GET_WHO_BOUGHT&item_name=" + $("#item_name").val();

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'application/json',
        success: function (response) {
         
        },
        error: function (e) {
          
              var names = $.parseJSON( e.responseText);
              var listString = ""
              $("#people_item_list_space h5").empty()
            for (i in names.names) {
              $("#people_item_list_space").append("<h5>" + names.names[i] +"<\/h5>")

            }

        }

    })
    }
    
    
    function getTransactionsByEmail(email_val) {
    var $url = "/ProjectSite/ProcessManagerTransactions?transaction=TRANSACTION_BY_EMAIL&email=" + $("#user_name_for_transaction").val();

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'application/json',
        success: function (response) {
         
        },
        error: function (e) {
          
              var transactions = $.parseJSON( e.responseText);
              console.log(transactions);
              var listString = ""
              $("#transaction_user_list_space h5").empty()
            for (i in transactions.transactions) {
              $("#transaction_user_list_space").append("<h4 > Transaction Number "+ transactions.transactions[i].transaction_id + " with " + transactions.transactions[i].first_name + " " + transactions.transactions[i].last_name +"<\/h4>")
              $("#transaction_user_list_space").append("<h5> Bought:  " + transactions.transactions[i].item_name +"<\/h5>")
              $("#transaction_user_list_space").append("<h5> From:  " + transactions.transactions[i].company +"<\/h5>")
              $("#transaction_user_list_space").append("<h5> Their email is:  " + transactions.transactions[i].email +"<\/h5>")

            }

        }

    })
    }
    
    
      function getTransactionsByItemName(email_val) {
    var $url = "/ProjectSite/ProcessManagerTransactions?transaction=TRANSACTION_BY_EMAIL&email=" + $("#user_name_for_transaction").val();

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'application/json',
        success: function (response) {
         
        },
        error: function (e) {
          
              var transactions = $.parseJSON( e.responseText);
              console.log(transactions);
              var listString = ""
              $("#transaction_user_list_space h5").empty()
            for (i in transactions.transactions) {
              $("#transaction_user_list_space").append("<h4 > Transaction Number "+ transactions.transactions[i].transaction_id + " with " + transactions.transactions[i].first_name + " " + transactions.transactions[i].last_name +"<\/h4>")
              $("#transaction_user_list_space").append("<h5> Bought:  " + transactions.transactions[i].item_name +"<\/h5>")
              $("#transaction_user_list_space").append("<h5> From:  " + transactions.transactions[i].company +"<\/h5>")
              $("#transaction_user_list_space").append("<h5> Their email is:  " + transactions.transactions[i].email +"<\/h5>")

            }

        }

    })
    }
    
    
    
      function generateTransaction(ad_id, seller_id,consumer_id,date,units,account_number) {
        var senderId = parseInt($("#guser_id").text());
        var receiver = $("#receiver").val();
        var subject = $("#subject").val();
        var message = $("#message").val()
        var $url = "/ProjectSite/ProcessCRLTransaction?transaction=CREATE_ADVERTISEMENT?" + 
          "ad_id=" +  ad_id +
          "&seller_id=" + seller_id +
          "&consumer_id=" + consumer_id +
  "&date=" + date +
   "&item=" + item+
   "&units=" + units;
   "&account_number=" +account_number;

        $.ajax({
            method: 'get',
            url: $url,
            dataType: 'json',
            success: function (send_sucess) {
            },
            error: function () {
            }

        });
    }



    


});

      
    function getAds() {
  var senderId = parseInt($("#guser_id").text());
    var $url = "/ProjectSite/ProcessCRLTransaction?transaction=GET_ADS";

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'application/json',
        success: function (response) {
        },
        error: function (e) {
            $("#list_ads_space").empty()
            var ads = $.parseJSON( e.responseText);
            $("#list_ads_space").append( "<div id=\"ads\"><\/div>" );
            for(m in ads.ads) {
              $("#ads").append("<div id=\""+ads.ads[m].advertisement_id + "\"></div>")
              $("#"+ads.ads[m].advertisement_id).append("<h3>" + ads.ads[m].item_name +"<\/h3>")
              $("#"+ads.ads[m].advertisement_id).append("<h4> AD ID is :" + ads.ads[m].advertisement_id +"<\/h4>")
              $("#"+ads.ads[m].advertisement_id).append("<h4>" + ads.ads[m].company +"<\/h4>")
              $("#"+ads.ads[m].advertisement_id).append("<p>" + ads.ads[m].content +"<\/p>")
              $("#"+ads.ads[m].advertisement_id).append("<a onclick=\"deleteAd("+ ads.ads[m].advertisement_id + ")\" href=\"javascript:void(0);\">Delete<\/a>")
              $("#"+ads.ads[m].advertisement_id).append("<hr>");
//            }


        }
        $("#list_ads_space").fadeIn("slow");
        $("#ads").fadeIn("slow");
    }});
}

function deleteAd(id) {
    var $url = "/ProjectSite/ProcessCRLTransaction?transaction=DELETE_ADVERTISEMENT&ad_id=" +id

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'json',
        success: function (send_sucess) {
            getAds();
        },
        error: function () {
            getAds();
        }

    });
}

function deleteUser(id) {
    var $url = "/ProjectSite/ProcessCRLTransaction?transaction=DELETE_USER&user_id=" +id

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'json',
        success: function (send_sucess) {
            getUsers();
        },
        error: function () {
            getUsers();
        }

    });
}

    function createAdvertisement(employee_id, date,type,company,item,content,price,available) {
        var senderId = parseInt($("#guser_id").text());
        var receiver = $("#receiver").val();
        var subject = $("#subject").val();
        var message = $("#message").val()
        var $url = "/ProjectSite/ProcessCRLTransaction?transaction=CREATE_ADVERTISEMENT" + 
          "&employee_id=" +  employee_id +
          "&date_of_ad=" + date +
          "&type=" + type +
  "&company=" + company +
   "&item=" + item+
   "&content=" + content +
   "&unit_price=" +price +
           "&num_available=" + available;

        $.ajax({
            method: 'get',
            url: $url,
            dataType: 'json',
            success: function (send_sucess) {
                getAds();
            },
            error: function () {
                getAds();
            }

        });
    }
    
        function createTransaction(ad_id, seller_id,consumer_id,number_of_units,account_number) {
        var senderId = parseInt($("#guser_id").text());
        var receiver = $("#receiver").val();
        var subject = $("#subject").val();
        var message = $("#message").val()
        var $url = "/ProjectSite/ProcessCRLTransaction?transaction=GENERATE_TRANSACTION" + 
          "&ad_id=" +  ad_id +
          "&seller_id=" + seller_id +
          "&consumer_id=" + consumer_id +
  "&number_of_units=" + number_of_units +
   "&account_number=" + account_number;

        $.ajax({
            method: 'get',
            url: $url,
            dataType: 'json',
            success: function (send_sucess) {
            },
            error: function () {
            }

        });
    }
    
        function getCustomerGroups(email) {
  var senderId = parseInt($("#guser_id").text());
    var $url = "/ProjectSite/ProcessCRLTransaction?transaction=CUSTOMER_GROUPS"
    +"&email=" + email;

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'application/json',
        success: function (response) {
         
        },
        error: function (e) {
             $("#user_group_space").empty()
            var groups = $.parseJSON( e.responseText);
            console.log(groups);
            for(m in groups.groups) {
              $("#user_group_space").append("<h5>" + groups.groups[m] +"<\/h5>")

        }
        $("#user_group_space").fadeIn("slow");

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
            console.log(e);
            var suggestions = $.parseJSON( e.responseText);
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
             $("#user_history_space").empty()
            var suggestions = $.parseJSON( e.responseText);
            console.log(suggestions)
            for(m in suggestions.suggestions) {
              $("#user_history_space").append("<h5>" + suggestions.suggestions[m] +"<\/h5>")
              
        }
        if (suggestions.suggestions.length ==0) {
             $("#user_history_space").append("<h5>No History<\/h5>")
        }
        $("#user_history_space").fadeIn("slow");

        }

    });
    }
    
        function getUsers() {
  var senderId = parseInt($("#guser_id").text());
    var $url = "/ProjectSite/ProcessCRLTransaction?transaction=GET_USERS"

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'application/json',
        success: function (response) {
         
        },
        error: function (e) {
             $("#account_alter_space").empty()
             console.log(e)
            var users = $.parseJSON( e.responseText);
            console.log(users);
            for(m in users.users) {
               $("#account_alter_space").append("<h3 onclick=\"fillEditFormById("+ users.users[m].userID + ")\" href=\"javascript:void(0);\">" + users.users[m].firstName + " " +users.users[m].lastName+ "<\/h3>")
               $("#account_alter_space").append("<a onclick=\"deleteUser("+ users.users[m].userID + ")\" href=\"javascript:void(0);\">Delete<\/a>")
        }$("#account_alter_space").append("</hr>");
        $("#account_alter_space").fadeIn("slow");

        }

    });
    }
    
        
        function fillEditFormById(user_id) {
            currentUserId = user_id;
  var senderId = parseInt($("#guser_id").text());
    var $url = "/ProjectSite/ProcessCRLTransaction?transaction=GET_USERS"

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'application/json',
        success: function (response) {
         
        },
        error: function (e) {
             console.log(e)
            var users = $.parseJSON( e.responseText);
            console.log(users);
            for(m in users.users) {
                if(parseInt(users.users[m].userID) == user_id) {
                    fillEditForm(users.users[m].firstName,users.users[m].lastName,users.users[m].userEmail, users.users[m].address);
                    register = false;
                }
            }
        }

    });
    }
    
    
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
            console.log(suggestions)
            for(m in bestSellers.groups) {
              $("#best_seller_space").append("<h5>" + bestSellers.groups[m] +"<\/h5>")

        }
        $("#best_seller_space").fadeIn("slow");

        }

    });
    }
    
     function register() {
        var email = $("#remail").val();
        var address = $("#address").val();
        var password = $("#rpassword").val();
        var first_name = $("#first_name").val()
        var last_name = $("#last_name").val()
        var $url = "/ProjectSite/ProcessRegistration?email=" + email + 
          "&address=" +  address +
          "&password=" + password +
          "&first_name=" + first_name +
  "&last_name=" + last_name;
        console.log($url);
        $.ajax({
            method: 'get',
            url: $url,
            dataType: 'json',
            success: function (send_sucess) {
            },
            error: function () {
            }

        });
    }
    
    
    
     function updateUser() {
        var email = $("#remail").val();
        var address = $("#address").val();
        var password = $("#rpassword").val();
        var first_name = $("#first_name").val()
        var last_name = $("#last_name").val()
        var $url = "/ProjectSite/ProcessCRLTransaction?transaction=UPDATE_USER"+
        "&email=" + email + 
          "&password=" + password +
          "&first_name=" + first_name +
        "&last_name=" + last_name +
        "&user_id=" + currentUserId+
                          "&address=" +  encodeURIComponent(address) ;
         $.ajax({
            method: 'get',
            url: $url,
            dataType: 'json',
            success: function (send_sucess) {
            },
            error: function () {
            }

        });
    }
    
    
    function fillEditForm(first_name,last_name,email, address) {
        $("#remail").val(email);
        $("#first_name").val(first_name);
        $("#last_name").val(last_name);
         $("#address").val(address);
    }
