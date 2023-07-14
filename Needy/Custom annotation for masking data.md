## Custom annotation for masking data

````
@JacksonAnnotationsInside
@JsonSerialize(using = ProtectDataSerializer.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProtectData {
    String[] value() default {};
}

public class ProtectDataSerializer extends StdSerializer<Object> implements ContextualSerializer {
    public ProtectDataSerializer() {
        super(Object.class);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
        return new ProtectDataSerializer();
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        String piiData = value.toString();
        if (SecurityUtils.isDeveloperAccount()) {
            piiData = "***";
        }
        gen.writeString(piiData);
    }
}

public class ProfileDto implements Serializable {
    private Long id;
    @ProtectData
    private String email;
    @ProtectData
    private String name;
}
````


````
@JacksonAnnotationsInside
@JsonSerialize(using = ProtectDataSerializer.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProtectData {

    String[] allowedRoles() default {"ADMIN"};

}

public class ProtectDataSerializer extends StdSerializer<Object> implements ContextualSerializer {

    private String[] allowedRoles;

    public ProtectDataSerializer(String[] allowedRoles) {
        this();
        this.allowedRoles = allowedRoles;
    }

    public ProtectDataSerializer() {
        super(Object.class);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        Optional<ProtectData> annotation = Optional.ofNullable(property).map(prop -> prop.getAnnotation(ProtectData.class));
        return new ProtectDataSerializer(annotation.map(ProtectData::allowedRoles).orElse(new String[] {"ADMIN"}));

    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String piiData = value.toString();
        if (authentication != null) {
            final List<String> allowedRolesList = Arrays.asList(this.allowedRoles);
            long count = authentication.getAuthorities().stream().filter(ga -> allowedRolesList.contains(ga.getAuthority().substring(5))).count();
            if (count == 0) {
                piiData = piiData.replaceAll("\\w(?=\\w{4})", "x");
            }
        }
        gen.writeString(piiData);
    }
}

public class ProfileDto implements Serializable {
    private Long id;
    @ProtectData
    private String email;
    @ProtectData
    private String name;
}
````
