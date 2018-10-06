package com.xsl.authority.web;

import com.xsl.authority.pojo.JWTpojo;
import com.xsl.authority.pojo.Result;
import com.xsl.authority.pojo.XslManager;
import com.xsl.authority.redis.JedisClient;
import com.xsl.authority.utils.CookieUtils;
import com.xsl.authority.utils.JsonUtils;
import com.xsl.authority.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${SSO_URL}")
    private String SSO_URL;

    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;

    @Value("${TOKEN_KEY_PREFIX}")
    private String TOKEN_KEY_PREFIX;

    @Value("${ID_COOKIE_KEY}")
    private String ID_COOKIE_KEY;

    @Value("${XSL_MANAGER_INFO_KEY}")
    private String XSL_MANAGER_INFO_KEY;

    @Value("${TOKEN_KEY_ID}")
    private String TOKEN_KEY_ID;

    @Value("${TOKEN_EXPIRE_TIME}")
    private Integer TOKEN_EXPIRE_TIME;

    @Resource
    private JedisClient jedisClient;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String tokenKey = "";
        tokenKey = CookieUtils.getCookieValue(httpServletRequest, TOKEN_KEY);
        if (tokenKey != null && !"".equals(tokenKey)){
            if (checkToken(tokenKey)){
                return true;
            }
        }
        tokenKey = CookieUtils.getCookieValue(httpServletRequest ,TOKEN_KEY_ID);
        if (tokenKey != null && !"".equals(tokenKey)){
            if (checkToken(tokenKey)){
                CookieUtils.setCookie(httpServletRequest ,httpServletResponse ,TOKEN_KEY ,tokenKey);
                String token = jedisClient.get(TOKEN_KEY_PREFIX + tokenKey);
                setIdInCookie(httpServletRequest,httpServletResponse,token);
                return true;
            }
        }
        String managerId = CookieUtils.getCookieValue(httpServletRequest, ID_COOKIE_KEY);
        StringBuffer returnURL = httpServletRequest.getRequestURL();
        if (managerId != null){
            httpServletResponse.sendRedirect(SSO_URL + "?returnUrl=" + returnURL + "&id=" + managerId);
            return false;
        }
        httpServletResponse.sendRedirect(SSO_URL + "?returnUrl=" + returnURL);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 功能描述: 将已登录的用户id存入cookie。
     * @param: [request, response, token]
     * @return: void
     * @auther: 11432_000
     * @date: 2018/10/4 19:53
     */
    private void setIdInCookie(HttpServletRequest request ,HttpServletResponse response , String token){
        Result payload = JwtUtils.getPayloadByToken(token);
        JWTpojo data = (JWTpojo) payload.getData();
        Map<String, Object> extend = data.getExtend();
        String managerInfoKey = extend.get("managerInfoKey").toString();
        String managerInfo = jedisClient.get(XSL_MANAGER_INFO_KEY + managerInfoKey);
        XslManager xslManager = JsonUtils.jsonToPojo(managerInfo, XslManager.class);
        CookieUtils.setCookie(request ,response ,ID_COOKIE_KEY,xslManager.getId().toString());
    }

    /**
     *
     *功能描述: 检查tokenKey是否合法。
     *
     * @param:
     * @return: boolean
     * @auther: 11432_000
     * @date: 2018/10/4 19:53
     */
    private boolean checkToken(String tokenId){
        boolean flag = false;

        String token = jedisClient.get(TOKEN_KEY_PREFIX + tokenId);
        if (token != null && !"".equals(token)){
            if (JwtUtils.checkJWTSign(token)){
                flag = true;
            }
        }
        return flag;
    }
}
