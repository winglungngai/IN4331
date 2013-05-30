<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Web Data Management - Chapter 5</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="resources/js/hello.js"></script>
<script>
	var xmlDoc = null;
	var xslDoc = null;
	function displayResult(xml, xsl, id) {
		// code for IE
		if (window.ActiveXObject) {
			ex = xml.transformNode(xsl);
			document.getElementById(id).innerHTML = ex;
		}
		// code for Mozilla, Firefox, Opera, etc.
		else if (document.implementation
				&& document.implementation.createDocument) {
			xsltProcessor = new XSLTProcessor();
			xsltProcessor.importStylesheet(xsl);
			resultDocument = xsltProcessor.transformToFragment(xml, document);
			document.getElementById(id).appendChild(resultDocument);
		}
	}
	$(document).ready(function() {

			$.get("ajax/listPlays", function(data) {
				xmlDoc = data;
			});

			$.get("resources/xsl/play.xsl", function(data) {
				xslDoc = data;
			});
	});
</script>
</head>
<body>
	<h1>Hello world</h1>
	<button id="getButton">Send an HTTP GET request to a page and
		get the result back</button>
	<br />
	<button id="postButton">Send an HTTP POST request to a page
		and get the result back</button>
		<div id="test"></div>
</body>
</html>