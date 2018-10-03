package com.xsl.authority.test;

import com.xsl.authority.export.SearchExperienceService;
import com.xsl.authority.utils.XslResult;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class dubboTest {

    private SearchExperienceService searchExperienceService;

    @Test
    public void TestDubbo(){
        ApplicationContext context = new ClassPathXmlApplicationContext("file:E:/Workplace/xsl-authority/xsl-authority-service/src/test/java/com/xsl/authority/test/dubbo.xml");
        searchExperienceService = (SearchExperienceService) context.getBean("searchExperienceService");
        XslResult xslHunterLevelExperience = null;
        try {
           xslHunterLevelExperience = searchExperienceService.getHunterExperience(1);
       }catch (Exception e){
           e.printStackTrace();
       }
        System.out.print(xslHunterLevelExperience.getData());
        System.out.print(xslHunterLevelExperience.getMsg());
    }

}
