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
	 * Test method for
	 * {@link es.eurohelp.euskalschema.rdfextractor.ExtractorManager#getInstance()}
	 * .
	 */
	@Test
	public final void testGetInstance() {
		// fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link es.eurohelp.euskalschema.rdfextractor.ExtractorManager#executeExtraction(java.lang.String, org.eclipse.rdf4j.rio.RDFFormat)}
	 * .
	 * @throws Exception 
	 */
	@Test
	public final void testExecuteExtraction() throws Exception {

		// Configurar 
		ConfigManager config = ConfigManager.getInstance();
		config.loadConfigurationFromYAMLFile();

		// Cargar Schema
		RDFStore store = new RDFStore();
		store.startRDFStore();
		File schemafile = new File(config.getConfigValue("schema-file"));
		store.loadRDFFromFile(schemafile, RDFFormat.TURTLE);

		// Ejecutar SPARQL
		InputStream sparqlio = FileUtils.getInstance().getInputStream("configuration/" + config.getConfigValue("sparql"));
		String sparql = null;
		sparql = IOUtils.toString(sparqlio, "UTF-8");
		Model model = store.execGraphQuery(sparql);
		
		
		// Convertir resultado de SPARQL a RDFa
		RdfaManager manager = RdfaManager.getInstance();
		manager.createRdfaFromModel(model);
	}
}
