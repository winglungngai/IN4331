
var movies = "";


function retrieveMovieData() {
	$('#resultsForm').empty();
	$('#descriptionForm').empty();
	$('#resultsForm').append("waiting for search results....");
	$.post("ajax/SearchMovie", {
		title : $("#Title").val(),
		genre : $("#Genre").val(),
		directorName : $("#DirectorName").val(),
		actorName : $("#ActorName").val(),
		earliestYear : $("#EarliestYear").val(),
		latestYear : $("#LatestYear").val(),
		keyword : $("#Keyword").val()
	}, function(xml) {
		$('#resultsForm').empty();
		displayResult(xml, movieXsl, "#resultsForm");
	});
}

var movieXsl = null;

$(function() {
	
	$("#searchButton" ).button({
	      icons: {
	        primary: "ui-icon-search"
	      },
	      text: false
	    });
	
	$("#EarliestYear" ).spinner({min:1950, max:2010});
	$("#LatestYear" ).spinner({min:1950, max:2010});
	
	$.get("resources/xslt/movies.xsl", function(data) {
		movieXsl = data;
	});
})


function displayDetails(id){
	var clonedContent= $('#'+id).clone();
	$('#descriptionForm').empty().append(clonedContent);
	clonedContent.show();
}
