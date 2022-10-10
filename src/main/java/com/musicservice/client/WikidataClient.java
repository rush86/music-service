package com.musicservice.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicservice.client.model.WikidataResponse;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WikidataClient {

  private final HashMap<String, JsonNode> WIKIDATA_MAP;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public WikidataClient() throws JsonProcessingException {

    WIKIDATA_MAP = new HashMap<>();
    JsonNode dataInput = objectMapper.readTree("""
    {
              "pageid": 3813,
              "ns": 0,
              "title": "Q2831",
              "lastrevid": 1729264576,
              "modified": "2022-09-15T15:10:31Z",
              "type": "item",
              "id": "Q2831",
              "sitelinks": {
                "enwiki": {
                  "site": "enwiki",
                  "title": "Michael Jackson",
                  "badges": [
                    "Q17437796"
                  ],
                  "url": "https://en.wikipedia.org/wiki/Michael_Jackson"
                },
                "enwikinews": {
                  "site": "enwikinews",
                  "title": "Category:Michael Jackson",
                  "badges": [],
                  "url": "https://en.wikinews.org/wiki/Category:Michael_Jackson"
                },
                "enwikiquote": {
                  "site": "enwikiquote",
                  "title": "Michael Jackson",
                  "badges": [],
                  "url": "https://en.wikiquote.org/wiki/Michael_Jackson"
                }
              }
            }
    """);
    WIKIDATA_MAP.put("https://www.wikidata.org/wiki/Q2831", dataInput);
    //WIKIDATA_MAP.put("", "");
  }

  public WikidataResponse getWikidataByLink(String wikidataLink) throws JsonProcessingException {
    return objectMapper.treeToValue(WIKIDATA_MAP.get(wikidataLink), WikidataResponse.class);
  }
}
