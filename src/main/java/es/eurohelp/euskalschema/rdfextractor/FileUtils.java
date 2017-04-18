package es.eurohelp.euskalschema.rdfextractor;

import java.io.InputStream;


/**
 * Contains utilities for workings with files.
 *
 * @author acarbajo
 *         Created at 14 de oct. de 2016
 */
public class FileUtils {

	private static FileUtils INSTANCE = null;

	/**
	 * Private constructor for FileUtils
	 * 
	 */
	private FileUtils() {
	}

	/**
	 * Retrieves the only instance of this Singleton class.
	 * 
	 * @return the FileUtils instance.
	 *
	 * @author acarbajo
	 */
	public synchronized static FileUtils getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new FileUtils();
		}
		return INSTANCE;
	}

	/**
	 * Returns an input stream for reading the specified resource.
	 * 
	 * @param name
	 *            the resource name, e.g. configuration/aldapa-default-configuration.yml
	 *            
	 * @return an input stream for reading the resource
	 *
	 * @author acarbajo
	 */
	
	public InputStream getInputStream(String name) {
		return FileUtils.class.getClassLoader().getResourceAsStream(name);
	}
	
	/**
	 * Returns the last segment of an URI, URL or Path.
	 * 
	 * @param url
	 *            the url, path or uri, e.g. http://schema.org/Thing
	 *            
	 * @return a String containing last segment of an URI, URL or Path.
	 *
	 * @author grozadilla 
	 */
	public String getUrlLastSegment(String url){
		String[] segments = url.split("/");
		return segments[segments.length-1];
	}
	
	
}
