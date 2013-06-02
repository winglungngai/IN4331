
var movies = "";

function displayMusicFile(fileName)
{
	 var bodyHTMLCode = "";
	 bodyHTMLCode += "<object classid=\"CLSID:07000E2B-6AAD-497D-8E5B-5976560AD429\" border=\"0\" height=\"650\" width=\"900\">"
		 bodyHTMLCode += "<param name=\"src\" value=\"mymusic.mus\" />"
		 bodyHTMLCode += "<param name=\"width\" value=\"300\" />"
		 bodyHTMLCode += "<param name=\"height\" value=\"400\" />"
		 bodyHTMLCode += "<param name=\"type\" value=\"application/x-myriad-music\" />"
		 bodyHTMLCode += "<param name=\"pluginspage\" value=\"http://www.myriad-online.com/cgi-bin/mmplug.pl\" />"
		 bodyHTMLCode += "<embed src=\"ajax/DownloadMusicXMLFile.xml?fileName=" + fileName + "\""
		 bodyHTMLCode += "width=600 height=450 type=\"application/x-myriad-music\" AUTOPLAY=ON"
		 bodyHTMLCode += "COLOR_THEME=SNOWBAL TITLE=OFF MIXER=OFF TEMPO=OFF PRINT=OFF SAVE=OFF"
		 bodyHTMLCode += "ZOOM=OFF TRANSP=OFF DOCINFO=OFF FORWARD=OFF BACKWARD=OFF STOP=OFF"
		 bodyHTMLCode += "CHANGE_VOLUME=OFF FULLSCREEN=OFF DISPLAY_METRONOME=OFF"
		 bodyHTMLCode += "ALLOW_VIEWS=OFF SING=OFF TWO_PAGES=ON"
		 bodyHTMLCode += "pluginspage=\"http://www.myriad-online.com/cgi-bin/mmplug.pl\">"
		 bodyHTMLCode += "</embed>"
		 bodyHTMLCode += "</object>"
	$('#content').empty().append(bodyHTMLCode);
}

$(function() {
    
    $("#searchButton" ).button({
	      icons: {
	        primary: "ui-icon-search"
	      },
	      text: false
	    });
})


function retrieveMovieData() {
	$.post("ajax/SearchMusic", {
		creditWords : $("#KeywordField").val()
	}, function(xml) {
		displayResult(xml, musicXsl, "#resultsDiv");
	});
}

var musicXsl = null;

$(function() {
	$.get("resources/xslt/music.xsl", function(data) {
		musicXsl = data;
	});
})