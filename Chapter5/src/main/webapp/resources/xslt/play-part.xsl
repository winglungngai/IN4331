<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
    <table>
      <tr>
        <th width="100px;">Act</th>
		<th>Scene</th>
		<th>Part</th>
      </tr>
      <xsl:for-each select="items/ACT">
			<tr>
				<td colspan="3">
					<a href="#{generate-id()}"><xsl:value-of select="TITLE"/></a>
				</td>
			</tr>
			<xsl:for-each select="SCENE">
			<tr>
				<td></td>
				<td>
					<a>
						<xsl:attribute name="href">#</xsl:attribute>
						<xsl:attribute name="onclick">showScene('<xsl:value-of select="/items/PLAY"/>', '<xsl:value-of select="../TITLE"/>', '<xsl:value-of select="TITLE"/>')</xsl:attribute>
						<xsl:value-of select="TITLE"/>
					</a>
				</td>
				<td>
					<ul>
					<xsl:for-each select="SPEECH">
						<li><xsl:value-of select="LINE"/></li>
					</xsl:for-each>
					</ul>
				</td>
			</tr>
			</xsl:for-each>
      </xsl:for-each>
    </table>
</xsl:template>
</xsl:stylesheet>