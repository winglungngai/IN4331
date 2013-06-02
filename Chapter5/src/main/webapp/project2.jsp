<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Project 2</title>
	<link rel="stylesheet" href="resources/jquery-ui-1.10.3/themes/base/jquery.ui.all.css">
	<script src="resources/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
	<script src="resources/jquery-ui-1.10.3/ui/jquery.ui.core.js"></script>
	<script src="resources/jquery-ui-1.10.3/ui/jquery.ui.widget.js"></script>
	<script src="resources/jquery-ui-1.10.3/ui/jquery.ui.button.js"></script>
	<script src="resources/jquery-ui-1.10.3/ui/jquery.ui.position.js"></script>
	<script src="resources/jquery-ui-1.10.3/ui/jquery.ui.menu.js"></script>
	<script src="resources/jquery-ui-1.10.3/ui/jquery.ui.autocomplete.js"></script>
	<script src="resources/jquery-ui-1.10.3/ui/jquery.ui.tooltip.js"></script>
	<script src="resources/js/project2.js"></script>
	<script src="resources/js/common.js"></script>
	
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
</head>
<body>

	<div class="ui-widget controlsPannel" style="overflow:hidden;">
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
			<button id="searchButton" type="button" style="height:23px;"></button>
		</div>
	</div>
	<div class="TableOfContents" id="playSummary" style="clear:both;float:left;width:49%;"></div>
	<div class="TableOfContents" id="displayDetails" style="float:left;width:49%;"></div>
	
</body>
</html>