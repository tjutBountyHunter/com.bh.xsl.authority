package com.xsl.authority.utils;

import com.xsl.authority.pojo.TokenHeader;
import com.xsl.authority.pojo.TokenPlayload;
import com.xsl.authority.pojo.User;

import java.util.UUID;

public class TokenUtil {

    //token DES的方法名
    public static final  String TOKEN_DES_KEY = "xslToken";
    //token类型
    public static final String JWT_TYP = "JWT";
    //token签证的生成算法
    public static final String JWT_ALG = "AES";
    //token过期时间
    public static final String JWT_EXP = "86400000";

    /**
     * 生成jwt的header部分内容
     *
     * @return
     * @throws Exception
     */
    private static String tokenHeaderBase64() throws Exception {
        TokenHeader tokenHeader = new TokenHeader();
        tokenHeader.setTyp(JWT_TYP);
        tokenHeader.setAlg(JWT_ALG);
        String headerJson = JsonUtils.objectToJson(tokenHeader);
        String headerBase64 = Base64Util.encryptBASE64(headerJson.getBytes());
        return headerBase64;
    }

    /**
     * 生成jwt的payload部分内容
     *
     * @param rulePid,tokenname
     * @param <T>自定义的数据块
     * @return
     * @throws Exception
     */
    private static <T> String tokenPayloadBase64(Integer rulePid,String rulename) throws Exception {
        TokenPlayload tokenPlayload = new TokenPlayload();
        tokenPlayload.setExp(JWT_EXP);
        tokenPlayload.setRoleid(rulePid);
        tokenPlayload.setRolename(rulename);
        String headerJson = JsonUtils.objectToJson(tokenPlayload);
        String headerBase64 = Base64Util.encryptBASE64(headerJson.getBytes());
        return headerBase64;
    }

    /**
     * 生成JWT
     *
     * @return
     */
    public static <T> String createJWT(Integer rulePid,String ruleName) throws Exception {
        StringBuilder jwtSb = new StringBuilder();
        StringBuilder headerPlayloadSb = new StringBuilder();

        String tokenHeaderBase64 = tokenHeaderBase64();
        String tokenPayloadBase64 = tokenPayloadBase64(rulePid,ruleName);

        jwtSb.append(tokenHeaderBase64);
        jwtSb.append(".");
        jwtSb.append(tokenPayloadBase64);
        jwtSb.append(".");

        headerPlayloadSb.append(tokenHeaderBase64);
        headerPlayloadSb.append(tokenPayloadBase64);

        String headerPlayloadSalt = SaltUtil.addSalt(headerPlayloadSb.toString());

        String key = DesUtil.initKey(TOKEN_DES_KEY);

        String signature = Base64Util.encryptBASE64(DesUtil.encrypt(headerPlayloadSalt.getBytes(), key));

        jwtSb.append(signature);

        return Base64Util.encryptBASE64(jwtSb.toString().getBytes());
    }

    /**
     * 校验token是否是服务器生成的，以防token被修改
     *
     * @param jwtBase64
     * @return
     * @throws Exception
     */
    public static <T> boolean verifyJWT(String jwtBase64) throws Exception {
        String jwt = new String(Base64Util.decryptBASE64(jwtBase64));

        if (!jwt.contains(".")) {
            return false;
        }

        String[] jwts = jwt.split("\\.");
        if (jwts.length < 3) {
            return false;
        }

        TokenPlayload tTokenPlayload = JsonUtils.jsonToPojo(new String(Base64Util.decryptBASE64(jwts[1])), TokenPlayload.class);
        String key = DesUtil.initKey(TOKEN_DES_KEY);

        //解析出header跟playload
        StringBuilder headerPlayloadSb = new StringBuilder();
        headerPlayloadSb.append(jwts[0]);
        headerPlayloadSb.append(jwts[1]);

        //解析signature
        String headerPlayloadSalt = new String(DesUtil.decrypt(Base64Util.decryptBASE64(jwts[2]), key));

        return SaltUtil.verifyPwd(headerPlayloadSb.toString(), headerPlayloadSalt);
    }
    /**
     * 获得token
     * @param   rulePid,ruleName
     * @return
     * @throws Exception
     */
    public static <T> String getToken(Integer rulePid,String ruleName) throws Exception {
        TokenPlayload<T> xslTokenPlayload = new TokenPlayload<>();
        String jwt = createJWT(rulePid,ruleName);
        return jwt;
    }

    public static void main(String[] args) throws Exception {
        User user = new User(1L,"傻逼");
        String jwt = getToken((int) user.getId(),user.getName());
        System.out.println("jwt:"+jwt);
        System.out.println("verifyJWT:"+verifyJWT(jwt));
        String token = "accessToken:user"+user.getId()+user.getName()+"-"+UUID.randomUUID().toString();
        System.out.println(token);
    }
}