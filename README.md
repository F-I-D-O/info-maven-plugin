This tiny maven plugin provides a quick way to see which artifacts are installed in the local repository. You no longer need to browse the repository manually!

**No installation required.** The plugin is on Maven Central

# Basic usage
```bash
mvn ninja.fido:info-maven-plugin:list
```

The plugin lists all artifacts, one per line. For filtering, combine it with a filtration tool, e.g:
```bash
mvn ninja.fido:info-maven-plugin:list | grep "google" # Linux

mvn ninja.fido:info-maven-plugin:list | sls "google" # Windows
``` 

# Shortening the command
Apart from custom shell commands, the command can be shortened by configuring Maven. Just add the following to the `settings.xml` file:
```XML
<pluginGroups>
    <pluginGroup>ninja.fido</pluginGroup>
</pluginGroups>
```
Then the command can be shortened to:
```bash
mvn info:list
```