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
    WIKIDATA_MAP.put("https://www.wikidata.org/wiki/Q2831", getDataByUrl("https://www.wikidata.org/wiki/Q2831"));
    WIKIDATA_MAP.put("https://www.wikidata.org/wiki/Q4036392", getDataByUrl("https://www.wikidata.org/wiki/Q4036392"));
    WIKIDATA_MAP.put("", getDataByUrl(""));
  }

  public WikidataResponse getWikidataByLink(String wikidataLink) throws JsonProcessingException {
    return objectMapper.treeToValue(WIKIDATA_MAP.get(wikidataLink), WikidataResponse.class);
  }

  private JsonNode getDataByUrl(String url) throws JsonProcessingException {
    if (url.equals("https://www.wikidata.org/wiki/Q4036392")) {
      return objectMapper.readTree("""
     {
          "pageid": 3846861,
          "ns": 0,
          "title": "Q4036392",
          "lastrevid": 1743193293,
          "modified": "2022-10-04T21:25:00Z",
          "type": "item",
          "id": "Q4036392",
          "sitelinks": {
            "commonswiki": {
              "site": "commonswiki",
              "title": "Category:Cold Cave",
              "badges": [],
              "url": "https://commons.wikimedia.org/wiki/Category:Cold_Cave"
            },
            "dawiki": {
              "site": "dawiki",
              "title": "Cold Cave",
              "badges": [],
              "url": "https://da.wikipedia.org/wiki/Cold_Cave"
            },
            "enwiki": {
              "site": "enwiki",
              "title": "Cold Cave",
              "badges": [],
              "url": "https://en.wikipedia.org/wiki/Cold_Cave"
            }
          }
        }
    """);
    }

    if (url.equals("https://www.wikidata.org/wiki/Q11649")) {
      return objectMapper.readTree("""
    {
      "pageid": 13180,
      "ns": 0,
      "title": "Q11649",
      "lastrevid": 1747510338,
      "modified": "2022-10-10T14:26:45Z",
      "type": "item",
      "id": "Q11649",
      "sitelinks": {
        "enwiki": {
          "site": "enwiki",
          "title": "Nirvana (band)",
          "badges": [
            "Q17437796"
          ],
          "url": "https://en.wikipedia.org/wiki/Nirvana_(band)"
        },
        "enwikiquote": {
          "site": "enwikiquote",
          "title": "Nirvana (band)",
          "badges": [
    
          ],
          "url": "https://en.wikiquote.org/wiki/Nirvana_(band)"
        }
      }
    }
    """);
    }

    return objectMapper.readTree("""
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
  }
}
