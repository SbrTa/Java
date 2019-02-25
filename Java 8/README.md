# Java 8 new features
  - Lambda Expression
  - Functional Interfaces
  - Default Methods
  - Predefined Functional Interfaces
    - Predicates
    - Functions
    - Supplier
    - Consumer
  - Double Colon Operator (Constructor and Method Reference)
  - Stream API
  - Date and Time API (Joda Time API)


# Lambda Expression
  - Came into picture to enable functonal programming in java.
  - Lambda Expression is just an annonymus function.
  - Lambda's can be also be classified as a clouser. 
  - Functional Interface is the must have requirement to invoke Lambda expression. 


# Stream API
  - The main purpose of stream is to process elements present inside collection. 
  - Best suited to perform bulk operation on the collection. 
  - stream() method is a default method present on the collection interface.
  
  ## Example:
  
  ```numbers = [1,2,3,4,5,6,7,8,9,10]```
  
  **Filter** - ```numbers.stream().filter(i->i%2==1).collect(Collectors.toList());```
  
  **Map** - ```numbers.stream().map(i->i*n).collect(Collectors.toList());```
  
  
