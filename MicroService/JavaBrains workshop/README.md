# Micro services

  - Buzzwords 
    - Spring cloud
    - Monolithic
    - RestTemplate
    - WebClient
    - Bean
    - Load Balancing
    - Discovery Server
    - Client side service discovery
    - Server side service discovery
    - Eureka
    - @LoadBalanced
    -
    - Fault tolarance
    - Resilience
    - Circuit breaker
    - Hystrix
    - Hystrix Dashboard
    - Bulkhead pattern
    -
    - Configuration server
    - Externalized Configuration
    - Spring Expression Language (SPEL)
    - @Value
    - @ConfigurationProperties
    - Spring boot actuator
    - YAML (.yml)
  
  - Pre requisite
    - Java
    - Springboot
    
    
  - Facts
    - Service discovery is a pattern. Eureka is a technology wich implements service discovery patterns.
    - Microservices are designed to solve specific problems for specific application. Yes, it can be re used in other application.
    - Whitelabel error page: When the application don't find any api with inputed url, then it redirects to /error. If /error also not defined, then it shows whitelabel error. It means an error about showing an error. 
    - Reacting programming in java is asynchronous way of coding.
    - `@Autowired` is a consumer. `@Autowired` means telling spring to give something;
    - `@Bean` is a producer. `@Bean` means telling spring i've something that other people will need. SO execute this method, save this instance somewhere, give others when required.
    - Bean initialization: Eager, Lazy. What is default for spring?
    - Eager Bean Initialization: Default in Spring. All the defined beans, and their dependencies, are created when the application context is created.
    - Lazy Bean Initialization: The bean will only be created, and its dependencies injected, once they're needed.
    - Spring cloud uses client side service discovery.
    - Fault tolarance: How much can a system tolarance for a particular fault.
    - Resilience: How many fault can a system tolarance.
    - Handale fallback
      - Through exception
      - Default dummy data
      - Return session data
    - Circuit breaker pattern
      - Fail fast
      - Fallback functionality
      - Automatic recovery
    - Hystrix: It implements circuit breaker pattern. Created by netflix.
    - `YAML` supports the concept of nesting and is a better alternative of `.properties' file
    
  - Hystrix 
    - Add netflix hystrix dependency
    - Enable hystrix by adding `@EnableCircuitBreaker` to the main class
    - Add `@HystrixCommand` to the method that need circuit breaker
    - Add fallback method name as - `@HystrixCommand("fallbackMathod")`. The fallback method `fallbackMathod` will be called if the circuit breaks.
        
  - Hystrix Dashboard
    - Add netflix hystrix dashboard and spring boot actuator dependency
    - Enable hystrix by adding `@EnableHystrixDashboard` to the main class
    - Add `management.endpoints.web.exposure.include=hystrix.stream` to application.properties
  
  - Externalized Configuration 
    - https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-external-config
  
  - Config server
    - Create spring boot project with spring cloud config server dependency
    - Add `@EnableConfigServer` to main class
    - Add configuration repo in application properties - `spring.cloud.config.server.git.uri=https://github.com/SbrTa/ConfigServer`
  
  - Config server client
    - Add `spring-cloud-starter-config` dependancy
    - Add `spring.cloud.config.uri: http://localhost:8888` in application properties
    - Add `@RefreshScope` to the class that need to update dynamically whenever config file is updated.
    - After updating configuration properties in repo, place a post request to `http://[client]/actuator/refresh`. It will update the class that are annotated with `@RefreshScope`.
    
  
  - @Value
    - Set value to variable: `@Value("your value")`
    - Set value to variable from property file: `@Value("${propertyName}")`
    - If property is not exists, application run will fail. 
    - Set default value in case of missing property: `@Value("${propertyName : defaultValue}")`
    - @Value can assign list too. Let `property.list.value = one, two, three`. `@Value("${property.list.value}") List<String> list;`
    - For `SPEL` use `#`. Let, `properties.dbValue = {connectionString:'http://___', userName:'foo', password:'1234'}`. `@Value("#{${properties.dbValue}}") Map<String, String> dbValue;`
    - `@ConfigurationProperties` is a powerful replacement of `@Value`    
    
