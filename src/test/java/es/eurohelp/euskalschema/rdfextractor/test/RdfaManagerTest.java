/**
 * 
 */
package es.eurohelp.euskalschema.rdfextractor.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.junit.Test;

import es.eurohelp.euskalschema.rdfextractor.ConfigManager;
import es.eurohelp.euskalschema.rdfextractor.FileUtils;
import es.eurohelp.euskalschema.rdfextractor.RDFStore;
import es.eurohelp.euskalschema.rdfextractor.RdfaManager;

/**
 * @author Mikel Egaña Aranguren
 *
 */
public class RdfaManagerTest {

	/**
	 * Test method for {@link es.eurohelp.euskalschema.rdfextractor.ExtractorManager#getInstance()}.
	 */
	@Test
	public final void testGetInstance() {
//		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link es.eurohelp.euskalschema.rdfextractor.ExtractorManager#executeExtraction(java.lang.String, org.eclipse.rdf4j.rio.RDFFormat)}.
	 */
	@Test
	public final void testExecuteExtraction() {
//		fail("Not yet implemented"); // TODO
		
		
		ConfigManager config = ConfigManager.getInstance();
		config.loadConfigurationFromYAMLFile();
		
		config.getConfigValue("");
		
		RDFStore store = new RDFStore();
		store.startRDFStore();
		File resultfile = new File(config.getConfigValue("result-file"));
		store.loadRDFFromFile(resultfile,RDFFormat.RDFXML);
		
		InputStream sparqlio = FileUtils.getInstance().getInputStream("configuration/" + config.getConfigValue("sparql"));
		String sparql = null;
		try {
			sparql = IOUtils.toString(sparqlio, "UTF-8");
//			logger.info("Loaded SPARQL: " + sparql);
		} catch (IOException e) {
			
		}
		
		
		Model model = store.execGraphQuery(sparql);
		RdfaManager manager = RdfaManager.getInstance();
		try {
			manager.createRdfaFromModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
