package test;

import com.alibaba.fastjson.JSON;
import com.ytz.mall.oauth.OauthApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;

/**
 * @ClassName: TestKey
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/11/9
 * @Version: V1.0
 */
@SpringBootTest(classes = OauthApplication.class)
@RunWith(SpringRunner.class)
public class TestKey {

    @Test
    public void test1() {
        ClassPathResource resource = new ClassPathResource("mallshopping.jks");

        KeyStoreKeyFactory keyFactory = new KeyStoreKeyFactory(resource, "mall_shopping".toCharArray());

        KeyPair keyPair = keyFactory.getKeyPair("mall_shopping", "mall_shopping".toCharArray());

        // 获取私钥
        RSAPrivateKey aPrivate = (RSAPrivateKey) keyPair.getPrivate();

        HashMap<String, Object> map = new HashMap<>();

        map.put("username", "www");
        map.put("address", "bj");

        Jwt jwt = JwtHelper.encode(JSON.toJSONString(map), new RsaSigner(aPrivate));

        // 获取令牌
        String encoded = jwt.getEncoded();
        System.out.println(encoded);

    }

    /**
     * 解析令牌
     */
    @Test
    public void test2() {
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhZGRyZXNzIjoiYmoiLCJ1c2VybmFtZSI6Ind3dyJ9.rwyaLlkt57_hL9qBYd0N-T-DffOUDyonC9dC_nwpNLdKQNMTCWWnq-6N3uHhhIi1DOlqFzd1mptWF659vXXr-eP-qf3vCb3USR0UcUBI3ayLCs_nQxmxfP2QmeK0oMg-Hv5iinY5qnRVWSIO343OcVBTCNcYBwK_qHBFFYsZiFVl6_dulEuBuEbjge9lp9T5j1F6DnFyjMRRyFFRXA-Kuvt7blsoz_BDkG274g4vurQ08fyE3Y-Fccef380EXwTTttQTuV4bQmBi5J-3NBM8XsuF7tG6DsfpCPOZfrjmJrQAJhFc58yIZkw5letiDDlIWb66BE2dsAuvploXwAFMJA";
        String pk = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0Tv1IxNChXuTvhrBXwDyuRGS51gwTEXfye+KcQ40dhWUe+oIyP2OxCyCSEzOJ+WKKGmTzVDGjgVuJoFc3ML2Sx4KkTQwe26WgeZbrS2mq+c1JD6k+xId7M/WJ04DbDRp5oWr7G1Taco+7fKAn8Ngoo8nmNjm8GlDglqiRWofOJcESEwygvRKN/Cx5HsMCiKIWv5PKKPSV81QIra453CabyhP1R9CtqTsrcXvlnUj4zNoN6N4ASIaWBEEf4SW+Hfy4D/dMNA5CxlsETaVqj45C0ho0kjh5ofsVsmNstmFssqh1uRMJRLu82xsNEkdu1R8M2orOHB8Nv5sWCOWzINtCwIDAQAB-----END PUBLIC KEY-----";
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(pk));

        String claims = jwt.getClaims();
        System.out.println(claims);

    }

}
