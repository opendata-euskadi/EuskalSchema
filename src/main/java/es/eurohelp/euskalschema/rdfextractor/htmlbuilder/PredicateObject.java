package es.eurohelp.euskalschema.rdfextractor.htmlbuilder;

public class PredicateObject {
	
	public static final String PROPERTY_TYPE = "1";
	public static final String LINK_TYPE = "2";
	public static final String REFERENCE_TYPE = "3";
	
    private String property;
    private String text;
    private String value;
    private String href;	
    private String styleClass =  "";
    private String type =  PredicateObject.PROPERTY_TYPE;

    public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public PredicateObject() {
    }

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
    
   
}
