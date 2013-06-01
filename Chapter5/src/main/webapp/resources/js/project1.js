
var movies = "";


function initSearchField()
{
    setSearchFieldBody();
}

function setSearchFieldBody()
{
    var bodyHTMLCode = "";
    bodyHTMLCode += "<h3>Find a movie by any of the following criteria's: </h3>";
    bodyHTMLCode += "<button id=\"SearchButton\" onclick=\"initResultField()  \">Search</button>";
    bodyHTMLCode += "<br>";
    bodyHTMLCode += "<input id=\"Title\" type=\"text\" size=\"25\" placeholder=\"Title\">";
    bodyHTMLCode += "<input id=\"Genre\" type=\"text\" size=\"25\" placeholder=\"Genre\">";
    bodyHTMLCode += "<input id=\"DirectorName\" type=\"text\" size=\"25\" placeholder=\"Director\">";
    bodyHTMLCode += "<input id=\"ActorName\" type=\"text\" size=\"25\" placeholder=\"Actor\">" + "<br>";
    bodyHTMLCode += "<input id=\"EarliestYear\" type=\"text\" size=\"25\" placeholder=\"From ? Year - \">";
    bodyHTMLCode += "<input id=\"LatestYear\" type=\"text\" size=\"25\" placeholder=\"Til ? Year\">";
    bodyHTMLCode += "<input id=\"Keyword\" type=\"text\" size=\"25\" placeholder=\"Keyword in Summary\">";
    
    document.getElementById("SearchField").innerHTML = bodyHTMLCode;
}

function initResultField()
{
    setResultFieldBody();
    retrieveMovieData();
}

function setResultFieldBody()
{
    var bodyHTMLCode = "";
    bodyHTMLCode += "<table class=\"TableOfContents\" id=\"TableOfContents\" summary=\"ChaptersOverview\"></table>";
    bodyHTMLCode += "<span id=\"SearchStatus\">waiting for search results....</span>";
    document.getElementById("EditField").innerHTML = bodyHTMLCode;
}

function retrieveMovieData() {
	$.post("ajax/SearchMovie", {
		title : $("#Title").val(),
		genre : $("#Genre").val(),
		directorName : $("#DirectorName").val(),
		actorName : $("#ActorName").val(),
		earliestYear : $("#EarliestYear").val(),
		latestYear : $("#LatestYear").val(),
		keyword : $("#Keyword").val()
	}, function(xml) {
		displayResult(xml, movieXsl, "#TableOfContents");
		setSearchStatus();
	});
}


function setSearchStatus()
{
    var searchStatus = "";
    searchStatus += "found x results";
    document.getElementById("SearchStatus").innerHTML = searchStatus;

}

var movieXsl = null;

$(function() {
	$.get("resources/xslt/movies.xsl", function(data) {
		movieXsl = data;
	});
})