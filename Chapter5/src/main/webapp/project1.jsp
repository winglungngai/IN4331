<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
    	<title>Movie Search Engine</title>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <style type="text/css">
            @import url("resources/css/style.css");
			*{
				font-size:12px;
			}
			#SearchForm input{
				padding:4px;
			}
			
         </style>
         
        <link rel="stylesheet" href="resources/jquery-ui-1.10.3/themes/base/jquery.ui.all.css">
        <script src="resources/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
		<script src="resources/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
        <script src="resources/js/project1.js"></script>
        <script src="resources/js/common.js"></script>
    </head>
    <body>
    
    
        <div id="SearchForm">
        	<div class="controlsPannel">
			    <input id="Title" type="text" size="25" placeholder="Title"></input>
			    <input id="Genre" type="text" size="25" placeholder="Genre"></input>
			    <input id="DirectorName" type="text" size="25" placeholder="Director"></input>
			    <input id="ActorName" type="text" size="25" placeholder="Actor"></input>
			    <input id="Keyword" type="text" size="25" placeholder="Keyword in Summary"></input>
			    <input style="padding: 0px !important" id="EarliestYear" type="text" size="4" placeholder="Start year"></input>
			    <input style="padding: 0px !important" id="LatestYear" type="text" size="4" placeholder="End year"></input>
			    <button id="searchButton" onclick="retrieveMovieData()  ">Search</button>
			</div>
    
        </div>
        <table style="width:40%;float:left;" class="TableOfContents" id="resultsForm"></table>
        <table style="width:50%;" class="TableOfContents" id="descriptionForm"></table>
    </body>
</html>
