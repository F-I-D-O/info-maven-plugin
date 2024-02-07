package ninja.fido;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mojo(name = "list")
public class ListMojo extends AbstractMojo {
    public void execute() throws MojoExecutionException
    {
        // get path to the .m2 directory
        String m2Path = System.getProperty("user.home") + "/.m2";

//        String m2Path = System.getProperty("user.home") + ".m2/repository/ninja/fido/info-maven-plugin/1.0-SNAPSHOT";

        // process the .m2 directory recursively
        List<String> paths;
        try (Stream<Path> stream = Files.walk(Paths.get(m2Path))) {
            paths = stream.filter(Files::isRegularFile)
                    .map(String::valueOf)
                    .filter(file -> file.endsWith(".pom"))
                    .map(file -> file.replace('\\', '/')) // convert delimiter to / for Windows compatibility
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // print found packages
        for (String path : paths) {
            String[] parts = path.split("/");

            // get plugin name from path
            String pluginName = parts[parts.length - 3];

            // get plugin version from path
            String pluginVersion = parts[parts.length - 2];

            // join the rest of the path to get the plugin group
            for(int i = parts.length - 4; ; i--) {
                if (parts[i].equals("repository")) {
                    parts = java.util.Arrays.copyOfRange(parts, i + 1, parts.length - 3);
                    break;
                }
            }
            String pluginGroup = String.join(".", parts);

            // print everything in three columns
            System.out.printf("%-40s %-40s %s\n", pluginGroup, pluginName, pluginVersion);
        }

    }
}
