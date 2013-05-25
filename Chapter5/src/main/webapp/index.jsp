<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Web Data Management - Chapter 5</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="js/hello.js"></script>
<script>
			$(document).ready(function(){
			  $("#getButton").click(function(){
			    $.get("ajax/asyncCall",function(data){
			      alert(data);
			    });
			  });
			  
			  $("#postButton").click(function(){
				    $.post("ajax/asyncCall",	
				    {
				    	title: 'Lost in Translation',
				    	genre: 'Drama'
				    },		
				    function(data){
				      alert(data);
				    });
				  });
			});

</script>
</head>
<body>
	<h1>Hello world</h1>
	<button id="getButton">Send an HTTP GET request to a page and get the result back</button>
	<br/>
	<button id="postButton">Send an HTTP POST request to a page and get the result back</button>
</body>
</html>