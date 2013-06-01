<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.commons.fileupload.disk.*"%>
<%@ page import="org.apache.commons.fileupload.servlet.*"%>
<%@ page import="org.apache.commons.io.output.*"%>
<%@ page import="org.wdm.util.*"%>
<html>
	<head>
		<title>MusicXML File Upload</title>
		<script>
			function displayMessage(){
				alert(document.getElementById("message").innerHTML);
				window.location.href = "project3.jsp";
			}
		</script>
	</head>
	<body onload="displayMessage()">
	<div id="message" style="display:none">
<%
	File file;
	int maxFileSize = 10000 * 1024;
	int maxMemSize = 10000 * 1024;
	
	// Verify the content type
	String contentType = request.getContentType();
	if ((contentType.indexOf("multipart/form-data") >= 0)) {
	
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		// Location to save data that is larger than maxMemSize.
		factory.setRepository(new File("c:\\temp"));
	
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);
		try {
			// Parse the request to get file items.
			List fileItems = upload.parseRequest(request);
			// Process the uploaded file items
			Iterator i = fileItems.iterator();
	
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					System.out.println(fi.getString());
	
					String fileName = fi.getName();
	
					int lastIndexOf = fileName.lastIndexOf("\\");
					fileName = (lastIndexOf >= 0) ? fileName.substring(lastIndexOf) : fileName.substring(lastIndexOf + 1);
	
					XMLDatabaseConnector xConnector = new XMLDatabaseConnector();
					String collectionPath = "/db/music";
					boolean succeeded = xConnector.upload(collectionPath,
							fileName, fi.getString());
	
					String successMessage = "File uploaded";
					String failureMessage = "No file uploaded. Make sure valid xml file is uploaded, especially no zip file please";
					out.println((succeeded) ? successMessage : failureMessage);
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	} else {
		out.println("<p>No file uploaded. Empty file received.</p>");
	}
%>
	</div>
	</body>
</html>