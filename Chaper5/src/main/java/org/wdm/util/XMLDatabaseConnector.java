package org.wdm.util;

import java.util.ArrayList;

import org.exist.xmldb.XQueryService;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;

/**
 * XMLDatabaseConnector connects to the XMLDatabase and executes XQuery command.
 * @author Wing
 *
 */
public class XMLDatabaseConnector {
	protected String DRIVER = "org.exist.xmldb.DatabaseImpl";
	protected String URI;
	
	/**
	 * Test
	 */
	public static void main(String[] args) throws Exception {
		XMLDatabaseConnector xConnector = new XMLDatabaseConnector();
		String collectionPath = "/db/movies";
		String xQuery = "for $x in doc('/movies/movies.xml')//title " + "return data($x)";
		ArrayList<String> resultSet = xConnector.read(collectionPath, xQuery);
		System.out.println(resultSet.toString());
	}

	/**
	 * XMLDatabaseConnector connects to the XMLDatabase and executes XQuery command.
	 */
	public XMLDatabaseConnector(String URI)
	{
		setURI(URI);
	}

	/**
	 * XMLDatabaseConnector connects to the XMLDatabase and executes XQuery command.
	 */
	public XMLDatabaseConnector()
	{
		setURI("xmldb:exist://localhost:8080/exist/xmlrpc");
	}

	/**
	 * Execute XQuery Command
	 */
	public void execute(String collectionPath, String xQuery)
	{
		try {

			// initialize database driver
			Class cl = Class.forName(DRIVER);
			Database database = (Database) cl.newInstance();
			DatabaseManager.registerDatabase(database);

			// get the collection
			Collection col = DatabaseManager.getCollection(URI + collectionPath);

			// query a document
			System.out.println("Execute xQuery = \n" + xQuery + "\n");

			// Instantiate a XQuery service
			XQueryService service = (XQueryService) col.getService("XQueryService", "1.0");
			service.setProperty("indent", "yes");

			// Execute the query, print the result
			ResourceSet result = service.query(xQuery);
			ResourceIterator i = result.getIterator();
			while (i.hasMoreResources()) {
				Resource r = i.nextResource();
				System.out.println((String) r.getContent());
			}
		} catch(ClassNotFoundException | XMLDBException | InstantiationException | IllegalAccessException e)
		{
			System.out.println("Error found during connection to XMLDatabase");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Read XMLfile using XQuery Command
	 */
	public ArrayList<String> read(String collectionPath, String xQuery)
	{
		ArrayList<String> resultSet = new ArrayList<String>();
		
		try {

			// initialize database driver
			Class cl = Class.forName(DRIVER);
			Database database = (Database) cl.newInstance();
			DatabaseManager.registerDatabase(database);

			// get the collection
			Collection col = DatabaseManager.getCollection(URI + collectionPath);

			// query a document
			System.out.println("Execute xQuery = \n" + xQuery + "\n");

			// Instantiate a XQuery service
			XQueryService service = (XQueryService) col.getService("XQueryService", "1.0");
			service.setProperty("indent", "yes");

			// Execute the query, print the result
			ResourceSet result = service.query(xQuery);
			ResourceIterator i = result.getIterator();
			while (i.hasMoreResources()) {
				Resource r = i.nextResource();
				resultSet.add((String) r.getContent());
			}
		} catch(ClassNotFoundException | XMLDBException | InstantiationException | IllegalAccessException e)
		{
			System.out.println("Error found during connection to XMLDatabase");
			e.printStackTrace();
		}
		
		return resultSet;
		
	}
	
	public String getDRIVER() {
		return DRIVER;
	}

	public void setDRIVER(String dRIVER) {
		DRIVER = dRIVER;
	}

	public String getURI() {
		return URI;
	}

	public void setURI(String uRI) {
		URI = uRI;
	}
}
