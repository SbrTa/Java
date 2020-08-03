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
    - Resilience
    - Fault tolarance
    - Circuit breaker
  
  - Pre requisite
    - Java
    - Springboot
    
    
  - Facts
    - Service discovery is a pattern. Eureka is a technology wich implements service discovery patterns.
    - Microservices are designed to solve specific problems for specific application. Yes, it can be re used in other application.
    - Whitelabel error page: When the application don't find any api with inputed url, then it redirects to /error. If /error also not defined, then it shows whitelabel error. It means an error about showing an error. 
    - Reacting programming in java is asynchronous way of coding.
    - @Autowired is a consumer. @Autowired means telling spring to give something;
    - @Bean is a producer. @Bean means telling spring i've something that other people will need. SO execute this method, save this instance somewhere, give others when required.
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
      
  
    
