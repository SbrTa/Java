# Configuration Class


```
  public class JsonPathConfig {
    Configuration configuration = Configuration.defaultConfiguration();
    public Configuration getConfiguration(){
        configuration.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);
        configuration.addOptions(Option.ALWAYS_RETURN_LIST);
        return configuration;
    }
  }
```
