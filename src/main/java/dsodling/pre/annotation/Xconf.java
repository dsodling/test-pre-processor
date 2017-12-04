package dsodling.pre.annotation;

/**
 *
 * @author Daniel Södling (daniel.sodling@pdsvision.se)
 */
public @interface Xconf {
    
    boolean overridable() default true;
    String targetFile() default "codebase/wt.properties";
    String defaultValue() default "";
    String value() default "";
    String multivalued() default "";
    String serviceClass() default "";
    String selector() default "";
    String description() default "";
    
}
