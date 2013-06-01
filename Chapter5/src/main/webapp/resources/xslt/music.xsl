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
						
					<tr id="song">
						<td>
							<a>
								<xsl:attribute name="style">display:block</xsl:attribute>
								<xsl:attribute name="onClick">displayMusicFile('<xsl:value-of select="fileName"/>')</xsl:attribute>
								<xsl:value-of select="credit[position() = 1]" /> <br></br> [
								<xsl:value-of select="credit[position() = 2]" />
									<xsl:for-each select="credit[position() > 2]">
										 - <xsl:value-of select="." />
								</xsl:for-each> ]
							</a>
						</td>
					</tr>
				</xsl:for-each>
			</tbody>
		</table>
	</xsl:template>
</xsl:stylesheet>