package dsodling.pre;

import dsodling.pre.annotation.Xconf;
import dsodling.pre.model.XconfDO;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

/**
 *
 * @author Daniel Södling (daniel.sodling@pdsvision.se)
 */
@SupportedAnnotationTypes("dsodling.pre.annotation.*")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class Processor extends AbstractProcessor {

    private final List<XconfDO> xconfs = new ArrayList<XconfDO>();
    private final StandardLocation OUTPUT = StandardLocation.SOURCE_OUTPUT;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        
        FileObject fo;
        StringBuilder line;
        Writer writer;
        MavenUtil util = MavenUtil.newInstance();
        
        System.out.println("--- Running pre-processor in " + util.getArtifactId() + " ---");
        
        if (annotations.isEmpty() == false) {
            processXconfs(env);
            try {
                if (xconfs.size() > 0) {
                    fo = processingEnv.getFiler().createResource(OUTPUT, "", "xconf.properties");
                    writer = fo.openWriter();
                    for (XconfDO xconf : xconfs) {
                        line = new StringBuilder();
                        line.append(xconf.getName()).append("=").append(xconf.getValue());
                        writer.write(line.toString());
                    }
                    writer.close();
                }
            } catch (IOException ex) {
                throw new RuntimeException("Was unable to process annotations", ex);
            }

        }

        return true;
    }

    
     
    private void processXconfs(RoundEnvironment env) {

        Xconf prop;
        XconfDO model;
        for (VariableElement ve : ElementFilter.fieldsIn(env.getElementsAnnotatedWith(Xconf.class))) {
            prop = ve.getAnnotation(Xconf.class);
            model = newXconf(ve.getConstantValue().toString(), prop.value());
            model.setTargetFile(prop.targetFile());
            model.setDescription(prop.description());
            xconfs.add(model);
        }

    }

    private XconfDO newXconf(String name, Object value) {
        return XconfDO.newXconfModel(name, value != null ? value.toString() : null);
    }

   

  

}
