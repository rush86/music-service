package com.musicservice.testutils;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.musicservice.MusicServiceApiITTest.EXTERNAL_API_WIREMOCK_PORT;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.tomakehurst.wiremock.WireMockServer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExternalApiWireMock {

  private final WireMockServer wireMockServer;

  private static final ExternalApiWireMock instance = new ExternalApiWireMock(EXTERNAL_API_WIREMOCK_PORT);

  ObjectMapper objectMapper = new ObjectMapper()
      .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
      .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
      .setSerializationInclusion(JsonInclude.Include.NON_NULL);

  private ExternalApiWireMock(int port) {
    wireMockServer = new WireMockServer(port);
    wireMockServer.start();
    setupMappings();
  }

  public static ExternalApiWireMock getInstance() {
    return instance;
  }

  public void setupMappings() {
    log.info("Setting up External API wiremock");
    wireMockServer.stubFor(get("/artist/f27ec8db-af05-4f36-916e-3d57f91ecf5e?inc=url-rels+release-groups&fmt=json")
        .willReturn(aResponse()
            .withHeader("Content-Type", "application/json")
            .withStatus(200)
            .withBody(getSuccessResponse())
        ));
  }

  private String getSuccessResponse() {
    return """
        {
            "disambiguation": "“King of Pop”",
            "begin_area": {
                "type": null,
                "disambiguation": "",
                "id": "34357067-8f7f-4c7a-8d5e-99b6e60f7891",
                "sort-name": "Gary",
                "type-id": null,
                "name": "Gary"
            },
            "isnis": [
                "000000011023081X"
            ],
            "gender-id": "36d3d30a-839d-3eda-8cb3-29be4384e4a9",
            "ipis": [
                "00002961801",
                "00523553664"
            ],
            "sort-name": "Jackson, Michael",
            "end-area": {
                "disambiguation": "",
                "type": null,
                "type-id": null,
                "name": "Los Angeles",
                "sort-name": "Los Angeles",
                "id": "1f40c6e1-47ba-4e35-996f-fe6ee5840e62"
            },
            "id": "f27ec8db-af05-4f36-916e-3d57f91ecf5e",
            "gender": "Male",
            "life-span": {
                "end": "2009-06-25",
                "begin": "1958-08-29",
                "ended": true
            },
            "begin-area": {
                "type": null,
                "disambiguation": "",
                "id": "34357067-8f7f-4c7a-8d5e-99b6e60f7891",
                "sort-name": "Gary",
                "type-id": null,
                "name": "Gary"
            },
            "end_area": {
                "disambiguation": "",
                "type": null,
                "type-id": null,
                "name": "Los Angeles",
                "sort-name": "Los Angeles",
                "id": "1f40c6e1-47ba-4e35-996f-fe6ee5840e62"
            },
            "area": {
                "type": null,
                "iso-3166-1-codes": [
                    "US"
                ],
                "disambiguation": "",
                "id": "489ce91b-6658-3307-9877-795b68554c98",
                "sort-name": "United States",
                "name": "United States",
                "type-id": null
            },
            "type": "Person",
            "release-groups": [
                {
                    "title": "Got to Be There",
                    "primary-type": "Album",
                    "secondary-type-ids": [],
                    "disambiguation": "",
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "id": "97e0014d-a267-33a0-a868-bb4e2552918a",
                    "secondary-types": [],
                    "first-release-date": "1971"
                },
                {
                    "id": "51343255-0ad3-3635-9aa2-548ba939b23e",
                    "first-release-date": "1972-08-04",
                    "secondary-types": [],
                    "secondary-type-ids": [],
                    "title": "Ben",
                    "primary-type": "Album",
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "disambiguation": ""
                },
                {
                    "secondary-type-ids": [],
                    "primary-type": "Album",
                    "title": "Music & Me",
                    "disambiguation": "",
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "id": "06b064b9-01e7-32d8-b585-86404584e795",
                    "first-release-date": "1973-04-13",
                    "secondary-types": []
                },
                {
                    "id": "50b9d7de-9124-33c0-83a3-76722bf346e5",
                    "first-release-date": "1975",
                    "secondary-types": [],
                    "secondary-type-ids": [],
                    "primary-type": "Album",
                    "title": "Forever, Michael",
                    "disambiguation": "",
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc"
                },
                {
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "disambiguation": "",
                    "title": "Off the Wall",
                    "primary-type": "Album",
                    "secondary-type-ids": [],
                    "secondary-types": [],
                    "first-release-date": "1979-08-10",
                    "id": "ee749c63-5699-38e0-b565-7e84414648d9"
                },
                {
                    "id": "f32fab67-77dd-3937-addc-9062e28e4c37",
                    "first-release-date": "1982-11-30",
                    "secondary-types": [],
                    "secondary-type-ids": [],
                    "title": "Thriller",
                    "primary-type": "Album",
                    "disambiguation": "",
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc"
                },
                {
                    "id": "a5711a77-42d1-3f4c-830c-e27a96f0800f",
                    "first-release-date": "1987-08-25",
                    "secondary-types": [],
                    "secondary-type-ids": [],
                    "primary-type": "Album",
                    "title": "Bad",
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "disambiguation": ""
                },
                {
                    "first-release-date": "1991-11-25",
                    "secondary-types": [],
                    "id": "d6b52521-0dfa-390f-970f-790174c22752",
                    "disambiguation": "",
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "secondary-type-ids": [],
                    "primary-type": "Album",
                    "title": "Dangerous"
                },
                {
                    "id": "c24c5313-da47-3155-8277-a6a1a4237966",
                    "secondary-types": [],
                    "first-release-date": "2001-10-29",
                    "title": "Invincible",
                    "primary-type": "Album",
                    "secondary-type-ids": [],
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "disambiguation": ""
                },
                {
                    "secondary-types": [
                        "Compilation"
                    ],
                    "first-release-date": "1980-03",
                    "id": "90915175-cc35-3970-99f5-8f279ab59585",
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "disambiguation": "",
                    "primary-type": "Album",
                    "title": "The Best of Michael Jackson",
                    "secondary-type-ids": [
                        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
                    ]
                },
                {
                    "first-release-date": "1981",
                    "secondary-types": [
                        "Compilation"
                    ],
                    "id": "0621bd78-b867-39ab-8606-9636bfd94447",
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "disambiguation": "",
                    "secondary-type-ids": [
                        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
                    ],
                    "title": "One Day in Your Life",
                    "primary-type": "Album"
                },
                {
                    "secondary-type-ids": [
                        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
                    ],
                    "primary-type": "Album",
                    "title": "18 Greatest Hits",
                    "disambiguation": "",
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "id": "54e774a0-7091-31f6-8765-9de5735fcbb1",
                    "first-release-date": "1983",
                    "secondary-types": [
                        "Compilation"
                    ]
                },
                {
                    "id": "22fc8d45-6802-46f8-8a78-6ae823a9ed92",
                    "first-release-date": "1983",
                    "secondary-types": [
                        "Compilation"
                    ],
                    "secondary-type-ids": [
                        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
                    ],
                    "primary-type": "Album",
                    "title": "14 Greatest Hits With the Jackson 5",
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "disambiguation": ""
                },
                {
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "disambiguation": "Compact Command Performances",
                    "secondary-type-ids": [
                        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
                    ],
                    "title": "18 Greatest Hits",
                    "primary-type": "Album",
                    "first-release-date": "1983",
                    "secondary-types": [
                        "Compilation"
                    ],
                    "id": "ffc3f8b5-7b22-40ed-8867-0cad52b6b2ae"
                },
                {
                    "secondary-types": [
                        "Compilation"
                    ],
                    "first-release-date": "1983",
                    "id": "6f33ff5d-025a-42d6-827e-6d5bb5a30b4a",
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "disambiguation": "",
                    "primary-type": "Album",
                    "title": "18 Greatest Hits",
                    "secondary-type-ids": [
                        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
                    ]
                },
                {
                    "disambiguation": "",
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "secondary-type-ids": [
                        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
                    ],
                    "title": "Great Songs and Performances That Inspired the Motown 25th Anniversary Television Special",
                    "primary-type": "Album",
                    "first-release-date": "1983",
                    "secondary-types": [
                        "Compilation"
                    ],
                    "id": "e6696f23-a356-4cff-a096-fdf2a1e1a358"
                },
                {
                    "secondary-types": [
                        "Compilation"
                    ],
                    "first-release-date": "1984-05-15",
                    "id": "500d9b05-68c3-3535-86e3-cf685869efc0",
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "disambiguation": "",
                    "title": "Farewell My Summer Love",
                    "primary-type": "Album",
                    "secondary-type-ids": [
                        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
                    ]
                },
                {
                    "first-release-date": "1986-07",
                    "secondary-types": [
                        "Compilation"
                    ],
                    "id": "b513c135-b957-305b-9c5c-7f829d6195b3",
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "disambiguation": "",
                    "secondary-type-ids": [
                        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
                    ],
                    "title": "Got to Be There / Ben",
                    "primary-type": "Album"
                },
                {
                    "title": "Anthology",
                    "primary-type": "Album",
                    "secondary-type-ids": [
                        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
                    ],
                    "disambiguation": "",
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "id": "37906983-1005-36fb-b8e7-3a04687e6f4f",
                    "secondary-types": [
                        "Compilation"
                    ],
                    "first-release-date": "1986"
                },
                {
                    "secondary-types": [
                        "Compilation"
                    ],
                    "first-release-date": "1986",
                    "id": "6a427340-2d07-45b5-b557-aaaab91357fd",
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "disambiguation": "",
                    "primary-type": "Album",
                    "title": "The 12″ Tape",
                    "secondary-type-ids": [
                        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
                    ]
                },
                {
                    "disambiguation": "",
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "secondary-type-ids": [
                        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
                    ],
                    "title": "Love Songs",
                    "primary-type": "Album",
                    "first-release-date": "1987",
                    "secondary-types": [
                        "Compilation"
                    ],
                    "id": "5baedc41-91da-44c9-8289-6619a853e635"
                },
                {
                    "id": "60005e6f-2299-3a22-a928-e8002b91e834",
                    "secondary-types": [
                        "Compilation"
                    ],
                    "first-release-date": "1987",
                    "title": "The Michael Jackson Mix",
                    "primary-type": "Album",
                    "secondary-type-ids": [
                        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
                    ],
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "disambiguation": ""
                },
                {
                    "secondary-type-ids": [
                        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
                    ],
                    "title": "Instrumental Version Collection",
                    "primary-type": "Album",
                    "disambiguation": "",
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "id": "f674b393-2e2a-3008-aca3-2f5a115ebe31",
                    "first-release-date": "1988-11-21",
                    "secondary-types": [
                        "Compilation"
                    ]
                },
                {
                    "id": "30abac22-0c88-3340-b955-61dd3be73c55",
                    "first-release-date": "1988",
                    "secondary-types": [
                        "Compilation"
                    ],
                    "secondary-type-ids": [
                        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
                    ],
                    "title": "The Original Soul of Michael Jackson",
                    "primary-type": "Album",
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "disambiguation": ""
                },
                {
                    "primary-type": "Album",
                    "title": "The Motown Years…His Greatest Hits",
                    "secondary-type-ids": [
                        "dd2a21e1-0c00-3729-a7a0-de60b84eb5d1"
                    ],
                    "disambiguation": "",
                    "primary-type-id": "f529b476-6e62-324f-b0aa-1f3e33d313fc",
                    "id": "1516d16c-fad9-413f-99ba-ad1f767c608a",
                    "secondary-types": [
                        "Compilation"
                    ],
                    "first-release-date": "1988"
                }
            ],
            "country": "US",
            "relations": [
                {
                    "begin": null,
                    "target-type": "url",
                    "attributes": [],
                    "url": {
                        "resource": "https://www.allmusic.com/artist/mn0000467203",
                        "id": "6d833c39-9b41-4fa9-a9f6-2ac7f4b971fb"
                    },
                    "ended": false,
                    "direction": "forward",
                    "source-credit": "",
                    "attribute-values": {},
                    "end": null,
                    "target-credit": "",
                    "type": "allmusic",
                    "type-id": "6b3e3c85-0002-4f34-aca6-80ace0d7e846",
                    "attribute-ids": {}
                },
                {
                    "ended": true,
                    "attributes": [],
                    "url": {
                        "id": "0ad9cef2-a1a9-401c-bd02-a673dd95cb15",
                        "resource": "https://www.bbc.co.uk/music/artists/f27ec8db-af05-4f36-916e-3d57f91ecf5e"
                    },
                    "begin": null,
                    "target-type": "url",
                    "type": "BBC Music page",
                    "target-credit": "",
                    "end": "2020-11-19",
                    "attribute-values": {},
                    "direction": "forward",
                    "source-credit": "",
                    "attribute-ids": {},
                    "type-id": "d028a975-000c-4525-9333-d3c8425e4b54"
                },
                {
                    "type-id": "04a5b104-a4c2-4bac-99a1-7b837c37d9e4",
                    "attribute-ids": {},
                    "attribute-values": {},
                    "end": null,
                    "direction": "forward",
                    "source-credit": "",
                    "type": "discogs",
                    "target-credit": "",
                    "attributes": [],
                    "url": {
                        "resource": "https://www.discogs.com/artist/15885",
                        "id": "01d7a3f4-00f9-4343-8043-cc518c73cc7f"
                    },
                    "begin": null,
                    "target-type": "url",
                    "ended": false
                },
                {
                    "url": {
                        "id": "1be5af05-e598-4c6c-b3c4-b5b794d3421f",
                        "resource": "http://www.mjfanclub.net/"
                    },
                    "attributes": [],
                    "target-type": "url",
                    "begin": null,
                    "ended": false,
                    "type-id": "f484f897-81cc-406e-96f9-cd799a04ee24",
                    "attribute-ids": {},
                    "end": null,
                    "attribute-values": {},
                    "source-credit": "",
                    "direction": "forward",
                    "type": "fanpage",
                    "target-credit": ""
                },
                {
                    "attribute-ids": {},
                    "type-id": "769085a1-c2f7-4c24-a532-2375a77693bd",
                    "target-credit": "",
                    "type": "free streaming",
                    "source-credit": "",
                    "direction": "forward",
                    "end": null,
                    "attribute-values": {},
                    "ended": false,
                    "target-type": "url",
                    "begin": null,
                    "url": {
                        "resource": "https://open.spotify.com/artist/3fMbdgg4jU18AjLCKBhRSm",
                        "id": "1656cd65-7658-4945-a47d-f49e7138b461"
                    },
                    "attributes": []
                },
                {
                    "ended": false,
                    "url": {
                        "resource": "https://www.deezer.com/artist/259",
                        "id": "291768af-919a-4855-b07b-6c04aafe8794"
                    },
                    "attributes": [],
                    "target-type": "url",
                    "begin": null,
                    "type": "free streaming",
                    "target-credit": "",
                    "attribute-values": {},
                    "end": null,
                    "source-credit": "",
                    "direction": "forward",
                    "attribute-ids": {},
                    "type-id": "769085a1-c2f7-4c24-a532-2375a77693bd"
                },
                {
                    "type-id": "221132e9-e30e-43f2-a741-15afc4c5fa7c",
                    "attribute-ids": {},
                    "source-credit": "",
                    "direction": "forward",
                    "end": null,
                    "attribute-values": {},
                    "target-credit": "",
                    "type": "image",
                    "target-type": "url",
                    "begin": null,
                    "url": {
                        "resource": "https://commons.wikimedia.org/wiki/File:Michael_Jackson_in_1988.jpg",
                        "id": "4cac77c7-348e-4618-b550-4cc735cab638"
                    },
                    "attributes": [],
                    "ended": false
                },
                {
                    "target-credit": "",
                    "type": "IMDb",
                    "direction": "forward",
                    "source-credit": "",
                    "end": null,
                    "attribute-values": {},
                    "attribute-ids": {},
                    "type-id": "94c8b0cc-4477-4106-932c-da60e63de61c",
                    "ended": false,
                    "begin": null,
                    "target-type": "url",
                    "attributes": [],
                    "url": {
                        "id": "fb1e53a6-5b7a-42d6-b4fc-76306fb0d8de",
                        "resource": "https://www.imdb.com/name/nm0001391/"
                    }
                },
                {
                    "url": {
                        "resource": "https://www.last.fm/music/Michael+Jackson",
                        "id": "02ca5284-1024-47c1-93e0-481003a01be1"
                    },
                    "attributes": [],
                    "target-type": "url",
                    "begin": null,
                    "ended": false,
                    "attribute-values": {},
                    "end": null,
                    "source-credit": "",
                    "direction": "forward",
                    "type": "last.fm",
                    "target-credit": "",
                    "type-id": "08db8098-c0df-4b78-82c3-c8697b4bba7f",
                    "attribute-ids": {}
                },
                {
                    "url": {
                        "id": "fba3ee32-77ba-4f79-a178-97113ecf214a",
                        "resource": "http://muzikum.eu/en/122-3710/michael-jackson/lyrics.html"
                    },
                    "attributes": [],
                    "target-type": "url",
                    "begin": null,
                    "ended": false,
                    "end": null,
                    "attribute-values": {},
                    "source-credit": "",
                    "direction": "forward",
                    "type": "lyrics",
                    "target-credit": "",
                    "type-id": "e4d73442-3762-45a8-905c-401da65544ed",
                    "attribute-ids": {}
                },
                {
                    "ended": false,
                    "url": {
                        "resource": "https://genius.com/artists/Michael-jackson",
                        "id": "d19b6ded-40d7-4d55-a8ec-9c426364dfd5"
                    },
                    "attributes": [],
                    "target-type": "url",
                    "begin": null,
                    "type": "lyrics",
                    "target-credit": "",
                    "attribute-values": {},
                    "end": null,
                    "source-credit": "",
                    "direction": "forward",
                    "attribute-ids": {},
                    "type-id": "e4d73442-3762-45a8-905c-401da65544ed"
                },
                {
                    "ended": false,
                    "begin": null,
                    "target-type": "url",
                    "attributes": [],
                    "url": {
                        "resource": "https://www.musixmatch.com/artist/Michael-Jackson",
                        "id": "490951c0-84da-4f9e-ab83-09f01152b2ab"
                    },
                    "target-credit": "",
                    "type": "lyrics",
                    "direction": "forward",
                    "source-credit": "",
                    "end": null,
                    "attribute-values": {},
                    "attribute-ids": {},
                    "type-id": "e4d73442-3762-45a8-905c-401da65544ed"
                },
                {
                    "ended": false,
                    "begin": null,
                    "target-type": "url",
                    "attributes": [],
                    "url": {
                        "id": "6de0c807-02ba-483a-a7bc-4a66662997af",
                        "resource": "https://myspace.com/michaeljackson"
                    },
                    "attribute-ids": {},
                    "type-id": "bac47923-ecde-4b59-822e-d08f0cd10156",
                    "target-credit": "",
                    "type": "myspace",
                    "direction": "forward",
                    "source-credit": "",
                    "attribute-values": {},
                    "end": null
                },
                {
                    "attribute-ids": {},
                    "type-id": "fe33d22f-c3b0-4d68-bd53-a856badf2b15",
                    "target-credit": "",
                    "type": "official homepage",
                    "direction": "forward",
                    "source-credit": "",
                    "attribute-values": {},
                    "end": null,
                    "ended": false,
                    "begin": null,
                    "target-type": "url",
                    "attributes": [],
                    "url": {
                        "resource": "http://www.michaeljackson.com/",
                        "id": "f03c74df-58fb-42b2-8974-d8ea74ad4beb"
                    }
                },
                {
                    "type": "official homepage",
                    "target-credit": "",
                    "end": null,
                    "attribute-values": {},
                    "direction": "forward",
                    "source-credit": "",
                    "attribute-ids": {},
                    "type-id": "fe33d22f-c3b0-4d68-bd53-a856badf2b15",
                    "ended": false,
                    "attributes": [],
                    "url": {
                        "resource": "http://www.sonymusic.co.jp/Music/International/Arch/ES/MichaelJackson/",
                        "id": "637cd810-10a3-4b37-bae7-78aae0be7ec8"
                    },
                    "begin": null,
                    "target-type": "url"
                },
                {
                    "type-id": "d94fb61c-fa20-4e3c-a19a-71a949fb2c55",
                    "attribute-ids": {},
                    "end": null,
                    "attribute-values": {},
                    "source-credit": "",
                    "direction": "forward",
                    "type": "other databases",
                    "target-credit": "",
                    "url": {
                        "id": "cf24f12a-e2fd-4d97-bf61-6dcca34cd5de",
                        "resource": "http://d-nb.info/gnd/118711083"
                    },
                    "attributes": [],
                    "target-type": "url",
                    "begin": null,
                    "ended": false
                },
                {
                    "ended": false,
                    "target-type": "url",
                    "begin": null,
                    "url": {
                        "resource": "http://id.loc.gov/authorities/names/n83133203",
                        "id": "28084967-acaa-4d4e-8371-3aabe6a61dba"
                    },
                    "attributes": [],
                    "attribute-ids": {},
                    "type-id": "d94fb61c-fa20-4e3c-a19a-71a949fb2c55",
                    "target-credit": "",
                    "type": "other databases",
                    "source-credit": "",
                    "direction": "forward",
                    "end": null,
                    "attribute-values": {}
                },
                {
                    "direction": "forward",
                    "source-credit": "",
                    "attribute-values": {},
                    "end": null,
                    "target-credit": "",
                    "type": "other databases",
                    "type-id": "d94fb61c-fa20-4e3c-a19a-71a949fb2c55",
                    "attribute-ids": {},
                    "begin": null,
                    "target-type": "url",
                    "attributes": [],
                    "url": {
                        "id": "8aad8396-38bf-4ce5-8b14-f67e1cde0f41",
                        "resource": "http://musicmoz.org/Bands_and_Artists/J/Jackson,_Michael/"
                    },
                    "ended": false
                },
                {
                    "target-credit": "",
                    "type": "other databases",
                    "direction": "forward",
                    "source-credit": "",
                    "end": null,
                    "attribute-values": {},
                    "attribute-ids": {},
                    "type-id": "d94fb61c-fa20-4e3c-a19a-71a949fb2c55",
                    "ended": false,
                    "begin": null,
                    "target-type": "url",
                    "attributes": [],
                    "url": {
                        "id": "e15d8e14-9c86-49fb-8764-99f17cccd811",
                        "resource": "http://ocremix.org/artist/5385/michael-jackson"
                    }
                },
                {
                    "ended": false,
                    "target-type": "url",
                    "begin": null,
                    "url": {
                        "resource": "https://catalogue.bnf.fr/ark:/12148/cb12088047p",
                        "id": "e5b48c51-24a9-4d46-9bc9-bafc995f3e51"
                    },
                    "attributes": [],
                    "attribute-ids": {},
                    "type-id": "d94fb61c-fa20-4e3c-a19a-71a949fb2c55",
                    "target-credit": "",
                    "type": "other databases",
                    "source-credit": "",
                    "direction": "forward",
                    "attribute-values": {},
                    "end": null
                },
                {
                    "type-id": "d94fb61c-fa20-4e3c-a19a-71a949fb2c55",
                    "attribute-ids": {},
                    "attribute-values": {},
                    "end": null,
                    "source-credit": "",
                    "direction": "forward",
                    "type": "other databases",
                    "target-credit": "",
                    "url": {
                        "id": "61993ebb-0f34-4da6-8b77-824a098628a0",
                        "resource": "https://imvdb.com/n/michael-jackson"
                    },
                    "attributes": [],
                    "target-type": "url",
                    "begin": null,
                    "ended": false
                },
                {
                    "attributes": [],
                    "url": {
                        "id": "386df118-79ea-42ac-86c6-3b377249fb6a",
                        "resource": "http://snaccooperative.org/ark:/99166/w64467gw"
                    },
                    "begin": null,
                    "target-type": "url",
                    "ended": false,
                    "type-id": "d94fb61c-fa20-4e3c-a19a-71a949fb2c55",
                    "attribute-ids": {},
                    "attribute-values": {},
                    "end": null,
                    "direction": "forward",
                    "source-credit": "",
                    "type": "other databases",
                    "target-credit": ""
                },
                {
                    "ended": false,
                    "begin": null,
                    "target-type": "url",
                    "attributes": [],
                    "url": {
                        "resource": "https://nla.gov.au/nla.party-299259",
                        "id": "a724b960-2581-4045-bec2-a37c5f44edbd"
                    },
                    "attribute-ids": {},
                    "type-id": "d94fb61c-fa20-4e3c-a19a-71a949fb2c55",
                    "target-credit": "",
                    "type": "other databases",
                    "direction": "forward",
                    "source-credit": "",
                    "end": null,
                    "attribute-values": {}
                },
                {
                    "ended": false,
                    "target-type": "url",
                    "begin": null,
                    "url": {
                        "id": "3324273e-16f1-4556-aa59-f16293cf2604",
                        "resource": "https://openlibrary.org/works/OL774692A"
                    },
                    "attributes": [],
                    "target-credit": "",
                    "type": "other databases",
                    "source-credit": "",
                    "direction": "forward",
                    "end": null,
                    "attribute-values": {},
                    "attribute-ids": {},
                    "type-id": "d94fb61c-fa20-4e3c-a19a-71a949fb2c55"
                },
                {
                    "ended": false,
                    "begin": null,
                    "target-type": "url",
                    "attributes": [],
                    "url": {
                        "resource": "https://rateyourmusic.com/artist/michael_jackson",
                        "id": "b73f0c6a-54d2-4c0f-b8a9-bd0625b998a6"
                    },
                    "attribute-ids": {},
                    "type-id": "d94fb61c-fa20-4e3c-a19a-71a949fb2c55",
                    "target-credit": "",
                    "type": "other databases",
                    "direction": "forward",
                    "source-credit": "",
                    "attribute-values": {},
                    "end": null
                },
                {
                    "attributes": [],
                    "url": {
                        "resource": "https://www.livefans.jp/artists/12437",
                        "id": "00863194-8840-4b4a-9241-73fb2ad8c912"
                    },
                    "begin": null,
                    "target-type": "url",
                    "ended": false,
                    "end": null,
                    "attribute-values": {},
                    "direction": "forward",
                    "source-credit": "",
                    "type": "other databases",
                    "target-credit": "",
                    "type-id": "d94fb61c-fa20-4e3c-a19a-71a949fb2c55",
                    "attribute-ids": {}
                },
                {
                    "attribute-ids": {},
                    "type-id": "d94fb61c-fa20-4e3c-a19a-71a949fb2c55",
                    "type": "other databases",
                    "target-credit": "",
                    "end": null,
                    "attribute-values": {},
                    "direction": "forward",
                    "source-credit": "",
                    "ended": false,
                    "attributes": [],
                    "url": {
                        "resource": "https://www.musik-sammler.de/artist/michael-jackson/",
                        "id": "3decf474-b194-4b5f-ba1c-b65903155933"
                    },
                    "begin": null,
                    "target-type": "url"
                },
                {
                    "attributes": [],
                    "url": {
                        "resource": "https://www.whosampled.com/Michael-Jackson/",
                        "id": "77dd5db9-4b16-409b-a9a5-3b811be35cc9"
                    },
                    "begin": null,
                    "target-type": "url",
                    "ended": false,
                    "end": null,
                    "attribute-values": {},
                    "direction": "forward",
                    "source-credit": "",
                    "type": "other databases",
                    "target-credit": "",
                    "type-id": "d94fb61c-fa20-4e3c-a19a-71a949fb2c55",
                    "attribute-ids": {}
                },
                {
                    "ended": false,
                    "attributes": [],
                    "url": {
                        "id": "de735eb2-a593-40c3-bbb6-539615428740",
                        "resource": "https://www.worldcat.org/identities/lccn-n83133203"
                    },
                    "begin": null,
                    "target-type": "url",
                    "type": "other databases",
                    "target-credit": "",
                    "end": null,
                    "attribute-values": {},
                    "direction": "forward",
                    "source-credit": "",
                    "attribute-ids": {},
                    "type-id": "d94fb61c-fa20-4e3c-a19a-71a949fb2c55"
                },
                {
                    "type-id": "d94fb61c-fa20-4e3c-a19a-71a949fb2c55",
                    "attribute-ids": {},
                    "direction": "forward",
                    "source-credit": "",
                    "attribute-values": {},
                    "end": null,
                    "target-credit": "",
                    "type": "other databases",
                    "begin": null,
                    "target-type": "url",
                    "attributes": [],
                    "url": {
                        "resource": "http://www.45cat.com/artist/michael-jackson",
                        "id": "e828c173-c610-42b0-8e39-eba9cee9eb81"
                    },
                    "ended": false
                },
                {
                    "target-credit": "",
                    "type": "purchase for download",
                    "direction": "forward",
                    "source-credit": "",
                    "attribute-values": {},
                    "end": "2020-10-12",
                    "attribute-ids": {},
                    "type-id": "f8319a2f-f824-4617-81c8-be6560b3b203",
                    "ended": true,
                    "begin": null,
                    "target-type": "url",
                    "attributes": [],
                    "url": {
                        "id": "e849469d-8fcc-4125-93f5-645b61f03b62",
                        "resource": "https://play.google.com/store/music/artist?id=Ai6bhstqbdyafulwecypadv2b34"
                    }
                },
                {
                    "type": "purchase for download",
                    "target-credit": "",
                    "attribute-values": {},
                    "end": null,
                    "source-credit": "",
                    "direction": "forward",
                    "attribute-ids": {},
                    "type-id": "f8319a2f-f824-4617-81c8-be6560b3b203",
                    "ended": false,
                    "url": {
                        "id": "e45fc108-8b2f-4fb8-ae5a-dcebaf9db3bf",
                        "resource": "https://music.apple.com/nz/artist/32940"
                    },
                    "attributes": [],
                    "target-type": "url",
                    "begin": null
                },
                {
                    "target-type": "url",
                    "begin": null,
                    "url": {
                        "id": "862b1a9c-c6bc-4128-8495-65e2d638ccfb",
                        "resource": "https://music.apple.com/us/artist/32940"
                    },
                    "attributes": [],
                    "ended": false,
                    "type-id": "f8319a2f-f824-4617-81c8-be6560b3b203",
                    "attribute-ids": {},
                    "source-credit": "",
                    "direction": "forward",
                    "end": null,
                    "attribute-values": {},
                    "target-credit": "",
                    "type": "purchase for download"
                },
                {
                    "attribute-ids": {},
                    "type-id": "f8319a2f-f824-4617-81c8-be6560b3b203",
                    "target-credit": "",
                    "type": "purchase for download",
                    "direction": "forward",
                    "source-credit": "",
                    "end": null,
                    "attribute-values": {},
                    "ended": false,
                    "begin": null,
                    "target-type": "url",
                    "attributes": [],
                    "url": {
                        "resource": "https://music.apple.com/za/artist/32940",
                        "id": "58fbfae3-c3ad-4ffa-bd5d-720d7da1d009"
                    }
                },
                {
                    "target-credit": "",
                    "type": "purchase for download",
                    "source-credit": "",
                    "direction": "forward",
                    "attribute-values": {},
                    "end": null,
                    "attribute-ids": {},
                    "type-id": "f8319a2f-f824-4617-81c8-be6560b3b203",
                    "ended": false,
                    "target-type": "url",
                    "begin": null,
                    "url": {
                        "resource": "https://uk.7digital.com/artist/michael-jackson",
                        "id": "5af840ad-c63b-4496-b288-2aace9c56681"
                    },
                    "attributes": []
                },
                {
                    "attributes": [],
                    "url": {
                        "resource": "https://www.beatport.com/artist/michael-jackson/206348",
                        "id": "1193ee39-cfcb-4890-be90-3837783cf528"
                    },
                    "begin": null,
                    "target-type": "url",
                    "ended": false,
                    "type-id": "f8319a2f-f824-4617-81c8-be6560b3b203",
                    "attribute-ids": {},
                    "attribute-values": {},
                    "end": null,
                    "direction": "forward",
                    "source-credit": "",
                    "type": "purchase for download",
                    "target-credit": ""
                },
                {
                    "attributes": [],
                    "url": {
                        "resource": "https://www.junodownload.com/artists/Michael+Jackson/releases/",
                        "id": "cd815184-3391-4293-9f08-1b709966c4b5"
                    },
                    "begin": null,
                    "target-type": "url",
                    "ended": false,
                    "attribute-values": {},
                    "end": null,
                    "direction": "forward",
                    "source-credit": "",
                    "type": "purchase for download",
                    "target-credit": "",
                    "type-id": "f8319a2f-f824-4617-81c8-be6560b3b203",
                    "attribute-ids": {}
                },
                {
                    "ended": false,
                    "url": {
                        "resource": "https://www.amazon.com/-/e/B000APU04Q",
                        "id": "d04bd85d-0eb8-4d4e-a089-c933ed7186a7"
                    },
                    "attributes": [],
                    "target-type": "url",
                    "begin": null,
                    "type": "purchase for mail-order",
                    "target-credit": "",
                    "end": null,
                    "attribute-values": {},
                    "source-credit": "",
                    "direction": "forward",
                    "attribute-ids": {},
                    "type-id": "611b1862-67af-4253-a64f-34adba305d1d"
                },
                {
                    "type-id": "611b1862-67af-4253-a64f-34adba305d1d",
                    "attribute-ids": {},
                    "attribute-values": {},
                    "end": null,
                    "source-credit": "",
                    "direction": "forward",
                    "type": "purchase for mail-order",
                    "target-credit": "",
                    "url": {
                        "resource": "https://www.cdjapan.co.jp/person/700227304",
                        "id": "dc90a8c6-e8c4-4ba0-8532-d76cf556d272"
                    },
                    "attributes": [],
                    "target-type": "url",
                    "begin": null,
                    "ended": false
                },
                {
                    "type-id": "79c5b84d-a206-4f4c-9832-78c028c312c3",
                    "attribute-ids": {},
                    "direction": "forward",
                    "source-credit": "",
                    "end": null,
                    "attribute-values": {},
                    "target-credit": "",
                    "type": "secondhandsongs",
                    "begin": null,
                    "target-type": "url",
                    "attributes": [],
                    "url": {
                        "resource": "https://secondhandsongs.com/artist/254",
                        "id": "5cd63d78-1153-4418-95d5-04bde9ace5f2"
                    },
                    "ended": false
                },
                {
                    "ended": false,
                    "attributes": [],
                    "url": {
                        "resource": "https://www.setlist.fm/setlists/michael-jackson-6bd6e252.html",
                        "id": "f1ed47d0-8e8f-4480-bed7-15d4ba6b2a41"
                    },
                    "begin": null,
                    "target-type": "url",
                    "attribute-ids": {},
                    "type-id": "bf5d0d5e-27a1-4e94-9df7-3cdc67b3b207",
                    "type": "setlistfm",
                    "target-credit": "",
                    "end": null,
                    "attribute-values": {},
                    "direction": "forward",
                    "source-credit": ""
                },
                {
                    "attributes": [],
                    "url": {
                        "resource": "https://plus.google.com/+MichaelJackson/",
                        "id": "bb29981f-cf17-461f-88d3-365800f3cd07"
                    },
                    "begin": null,
                    "target-type": "url",
                    "ended": true,
                    "attribute-values": {},
                    "end": "2019-04",
                    "direction": "forward",
                    "source-credit": "",
                    "type": "social network",
                    "target-credit": "",
                    "type-id": "99429741-f3f6-484b-84f8-23af51991770",
                    "attribute-ids": {}
                },
                {
                    "ended": false,
                    "target-type": "url",
                    "begin": null,
                    "url": {
                        "resource": "https://twitter.com/michaeljackson",
                        "id": "1486d902-606c-4570-ad7f-5cf184914f5a"
                    },
                    "attributes": [],
                    "attribute-ids": {},
                    "type-id": "99429741-f3f6-484b-84f8-23af51991770",
                    "target-credit": "",
                    "type": "social network",
                    "source-credit": "",
                    "direction": "forward",
                    "attribute-values": {},
                    "end": null
                },
                {
                    "direction": "forward",
                    "source-credit": "",
                    "end": null,
                    "attribute-values": {},
                    "target-credit": "",
                    "type": "social network",
                    "type-id": "99429741-f3f6-484b-84f8-23af51991770",
                    "attribute-ids": {},
                    "begin": null,
                    "target-type": "url",
                    "attributes": [],
                    "url": {
                        "resource": "https://www.facebook.com/michaeljackson",
                        "id": "7eb52fdf-8784-4c8b-83a9-e11b6e2c353a"
                    },
                    "ended": false
                },
                {
                    "source-credit": "",
                    "direction": "forward",
                    "attribute-values": {},
                    "end": null,
                    "target-credit": "",
                    "type": "social network",
                    "type-id": "99429741-f3f6-484b-84f8-23af51991770",
                    "attribute-ids": {},
                    "target-type": "url",
                    "begin": null,
                    "url": {
                        "id": "caf9bccc-c7d1-4fde-a670-80043d2e815c",
                        "resource": "https://www.instagram.com/michaeljackson/"
                    },
                    "attributes": [],
                    "ended": false
                },
                {
                    "attributes": [],
                    "url": {
                        "id": "0d83bb80-bb6d-46b9-86df-780d630a5870",
                        "resource": "https://www.songkick.com/artists/237682"
                    },
                    "begin": null,
                    "target-type": "url",
                    "ended": false,
                    "type-id": "aac9c4bc-a5b9-30b8-9839-e3ac314c6e58",
                    "attribute-ids": {},
                    "end": null,
                    "attribute-values": {},
                    "direction": "forward",
                    "source-credit": "",
                    "type": "songkick",
                    "target-credit": ""
                },
                {
                    "type-id": "63cc5d1f-f096-4c94-a43f-ecb32ea94161",
                    "attribute-ids": {},
                    "attribute-values": {},
                    "end": null,
                    "direction": "forward",
                    "source-credit": "",
                    "type": "streaming",
                    "target-credit": "",
                    "attributes": [],
                    "url": {
                        "id": "bd762270-61f5-4a2c-a39d-5719f10c2425",
                        "resource": "https://listen.tidal.com/artist/606"
                    },
                    "begin": null,
                    "target-type": "url",
                    "ended": false
                },
                {
                    "ended": false,
                    "target-type": "url",
                    "begin": null,
                    "url": {
                        "resource": "https://music.amazon.com/artists/B000QJK2YE",
                        "id": "6985056a-01a2-41e1-b1a9-3d86e194b544"
                    },
                    "attributes": [],
                    "attribute-ids": {},
                    "type-id": "63cc5d1f-f096-4c94-a43f-ecb32ea94161",
                    "target-credit": "",
                    "type": "streaming",
                    "source-credit": "",
                    "direction": "forward",
                    "attribute-values": {},
                    "end": null
                },
                {
                    "begin": null,
                    "target-type": "url",
                    "attributes": [],
                    "url": {
                        "id": "e45fc108-8b2f-4fb8-ae5a-dcebaf9db3bf",
                        "resource": "https://music.apple.com/nz/artist/32940"
                    },
                    "ended": false,
                    "type-id": "63cc5d1f-f096-4c94-a43f-ecb32ea94161",
                    "attribute-ids": {},
                    "direction": "forward",
                    "source-credit": "",
                    "end": null,
                    "attribute-values": {},
                    "target-credit": "",
                    "type": "streaming"
                },
                {
                    "type-id": "63cc5d1f-f096-4c94-a43f-ecb32ea94161",
                    "attribute-ids": {},
                    "direction": "forward",
                    "source-credit": "",
                    "attribute-values": {},
                    "end": null,
                    "target-credit": "",
                    "type": "streaming",
                    "begin": null,
                    "target-type": "url",
                    "attributes": [],
                    "url": {
                        "id": "862b1a9c-c6bc-4128-8495-65e2d638ccfb",
                        "resource": "https://music.apple.com/us/artist/32940"
                    },
                    "ended": false
                },
                {
                    "type-id": "63cc5d1f-f096-4c94-a43f-ecb32ea94161",
                    "attribute-ids": {},
                    "attribute-values": {},
                    "end": null,
                    "source-credit": "",
                    "direction": "forward",
                    "type": "streaming",
                    "target-credit": "",
                    "url": {
                        "resource": "https://music.apple.com/za/artist/32940",
                        "id": "58fbfae3-c3ad-4ffa-bd5d-720d7da1d009"
                    },
                    "attributes": [],
                    "target-type": "url",
                    "begin": null,
                    "ended": false
                },
                {
                    "type-id": "63cc5d1f-f096-4c94-a43f-ecb32ea94161",
                    "attribute-ids": {},
                    "attribute-values": {},
                    "end": null,
                    "direction": "forward",
                    "source-credit": "",
                    "type": "streaming",
                    "target-credit": "",
                    "attributes": [],
                    "url": {
                        "id": "621ff8e6-e3cd-48c4-b568-a04d004e9238",
                        "resource": "https://us.napster.com/artist/michael-jackson"
                    },
                    "begin": null,
                    "target-type": "url",
                    "ended": false
                },
                {
                    "ended": false,
                    "attributes": [],
                    "url": {
                        "resource": "https://vgmdb.net/artist/7312",
                        "id": "c0e48fe8-dbe3-47fb-b3f1-34edb5b3f261"
                    },
                    "begin": null,
                    "target-type": "url",
                    "attribute-ids": {},
                    "type-id": "0af15ab3-c615-46d6-b95b-a5fcd2a92ed9",
                    "type": "vgmdb",
                    "target-credit": "",
                    "end": null,
                    "attribute-values": {},
                    "direction": "forward",
                    "source-credit": ""
                },
                {
                    "begin": null,
                    "target-type": "url",
                    "attributes": [],
                    "url": {
                        "id": "31f63e3e-84b7-4c58-a864-64bcaf54ff8b",
                        "resource": "http://viaf.org/viaf/27092134"
                    },
                    "ended": false,
                    "type-id": "e8571dcc-35d4-4e91-a577-a3382fd84460",
                    "attribute-ids": {},
                    "direction": "forward",
                    "source-credit": "",
                    "end": null,
                    "attribute-values": {},
                    "target-credit": "",
                    "type": "VIAF"
                },
                {
                    "direction": "forward",
                    "source-credit": "",
                    "end": null,
                    "attribute-values": {},
                    "target-credit": "",
                    "type": "wikidata",
                    "type-id": "689870a4-a1e4-4912-b17f-7b2664215698",
                    "attribute-ids": {},
                    "begin": null,
                    "target-type": "url",
                    "attributes": [],
                    "url": {
                        "resource": "https://www.wikidata.org/wiki/Q2831",
                        "id": "27011c1f-bd4f-474c-ae04-23e00f79ccd9"
                    },
                    "ended": false
                },
                {
                    "target-type": "url",
                    "begin": null,
                    "url": {
                        "id": "65cf3ee3-3070-4bda-8f75-eaa8f1f42031",
                        "resource": "https://www.youtube.com/user/michaeljackson"
                    },
                    "attributes": [],
                    "ended": false,
                    "type-id": "6a540e5b-58c6-4192-b6ba-dbc71ec8fcf0",
                    "attribute-ids": {},
                    "source-credit": "",
                    "direction": "forward",
                    "end": null,
                    "attribute-values": {},
                    "target-credit": "",
                    "type": "youtube"
                }
            ],
            "type-id": "b6e035f4-3ce9-331c-97df-83397230b0df",
            "name": "Michael Jackson"
        }
        """;
  }

  public void resetAll() {
    wireMockServer.resetAll();
    setupMappings();
  }

}
