<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<table>
			<thead>
				<tr>
					<th colspan="4">Movies</th>
				</tr>
			</thead>
			<tbody>
				<xsl:for-each select="movies/movie">
					<tr>
						<td>
							<xsl:value-of select="@id" />
						</td>
						<td>
							<a onclick='displayDetails("{generate-id(.)}")'>
								<xsl:attribute name="href">#</xsl:attribute>
								<xsl:value-of select="title" />
							</a>
						</td>
						<td>
							<xsl:value-of select="year" />
						</td>
						<td id="{generate-id(.)}" style="display:none;">
							<xsl:value-of select="summary" />
						</td>
					</tr>
				</xsl:for-each>
			</tbody>
		</table>
	</xsl:template>
</xsl:stylesheet>