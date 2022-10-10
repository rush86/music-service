package com.musicservice.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.musicservice.client.model.MusicBrainzArtist;
import java.io.IOException;
import java.util.UUID;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

class MusicBrainzClientTest {

  private static MockWebServer mockWebServer;

  private MusicBrainzClient musicBrainzClient;

  ObjectMapper objectMapper = new ObjectMapper()
      .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
      .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
      .setSerializationInclusion(JsonInclude.Include.NON_NULL);

  private RestTemplate restTemplate = new RestTemplateBuilder()
      .additionalMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
      .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
      .build();

  @BeforeAll
  static void setUp() throws IOException {
    mockWebServer = new MockWebServer();
    mockWebServer.start();
  }

  @BeforeEach
  void initialize() {
    String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
    musicBrainzClient = new MusicBrainzClient(restTemplate, baseUrl);
  }

  @AfterAll
  static void tearDown() throws IOException {
    mockWebServer.shutdown();
  }

  @Test
  @DisplayName("Get Artist Info")
  void should_get_artist_info_for_given_mbid() throws InterruptedException, JsonProcessingException {

    var expectedResponse = MusicBrainzArtist.builder()
        .name("Test Artist")
        .gender("Male")
        .country("Sweden")
        .build();
    mockWebServer.enqueue(
        new MockResponse()
            .setResponseCode(200)
            .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .setBody(objectMapper.writeValueAsString(expectedResponse))
    );

    UUID randomUuid = UUID.randomUUID();
    var actualResponse = musicBrainzClient.getArtistByMbid(randomUuid);
    RecordedRequest recordedRequest = mockWebServer.takeRequest();

    assertNotNull(recordedRequest.getHeader("Accept-Encoding"));
    assertEquals("gzip", recordedRequest.getHeader("Accept-Encoding"));
    assertEquals(expectedResponse, actualResponse);
  }

}