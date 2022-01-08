package net.renfei.utils.wechat;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

public abstract class SignatureUtil {

	private static Logger logger = LoggerFactory.getLogger(SignatureUtil.class);
	
	/**
	 * 生成sign HMAC-SHA256 或 MD5 签名
	 * @param map map
	 * @param paternerKey paternerKey
	 * @return sign
	 */
	public static String generateSign(Map<String, String> map,String paternerKey){
		return generateSign(map, null, paternerKey);
	}
	
	/**
	 * 生成sign HMAC-SHA256 或 MD5 签名
	 * @param map map
	 * @param signType HMAC-SHA256 或 MD5
	 * @param paternerKey paternerKey
	 * @return sign
	 */
	public static String generateSign(Map<String, String> map, String signType, String paternerKey){
		Map<String, String> tmap = MapUtil.order(map);
		tmap.remove("sign");
		String str = MapUtil.mapJoin(tmap, false, false);
		if(signType == null){
			signType = tmap.get("sign_type");
		}
		if("HMAC-SHA256".equalsIgnoreCase(signType)){
			try {
				  Mac sha256HMAC = Mac.getInstance("HmacSHA256");
				  SecretKeySpec secretKey = new SecretKeySpec(paternerKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
				sha256HMAC.init(secretKey);
				  return Hex.encodeHexString(sha256HMAC.doFinal((str+"&key="+paternerKey).getBytes(StandardCharsets.UTF_8))).toUpperCase();
			} catch (Exception e) {
				logger.error("", e);
			}
			return null;
		}else{//default MD5
			return DigestUtils.md5Hex(str+"&key="+paternerKey).toUpperCase();
		}
	}
	
	/**
	 * 生成事件消息接收签名
	 * @param token token
	 * @param timestamp timestamp
	 * @param nonce nonce
	 * @return str
	 */
	public static String generateEventMessageSignature(String token, String timestamp,String nonce) {
		String[] array = new String[]{token,timestamp,nonce};
		Arrays.sort(array);
		String s = StringUtils.arrayToDelimitedString(array, "");
		return DigestUtils.shaHex(s);
	}

	/**
	 * mch 支付、代扣异步通知签名验证
	 * @param map 参与签名的参数
	 * @param key mch key
	 * @return boolean
	 */
	public static boolean validateSign(Map<String,String> map,String key){
		return validateSign(map, null, key);
	}
	
	/**
	 * mch 支付、代扣API调用签名验证
	 * 
	 * @param map 参与签名的参数
	 * @param sign_type HMAC-SHA256 或 MD5 
	 * @param key mch key
	 * @return boolean
	 */
	public static boolean validateSign(Map<String,String> map,String sign_type,String key){
		if(map.get("sign") == null){
			return false;
		}
		return map.get("sign").equals(generateSign(map,sign_type,key));
	}

}
