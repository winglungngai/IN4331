<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>jQuery UI Autocomplete - Combobox</title>
	<link rel="stylesheet" href="resources/jquery-ui-1.10.3/themes/base/jquery.ui.all.css">
	<script src="resources/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
	<script src="resources/jquery-ui-1.10.3/ui/jquery.ui.core.js"></script>
	<script src="resources/jquery-ui-1.10.3/ui/jquery.ui.widget.js"></script>
	<script src="resources/jquery-ui-1.10.3/ui/jquery.ui.button.js"></script>
	<script src="resources/jquery-ui-1.10.3/ui/jquery.ui.position.js"></script>
	<script src="resources/jquery-ui-1.10.3/ui/jquery.ui.menu.js"></script>
	<script src="resources/jquery-ui-1.10.3/ui/jquery.ui.autocomplete.js"></script>
	<script src="resources/jquery-ui-1.10.3/ui/jquery.ui.tooltip.js"></script>
	<!-- <link rel="stylesheet" href="../demos.css">-->
	<style>
	.custom-combobox {
		position: relative;
		display: inline-block;
	}
	.custom-combobox-toggle {
		position: absolute;
		top: 0;
		bottom: 0;
		margin-left: -1px;
		padding: 0;
		/* support: IE7 */
		*height: 1.7em;
		*top: 0.1em;
	}
	.custom-combobox-input {
		margin: 0;
		padding: 0.3em;
	}
	*{
		font-size:12px;
	}
	.comboboxContainer{
		float:left;
		overflow:hidden;
		padding-right:30px;
	}
	</style>
	
	<style type="text/css">
		@import url("resources/css/style.css");
    </style>
	<script>
	
	var LIST_ACTS_BY_PLAY_URL = "ajax/listActsByPlay";
	var LIST_PLAYS_URL = "ajax/listPlays";
	var LIST_CHARACTERS_BY_PLAY_URL = "ajax/listChatactersByPlay";
	var LIST_SCENES_BY_PLAY_URL = "ajax/listScenesByPlay";
	var LIST_SCENES_BY_PLAY_ACT_URL = "ajax/listScenesByPlayAct";
	var GET_CHARACTER_PART = "ajax/getPartByCharacter";
	var GET_PLAY_SUMMARY = "ajax/getPlaySumary";
	var GET_SCENE_BY_TITLE_PLAY_ACT = "ajax/getSceneByPlayActTitle";
	var GET_PLAY_DETAILS = "ajax/getPlayDetails";
	
	var partXsl = null;
	var summaryXsl = null;
	var sceneXsl = null;
	var playXsl = null;
	(function( $ ) {
		$.widget( "custom.combobox", {
			_create: function() {
				this.wrapper = $( "<span>" )
					.addClass( "custom-combobox" )
					.insertAfter( this.element );

				this.element.hide();
				this._createAutocomplete();
				this._createShowAllButton();
			},

			_createAutocomplete: function() {
				var selected = this.element.children( ":selected" ),
					value = selected.val() ? selected.text() : "";

				this.input = $( "<input>" )
					.appendTo( this.wrapper )
					.val( value )
					.attr( "title", "" )
					.addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" )
					.autocomplete({
						delay: 0,
						minLength: 0,
						autoFocus:true,
						source: $.proxy( this, "_source" )
					})
					.tooltip({
						tooltipClass: "ui-state-highlight"
					});

				this._on( this.input, {
					autocompleteselect: function( event, ui ) {
						ui.item.option.selected = true;
						console.log('______________' + ui.item.value);
						this._trigger( "select", event, {
							item: ui.item.option
						});
					},
					autocompletechange: "_removeIfInvalid"
				});
				
			},

			_createShowAllButton: function() {
				var input = this.input,
					wasOpen = false;

				$( "<a>" )
					.attr( "tabIndex", -1 )
					.attr( "title", "Show All Items" )
					.tooltip()
					.appendTo( this.wrapper )
					.button({
						icons: {
							primary: "ui-icon-triangle-1-s"
						},
						text: false
					})
					.removeClass( "ui-corner-all" )
					.addClass( "custom-combobox-toggle ui-corner-right" )
					.mousedown(function() {
						wasOpen = input.autocomplete( "widget" ).is( ":visible" );
					})
					.click(function() {
						input.focus();

						// Close if already visible
						if ( wasOpen ) {
							return;
						}

						// Pass empty string as value to search for, displaying all results
						input.autocomplete( "search", "" );
					});
			},

			_source: function( request, response ) {
				var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
				response( this.element.children( "option" ).map(function() {
					var text = $( this ).text();
					if ( this.value && ( !request.term || matcher.test(text) ) )
						return {
							label: text,
							value: text,
							option: this
						};
				}) );
			},

			_removeIfInvalid: function( event, ui ) {
				// Selected an item, nothing to do
				if ( ui.item ) {
					return;
				}

				// Search for a match (case-insensitive)
				var value = this.input.val(),
					valueLowerCase = value.toLowerCase(),
					valid = false;
				this.element.children( "option" ).each(function() {
					if ( $( this ).text().toLowerCase() === valueLowerCase ) {
						this.selected = valid = true;
						return false;
					}
				});

				// Found a match, nothing to do
				if ( valid ) {
					return;
				}

				// Remove invalid value
				this.input
					.val( "" )
					.attr( "title", value + " didn't match any item" )
					.tooltip( "open" );
				this.element.val( "" );
				this._delay(function() {
					this.input.tooltip( "close" ).attr( "title", "" );
				}, 2500 );
				this.input.data( "ui-autocomplete" ).term = "";
			},

			_destroy: function() {
				this.wrapper.remove();
				this.element.show();
			}
		});
		
	})( jQuery );
	
	function getEntities(url, data){
		var result = null;
		$.ajax({
			url: url, 
			async: false,
			type:'get',
			contentType: 'application/xml',
			data: data,
			success: function(data) {
				result = data;
			}
		});
		return result;
	}

	function getActors(play){
		return getEntities(LIST_CHARACTERS_BY_PLAY_URL, {play:play});
	}
	
	function getActs(play){
		return getEntities(LIST_ACTS_BY_PLAY_URL, {play:play});
	}
	
	function getScenes(play){
		return getEntities(LIST_SCENES_BY_PLAY_URL, {play:play});
	}
	
	function getScenesByAct(play, act){
		return getEntities(LIST_SCENES_BY_PLAY_ACT_URL, {play:play, act:act});
	}
	
	function getCharacterPart(play, act, scene, character){
		return getEntities(GET_CHARACTER_PART, {play:play, act:act, scene:scene, character:character});
	}
	
	function getPlaySummary(play){
		return getEntities(GET_PLAY_SUMMARY, {play:play});
	}
	
	function getPlayDetails(play){
		return getEntities(GET_PLAY_DETAILS, {play:play});
	}
	
	function getSceneByPlayActTitle(play, act, scene){
		return getEntities(GET_SCENE_BY_TITLE_PLAY_ACT, {play:play, act:act, scene:scene});
	}
	
	$(function() {
		$.get("resources/xslt/play-part.xsl", function(data) {
			partXsl = data;
		});
		$.get("resources/xslt/play-summary.xsl", function(data) {
			summaryXsl = data;
		});
		
		$.get("resources/xslt/play-scene.xsl", function(data) {
			sceneXsl = data;
		});
		
		$.get("resources/xslt/play-details.xsl", function(data) {
			playXsl = data;
		});
		
		$( "#play" ).combobox();
		$( "#act" ).combobox();
		$( "#scene" ).combobox();
		$( "#actor" ).combobox();
		$.get(LIST_PLAYS_URL, function(data) {
			xmlDoc = data;
			$.get("resources/xslt/play-search.xsl", function(data) {
				xslDoc = data;
				displayResult(xmlDoc, xslDoc, 'play');
				
				$( "#play" ).combobox({select: function(event, ui){
					var play = ui.item.value;
					var actors = getActors(play);
					$('#actor').empty();
					$('#actor').combobox({select: function(event, ui){
						console.log(play);
					}});
					displayResult(actors, xslDoc, 'actor');
					
					var acts = getActs(play);
					$('#act').empty();
					$('#act').combobox({select: function(event, ui){
						var act = ui.item.value;
						var play = $('#play').val();
						var scenes = getScenesByAct(play, act);
						$('#scene').empty();
						displayResult(scenes, xslDoc, 'scene');
						$('#scene').combobox({select: function(event, ui){
							console.log(ui.item.value);
						}});
					}});
					displayResult(acts, xslDoc, 'act');
					
					var scenes = getScenes(play);
					$('#scene').empty();
					$('#scene').combobox({select: function(event, ui){
						console.log(ui.item.value);
					}});
					displayResult(scenes, xslDoc, 'scene');
					
					$('#playSummary').empty();
					var summary = getPlaySummary(play);
					displayResult(summary, summaryXsl, 'playSummary');
					showPlay(play);
					
				}});
			});
		});
		
		$('#searchButton').button().click(function() {
			
			var play = $('#play').val();
			var act =$('#act').val();
			var scene = $('#scene').val();
			var character = $('#actor').val();
			$('#displayDetails').empty();
			var part = getCharacterPart(play, act, scene, character);
			displayResult(part, partXsl, 'displayDetails');
		});
	});
	
	function showPart(play, act, scene, character){
		$('#displayDetails').empty();
		var part = getCharacterPart(play, act, scene, character);
		displayResult(part, partXsl, 'displayDetails');
	}
	
	function showScene(play, act, scene){
		$('#displayDetails').empty();
		var scene = getSceneByPlayActTitle(play, act, scene);
		displayResult(scene, sceneXsl, 'displayDetails');
	}
	
	function showPlay(play){
		$('#displayDetails').empty();
		var play = getPlayDetails(play);
		displayResult(play, playXsl, 'displayDetails');
	}
	
	var xmlDoc = null;
	var xslDoc = null;
	function displayResult(xml, xsl, id) {
		// code for IE
		if (window.ActiveXObject) {
			ex = xml.transformNode(xsl);
			document.getElementById(id).innerHTML = ex;
		}
		// code for Mozilla, Firefox, Opera, etc.
		else if (document.implementation
				&& document.implementation.createDocument) {
			xsltProcessor = new XSLTProcessor();
			xsltProcessor.importStylesheet(xsl);
			resultDocument = xsltProcessor.transformToFragment(xml, document);
			document.getElementById(id).appendChild(resultDocument);
		}
	}
	
	</script>
</head>
<body>

	<div class="ui-widget">
		<div class="comboboxContainer">
			<label>Select your preferred play</label>
			<select id="play"></select>
		</div>
		<div class="comboboxContainer">
			<label> actor </label>
			<select id="actor"></select>
		</div>
		<div class="comboboxContainer">
			<label> act </label>
			<select id="act"></select>
		</div>
		<div class="comboboxContainer">
			<label> and scene </label>
			<select id="scene"></select>
		</div>
		
		<div class="comboboxContainer">
			<input id="searchButton" type="button" value="Search"></input>
		</div>
	</div>
	<div class="TableOfContents" id="playSummary" style="clear:both;float:left;width:49%;"></div>
	<div class="TableOfContents" id="displayDetails" style="float:left;width:49%;"></div>
	
</body>
</html>