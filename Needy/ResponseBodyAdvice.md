## ResponseBodyAdvice
Allows customizing the response after the execution of an @ResponseBody or a ResponseEntity controller method but before the body is written with an HttpMessageConverter. 


````
@ControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType contentType,
                                  Class converterType, ServerHttpRequest request, ServerHttpResponse response) {
        return MaskingUtils.maskDeveloperAccountResponse(body);
    }
}

@Slf4j
public final class MaskingUtils {

    private static final List<String> maskedFieldsForDeveloper = Arrays.asList("username", "fullname", "email", "phone");
    private static final String maskText = "***";

    public static Object maskDeveloperAccountResponse(Object body) {
        if (SecurityUtils.isDeveloperAccount()) {
            try {
                if (body instanceof Page) {
                    Map<String, Object> map = toMap(body);
                    Object contents = map.get("content");
                    map.put("content", maskListOrSet(contents));
                    body = map;
                } else if (body instanceof List || body instanceof Set) {
                    body = maskListOrSet(body);
                } else {
                    body = mask(toMap(body));
                }
            } catch (Exception e) {
                log.error("failed to mask response for developer account - ", e);
                body = new HashMap<>();
            }
        }
        return body;
    }

    private static Map<String, Object> toMap(Object data) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JacksonCustomDateModule());
        return mapper.convertValue(data, Map.class);
    }

    private static Object maskListOrSet(Object body) {
        List<Object> list = new ArrayList<Object>();
        Iterator<?> iterator = ((Collection<?>) body).iterator();
        while (iterator.hasNext()) {
            list.add(mask(toMap(iterator.next())));
        }
        return list;
    }

    private static Object mask(Map<String, Object> map) {
        for (String key : map.keySet()) {
            if (nonNull(map.get(key)) && maskedFieldsForDeveloper.contains(key.toLowerCase()))
                map.put(key, maskText);
        }
        return map;
    }
}


public class JacksonCustomDateModule extends SimpleModule {
    public JacksonCustomDateModule() {
        addSerializer(Temporal.class, new JacksonCustomDateSerializer());
    }

    private static class JacksonCustomDateSerializer extends JsonSerializer<Temporal> {
        @Override
        public void serialize(Temporal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(value.toString());
        }
    }
}
````
