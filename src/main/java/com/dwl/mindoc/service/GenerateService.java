package com.dwl.mindoc.service;

import com.dwl.mindoc.MinConfig;
import com.dwl.mindoc.dao.BaseDao;
import com.dwl.mindoc.database.BaseFactory;
import com.dwl.mindoc.database.Database;
import com.dwl.mindoc.domain.ColumnVo;
import com.dwl.mindoc.domain.TableVo;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.Document;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: mindoc
 * @description: generator
 * @author: daiwenlong
 * @create: 2018-10-13 13:00
 **/
@Service
public class GenerateService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BaseFactory factory;

    @Autowired
    private BaseDao dao;

    @Autowired
    private MinConfig config;

    @PostConstruct
    public void genertator(){
        try {
            Database base = factory.getDataBase();
            List<TableVo> tables = dao.getTables(base);
            tables.forEach(table->{
                table.setColumns(dao.getColumns(base,table.getTableName()));
                logger.info("mindoc - TableName：{} TableComment：{} loading...",table.getTableName(),table.getTableComment());
            });
            if("pdf".equalsIgnoreCase(config.getValue("fileType"))){
                makePdf(tables);
            }else{
                makeDoc(tables);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 生成doc
     * @param tables
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public void makeDoc(List<TableVo> tables){
        logger.info("mindoc - makeDoc satrting...");
        // 第一步：创建一个Configuration对象。
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 第二步：设置模板文件所在的路径。
        try {
            configuration.setDirectoryForTemplateLoading(ResourceUtils.getFile("classpath:"));
            // 第三步：设置模板文件使用的字符集。
            configuration.setDefaultEncoding("utf-8");
            // 第四步：加载一个模板，创建一个模板对象。
            Template template = configuration.getTemplate("doc.xml");
            // 第五步：创建一个模板使用的数据集。
            Map<String,Object> dataModel = new HashMap<>();
            dataModel.put("tables",tables);
            //生成文件放在项目根目录下
            File outFile = new File(System.getProperty("user.dir")+"\\DatabaseDesign.doc");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
            // 第六步：调用模板对象的process方法输出文件。
            template.process(dataModel, out);
            logger.info("mindoc - MakeDoc succeeded.");
            logger.info("mindoc - Doc directory {}",outFile);
        } catch (IOException |TemplateException e) {
            logger.warn("mindoc - MakeDoc failed.");
        }
    }


    public void makePdf(List<TableVo> tables) {

        try {
            logger.info("mindoc - makePdf satrting...");
            PdfDocument pdfDoc = new PdfDocument(
                    new PdfWriter(System.getProperty("user.dir") + "\\DatabaseDesign.pdf"));
            Document doc = new Document(pdfDoc);// 构建文档对象
            TextFooterEventHandler eh = new TextFooterEventHandler(doc);
            pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, eh);
            // 中文字体
            PdfFont sysFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
            Paragraph paragraph = new Paragraph();
            paragraph.add("数据库设计文档").setFont(sysFont).setBold().setFontSize(20).setTextAlignment(TextAlignment.CENTER);
            doc.add(paragraph);
            int num = 0;
            for (TableVo vo : tables) {
                num++;
                doc.add(new Paragraph(""));
                String title = num +"  表名：" + vo.getTableName() + "   表注释：" + vo.getTableComment();
                doc.add(new Paragraph(title).setFont(sysFont).setBold());
                // 构建表格以100%的宽度
                Table table = new Table(5).setWidth(UnitValue.createPercentValue(100));

                table.addCell(new Cell().add(new Paragraph("列名")).setFont(sysFont)
                        .setBackgroundColor(new DeviceRgb(221, 234, 238)));
                table.addCell(new Cell().add(new Paragraph("数据类型")).setFont(sysFont)
                        .setBackgroundColor(new DeviceRgb(221, 234, 238)));
                table.addCell(new Cell().add(new Paragraph("约束")).setFont(sysFont)
                        .setBackgroundColor(new DeviceRgb(221, 234, 238)));
                table.addCell(new Cell().add(new Paragraph("允许空")).setFont(sysFont)
                        .setBackgroundColor(new DeviceRgb(221, 234, 238)));
                table.addCell(new Cell().add(new Paragraph("备注")).setFont(sysFont)
                        .setBackgroundColor(new DeviceRgb(221, 234, 238)));
                for (ColumnVo col : vo.getColumns()) {
                    table.addCell(new Cell().add(new Paragraph(col.getColumnName())).setFont(sysFont));
                    table.addCell(new Cell().add(new Paragraph(col.getColumnType())).setFont(sysFont));
                    table.addCell(new Cell().add(new Paragraph(col.getColumnKey())).setFont(sysFont));
                    table.addCell(new Cell().add(new Paragraph(col.getIsNullable())).setFont(sysFont));
                    table.addCell(new Cell().add(new Paragraph(col.getColumnComment())).setFont(sysFont));
                }
                // 将表格添加入文档并页面居中
                doc.add(table.setHorizontalAlignment(HorizontalAlignment.CENTER));
            }
            doc.close();
            logger.info("mindoc - MakePdf succeeded.");
            logger.info("mindoc - Pdf directory {}",System.getProperty("user.dir") + "\\DatabaseDesign.pdf");
        } catch (Exception e) {
            logger.warn("mindoc - MakePdf failed.");
        }

    }


}


