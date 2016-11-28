/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




function countPasswordFields(){
        var inputLength = document.getElementsByTagName('input').length;
	var input= document.getElementsByTagName('input');
	
	var counts = 0;
	for (var i=0, length1 = input.length; i<length1; i++) 
	{
		if (input[i].type === "password") 
		{
			counts++;
		}
	}
	
	alert("Password form fields: "+ counts +'/'+ inputLength );
}

