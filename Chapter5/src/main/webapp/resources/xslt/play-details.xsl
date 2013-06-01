<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<table>
			<xsl:apply-templates/>
		</table>
	</xsl:template>
	<xsl:template match="PERSONAE">
			<tr>
				<td><b>Characters</b></td>
				<td><b>Group</b></td>
			</tr>
			<xsl:apply-templates select="ACT_NO|SCENE_NO|PERSONA|PGROUP|SCNDESCR"/>
	</xsl:template>
	
	<xsl:template match="PERSONAE/PERSONA">
		<tr>
			<td><xsl:apply-templates/></td>
			<td></td>
		</tr>
	</xsl:template>
	
	<xsl:template match="PGROUP">
		<tr>
			<td>
				<xsl:for-each select="PERSONA">
					<xsl:value-of select="text()"/><br/>
				</xsl:for-each>
			</td>
			<td>
				<xsl:value-of select="GRPDESCR"/>
			</td>
		</tr>
	</xsl:template>
	<xsl:template match="SCNDESCR">
		<tr>
			<td><b>SCNDESCR</b></td>
			<td><xsl:value-of select="."/></td>
		</tr>
	</xsl:template>
	<xsl:template match="ACT_NO">
		<tr>
			<td><b>Number of acts:</b></td>
			<td><xsl:value-of select="."/></td>
		</tr>
	</xsl:template>
	<xsl:template match="SCENE_NO">
		<tr>
			<td><b>Number of scenes:</b></td>
			<td><xsl:value-of select="."/></td>
		</tr>
	</xsl:template>
</xsl:stylesheet>