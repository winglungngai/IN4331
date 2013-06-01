<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
    <head>
        <style type="text/css">
            @import url("resources/css/style.css");
         </style>
        <title>Music Search Engine</title>
        <script src="resources/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
        <script src="resources/js/common.js"></script>
        <script src="resources/js/project3.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
<body onload="initSearchField()">

	<div id="container" style="">

		<div id="header" style="background-color: #00AAFF;">
			<h1 style="margin-bottom: 0;">Project 3 - MusicXML</h1>
		</div>

		<div id="menu" style="height: 450px; width: 300px; float: left;">
			<div id="SearchField"></div>
			<div id="EditField"></div>
			<div id="UploadField">
				Select a MusicXml file to upload: <br />
				<form action="UploadMusicXMLFile.jsp" method="post"
					enctype="multipart/form-data" acceptcharset="UTF-8">
					<input type="file" name="file" size="25" /> <br /> <input
						type="submit" value="Upload File" />
				</form>
			</div>
		</div>

		<div id="content" style="height:450px; float: left;">
			Content goes here</div>

		<div id="footer"
			style="background-color: #00AAFF; clear: both; text-align: center;">
			Copyright www.localhost.com</div>

	</div>
</body>
</html>
