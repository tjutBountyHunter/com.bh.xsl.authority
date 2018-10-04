package com.xsl.authority.mapper;

import com.xsl.authority.pojo.XslLevelRule;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface LevelRuleMapper {

    /**
     * 查询父节点的所有节点
     * @param pid
     * @return
     */
    @Select("SELECT id,pid,name,url,token,usecount,priority  FROM xsl_rule WHERE pid=#{pid}")
    List<XslLevelRule> getChildRules(Integer pid);

    /**
     * 查询所有权限数据
     * @return
     */
    @Select("SELECT id,pid,name,url,token,usecount,priority FROM xsl_rule")
    List<XslLevelRule> getAll();

    void insertRules(XslLevelRule xslLevelRule);

    /**
     * 通过id查询权限信息
     * @param id
     * @return
     */
    @Select("SELECT id,pid,name,url,token,usecount,priority FROM xsl_rule WHERE id = #{id}")
    XslLevelRule getRuleById(Integer id);

    void updateRule(XslLevelRule xslLevelRule);

    /**
     * 通过权限id删除权限信息
     * @param id
     */
    void deleteRuleById(Integer id);

    /**
     * 通过猎人等级角色id查询其所有权限
     * @param hunterlevelid
     * @return
     */
    @Select("select ruleId from xsl_hunter_level_rule where hunterlevelId = #{hunterlevelid}")
    List<Integer> getRuleIdsByRoleids(Integer hunterlevelid);

    /**
     * 通过雇主等级角色id查询其所有权限
     * @param masterlevelid
     * @return
     */
    @Select("select ruleId from xsl_master_level_rule where masterlevelId = #{masterlevelid}")
    List<Integer> getRuleIdsByMasterLevelids(Integer masterlevelid);

    /**
     * 通过管理员id查询其所有权限
     * @param adminid
     * @return
     */
    @Select("select ruleId from xsl_admin_rule where adminId = #{adminid}")
    List<Integer> getRuleIdsByAdminlids(Integer adminid);

    /**
     *  通过管理员id查询其所有权限
     * @param roleid
     * @return
     */
    @Select("select ruleId from xsl_role_rule where roleId = #{roleid}")
    List<Integer> getRuleIdsByRoleid(Integer roleid);

    /**
     * 通过id获取权限token
     * @param id
     * @return
     */
    @Select("select token from xsl_rule where id=#{id}")
    String getToken(Integer id);

    @Select("select id from xsl_rule where name=#{name}")
    Integer getRuleIdByName(String name);

    /**
     * 通过URL更新调用次数
     * @param usecount
     * @param url
     */
    @Update("UPDATE xsl_rule SET usecount=#{0} WHERE url = #{1}")
    void updateUseCountByUrl(Integer usecount,String url);

    /**
     * 通过url查询调用次数
     * @param url
     * @return
     */
    @Select("select usecount from xsl_rule where url = #{url}")
    Integer getUseCountByUrl(String url);

    @Update("UPDATE xsl_rule SET assgincount=#{0} WHERE url = #{1}")
    void updateAssginCountByUrl(Integer assgincount,String url);

    @Select("select assgincount from xsl_rule where url = #{url}")
    Integer getAssginCountByUrl(String url);
}
