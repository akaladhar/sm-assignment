package com.sm.assignment;

import com.sm.assignment.pojo.GetSavingsResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssignmentApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceInfoApplicationTest {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testGetSavings() throws Exception {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<GetSavingsResponse> response = restTemplate.exchange(
                createURLWithPort("/getSavings?date=10/06/2004&mpg=10&mileage=200&fuel=petrol"),
                HttpMethod.GET, entity, GetSavingsResponse.class);

        assertEquals("Response is not correct", "Today's journey is costlier by 48.56 pounds", ((GetSavingsResponse)response.getBody()).getMessage());
        assertEquals("HTTP status code ", HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetSavingsError() throws Exception {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/getSavings?date=10/06/2004&mpg=10&mileage=-200&fuel=petrol"),
                HttpMethod.GET, entity, String.class);

        Assert.assertEquals("Invalid data supplied", "Invalid mileage supplied", response.getBody());
        assertEquals("HTTP status code ", HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
