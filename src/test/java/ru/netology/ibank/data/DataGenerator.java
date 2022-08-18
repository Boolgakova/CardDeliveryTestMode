package ru.netology.ibank.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Value;

import static io.restassured.RestAssured.given;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateLogin() {
        Faker faker = new Faker();
        String login = faker.name().username();
        return login;
    }

    public static String generatePassword() {
        Faker faker = new Faker();
        String password = faker.code().asin();
        return password;
    }

    public static class Authorization {
        private Authorization() {
        }

        public static RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(9999)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        public static setUpAll() {
            // сам запрос
            given() // "дано"
                    .spec(requestSpec) // указываем, какую спецификацию используем
                    .body(Registration.generateUser()) // передаём в теле объект, который будет преобразован в JSON
                    .when() // "когда"
                    .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                    .then() // "тогда ожидаем"
                    .statusCode(200); // код 200 OK

        }

        public static class Registration {
            private Registration() {
            }

            public static UserInfo generateUser(String status) {
                UserInfo user = new UserInfo(generateLogin(), generatePassword(), status);
                return user;
            }
        }

        @Value
        public static class UserInfo {
            String login;
            String password;
            String status;
        }
    }
}
