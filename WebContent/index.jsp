
 <!DOCTYPE html>
<html lang="en">
<head>
  <title>URL Shortner</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">

  <h1 >URL Shortner</h1>
  <p><kbd>Shortcuts are probably quicker</kbd></p>  	

<div ng-app="mainApp">
        <ng-view></ng-view>
      </div>
      
</div>
 
      <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.28/angular.min.js"></script>
      <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.28//angular-route.min.js"></script>
      <script type="text/javascript">
      var mainApp = angular.module("mainApp", ['ngRoute']);
      
      mainApp.config(function($routeProvider) {
          $routeProvider
              .when('/home', {
                  templateUrl: 'welcome.jsp',
                  controller: 'homeController'
              })
              
              
              .when('/viewStudent', {
                  templateUrl: 'viewStudent.html',
                  controller: 'StudentController'
              })
              
      		
      		.when('/display',{
      			templateUrl: 'display.jsp',
                  controller: 'displayController'
      		})
      		
      		
      		.when('/shorten',{
      			templateUrl: 'shorten.jsp',
                  controller: 'shortenController'
      		})
      		
      		
              .otherwise({
                  redirectTo: '/home'
              });
      });
       
      mainApp.controller('StudentController', function($scope) {
          $scope.students = [
              {name: 'Mark Waugh', city:'New York'},
              {name: 'Steve Jonathan', city:'London'},
              {name: 'John Marcus', city:'Paris'}
          ];
      	
          $scope.message = "Select an option for URL Shorten";
      });

      	function displayController($scope,$http) {
      	var url="http://127.0.0.1:8080/URL/displayData";
      	$http.get(url).success( function(response) {
                                 $scope.results = response;
                              });
      }

      function shortenController($scope,$http) {
    	  $scope.shortURLCall = function() {
    		  var urlold = $scope.urlold;
 	         alert(urlold + ' is the original URL');   
 	         var hitURL="http://127.0.0.1:8080/URL/short?url="+urlold;
 	         alert(hitURL);
 	         
  	        $http.get(hitURL).success( function(response2) {
  	        	//alert(response2);
                $scope.shortURLS = response2;
                //var urlnew = $scope.shortURLS.urlnew;
                //alert(urlnew);
             });

 	    };
      	

      }
      </script>

</body>
</html>