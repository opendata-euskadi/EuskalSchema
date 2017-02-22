/**
 * 
 */
package es.eurohelp.euskalschema.rdfextractor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

/**
 * @author Mikel Egaña Aranguren
 *
 */
public class ExtractorManager {

	static final Logger logger = LoggerFactory.getLogger(ExtractorManager.class);
	private static ExtractorManager INSTANCE = null;
	private String url = null;
	private String sparql = null;
	private String fileName = null;
	
	public synchronized static ExtractorManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ExtractorManager();
		}
		return INSTANCE;
	}
	
	public void loadConfigurationFromYAMLFile () {
		InputStream io = FileUtils.getInstance().getInputStream("configuration/config.yml");
		Yaml yaml = new Yaml();
		Map<String, String> configuration = (Map<String, String>) yaml.load(io);
		url = configuration.get("url");
		fileName = configuration.get("result-file");
		
		InputStream sparqlio = FileUtils.getInstance().getInputStream("configuration/" + configuration.get("sparql"));
		
		try {
			sparql = IOUtils.toString(sparqlio, "UTF-8");
//			logger.info("Loaded SPARQL: " + sparql);
		} catch (IOException e) {
			logger.error("IO exception", e);
		}
	}
	
	public void executeExtraction () {
		RDFStore store = new RDFStore();
		store.startRDFStore();
		store.loadRDFFromURL(url,RDFFormat.TURTLE);
		Model model = store.execGraphQuery(sparql);
		logger.info("Result size: " + model.size());
		try {
			File resultfile = new File(fileName);
			FileOutputStream out = new FileOutputStream(resultfile);
			Rio.write(model, out, RDFFormat.RDFXML);
		} catch (FileNotFoundException e) {
			logger.error("File not found",e);
		}
		
//		store.stopRDFStore();
	}
}
