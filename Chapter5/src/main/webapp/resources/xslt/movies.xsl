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
						<span id="{generate-id(.)}" style="display:none;">
							<table class="TableOfContents">
								<tr><td>Movie title:</td><td><xsl:value-of select="title" /></td></tr>
								<tr><td>Summary:</td><td><xsl:value-of select="summary" /></td></tr>
								<tr><td>Genre:</td><td><xsl:value-of select="genre" /></td></tr>
								<tr><td>Year:</td><td><xsl:value-of select="year" /></td></tr>
								<tr><td>Country:</td><td><xsl:value-of select="country" /></td></tr>
								<tr><td>Director:</td><td><xsl:apply-templates select="director"/></td></tr>
								<tr><td>Actors:</td><td><xsl:apply-templates select="actors"/></td></tr>
							</table>
						</span>
					</tr>
				</xsl:for-each>
			</tbody>
		</table>
	</xsl:template>
	
	<xsl:template match="director">
		<ul>
			<xsl:apply-templates/>
		</ul>
	</xsl:template>
	
	<xsl:template match="actors">
		<ul>
			<xsl:apply-templates/>
		</ul>
	</xsl:template>
	
	<xsl:template match="actor">
		<li><xsl:value-of select="concat(first_name,' ',last_name, ' (',birth_date,')')"/></li>
	</xsl:template>
</xsl:stylesheet>