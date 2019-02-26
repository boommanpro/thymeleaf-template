package cn.boommanpro;

import cn.boommanpro.common.StringUtils;
import cn.boommanpro.config.ThymeLeafConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThymeleafTemplateApplicationTests {

    @Test
    public void contextLoads() {

    }


    @Test
    public void convert() {
        String beanName = "WormHole";
        System.out.println(StringUtils.upper2LowerHump(beanName));
        System.out.println(StringUtils.upper2UpperUnderLine(beanName));
        System.out.println(StringUtils.upper2LowerMidLine(beanName));
    }

    @Test
    public void mkdirs() {

        String outPath = "D:\\thymeleaf-template\\generator\\";

        String referencePackage = "cn.boommanpro";

        String filePath = outPath + referencePackage.replaceAll("\\.", "\\\\");


        File file = new File(filePath);
        boolean result = file.mkdirs();
        System.out.println(result);
    }


    @Test
    public void thymeleafGeneratorTest() throws IOException {

        //输出的目录

        String outPath = "D:\\thymeleaf-template\\generator\\";

        String suffix = ".java";

        Context context = new Context();




        String core = "Account";
        String humpCore = StringUtils.upper2LowerHump(core);
        String upperCore = StringUtils.upper2UpperUnderLine(core);
        String midLineName = StringUtils.upper2LowerMidLine(core);
        String referencePackage = "cn.boommanpro.module.account"+".config";
        context.setVariable("core", core);
        context.setVariable("humpCore", humpCore);
        context.setVariable("upperCore", upperCore);
        context.setVariable("midLineName", midLineName);
        context.setVariable("referencePackage", referencePackage);

        String filePath = outPath + referencePackage.replaceAll("\\.", "\\\\") + "\\";


        //配置1.DataSourceConfig

        String dataSourceConfig = "DataSourceConfig";

        String generatorDataSourceConfigFileName = core + dataSourceConfig;


        //配置2.DataSourceConfiguration


        String dataSourceConfiguration = "DataSourceConfiguration";

        String generatorDataSourceConfigurationFileName = core + dataSourceConfiguration;
        //配置3.MybatisConfiguration


        String mybatisConfiguration = "MybatisConfiguration";

        String generatorMybatisConfigurationFileName = core + mybatisConfiguration;

        //配置4.Tx
        String tx = "Tx";

        String generatorTxFileName = tx + core;


        //配置5.yml文件

        String application = "application.yml";




        //创建文件夹


        File file = new File(filePath);
        boolean result = file.mkdirs();


        //创建文件

        createFile(filePath, generatorDataSourceConfigFileName + suffix);
        createFile(filePath, generatorDataSourceConfigurationFileName + suffix);
        createFile(filePath, generatorMybatisConfigurationFileName + suffix);
        createFile(filePath, generatorTxFileName + suffix);
        createFile(outPath, application);


        //生成


        generator(suffix, dataSourceConfig, generatorDataSourceConfigFileName, filePath, context);

        generator(suffix, dataSourceConfiguration, generatorDataSourceConfigurationFileName, filePath, context);

        generator(suffix, mybatisConfiguration, generatorMybatisConfigurationFileName, filePath, context);

        generator(suffix, tx, generatorTxFileName, filePath, context);
        generator("", application, application, outPath, context);


    }

    private void generator(String suffix, String fileName, String generatorFileName, String generatorFilePath, Context context) throws IOException {
        Writer writer = new FileWriter(generatorFilePath + generatorFileName + suffix);
        writer.write(ThymeLeafConfig.getTemplateEngine().process("config/" + fileName + suffix, context));
        writer.close();
    }


    private void createFile(String path,String fileName) {
        File file = new File(path, fileName);

        if (!file.exists()) {

            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

}
