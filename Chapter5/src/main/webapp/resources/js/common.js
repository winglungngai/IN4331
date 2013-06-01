function displayResult(xml, xsl, selector) {
	// code for IE
	if (window.ActiveXObject) {
		ex = xml.transformNode(xsl);
		$(selector).append(ex);
	}
	// code for Mozilla, Firefox, Opera, etc.
	else if (document.implementation
			&& document.implementation.createDocument) {
		xsltProcessor = new XSLTProcessor();
		xsltProcessor.importStylesheet(xsl);
		resultDocument = xsltProcessor.transformToFragment(xml, document);
		$(selector).append(resultDocument);
	}
}