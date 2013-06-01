<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<table>
			<thead>
				<tr>
					<th colspan="4">Songs</th>
				</tr>
			</thead>
			<tbody>
				<xsl:for-each select="songs/song">
				<a>
						<xsl:attribute name="onClick">displayMusicFile('<xsl:value-of select="fileName"/>')</xsl:attribute>
						
					<tr id="song">
						<td>
						
						<xsl:value-of select="credit[position() = 1]" /> <br></br> [
						<xsl:value-of select="credit[position() = 2]" />
							<xsl:for-each select="credit[position() > 2]">
								 - <xsl:value-of select="." />
							</xsl:for-each> ]
						
						</td>
					</tr>
					</a>
				</xsl:for-each>
			</tbody>
		</table>
	</xsl:template>
</xsl:stylesheet>