<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
			<option></option>
			<xsl:for-each select="items/TITLE">
				<option value="{.}"><xsl:value-of select="." /></option>
			</xsl:for-each>
			<xsl:for-each select="items/PERSONA/SPEAKER">
				<option value="{.}"><xsl:value-of select="." /></option>
			</xsl:for-each>
			<xsl:for-each select="items/ACT/TITLE">
				<option value="{.}"><xsl:value-of select="." /></option>
			</xsl:for-each>
			<xsl:for-each select="items/SCENE/TITLE">
				<option value="{.}"><xsl:value-of select="." /></option>
			</xsl:for-each>
	</xsl:template>
	
</xsl:stylesheet>