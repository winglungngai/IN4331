<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<thead>
			<tr>
				<th colspan="4">Movies</th>
			</tr>
		</thead>
		<tbody>
			<xsl:for-each select="movies/movie">
				<tr>
					<td>
						<xsl:value-of select="title" />
					</td>
					<td>
						<xsl:value-of select="year" />
					</td>
					<td>
						<xsl:value-of select="summary" />
					</td>
				</tr>
			</xsl:for-each>
		</tbody>
	</xsl:template>
</xsl:stylesheet>