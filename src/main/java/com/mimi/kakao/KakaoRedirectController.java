package com.mimi.kakao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class KakaoRedirectController {

    @Value("${kakao.restapi.key}")
    private String kakaoRestApiKey;

    @Value("${kakao.redirect.uri}")
    private String redirectUri;

    @GetMapping("/oauth/kakao/Redirect")
    public String kakaoCallback(@RequestParam String code) {
        // 카카오 서버로부터 받은 인가 코드를 사용하여 토큰 발급
        String token = getKakaoToken(code);

        // 발급받은 토큰으로 로그인 처리
        // ...

        return "redirect:/timeline/timeline-view"; // 로그인 성공 후 메인 페이지로 리다이렉트
    }

    private String getKakaoToken(String code) {
        // 카카오 서버로부터 액세스 토큰을 받아오는 로직
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://kauth.kakao.com/oauth/token";

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "authorization_code");
        params.put("client_id", kakaoRestApiKey);
        params.put("redirect_uri", redirectUri);
        params.put("code", code);

        Map<String, String> response = restTemplate.postForObject(url, params, Map.class);

        // 'token' 변수를 여기서 선언하고 값을 할당합니다.
        String token = response.get("access_token");

        return token;  // 이제 'token' 변수를 반환할 수 있습니다.
    }
}
