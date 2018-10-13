package com.dwl.mindoc.service;

import com.dwl.mindoc.dao.BaseDao;
import com.dwl.mindoc.database.BaseFactory;
import com.dwl.mindoc.database.Database;
import com.dwl.mindoc.domain.TableVo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
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

    @Autowired
    private BaseFactory factory;

    @Autowired
    private BaseDao dao;

    @PostConstruct
    public void genertator(){
        try {
            Database base = factory.getDataBase();
            List<TableVo> tables = dao.getTables(base);
            tables.forEach(table->{
                table.setColumns(dao.getColumns(base,table.getTableName()));
            });
            makeDoc(tables);
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
        } catch (IOException |TemplateException e) {
        }
    }

}


