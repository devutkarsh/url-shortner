
<div  ng-controller="displayController">
<table class="table">
   <tr>
      <th>ID</th>
      <th>URL</th>
	  <th>Shorten URL</th>
	  <th>Hits</th>
   </tr>
   <tr ng-repeat="result in results">
      <td>{{ result.urlid }}</td>
      <td>{{ result.url }}</td>
	  <td><a href="http://127.0.0.1:8080/URL/c={{ result.urlnew }}" target="_blank">http://127.0.0.1:8080/URL/c={{ result.urlnew }}</a></td>
	  <td>{{ result.hits }}</td>
   </tr>
</table>
</div>

