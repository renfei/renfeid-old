package net.renfei.utils;

import net.renfei.exception.BusinessException;
import net.renfei.model.kitbox.IcpAuthVo;
import net.renfei.model.kitbox.IcpQueryVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.EntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * ICP 查询工具类
 *
 * @author renfei
 */
public class IcpQueryUtil {
    private static final Logger logger = LoggerFactory.getLogger(IcpQueryUtil.class);

    public IcpQueryVo queryIcpInfo(String domain) throws IOException, ParseException {
        domain = domain.toLowerCase();
        if (!domain.startsWith("https://") && !domain.startsWith("http://")) {
            domain = "http://" + domain;
        }
        URL url = new URL(domain);
        domain = url.getHost();
        if (domain.startsWith("www.")) {
            domain = domain.substring(4);
        }
        long timeStamp = System.currentTimeMillis();
        String authKey = DigestUtils.md5Hex("testtest" + timeStamp);
        String token = post("auth", "authKey=" + authKey + "&timeStamp=" + timeStamp, "application/x-www-form-urlencoded;charset=UTF-8", "0");
        IcpAuthVo icpAuthVo = JacksonUtil.string2Obj(token, IcpAuthVo.class);
        assert icpAuthVo != null;
        if (icpAuthVo.getCode() != 200 || !icpAuthVo.getSuccess()) {
            logger.error("ICP auth查询失败：{}", token);
            throw new BusinessException("服务暂时不可用");
        }
        token = icpAuthVo.getParams().getBussiness();
        String query = post("icpAbbreviateInfo/queryByCondition", "{\"pageNum\":\"\",\"pageSize\":\"\",\"unitName\":\"" + domain + "\"}", "application/json;charset=UTF-8", token);
        return JacksonUtil.string2Obj(query, IcpQueryVo.class);
    }

    private String post(String url, String data, String content, String token) throws IOException, ParseException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            String vIp = "101."
                    + ThreadLocalRandom.current().nextInt(0, 255)
                    + "."
                    + ThreadLocalRandom.current().nextInt(0, 255)
                    + "."
                    + ThreadLocalRandom.current().nextInt(0, 255);
            RequestConfig requestConfig = RequestConfig.DEFAULT;
            HttpPost httpPost = new HttpPost("https://hlwicpfwc.miit.gov.cn/icpproject_query/api/" + url);
            httpPost.setConfig(requestConfig);
            httpPost.setHeaders(buildHeaders(content, token, vIp));
            HttpEntity httpEntity = null;
            if ("application/x-www-form-urlencoded;charset=UTF-8".equals(content)) {
                List<NameValuePair> parameters = new ArrayList<>();
                for (String par : data.split("&")
                ) {
                    parameters.add(new BasicNameValuePair(par.split("=")[0], par.split("=")[1]));
                }
                httpEntity = EntityBuilder.create()
                        .setParameters(parameters)
                        .build();
            } else {
                httpEntity = EntityBuilder.create()
                        .setText(data)
                        .build();
            }
            httpPost.setEntity(httpEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            String responseString = EntityUtils.toString(response.getEntity());
            if (response.getCode() == 200) {
                return responseString;
            }
            return null;
        }
    }

    private Header[] buildHeaders(String content, String token, String ip) {
        return new Header[]{
                new Header() {
                    @Override
                    public boolean isSensitive() {
                        return false;
                    }

                    @Override
                    public String getName() {
                        return "Content-Type";
                    }

                    @Override
                    public String getValue() {
                        return content;
                    }
                },
                new Header() {
                    @Override
                    public boolean isSensitive() {
                        return false;
                    }

                    @Override
                    public String getName() {
                        return "Origin";
                    }

                    @Override
                    public String getValue() {
                        return "https://beian.miit.gov.cn/";
                    }
                },
                new Header() {
                    @Override
                    public boolean isSensitive() {
                        return false;
                    }

                    @Override
                    public String getName() {
                        return "Referer";
                    }

                    @Override
                    public String getValue() {
                        return "https://beian.miit.gov.cn/";
                    }
                },
                new Header() {
                    @Override
                    public boolean isSensitive() {
                        return false;
                    }

                    @Override
                    public String getName() {
                        return "token";
                    }

                    @Override
                    public String getValue() {
                        return token;
                    }
                },
                new Header() {
                    @Override
                    public boolean isSensitive() {
                        return false;
                    }

                    @Override
                    public String getName() {
                        return "User-Agent";
                    }

                    @Override
                    public String getValue() {
                        return "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.87 Safari/537.36";
                    }
                },
                new Header() {
                    @Override
                    public boolean isSensitive() {
                        return false;
                    }

                    @Override
                    public String getName() {
                        return "CLIENT-IP";
                    }

                    @Override
                    public String getValue() {
                        return ip;
                    }
                },
                new Header() {
                    @Override
                    public boolean isSensitive() {
                        return false;
                    }

                    @Override
                    public String getName() {
                        return "X-FORWARDED-FOR";
                    }

                    @Override
                    public String getValue() {
                        return ip;
                    }
                }
        };
    }
}
