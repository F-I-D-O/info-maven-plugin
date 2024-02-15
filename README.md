**No installation required.** The plugin is on Maven Central

# Basic usage
```bash
mvn ninja.fido:info-maven-plugin:list
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