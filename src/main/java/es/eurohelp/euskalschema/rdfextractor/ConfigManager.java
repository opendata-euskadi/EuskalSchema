/**
 * 
 */
package es.eurohelp.euskalschema.rdfextractor;

import java.io.InputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

/**
 * @author Mikel Egaña Aranguren
 *
 */
public class ConfigManager {

	static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);
	private static ConfigManager INSTANCE = null;
	private Map<String, String> configuration = null;
	
	public synchronized static ConfigManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ConfigManager();
			INSTANCE.loadConfigurationFromYAMLFile();
		}
		return INSTANCE;
	}
	
	public void loadConfigurationFromYAMLFile () {
		InputStream io = FileUtils.getInstance().getInputStream("configuration/config.yml");
		Yaml yaml = new Yaml();
		configuration = (Map<String, String>) yaml.load(io);
	}
	
	public String getConfigValue(String property){
		return configuration.get(property);
	}
}
