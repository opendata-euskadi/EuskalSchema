/**
 * 
 */
package es.eurohelp.euskalschema.rdfextractor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParseException;
import org.eclipse.rdf4j.sail.memory.MemoryStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mikel Egaña Aranguren
 *
 */
public class RDFStore {

	static final Logger logger = LoggerFactory.getLogger(RDFStore.class);

	Repository repo;
	RepositoryConnection con;

	public RDFStore() {
		repo = new SailRepository(new MemoryStore());
	}

	public void startRDFStore() {
		repo.initialize();
		con = repo.getConnection();
		con.begin();
	}
	
	public void stopRDFStore() {
		con.close();
		repo.shutDown();
	}

	public void loadRDFFromURL(String rdfurl, RDFFormat format) {
		try {
			URL url = new URL(rdfurl);
			con.add(url, url.toString(), format);
			logger.info("Loaded RDF from " + rdfurl + " in format " + format);
		} catch (MalformedURLException e) {
			logger.error("Malformed URL", e);
			e.printStackTrace();
		} catch (RDFParseException e) {
			logger.error("Malformed RDF", e);
		} catch (RepositoryException e) {
			logger.error("Repository error", e);
		} catch (IOException e) {
			logger.error("IO exception", e);
		}
	}
}
