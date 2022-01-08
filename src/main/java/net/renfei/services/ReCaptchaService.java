package net.renfei.services;

import net.renfei.model.auth.ReCaptchaVerify;
import net.renfei.model.auth.ReCaptchaVerifyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * reCAPTCHA
 *
 * @author renfei
 */
@FeignClient(name = "reCAPTCHA", url = "https://www.recaptcha.net")
public interface ReCaptchaService {
    /**
     * Token Restrictions
     * Each reCAPTCHA user response token is valid for two minutes, and can only be verified once to prevent replay attacks. If you need a new token, you can re-run the reCAPTCHA verification.
     * After you get the response token, you need to verify it within two minutes with reCAPTCHA using the following API to ensure the token is valid.
     *
     * @param reCaptchaVerify
     * @return ReCaptchaVerifyResponse
     */
    @PostMapping("/recaptcha/api/siteverify")
    ReCaptchaVerifyResponse siteVerify(@RequestBody ReCaptchaVerify reCaptchaVerify);
}
