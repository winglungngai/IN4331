<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<ul class="level_0">
			<xsl:for-each select="items/TITLE">
				<li>
					<span>+</span><span><xsl:value-of select="." /></span>
				</li>
			</xsl:for-each>
		</ul>
	</xsl:template>
	
</xsl:stylesheet>