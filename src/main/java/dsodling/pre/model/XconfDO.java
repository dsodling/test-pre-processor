package dsodling.pre.model;

/**
 *
 * @author Daniel Södling (daniel.sodling@pdsvision.se)
 */
public class XconfDO {

    public static String VALUE = "value";
    public static String DEFAULT = "default";
    public static String DATA_UTILITY = "dataUtility";
    public static String UI_COMPONENT_VALIDATOR = "uiComponentValidator";
    
    private String name;
    private String defaultValue;
    private String value;
    private String targetFile = "codebase/wt.properties";
    private boolean overridable = true;
    private String multivalued;
    private String type = VALUE;
    private String description;
    
    public static XconfDO newXconfModel(String name, String value) {
        return new XconfDO(name, value);
    }
    
    public static XconfDO newXconfModel(String name, String value, String targetFile) {
        return new XconfDO(name, value, targetFile);
    }
    
    public XconfDO(String name, String value) {
        this.name = name;
        this.value = value;
    }
    
    public XconfDO(String name, String value, String targetFile) {
        this(name, value);
        this.targetFile = targetFile;
    }
    
    public XconfDO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getMultivalued() {
        return multivalued;
    }

    public void setMultivalued(String multivalued) {
        this.multivalued = multivalued;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOverridable() {
        return overridable;
    }

    public void setOverridable(boolean overridable) {
        this.overridable = overridable;
    }

    public String getTargetFile() {
        return targetFile;
    }

    public void setTargetFile(String targetFile) {
        this.targetFile = targetFile;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Property{" + "name=" + name + ", defaultValue=" + defaultValue + ", value=" + value + ", targetFile=" + targetFile + ", overridable=" + overridable + ", multivalued=" + multivalued + '}';
    }
    
    /**
     * Constructs the entry for the xconf file.
     * 
     * @return 
     */
    public String toXML() {
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("<Property name=\"").append(name).append("\"");
        if (defaultValue != null && !defaultValue.isEmpty()) {
            sb.append(" default=\"").append(defaultValue).append("\"");
        } else {
            sb.append(" value=\"").append(value).append("\"");
        }
        sb.append(" overridable=\"").append(overridable).append("\"");
        sb.append(" targetFile=\"").append(targetFile).append("\"");
        if (multivalued != null && !multivalued.isEmpty()) {
            sb.append(" multivalued=\"").append(multivalued).append("\"");
        }
        sb.append(" />");
        
        return sb.toString();
    }
    
}
