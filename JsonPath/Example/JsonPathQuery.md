# Query into json using JsonPath

## Sample Query 1
```
  JsonPath.using(jsonPathConfig.getConfiguration())
          .parse(new Gson().toJson(JsonData))
          .read("$.data[?(@.category_name == '"+request.getCategoryCode()+"')]"+
          ".data[*].['utility_code','utility_value','utility_image']")
```
## Sample Query 1 output


```
    [
        {
            "utility_code": "BGDCL",
            "utility_value": "BGDCL",
            "utility_image": "http://api.sslwireless.com/uploads/utility_logo/bgdcl.png"
        },
        {
            "utility_code": "DESCO",
            "utility_value": "DESCO",
            "utility_image": "http://api.sslwireless.com/uploads/utility_logo/desco.png"
        }
    ]
```

## Sample Query 2
```
  JsonPath.using(jsonPathConfig.getConfiguration())
          .parse(new Gson().toJson(JsonData))
          .read("$.data[?(@.category_name == '"+request.getCategoryCode()+"')]"+
          ".data[?(@.utility_code == '"+request.getUtilityCode()+"')]"+
          ".data[*].['utility_bill_type']");
```


## Sample Query 3
```
  JsonPath.using(jsonPathConfig.getConfiguration())
          .parse(new Gson().toJson(JsonData))
          .read("$.data[?(@.category_name == '"+request.getCategoryCode()+"')]"+
          ".data[?(@.utility_code == '"+request.getUtilityCode()+"')]"+
          ".data[?(@.utility_bill_type == '"+request.getUtilityBillType()+"')]");
```  

