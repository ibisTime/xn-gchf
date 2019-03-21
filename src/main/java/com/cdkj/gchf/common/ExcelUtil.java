package com.cdkj.gchf.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    public static void main(String[] args) {

        String filePath = "/Users/silver/Desktop/JM/锦鸿建设有限公司金街名苑商住楼/参建单位导入模板.xlsx";
        String columns[] = { "projectCode", "corpCode", "corpName",
                "corpType" };
        readExcelData(filePath, columns);

    }

    public static List<Map<String, String>> readExcelData(String filePath,
            String columns[]) {
        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        List<Map<String, String>> list = null;
        String cellData = null;
        wb = readExcel(filePath);
        if (wb != null) {

            // 用来存放表中数据
            list = new ArrayList<Map<String, String>>();

            // 获取第一个sheet
            sheet = wb.getSheetAt(0);

            // 获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();

            // 获取第一行
            row = sheet.getRow(0);

            // 获取最大列数
            int colnum = row.getPhysicalNumberOfCells();

            for (int i = 1; i < rownum; i++) {
                Map<String, String> map = new LinkedHashMap<String, String>();
                row = sheet.getRow(i);
                if (row != null) {
                    for (int j = 0; j < colnum; j++) {
                        cellData = parseCellData(row.getCell(j));
                        map.put(columns[j], cellData);
                    }
                } else {
                    break;
                }
                list.add(map);
            }
        }

        // 遍历解析出来的list
        for (Map<String, String> map : list) {
            for (Entry<String, String> entry : map.entrySet()) {
                System.out.print(entry.getKey() + ":" + entry.getValue() + ",");
            }
            System.out.println();
        }

        return list;
    }

    private static String parseCellData(Cell cell) {
        String cellData = null;
        switch (cell.getCellTypeEnum()) {
            case STRING:

                cellData = String.valueOf(cell.getRichStringCellValue())
                    .replace("E", "");
                break;

            case NUMERIC:

                if ("yyyy\\-mm\\-dd;@"
                    .equals(cell.getCellStyle().getDataFormatString())) {
                    cellData = DateUtil.dateToStr(cell.getDateCellValue(),
                        DateUtil.FRONT_DATE_FORMAT_STRING);
                } else {
                    cellData = String.valueOf(cell.getNumericCellValue());
                    cellData = cellData.substring(0, cellData.length() - 2)
                        .replace(".", "").replace("E", "");
                }

                break;

            default:
                ;
        }
        return cellData;
    }

    // 读取excel
    public static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xlsx".equals(extString)) {
                wb = new XSSFWorkbook(is);
            } else {

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

}
