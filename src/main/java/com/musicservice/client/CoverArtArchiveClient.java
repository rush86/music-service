package com.musicservice.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicservice.client.model.CoverArtArchiveResponse;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CoverArtArchiveClient {

  private final HashMap<String, JsonNode> COVER_ART_MAP;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public CoverArtArchiveClient() throws JsonProcessingException {
    COVER_ART_MAP = new HashMap<>();

    JsonNode dataInput = objectMapper.readTree("""
        {"images":[{"approved":true,"back":false,"comment":"","edit":69681264,"front":true,"id":26167697750,"image":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167697750.jpg","thumbnails":{"1200":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167697750-1200.jpg","250":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167697750-250.jpg","500":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167697750-500.jpg","large":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167697750-500.jpg","small":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167697750-250.jpg"},"types":["Front"]},{"approved":true,"back":true,"comment":"","edit":69681270,"front":false,"id":26167698215,"image":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167698215.jpg","thumbnails":{"1200":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167698215-1200.jpg","250":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167698215-250.jpg","500":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167698215-500.jpg","large":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167698215-500.jpg","small":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167698215-250.jpg"},"types":["Back"]},{"approved":true,"back":false,"comment":"","edit":69681273,"front":false,"id":26167698655,"image":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167698655.jpg","thumbnails":{"1200":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167698655-1200.jpg","250":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167698655-250.jpg","500":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167698655-500.jpg","large":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167698655-500.jpg","small":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167698655-250.jpg"},"types":["Medium"]},{"approved":true,"back":false,"comment":"","edit":69681275,"front":false,"id":26167699078,"image":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167699078.jpg","thumbnails":{"1200":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167699078-1200.jpg","250":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167699078-250.jpg","500":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167699078-500.jpg","large":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167699078-500.jpg","small":"http://coverartarchive.org/release/51258fa4-29c5-4b86-bfda-b630573ec222/26167699078-250.jpg"},"types":["Medium"]}],"release":"https://musicbrainz.org/release/51258fa4-29c5-4b86-bfda-b630573ec222"}
        """);
    COVER_ART_MAP.put("97e0014d-a267-33a0-a868-bb4e2552918a", dataInput);
    COVER_ART_MAP.put("f32fab67-77dd-3937-addc-9062e28e4c37", dataInput);
  }

  public CoverArtArchiveResponse getCoverArtByMbid(String id)  {
    try {
      return objectMapper.treeToValue(COVER_ART_MAP.get(id), CoverArtArchiveResponse.class);
    } catch (Exception e) {
      return CoverArtArchiveResponse.builder().build();
    }
  }
}
