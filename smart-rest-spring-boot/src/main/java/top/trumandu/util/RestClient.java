package top.trumandu.util;

import jakarta.annotation.Resource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author Truman.P.Du
 * @date 2023/07/31
 * @description 参考：<a href="https://github.com/coder-amiao/simplest-api">simplest-api</a>
 */
@SuppressWarnings("unused")
@Component
public class RestClient {
    @Resource
    private RestTemplate restTemplate;

    public ResponseEntity<String> doGet(String url) {
        ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);
        return response;
    }

    public ResponseEntity<String> doPost(String url, Object jsonData) {
        return this.doPost(url, jsonData, null);
    }

    public ResponseEntity<String> doPost(String url, Object jsonData, Map<String, String> head) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (head != null) {
            headers.addAll((MultiValueMap) head);
        }
        HttpEntity<Object> requestEntity = new HttpEntity<>(jsonData, headers);
        ResponseEntity<String> response = this.restTemplate.postForEntity(url, requestEntity, String.class);
        return response;
    }

    public String doPut(String url, Object jsonData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(jsonData, headers);
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        return responseEntity.getBody();
    }

    public ResponseEntity<String> doDelete(String url) {
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
        return responseEntity;
    }


    public ResponseEntity<String> doPostForm(String url, Map<String, Object> map) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            params.add(entry.getKey(), entry.getValue().toString());
        }
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
        return restTemplate.postForEntity(url, entity, String.class);
    }

    public String sendFilePost(String url, File file) throws IOException {
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        FileSystemResource resource = new FileSystemResource(file);
        map.add("file", resource);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> httpEntity = new HttpEntity<>(map, headers);

        return restTemplate.postForObject(url, httpEntity, String.class);
    }


}
