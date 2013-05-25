
var movies = "";

initSearchField();

function initSearchField()
{
    setSearchFieldBody();
}

function setSearchFieldBody()
{
    var bodyHTMLCode = "";
    bodyHTMLCode += "Find a movie by any of the following criteria's: ";
    bodyHTMLCode += "<input id=\"Title\" type=\"text\" size=\"25\" placeholder=\"Title of the Movie\">";
    bodyHTMLCode += "<input id=\"Genre\" type=\"text\" size=\"25\" placeholder=\"Genre\">";
    bodyHTMLCode += "<input id=\"Director\" type=\"text\" size=\"25\" placeholder=\"Director\">";
    bodyHTMLCode += "<input id=\"Director\" type=\"text\" size=\"25\" placeholder=\"Actor\">";
    bodyHTMLCode += "<input id=\"EarliestYear\" type=\"text\" size=\"25\" placeholder=\"EarliestYear\">";
    bodyHTMLCode += "<input id=\"LatestYear\" type=\"text\" size=\"25\" placeholder=\"LatestYear\">";
    bodyHTMLCode += "<input id=\"Keyword\" type=\"text\" size=\"25\" placeholder=\"Keyword in Summary\">";
    bodyHTMLCode += "<button id=\"SearchButton\" onclick=\"initResultField()  \">Search</button>";
    document.getElementById("SearchField").innerHTML = bodyHTMLCode;
}

function initResultField()
{
    setResultFieldBody();
    setSearchStatus();
    setMovies();
    retrieveMovieData();
}

function setResultFieldBody()
{
    var bodyHTMLCode = "";
    bodyHTMLCode += "<h3><span id=\"SearchStatus\">Lalalalaal 1 results</span></h3>";
    bodyHTMLCode += "<table id=\"TableOfContents\" summary=\"ChaptersOverview\"></table>";
    document.getElementById("EditField").innerHTML = bodyHTMLCode;
}

function retrieveMovieData()
{

    $.get("response.html", function(data) {
        movies = data;
        setMovies();
    });
}

function setSearchStatus()
{
    var searchStatus = "";
    searchStatus += "waiting for search results";
    document.getElementById("SearchStatus").innerHTML = searchStatus;

}

function setMovies()
{
    var TOCHTMLCode = "";
    TOCHTMLCode += "<thead><tr><th colspan=\"4\">Movies</th></tr></thead>";
    TOCHTMLCode += "<tbody>";
    TOCHTMLCode += "<tr><td>" + movies + "</td></tr>";
    TOCHTMLCode += "</tbody>";
    document.getElementById("TableOfContents").innerHTML = TOCHTMLCode;
}






