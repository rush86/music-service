package com.musicservice.core.service;

import com.musicservice.client.WikidataClient;
import com.musicservice.client.WikipediaClient;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WikipediaDataService {

  private final WikidataClient wikidataClient;
  private final WikipediaClient wikipediaClient;

  public WikipediaDataService(WikidataClient wikidataClient, WikipediaClient wikipediaClient) {
    this.wikidataClient = wikidataClient;
    this.wikipediaClient = wikipediaClient;
  }

  public String getWikipediaDescription(String wikiDataLink) {
    try {
      var wikidataResponse = wikidataClient.getWikidataByLink(wikiDataLink);
      if (Objects.nonNull(wikidataResponse) && Objects.nonNull(wikidataResponse.getSitelinks())){
        var siteLinks = wikidataResponse.getSitelinks();
        return wikipediaClient.getWikipediaByTitle(siteLinks.getEnwiki().getUrl()).getExtract();
      }
    } catch (Exception e) {
      log.error("Error: ", e);
    }

    return null;
  }
}