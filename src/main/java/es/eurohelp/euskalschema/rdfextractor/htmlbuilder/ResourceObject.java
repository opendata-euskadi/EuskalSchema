package es.eurohelp.euskalschema.rdfextractor.htmlbuilder;

import java.util.ArrayList;
import java.util.List;

public class ResourceObject {
	private String type;
    private String uri;
    
    public List<PredicateObject> getPredicates() {
		return predicates;
	}

	public void setPredicates(List<PredicateObject> predicates) {
		this.predicates = predicates;
	}

	private List<PredicateObject> predicates = new ArrayList<PredicateObject>();

    public ResourceObject() {
        
    }
    
    public void setType(String type) {
		this.type = type;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public ResourceObject(String type, String uri) {
            this.type = type;
            this.uri = uri;
    }

    public String getType() {
            return type;
    }

    public String getUri() {
            return uri;
    }
}
