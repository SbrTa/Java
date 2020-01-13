
### DataExportService
```
@Service
public class DataExportService {
    public void exportAsExcel(HttpServletResponse response, String fileName, List<ExcelColumnDetails> columns, List<?> data)
    {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("sheet1");
        int rowCount = 0;
        int columnCount = 0;
        Row row = sheet.createRow(rowCount++);
        for (ExcelColumnDetails columnDetails: columns){
            row.createCell(columnCount++).setCellValue(columnDetails.getHeader());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        for (Object obj:data){
            row = sheet.createRow(rowCount++);
            Map<String,Object> rowData = objectMapper.convertValue(obj,Map.class);
            columnCount = 0;
            for (ExcelColumnDetails columnDetails: columns){
                Cell cell = row.createCell(columnCount++);
                var value = rowData.get(columnDetails.getParameter());
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                } else if (value instanceof Short) {
                    cell.setCellValue((Short) value);
                } else if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                } else if (value instanceof Long) {
                    cell.setCellValue((Long) value);
                } else if (value instanceof Float) {
                    cell.setCellValue((Float) value);
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                }else if (value instanceof BigInteger) {
                    cell.setCellValue(value.toString());
                }else if (value instanceof BigDecimal) {
                    cell.setCellValue(value.toString());
                }else if (value instanceof Date) {
                    cell.setCellValue((Date) value);
                }else {
                    cell.setCellValue(value==null?"":value.toString());
                }
            }
        }
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition","attachment; filename="+fileName+".xlsx");
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Sample Controller
```
@RestController
public class DataExportControllerTestPurpose {
    @Autowired
    DataExportService dataExportService;
   
    @GetMapping("/download/excel")
    public void downloadExcel(HttpServletResponse response) {
        List<Student> students = new ArrayList<>();
        for (int i=1; i<=10; i++){
            Student student = new Student();
            student.setId(i);
            student.setName("Name-"+i);
            student.setDept("Dept-"+i);
            student.setShift("Shift-"+i);
            student.setDue(10*1.123*i);
            student.setActive(true);
            student.setBd(new BigDecimal("123456789.123456789"));
            if (i==5) {
                student.setName(null);
                student.setDue(null);
            }
            students.add(student);
        }

        List<ExcelColumnDetails> columns = new ArrayList<>();
        columns.add(new ExcelColumnDetails("id","ID"));
        columns.add(new ExcelColumnDetails("name","Student Name"));
        columns.add(new ExcelColumnDetails("dept","Department"));
        columns.add(new ExcelColumnDetails("shift","Shift"));
        columns.add(new ExcelColumnDetails("due","Due"));
        columns.add(new ExcelColumnDetails("bd","BD"));

        dataExportService.exportAsExcel(response,"report",columns,students);
    }
}
```

### Student Class
```
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    private int id;
    private String name;
    private String dept;
    private String shift;
    private Double due;
    private boolean isActive;
    private BigDecimal bd;
}
```

### ExcelColumnDetails Class
```
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExcelColumnDetails implements Serializable {
    private String parameter;
    private String header;
}
```
