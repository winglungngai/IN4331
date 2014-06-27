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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
<body>

	<div id="container" style="">

		<div id="menu" style="width: 500px; float: left;">
			<div class="controlsPannel" style="margin-right:20px;">
				<div id="SearchField">
					<input id="KeywordField" type="text" size="25" placeholder="Find Music">
	    			<button id="searchButton" style="height:23px;" onclick="retrieveMovieData()"></button>
				</div>
				<div id="SearchField">
					<input id="KeywordLyricsField" type="text" size="25" placeholder="Find Music By Lyrics">
	    			<button id="searchLyricsButton" style="height:23px;" onclick="retrieveMovieByLyrics()"></button>
				</div>
	
				<div id="UploadField">
					Select a MusicXml file to upload: <br />
					<form action="UploadMusicXMLFile.jsp" method="post"
						enctype="multipart/form-data" acceptcharset="UTF-8">
						<input type="file" name="file" size="25" />  
						<input type="submit" value="Upload File" />
					</form>
				</div>
			</div>
			
			<div id="resultsDiv" class="TableOfContents"></div>
		</div>

		<div id="content" style="height:450px; float: left;"></div>
	</div>
</body>
</html>
