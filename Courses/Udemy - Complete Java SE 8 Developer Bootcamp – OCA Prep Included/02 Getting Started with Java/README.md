

## Parameter vs Argument
   Generally a **parameter** is what appears in the definition of the method. An **argument** is the instance passed to the method during runtime.
   ```java
   int square(int n){
        return n * n;
   }
   int printSquare(){
        System.out.println(square(3));
   }
```
   Here, n in ```square(int n)``` is called **parameter** or formal parameter and 3 in ```square(3)``` is called **argument** or actual parameter.
   
   
## Rules for writting main method
  ``` public static void main(String[] args)```
  - **public**: This is the access modifier of the main method. It has to be public so that java runtime can execute this method.
  - **static**: When java runtime starts, there is no object of the class present. That’s why the main method has to be static so that JVM can load the class into memory and call the main method.
  - **void**: Java programming mandates that every method provide the return type. Java main method doesn’t return anything, that’s why it’s return type is void. This has been done to keep things simple because once the main method is finished executing, java program terminates. So there is no point in returning anything.
  - **main**: This is the name of java main method. It’s fixed and when we start a java program, it looks for the main method.
  - **String[] args**: Java main method accepts a single argument of type String array. This is also called as java command line arguments. 
  - Modifiers are order independent. **public static** and **static public** are same.
  
