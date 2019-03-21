

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
