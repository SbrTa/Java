

## Parameter vs Argument
   Generally a **parameter** is what appears in the definition of the method. An **argument** is the instance passed to the method during runtime.
   ```javascript
// code away!

let generateProject = project => {
  let code = [];
  for (let js = 0; js < project.length; js++) {
    code.push(js);
  }
};
```
