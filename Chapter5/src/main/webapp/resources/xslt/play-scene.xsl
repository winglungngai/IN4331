<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:template match="SCENE">
        <h3>
            <a>
                <xsl:attribute name="name"><xsl:value-of select="generate-id()"/></xsl:attribute>
                <xsl:value-of select="TITLE"/>            
            </a>
        </h3>
        <table border="0">
        	<xsl:apply-templates select="SPEECH|STAGEDIR"/>
        </table>
    </xsl:template>
    <xsl:template match="SPEECH">
        <tr>
            <td width="20%" valign="top">
                <div><xsl:apply-templates select="SPEAKER"/></div>
            </td>
            <td width="80%" valign="top">
                <verse>
                    <xsl:apply-templates select="LINE|STAGEDIR"/>
                </verse>
            </td>
        </tr>
    </xsl:template>
    <xsl:template match="SPEAKER">
        <a>
        	<xsl:attribute name="href">#</xsl:attribute>
        	<xsl:attribute name="onClick">showPart('<xsl:value-of select="/items/PLAY/text()"/>', '','', '<xsl:value-of select="text()"/>')</xsl:attribute>
        	<xsl:value-of select="text()"/>
        </a>
        <br/>
    </xsl:template>
    
    <xsl:template match="LINE">
        <xsl:apply-templates/><br/>
    </xsl:template>
    
    <xsl:template match="LINE/STAGEDIR">[<b>
            <xsl:apply-templates/>
        </b>]</xsl:template>
    <xsl:template match="SCENE/STAGEDIR">
        <tr>
            <td colspan="3">
                <b>
                    <xsl:apply-templates/>
                </b>
            </td>
        </tr>
    </xsl:template>
</xsl:stylesheet>