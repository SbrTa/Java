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
