package com.analysis.boom.common.utils;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * 将读取到的数据库内容写到Excel模板表中，供下载需要
 */
public class ExcelUtils {

    private static final Logger LOGGER = Logger.getLogger(ExcelUtils.class.getName());

    public static boolean writerExcelFile(String path, String sheetName, String style, List<String> list) throws IOException {
        Workbook workbook;
        if ("XLS".equals(style)) {
            workbook = new HSSFWorkbook();
        } else {
            workbook = new XSSFWorkbook();
        }
        // 生成一个表格
        Sheet sheet = workbook.createSheet(sheetName);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成样式
        Row row = sheet.createRow(0);
        String titles = list.get(0);
        String[] titlesSplit = titles.split("//");
        for (int i = 0; i < titlesSplit.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(titlesSplit[i]);
        }
        List<String> listData = list.subList(1, list.size());
        for (int i = 0; i < listData.size(); i++) {
            row = sheet.createRow(i + 1);
            String[] split = listData.get(i).split("//");
            for (int j = 0; j < split.length; j++) {
                Cell cell = row.createCell(j);
                String s = split[j];
                if (validateNumber(s)) {
                    cell.setCellValue(new BigDecimal(s).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                } else if (s.contains("%")) {
                    cell.setCellValue(new BigDecimal(s.replace("%", "")).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+ "%") ;
                } else {
                    cell.setCellValue(s);
                }

            }
        }
        /*
         * 写入到文件中
         */
        boolean isCorrect = false;
        File file = new File(path);
        // 如果文件存在,则删除已有的文件,重新创建一份新的
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            isCorrect = true;
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return isCorrect;
    }

    /**
     * 判断是否是整数或者是小数
     *
     * @param str
     * @return true：是，false不是
     */

    private static boolean validateNumber(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        // 说明一下的是该正则只能识别4位小数；如果不限制小数位数的话，写成[+-]?[0-9]+(\\.[0-9]+)?就可以了
        return str.matches("[+-]?[0-9]+(\\.[0-9]{1,4})?");

    }
}