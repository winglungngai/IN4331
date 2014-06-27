<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
    <head>
        <style type="text/css">
            @import url("resources/css/style.css");
         </style>
        <title>Music Search Engine</title>
        <link rel="stylesheet" href="resources/jquery-ui-1.10.3/themes/base/jquery.ui.all.css">
        <link rel="stylesheet" type="text/css" href="resources/css/piano.css">
        <script src="resources/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
		<script src="resources/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
        <script src="resources/js/common.js"></script>
        <script src="resources/js/project3.js"></script>
        <script src="resources//js/audio.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
<body>

	<div id="container" style="width:100%;">

		<div id="menu" style="width: 500px; float: left;">			
			<div id="resultsDiv" class="TableOfContents"></div>
		</div>

		<div id="content" style="height:450px;float:left;"></div>
		<div id="piano">
			<textarea col="100" id="pianoNotes"></textarea>
			<button id="searchPianoButton" style="height:23px;" onclick="searchByNotes()"></button>
		</div>
		<script src="resources//js/piano.js"></script>
	</div>
</body>
</html>
