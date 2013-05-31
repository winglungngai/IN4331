package org.wdm.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
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
		String collectionPath = "/db/music";
		xConnector.upload(collectionPath, "ohmygod3", "<omg></omg>");
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
	 * Upload files to database
	 */
	public boolean upload(String collectionPath, String docName, String xml)
	{
		boolean succeeded = false;
        try {
	        	
			String uri = "http://localhost:8080/exist/xmlrpc";
			 
	        XmlRpcClient client = new XmlRpcClient();
	        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
	
			config.setServerURL(new URL(uri));
	        config.setBasicUserName("admin");
	        config.setBasicPassword("admin");
	        client.setConfig(config);
	        
			// set parameters for XML-RPC call
			Vector<Object> params = new Vector<Object>();
			params.addElement(xml);
			params.addElement(collectionPath + "/" + docName);
			params.addElement(new Integer(1));
	
			// execute the call
			succeeded = (Boolean)client.execute("parse", params);
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return succeeded;
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
