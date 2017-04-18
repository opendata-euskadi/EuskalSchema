/**
 * 
 */
package es.eurohelp.euskalschema.rdfextractor.test;

import static org.junit.Assert.*;

import org.eclipse.rdf4j.rio.RDFFormat;
import org.junit.Test;

import es.eurohelp.euskalschema.rdfextractor.ExtractorManager;

/**
 * @author Mikel Egaña Aranguren
 *
 */
public class ExtractorManagerTest {

	/**
	 * Test method for {@link es.eurohelp.euskalschema.rdfextractor.ExtractorManager#getInstance()}.
	 */
	@Test
	public final void testGetInstance() {
//		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link es.eurohelp.euskalschema.rdfextractor.ExtractorManager#loadConfigurationFromYAMLFile()}.
	 */
	@Test
	public final void testLoadConfigurationFromYAMLFile() {
//		fail("Not yet implemented"); // TODO
//		assertEquals(, ); // TODO
		
		ExtractorManager.getInstance().loadConfigurationFromYAMLFile();
	}

	/**
	 * Test method for {@link es.eurohelp.euskalschema.rdfextractor.ExtractorManager#executeExtraction(java.lang.String, org.eclipse.rdf4j.rio.RDFFormat)}.
	 */
	@Test
	public final void testExecuteExtraction() {
//		fail("Not yet implemented"); // TODO
		ExtractorManager manager = ExtractorManager.getInstance();
		manager.loadConfigurationFromYAMLFile();
		manager.executeExtraction();
	}

}
