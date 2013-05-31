<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <style type="text/css">
            @import url("resources/css/style.css");
         </style>
        <title>Music Search Engine</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
		<h3>File Upload:</h3>
		Select a file to upload: <br />
		<form action="UploadMusicXMLFile.jsp" method="post"
		                        enctype="multipart/form-data">
		<input type="file" name="file" size="50" />
		<br />
		<input type="submit" value="Upload File" />
		</form>
        <div id="SearchField"></div>
        <div id="EditField"></div>
        <!-- <script src="resources/js/yui-min.js"></script>-->
        <script src="resources/js/project3.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    </body>
</html>