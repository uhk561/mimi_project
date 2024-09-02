package com.mimi.kakao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class KakaoLoginController {

    @Value("${kakao.restapi.key}")
    private String kakaoRestApiKey;

    @PostMapping("/api/kakao/login")
    public ResponseEntity<?> kakaoLogin(@RequestParam String token) {
        RestTemplate restTemplate = new RestTemplate();

        // 카카오 API를 통해 사용자 정보 가져오기
        String url = "https://kapi.kakao.com/v2/user/me";
        Map<String, String> headers = Map.of("Authorization", "Bearer " + token);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, headers, Map.class);

        // 여기서 사용자 정보를 가져와서 회원가입 또는 로그인 처리
        Map<String, Object> userData = response.getBody();

        // 예제: 카카오 사용자 ID 추출
        Long kakaoId = (Long) userData.get("id");

        // 예제: 사용자 회원가입 또는 로그인 처리 로직 추가

        return ResponseEntity.ok().body("로그인 성공");
    }
}