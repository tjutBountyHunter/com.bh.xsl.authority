package com.xsl.authority.export;

import com.xsl.authority.mapper.SearchLevelMapper;
import com.xsl.authority.pojo.XslHunterLevel;
import com.xsl.authority.pojo.XslMasterLevel;
import com.xsl.authority.utils.XslResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 梁俊伟
 * 提供查询等级信息的实现类
 */
@Service
public class SearchLevelServiceImpl implements SearchLevelService {

    @Autowired
    private SearchLevelMapper searchLevelMapper;

    /**
     *通过等级查询猎人等级信息
     * @param level
     * @return
     */
    @Override
    public XslResult getHunterLevelInfo(Integer level) {
        XslHunterLevel xslHunterLevel = searchLevelMapper.getHunterLevelInfo(level);
        try {
            if(xslHunterLevel==null)
                return XslResult.build(500,"获取等级信息失败");
            else
                return XslResult.ok(xslHunterLevel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过等级查询雇主等级信息
     * @param level
     * @return
     */
    @Override
    public XslResult getMasterLevelInfo(Integer level) {
        XslMasterLevel xslMasterLevel = searchLevelMapper.getMatserLevelInfo(level);
        try {
            if(xslMasterLevel==null)
                return XslResult.build(500,"获取等级信息失败");
            else
                return XslResult.ok(xslMasterLevel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
