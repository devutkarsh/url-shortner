<script>
	var x = null;

	function send(){
		x = new XMLHttpRequest();
		
		
		
		x.onreadystatechange = function(){
			if(x.readyState == 4){
				
				document.getElementById("response").innerHTML = x.responseText;
				 document.getElementById("response").href= x.responseText;
				
			}
		}
		
		var str = document.getElementById("inputURL").value;
		
		//alert(str);
		x.open("GET", "./short?url="+str);
		x.send();
		
	}

</script>

<div  ng-controller="shortenController">



    <form>

        <div class="form-group">

            <label for="inputURL">Enter your URL to be shorten :</label>

            <input type="url" class="form-control" id="inputURL" placeholder="Enter your URL" ng-model="urlold">

        </div>

        <button type="submit" class="btn btn-primary" onclick="send()">Shorten</button>

    </form>
<br/>

<table class="table">
   <tr>
      <th>Your Short URL is:</th>
   </tr>
   <tr>
   <td><a href="#" id="response" target="_blank"></a></td>
   </tr>
</table>


	
</div>

	