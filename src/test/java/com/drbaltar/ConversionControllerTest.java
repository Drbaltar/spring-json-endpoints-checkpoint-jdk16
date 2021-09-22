package com.drbaltar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ConversionController.class)
public class ConversionControllerTest {

    @Autowired
    MockMvc mvc;

    String testInput = """
                {
                  "activities": [
                    {
                      "user": {
                        "id": 1467,
                        "username": "someuser",
                        "emails": [
                          {"id": 91, "address": "personal@example.com", "primary": true},
                          {"id": 47, "address": "work@example.com", "primary": false}
                        ]
                      },
                      "status": {
                        "text": "Just went snowboarding today!",
                        "date": "2017-04-02 01:32"
                      }
                    },
                    {
                      "user": {
                        "id": 98732,
                        "username": "otheruser",
                        "emails": [
                          {"id": 22, "address": "other@example.com", "primary": false},
                          {"id": 35, "address": "otherprimary@example.com", "primary": true}
                        ]
                      },
                      "status": {
                        "text": "Great times!",
                        "date": "2017-04-02 01:32"
                      }
                    }
                  ]
                }
            """;

    String expectedDetailedView = """
            [
              {
                "userId": 1467,
                "user": "someuser",
                "email": "personal@example.com",
                "date": "2017-04-02 01:32",
                "statusText": "Just went snowboarding today!"
              },
              {
                "userId": 98732,
                "user": "otheruser",
                "email": "otherprimary@example.com",
                "date": "2017-04-02 01:32",
                "statusText": "Great times!"
              }
            ]
            """;

    String expectedCompactView = """
            [
              {
                "user": "someuser",
                "date": "2017-04-02 01:32",
                "statusText": "Just went snowboarding today!"
              },
              {
                "user": "otheruser",
                "date": "2017-04-02 01:32",
                "statusText": "Great times!"
              }
            ]
            """;

    String expectedMailingList = """
            [
                {
                    "username": "someuser",
                    "addresses": ["personal@example.com", "work@example.com"]
                },
                {
                    "username": "otheruser",
                    "addresses": ["other@example.com","otherprimary@example.com"]
                }
            ]
            """;

    @Test
    void shouldReturnDetailedViewOfSentJSON() throws Exception {
        RequestBuilder request = post("/activities/simplify")
                .content(testInput)
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/vnd.galvanize.detailed+json");

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(expectedDetailedView));
    }

    @Test
    void shouldReturnCompactViewOfSentJSON() throws Exception {
        RequestBuilder request = post("/activities/simplify")
                .content(testInput)
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/vnd.galvanize.compact+json");

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(expectedCompactView));
    }

    @Test
    void shouldReturnMailingListOfSentJSON() throws Exception {
        RequestBuilder request = post("/activities/mailing-list")
                .content(testInput)
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/json");

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(expectedMailingList));
    }
}
