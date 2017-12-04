package dsodling.pre;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;

/**
 *
 * @author Daniel Södling (daniel.sodling@pdsvision.se)
 */
public class MavenUtil {

    private final Model model;
    private final File pom;
    
    public static MavenUtil newInstance() {
        return new MavenUtil();
    }

    public MavenUtil() {
        FileReader reader = null;
        MavenXpp3Reader mavenreader = new MavenXpp3Reader();
        try {
            pom = new File("pom.xml");
            reader = new FileReader(pom);
            model = mavenreader.read(reader);
            reader.close();
        } catch (Exception ex) {
            throw new RuntimeException("Was unble to read pom.xml", ex);
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    // Ignore
                }
            }
        }
    }
    
    public String getArtifactId() {
        return model.getArtifactId();
    }

    public String getProperty(String key) {
        Properties props = model.getProperties();
        if(props != null) {
            props.list(System.out);
        }
        return model.getProperties().getProperty(key);
    }

}
