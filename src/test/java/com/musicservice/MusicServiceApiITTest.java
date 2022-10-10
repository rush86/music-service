package com.musicservice;

import static com.musicservice.MusicServiceApiITTest.EXTERNAL_API_WIREMOCK_PORT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.musicservice.api.dto.MusicArtistDTO;
import com.musicservice.testutils.ExternalApiWireMock;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.actuate.metrics.AutoConfigureMetrics;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

@ActiveProfiles({"localhost, test"})
@Slf4j
@TestPropertySource(
    properties = {
        "service.music-brainz.api.url=http://localhost:" + EXTERNAL_API_WIREMOCK_PORT
    }
)
@AutoConfigureMetrics
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MusicServiceApiITTest {

  public static final int EXTERNAL_API_WIREMOCK_PORT = 8777;
  public static final String TEST_MBID = "f27ec8db-af05-4f36-916e-3d57f91ecf5e";

  private static ExternalApiWireMock apiMock;

  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int servicePort;

  private final String baseUrl = "http://localhost:";

  @BeforeAll
  static void beforeAll() {
    apiMock = ExternalApiWireMock.getInstance();
  }

  @BeforeEach
  void setUp() {
    apiMock.resetAll();
    RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
        .messageConverters(getMessageConvertersWithoutXml()).rootUri(baseUrl + servicePort);
    restTemplate = new TestRestTemplate(restTemplateBuilder);
  }

  @Test
  @DisplayName("Given valid MBID, we should get Artist Information")
  void givenValidMbid_shouldReturnArtistInformation() {
    var result = restTemplate.getForEntity(baseUrl + servicePort + "/musify/music-artist/details/f27ec8db-af05-4f36-916e-3d57f91ecf5e",
        MusicArtistDTO.class);

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertNotNull(result.getHeaders().get("Cache-Control"));
    assertEquals("US", result.getBody().country());
    assertEquals("f27ec8db-af05-4f36-916e-3d57f91ecf5e", result.getBody().mbid());
    assertTrue(result.getBody().musicAlbumDTOS().size() == 25);
  }

  @Test
  @DisplayName("Given invalid MBID, we should get 404 Not Found Error")
  void givenInValidMbid_shouldReturn404NotFoundException() {
    var result = restTemplate.getForEntity(baseUrl + servicePort + "/musify/music-artist/details/f27ec8db-af05-4f36-916e-3d57f91ecf51",
        MusicArtistDTO.class);

    assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
  }

  private static List<HttpMessageConverter<?>> getMessageConvertersWithoutXml() {
    List<HttpMessageConverter<?>> messageConverters = new RestTemplate().getMessageConverters();
    messageConverters.removeIf(converter -> converter instanceof MappingJackson2XmlHttpMessageConverter);
    return messageConverters;
  }
}
