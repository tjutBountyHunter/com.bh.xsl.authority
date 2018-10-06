//package com.xsl.authority.test;
//
//import com.xsl.authority.export.SearchExperienceService;
//import com.xsl.authority.export.SearchLevelService;
//import com.xsl.authority.pojo.XslMasterLevel;
//import com.xsl.authority.utils.XslResult;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//public class dubboTest {
//
//    @Autowired
//    private SearchExperienceService searchExperienceService;
//
//    @Autowired
//    private  SearchLevelService searchLevelService;
//
//    @Test
//    public void TestDubbo(){
//        ApplicationContext context = new ClassPathXmlApplicationContext("file:E:/Workplace/xsl-authority/xsl-authority-service/src/test/java/com/xsl/authority/test/dubbo.xml");
//        searchExperienceService = (SearchExperienceService) context.getBean("searchExperienceService");
//        XslResult xslHunterLevelExperience = null;
//        try {
//           xslHunterLevelExperience = searchExperienceService.getHunterExperience(1);
//            System.out.print(xslHunterLevelExperience.getData());
//            System.out.print(xslHunterLevelExperience.getMsg());
//       }catch (Exception e){
//           e.printStackTrace();
//       }
//
//    }
//
//    @Test
//    public void TestgeLevel(){
//
//        ApplicationContext context = new ClassPathXmlApplicationContext("file:E:/Workplace/xsl-authority/xsl-authority-service/src/test/java/com/xsl/authority/test/dubbo.xml");
//        searchLevelService = (SearchLevelService) context.getBean("searchLevelService");
//        XslResult xslMasterLevel = null;
//        try {
//            xslMasterLevel = searchLevelService.getMasterLevelInfo(1);
//            XslMasterLevel xslMaster = (XslMasterLevel) xslMasterLevel.getData();
//            System.out.print(xslMaster.getName());
//            System.out.print(xslMasterLevel.getStatus());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//}
