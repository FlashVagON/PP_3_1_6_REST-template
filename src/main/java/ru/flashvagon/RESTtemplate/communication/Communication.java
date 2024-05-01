package ru.flashvagon.RESTtemplate.communication;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.flashvagon.RESTtemplate.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class Communication {

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;
    private final String URL = "http://94.198.50.185:7081/api/users";

    public Communication(RestTemplate restTemplate, HttpHeaders headers) {
        this.restTemplate = restTemplate;
        this.headers = headers;
        this.headers.set("Cookie",
                String.join(";", Objects.requireNonNull(restTemplate.headForHeaders(URL).get("Set-Cookie"))));
    }

    public String getAnswer() {
        return addUser().getBody() + updateUser().getBody() + deleteUser().getBody();
    }

    // Добавление пользователя - …/api/users ( POST )
    private ResponseEntity<String> addUser() {
        User user = new User(3L, "James", "Brown", (byte) 5);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> temp = restTemplate.postForEntity(URL, entity, String.class);
        System.out.println(temp);
        return temp;
    }

    // Изменение пользователя - …/api/users ( PUT )
    private ResponseEntity<String> updateUser() {
        User user = new User(3L, "Thomas", "Shelby", (byte) 5);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> temp = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class, 3);
        System.out.println(temp);
        return temp;
    }

    // Удаление пользователя - …/api/users /{id} ( DELETE )
    private ResponseEntity<String> deleteUser() {
        Map<String, Long> uriVariables = new HashMap<>() {{
            put("id", 3L);
        }};
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> temp = restTemplate.exchange(URL + "/{id}", HttpMethod.DELETE, entity, String.class, uriVariables);
        System.out.println(temp);
        return temp;
    }
}
