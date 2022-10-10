package com.musicservice.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicservice.client.model.WikipediaResponse;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WikipediaClient {

  private final HashMap<String, JsonNode> WIKIPEDIA_MAP;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public WikipediaClient() throws JsonProcessingException {
    WIKIPEDIA_MAP = new HashMap<>();
    WIKIPEDIA_MAP.put("https://en.wikipedia.org/wiki/Michael_Jackson", objectMapper.readTree("{\"type\":\"standard\",\"title\":\"Michael Jackson\",\"displaytitle\":\"<span class=\\\"mw-page-title-main\\\">Michael Jackson</span>\",\"namespace\":{\"id\":0,\"text\":\"\"},\"wikibase_item\":\"Q2831\",\"titles\":{\"canonical\":\"Michael_Jackson\",\"normalized\":\"Michael Jackson\",\"display\":\"<span class=\\\"mw-page-title-main\\\">Michael Jackson</span>\"},\"pageid\":14995351,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Michael_Jackson_in_1988.jpg/191px-Michael_Jackson_in_1988.jpg\",\"width\":191,\"height\":320},\"originalimage\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/3/31/Michael_Jackson_in_1988.jpg\",\"width\":300,\"height\":502},\"lang\":\"en\",\"dir\":\"ltr\",\"revision\":\"1114378591\",\"tid\":\"4f94b5b0-458b-11ed-bca8-a1d4f9adb6bf\",\"timestamp\":\"2022-10-06T06:00:33Z\",\"description\":\"American singer, songwriter, and dancer (1958–2009)\",\"description_source\":\"local\",\"content_urls\":{\"desktop\":{\"page\":\"https://en.wikipedia.org/wiki/Michael_Jackson\",\"revisions\":\"https://en.wikipedia.org/wiki/Michael_Jackson?action=history\",\"edit\":\"https://en.wikipedia.org/wiki/Michael_Jackson?action=edit\",\"talk\":\"https://en.wikipedia.org/wiki/Talk:Michael_Jackson\"},\"mobile\":{\"page\":\"https://en.m.wikipedia.org/wiki/Michael_Jackson\",\"revisions\":\"https://en.m.wikipedia.org/wiki/Special:History/Michael_Jackson\",\"edit\":\"https://en.m.wikipedia.org/wiki/Michael_Jackson?action=edit\",\"talk\":\"https://en.m.wikipedia.org/wiki/Talk:Michael_Jackson\"}},\"extract\":\"Michael Joseph Jackson was an American singer, songwriter, dancer, and philanthropist. Dubbed the \\\"King of Pop\\\", he is regarded as one of the most significant cultural figures of the 20th century. Over a four-decade career, his contributions to music, dance, and fashion, along with his publicized personal life, made him a global figure in popular culture. Jackson influenced artists across many music genres; through stage and video performances, he popularized complicated dance moves such as the moonwalk, to which he gave the name, as well as the robot. He is the most awarded individual music artist in history.\",\"extract_html\":\"<p><b>Michael Joseph Jackson</b> was an American singer, songwriter, dancer, and philanthropist. Dubbed the \\\"King of Pop\\\", he is regarded as one of the most significant cultural figures of the 20th century. Over a four-decade career, his contributions to music, dance, and fashion, along with his publicized personal life, made him a global figure in popular culture. Jackson influenced artists across many music genres; through stage and video performances, he popularized complicated dance moves such as the moonwalk, to which he gave the name, as well as the robot. He is the most awarded individual music artist in history.</p>\"}"));
    //WIKIPEDIA_MAP.put("", "");
  }

  public WikipediaResponse getWikipediaByTitle(String titleLink) throws JsonProcessingException {
    return objectMapper.treeToValue(WIKIPEDIA_MAP.get(titleLink), WikipediaResponse.class);
  }
}
