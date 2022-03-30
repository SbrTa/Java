## Concurrency with Spring Security
Spring Security by definition is thread-bound, but by default is not ready to be used in multithreading environment. 
However, with simple steps we are able to deal fix the problem.

In most environments, Security is stored on a per Thread basis. This means that when work is done on a new Thread, the SecurityContext is lost. 
Spring Security provides low-level abstractions for working in multi-threaded environments. 

#### DelegatingSecurityContextExecutor
Propagate SecurityContext to Thread. 

```
  Executor delegatedExecutor = ForkJoinPool.commonPool();
  Executor delegatingExecutor = new DelegatingSecurityContextExecutor(delegatedExecutor, SecurityContextHolder.getContext());
  CompletableFuture.runAsync(() -> methodToRunAsynchronously(), delegatingExecutor);
```

#### MODE_INHERITABLETHREADLOCAL
```
  @Bean
  public MethodInvokingFactoryBean methodInvokingFactoryBean() {
      MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
      methodInvokingFactoryBean.setTargetClass(SecurityContextHolder.class);
      methodInvokingFactoryBean.setTargetMethod("setStrategyName");
      methodInvokingFactoryBean.setArguments(new String[]{SecurityContextHolder.MODE_INHERITABLETHREADLOCAL});
      return methodInvokingFactoryBean;
  }

```
It forces Spring to wrap its async executor with Security delegate DelegatingSecurityContextTaskExecutor. 
Simple as that, we are safe to use @Async methods without worring about security context.




### Reference:
  - https://dev.to/spooz/spring-security-and-threads
  - https://www.linkedin.com/pulse/minute-read-concurrency-spring-security-keshavram-kuduwa/?trk=articles_directory
