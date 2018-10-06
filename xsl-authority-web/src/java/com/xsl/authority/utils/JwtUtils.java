package com.xsl.authority.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACVerifier;
import com.xsl.authority.pojo.JWTpojo;
import com.xsl.authority.pojo.Result;
import com.xsl.authority.redis.JedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * 说明：
 *
 * @Auther: 11432_000
 * @Date: 2018/10/1 17:07
 * @Description:
 */
public class JwtUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);
    @Resource
    private JedisClient jedisClient;

    /** jwt 密匙，不要轻易更改*/
    private static final String SECRET = "jfiskaiifdhgailbghailfgahgfkdyvd";
    /** 网络时间获取地址*/
    private static final String URL_BY_GET_TIME = "http://www.baidu.com";
    /** 成功码 */
    private static final int SUCCESS = 200;
    /** token key*/
    @Value("${TOKEN_KEY_PREFIX}")
    private String TOKEN_KEY_PREFIX;

    public static boolean checkJWTSign(String token) {
        /**
         *
         * 功能描述: 校验token是否有效，有效返回true，无效返回false
         *
         * @param: [token]
         * @return: boolean
         * @auther: 11432_000
         * @date: 2018/9/24 18:03
         */
        boolean flag = true;
        Result payload = getPayloadByToken(token);
        if (payload.getStatus() != SUCCESS){
            flag = false;
        }
        if (flag){
            JWTpojo data =(JWTpojo) payload.getData();
            long exp = Long.valueOf(data.getExp().getTime());
            long now = Long.valueOf(getInternetTime().getTime());
            if (now > exp){
                flag = false;
            }
        }
        return flag;
    }

    public static Result getPayloadByToken(String token) {
        /**
         *
         * 功能描述: 根据token取token中的数据，返回值：100-成功，404-提取失败
         *
         * @param: [token]
         * @return: pojo.Result
         * @auther: 11432_000
         * @date: 2018/9/24 16:44
         */
        try {
            //反序列化
            JWSObject jwsObject = JWSObject.parse(token);
            //获取有效载荷
            Payload payload = jwsObject.getPayload();
            //获取校验签名
            JWSVerifier verifier = new MACVerifier(SECRET);
            if (!jwsObject.verify(verifier)){
                return ResultUtils.setResult(404,"签名校验失败");
            }
            //返回值
            JWTpojo jwTpojo = JsonUtils.jsonToPojo(payload.toString(), JWTpojo.class);
            return ResultUtils.setResult(200,jwTpojo);
        }catch (Exception e){
            LOGGER.error("提取信息失败");
            return ResultUtils.setResult(404,"提取信息失败");
        }
    }

    public static Date getInternetTime(){
        /**
         *
         * 功能描述: 获取网络时间，失败返回本地时间。
         *
         * @param: []
         * @return: java.util.Date
         * @auther: 11432_000
         * @date: 2018/9/24 17:47
         */
        try {
            URL url = new URL(URL_BY_GET_TIME);
            //获取链接
            URLConnection urlConnection = url.openConnection();
            //连接
            urlConnection.connect();
            long date = urlConnection.getDate();
            return new Date(date);
        }catch (Exception e){

        }
        return new Date();
    }
}
