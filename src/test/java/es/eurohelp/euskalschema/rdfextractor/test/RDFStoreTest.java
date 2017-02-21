/**
 * 
 */
package es.eurohelp.euskalschema.rdfextractor.test;

import static org.junit.Assert.*;

import org.eclipse.rdf4j.rio.RDFFormat;
import org.junit.Test;

import es.eurohelp.euskalschema.rdfextractor.RDFStore;

/**
 * @author Mikel Egaña Aranguren
 *
 */
public class RDFStoreTest {

	/**
	 * Test method for {@link es.eurohelp.euskalschema.rdfextractor.RDFStore#loadRDFFromURL(java.lang.String)}.
	 */
	@Test
	public final void testLoadRDFFromURL() {
		RDFStore store = new RDFStore();
		store.startRDFStore();
		store.loadRDFFromURL("http://schemaorgae.appspot.com/version/latest/schema.ttl",RDFFormat.TURTLE);
	}

}
