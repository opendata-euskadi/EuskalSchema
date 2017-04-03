/**
 * 
 */
package es.eurohelp.euskalschema.rdfextractor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.util.Models;
import org.eclipse.rdf4j.model.util.RDFCollections;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import com.google.common.base.Predicate;

import es.eurohelp.euskalschema.rdfextractor.htmlbuilder.PredicateObject;
import es.eurohelp.euskalschema.rdfextractor.htmlbuilder.ResourceObject;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

/**
 * @author Mikel Egaña Aranguren
 *
 */
public class RdfaManager {

	static final Logger logger = LoggerFactory.getLogger(RdfaManager.class);
	private static RdfaManager INSTANCE = null;
	
	public synchronized static RdfaManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RdfaManager();
		}
		return INSTANCE;
	}
	
	public void createRdfaFromModel(Model model) throws Exception{
		Configuration cfg = new Configuration(new Version(2, 3, 20));

        // Where do we load the templates from:
        //cfg.setClassForTemplateLoading(ExtractorManager.class, "resources");
        //cfg.setDirectoryForTemplateLoading(new File("/src/main/resources"));
        // Some other recommended settings:
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // 2. Proccess template(s)
        //
        // You will do this for several times in typical applications.
        // 2.1. Prepare the template input:

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("title", "EuskalSchema");
        

        List<ResourceObject> resourcesList = new ArrayList<ResourceObject>();
        
        for (Resource resource: model.subjects() /*model.filter(null, RDF.TYPE, SCHEMA.PERSON).subjects()*/) {
        	ResourceObject rObject = new ResourceObject();
        	rObject.setUri(resource.stringValue());
			for (Statement typeStatement: model.filter(resource, null, null)) {
				if (typeStatement.getPredicate().stringValue().contains("#type")){
					rObject.setType(typeStatement.getObject().stringValue());
				}else{
					PredicateObject predicate = new PredicateObject();
					predicate.setHref("");
					if (typeStatement.getPredicate().stringValue().contains("#label")){
						predicate.setStyleClass("h");
						predicate.setValue(typeStatement.getObject().stringValue());
					}else if (typeStatement.getPredicate().stringValue().contains("#comment")){
						predicate.setValue(typeStatement.getObject().stringValue());
					}else if (typeStatement.getPredicate().stringValue().contains("#subClassOf")){
						predicate.setType(PredicateObject.REFERENCE_TYPE);
						predicate.setHref(typeStatement.getObject().toString());
						predicate.setText("Subclass of: ");
						predicate.setValue(FileUtils.getInstance().getUrlLastSegment(typeStatement.getObject().stringValue()));
					}else if (typeStatement.getObject().stringValue().contains("SchemaDotOrgSources#")){
						predicate.setType(PredicateObject.REFERENCE_TYPE);
						predicate.setHref(typeStatement.getObject().toString());
						predicate.setText("Source: ");
						predicate.setValue(FileUtils.getInstance().getUrlLastSegment(typeStatement.getObject().stringValue().split("#")[1]));
					}else if (typeStatement.getPredicate().stringValue().contains("#equivalentClass")){
						predicate.setType(PredicateObject.LINK_TYPE);
						predicate.setHref(typeStatement.getObject().toString());
					}else if (typeStatement.getPredicate().stringValue().contains("domainIncludes")){
						predicate.setType(PredicateObject.REFERENCE_TYPE);
						predicate.setHref(typeStatement.getObject().toString());
						predicate.setText("Domain: ");
						predicate.setValue(FileUtils.getInstance().getUrlLastSegment(typeStatement.getObject().stringValue()));
					}else if (typeStatement.getPredicate().stringValue().contains("rangeIncludes")){
						predicate.setType(PredicateObject.REFERENCE_TYPE);
						predicate.setHref(typeStatement.getObject().toString());
						predicate.setText("Range: ");
						predicate.setValue(FileUtils.getInstance().getUrlLastSegment(typeStatement.getObject().stringValue()));
					}else{
						predicate.setType(PredicateObject.LINK_TYPE);
						predicate.setHref(typeStatement.getObject().toString());
					}
					predicate.setProperty(typeStatement.getPredicate().stringValue());
					rObject.getPredicates().add(predicate);
					Collections.sort(rObject.getPredicates(), new PredicatesComparator());
				}
				
			}
			resourcesList.add(rObject);
		}
        input.put("resources", resourcesList);

        // 2.2. Get the template

        Template template = cfg.getTemplate("/src/main/resources/schema_template.ftl");

        // 2.3. Generate the output

        // Write output to the console
        Writer consoleWriter = new OutputStreamWriter(System.out);
        template.process(input, consoleWriter);

        ConfigManager config = ConfigManager.getInstance();
        String htmlFileName = config.getConfigValue("html-result-file");
        
        // For the sake of example, also write output into a file:
        Writer fileWriter = new FileWriter(new File(htmlFileName));
        try {
                template.process(input, fileWriter);
        } finally {
                fileWriter.close();
        }
	}	
	
	static class PredicatesComparator implements Comparator<PredicateObject> {
		public int compare(PredicateObject p1, PredicateObject p2) {
			if (p1.getProperty().contains("#label")){
				return -1;
			}else if(p1.getProperty().contains("#comment") && !p2.getProperty().contains("#label")){
				return -1;
			}else{
				return 1;
			}
		}
	}
}
