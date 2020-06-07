package com.reviewcongty.backend.api.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class GoogleRecaptchaFilter implements Filter {
    private static final String RECAPTCHA_RESPONSE_HEADER = "g-recaptcha-response";
    private static final String GOOGLE_RECAPTCHA_VERIFY_URL =
            "https://www.google.com/recaptcha/api/siteverify";

    @Value("${google.recaptcha.secret-key}")
    String recaptchaSecret;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if (!"POST".equals(req.getMethod())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String ip = req.getRemoteAddr();
        String recaptchaResponse = req.getHeader(RECAPTCHA_RESPONSE_HEADER);

        boolean valid = verifyRecaptcha(recaptchaResponse, ip);

        if (valid) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write(convertObjectToJson(new Object() {
                public String message = "Not pass recaptcha!";
            }));
        }
    }

    private Boolean verifyRecaptcha(String recaptchaResponse, String ip) {
        Map<String, String> body = new HashMap<>();
        body.put("secret", recaptchaSecret);
        body.put("response", recaptchaResponse);
        body.put("remoteip", ip);
        log.debug("Request body for recaptcha: {}", body);

        ResponseEntity<Map> recaptchaResponseEntity =
                restTemplateBuilder.build()
                        .postForEntity(GOOGLE_RECAPTCHA_VERIFY_URL +
                                        "?secret={secret}&response={response}&remoteip={remoteip}",
                                body, Map.class, body);
        log.debug("Response from recaptcha: {}", recaptchaResponseEntity);

        Map<String, Object> responseBody = recaptchaResponseEntity.getBody();
        boolean recaptchaSuccess = (Boolean) responseBody.get("success");

        if (!recaptchaSuccess) {
            List<String> errorCodes = (List) responseBody.get("error-codes");
            log.warn("Verify recaptcha is fail with error-codes: {}", errorCodes);
        }

        return recaptchaSuccess;
    }

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        return mapper.writeValueAsString(object);
    }
}
