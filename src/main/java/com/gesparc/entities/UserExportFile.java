package com.gesparc.entities;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gesparc.entities.referentiel.ArticleEntity;

public class UserExportFile {
	private XSSFWorkbook workbook ;
	private XSSFSheet sheet;
	
	private  List <ArticleEntity> articles ;

	@SuppressWarnings("unused")
	public UserExportFile(List <ArticleEntity> articles) {
		System.out.print("test 4 \n");
		@SuppressWarnings("resource")
		XSSFWorkbook   workbook = new  XSSFWorkbook();
		this.articles = articles;
		System.out.print("test 5");

	}
	
	private void createCell(Row row ,int columnCounht ,Object value, CellStyle style )
	{
		sheet.autoSizeColumn(columnCounht);
		Cell cell =row.createCell(columnCounht);
		if(value instanceof  Long)
		{
		cell.setCellValue((Long) value);	
		}else if(value instanceof Integer)
		{
			cell.setCellValue((Integer) value);	

		}
		else if(value instanceof Boolean)
		{
			cell.setCellValue((Boolean) value);	

		}
		else
		{
			cell.setCellValue((String) value);	

		}
		cell.setCellStyle(style);
	}
	
	private void writeHeaderLine()
	{
		System.out.print("writeHeaderLine 1 \n ");
		sheet =workbook.createSheet("Article");
		Row row =sheet.createRow(0);
		CellStyle style =workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(20);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		createCell(row,0,"Inventaire Article",style);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
		font.setFontHeightInPoints((short)(10));

		row =sheet.createRow(1);
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
       createCell(row,0,"Code article ",style);
       createCell(row,1,"designation",style);
       createCell(row,2,"Quantite du stock",style);

		System.out.print("writeHeaderLine 2 \n ");


	}
	
	private void writeDataLines()
	{
		int rowcount =2;
		
		CellStyle style =workbook .createCellStyle();
		XSSFFont font =		workbook.createFont();
		style.setFont(font);
		
		for(ArticleEntity article :articles)
		{
			Row row =sheet.createRow(rowcount++);
			int columnCounht =0;
			        createCell(row,columnCounht++,article.getCodeArticle(),style);
			        createCell(row,columnCounht++,article.getDesignation(),style);
			        createCell(row,columnCounht++,article.getQuantiteStock(),style);

		}
	}
	
	public void export (HttpServletResponse response) throws IOException
	{
		System.out.print("test 3 \n ");
		writeHeaderLine();	
		System.out.print("test 6 \n ");

		writeDataLines();
		
		ServletOutputStream outputStream =response.getOutputStream() ;
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}
