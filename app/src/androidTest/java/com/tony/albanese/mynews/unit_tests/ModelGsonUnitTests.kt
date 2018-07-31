package com.tony.albanese.mynews.unit_tests

import android.support.test.runner.AndroidJUnit4
import com.google.gson.Gson
import com.tony.albanese.mynews.model.ServerResponse
import junit.framework.Assert.assertEquals
import org.json.JSONException
import org.json.JSONObject
import org.junit.Test
import org.junit.runner.RunWith

//These unit tests are designed to test the data model and its serialization via Gson.
@RunWith(AndroidJUnit4::class)
class ModelGsonUnitTests {

    //Need sample json response.

        val testJson = "{\n" +
                "  \"status\": \"OK\",\n" +
                "  \"copyright\": \"Copyright (c) 2018 The New York Times Company. All Rights Reserved.\",\n" +
                "  \"response\": {\n" +
                "    \"docs\": [\n" +
                "      {\n" +
                "        \"web_url\": \"https://www.nytimes.com/1989/01/15/books/a-death-in-leningrad.html\",\n" +
                "        \"snippet\": \"LEAD:  STALIN AND THE KIROV MURDER  By Robert Conquest.  164 pp. New York:  Oxford University Press. \$16.95.\",\n" +
                "        \"multimedia\": [],\n" +
                "        \"headline\": {\n" +
                "          \"main\": \"A DEATH IN LENINGRAD\",\n" +
                "          \"kicker\": null,\n" +
                "          \"content_kicker\": null,\n" +
                "          \"print_headline\": null,\n" +
                "          \"name\": null,\n" +
                "          \"seo\": null,\n" +
                "          \"sub\": null\n" +
                "        },\n" +
                "        \"news_desk\": \"Book Review Desk\",\n" +
                "        \"score\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"web_url\": \"https://query.nytimes.com/gst/abstract.html?res=9B01E6D71739E63BBC4C51DFB766838B669EDE\",\n" +
                "        \"snippet\": \"Spain repts Nasser has accepted Gen Franco's inv to visit Spain\",\n" +
                "        \"abstract\": \"Spain repts Nasser has accepted Gen Franco's inv to visit Spain\",\n" +
                "        \"multimedia\": [],\n" +
                "        \"headline\": {\n" +
                "          \"main\": \"Nasser to Visit Spain\",\n" +
                "          \"kicker\": null,\n" +
                "          \"content_kicker\": null,\n" +
                "          \"print_headline\": null,\n" +
                "          \"name\": null,\n" +
                "          \"seo\": null,\n" +
                "          \"sub\": null\n" +
                "        },\n" +
                "        \"score\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"web_url\": \"https://www.nytimes.com/1862/01/24/news/congress.html\",\n" +
                "        \"snippet\": \"In the Senate yesterday, petitions were presented for the employment of homeopathic physicians in the army. A joint resolution from the Michigan Legislature, in favor of the exchange of prisoners, with special reference to the case of Col. WILCOX,...\",\n" +
                "        \"multimedia\": [],\n" +
                "        \"headline\": {\n" +
                "          \"main\": \"CONGRESS.\",\n" +
                "          \"kicker\": null,\n" +
                "          \"content_kicker\": null,\n" +
                "          \"print_headline\": null,\n" +
                "          \"name\": null,\n" +
                "          \"seo\": null,\n" +
                "          \"sub\": null\n" +
                "        },\n" +
                "        \"score\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"web_url\": \"https://runway.blogs.nytimes.com/2011/02/15/the-ghost-of-bill-blass/\",\n" +
                "        \"snippet\": \"her blue-tinted shades, seated at a window looking out on Lexington Avenue. But the passing of that fabled society haunt - it shuttered a dozen years ago - has not deterred young designers from drawing on its patrician charms.\",\n" +
                "        \"abstract\": \"her blue-tinted shades, seated at a window looking out on Lexington Avenue. But the passing of that fabled society haunt - it shuttered a dozen years ago - has not deterred young designers from drawing on its patrician charms.\",\n" +
                "        \"multimedia\": [],\n" +
                "        \"headline\": {\n" +
                "          \"main\": \"The Ghost of Bill Blass\",\n" +
                "          \"kicker\": \"On the Runway\",\n" +
                "          \"content_kicker\": null,\n" +
                "          \"print_headline\": null,\n" +
                "          \"name\": null,\n" +
                "          \"seo\": null,\n" +
                "          \"sub\": null\n" +
                "        },\n" +
                "        \"score\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"web_url\": \"https://query.nytimes.com/gst/abstract.html?res=9904E7DF1439E033A25752C2A9679D946295D6CF\",\n" +
                "        \"snippet\": \"DUESSELDORF, Nov. 20. -- German circles reported here today that the Ruhr industrialists had notified the French they intend to return on Thursday to resume the broken off negotiations with M. Frantzen's technical mission. But there is reason to b...\",\n" +
                "        \"abstract\": \"Ruhr mine owners will meet French again, attitude of workers said to be responsible\",\n" +
                "        \"multimedia\": [],\n" +
                "        \"headline\": {\n" +
                "          \"main\": \"RUHR MINE OWNERS TO MEET FRENCH AGAIN; Attitude of Workers Said to Have Hastened Moves to Resume Work.\",\n" +
                "          \"kicker\": null,\n" +
                "          \"content_kicker\": null,\n" +
                "          \"print_headline\": null,\n" +
                "          \"name\": null,\n" +
                "          \"seo\": null,\n" +
                "          \"sub\": null\n" +
                "        },\n" +
                "        \"score\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"web_url\": \"https://runway.blogs.nytimes.com/2011/02/15/halstons-disco-revival/\",\n" +
                "        \"snippet\": \"The fall 2011 collection seems intent on sticking to a 1970s formula.\",\n" +
                "        \"abstract\": \"The fall 2011 collection seems intent on sticking to a 1970s formula.\",\n" +
                "        \"multimedia\": [\n" +
                "          {\n" +
                "            \"rank\": 0,\n" +
                "            \"subtype\": \"thumbnail\",\n" +
                "            \"caption\": null,\n" +
                "            \"credit\": null,\n" +
                "            \"type\": \"image\",\n" +
                "            \"url\": \"images/2011/02/16/fashion/16halston_blog/16halston_blog-thumbStandard.jpg\",\n" +
                "            \"height\": 75,\n" +
                "            \"width\": 75,\n" +
                "            \"legacy\": {\n" +
                "              \"thumbnailheight\": \"75\",\n" +
                "              \"thumbnail\": \"images/2011/02/16/fashion/16halston_blog/16halston_blog-thumbStandard.jpg\",\n" +
                "              \"thumbnailwidth\": \"75\"\n" +
                "            },\n" +
                "            \"subType\": \"thumbnail\",\n" +
                "            \"crop_name\": null\n" +
                "          }\n" +
                "        ],\n" +
                "        \"headline\": {\n" +
                "          \"main\": \"Halston's Disco Revival \",\n" +
                "          \"kicker\": \"On the Runway\",\n" +
                "          \"content_kicker\": null,\n" +
                "          \"print_headline\": null,\n" +
                "          \"name\": null,\n" +
                "          \"seo\": null,\n" +
                "          \"sub\": null\n" +
                "        },\n" +
                "        \"news_desk\": \"Styles\",\n" +
                "        \"score\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"web_url\": \"https://query.nytimes.com/gst/abstract.html?res=9E04E6DB173AEF3ABC4D53DFBE66838A659EDE\",\n" +
                "        \"snippet\": \"Unidentified body found strangled\",\n" +
                "        \"abstract\": \"Unidentified body found strangled\",\n" +
                "        \"multimedia\": [],\n" +
                "        \"headline\": {\n" +
                "          \"main\": \"WOMAN STRANGLED, LEFT ON SIDEWALK; Wedding Ring Only Cine to Identity of Bronx Murder Victim\",\n" +
                "          \"kicker\": null,\n" +
                "          \"content_kicker\": null,\n" +
                "          \"print_headline\": null,\n" +
                "          \"name\": null,\n" +
                "          \"seo\": null,\n" +
                "          \"sub\": null\n" +
                "        },\n" +
                "        \"score\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"web_url\": \"https://query.nytimes.com/gst/abstract.html?res=9807E7DB163EE033A25755C2A9679C946597D6CF\",\n" +
                "        \"snippet\": \"\",\n" +
                "        \"multimedia\": [],\n" +
                "        \"headline\": {\n" +
                "          \"main\": \"No Dictator.\",\n" +
                "          \"kicker\": \"1\",\n" +
                "          \"content_kicker\": null,\n" +
                "          \"print_headline\": null,\n" +
                "          \"name\": null,\n" +
                "          \"seo\": null,\n" +
                "          \"sub\": null\n" +
                "        },\n" +
                "        \"score\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"web_url\": \"https://www.nytimes.com/2014/04/27/fashion/weddings/whitney-cale-peter-brejcha.html\",\n" +
                "        \"snippet\": \"The bride is studying for a master’s degree in psychology; the groom is a litigation associate.\",\n" +
                "        \"multimedia\": [],\n" +
                "        \"headline\": {\n" +
                "          \"main\": \"Whitney Cale, Peter Brejcha\",\n" +
                "          \"kicker\": null,\n" +
                "          \"content_kicker\": null,\n" +
                "          \"print_headline\": \"Whitney Cale, Peter Brejcha\",\n" +
                "          \"name\": null,\n" +
                "          \"seo\": null,\n" +
                "          \"sub\": null\n" +
                "        },\n" +
                "        \"news_desk\": \"Society\",\n" +
                "        \"score\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"web_url\": \"https://krugman.blogs.nytimes.com/2011/02/08/ideas-are-not-the-same-as-race/\",\n" +
                "        \"snippet\": \"Academics are mostly liberal. So?\",\n" +
                "        \"abstract\": \"Academics are mostly liberal. So?\",\n" +
                "        \"multimedia\": [],\n" +
                "        \"headline\": {\n" +
                "          \"main\": \"Ideas Are Not The Same As Race\",\n" +
                "          \"kicker\": \"Paul Krugman\",\n" +
                "          \"content_kicker\": null,\n" +
                "          \"print_headline\": null,\n" +
                "          \"name\": null,\n" +
                "          \"seo\": null,\n" +
                "          \"sub\": null\n" +
                "        },\n" +
                "        \"score\": 1\n" +
                "      }\n" +
                "    ],\n" +
                "    \"meta\": {\n" +
                "      \"hits\": 15514239,\n" +
                "      \"offset\": 0,\n" +
                "      \"time\": 73\n" +
                "    }\n" +
                "  }\n" +
                "}"
    val badJson = "jsjd soosdfsdf"
    @Test //Function tests json response is valid.
    fun isValidJson(){
        var result: Boolean
        try{
            val json = JSONObject(testJson)
            result = true
        }catch (e: JSONException){
            result = false
        }

        assertEquals(true, result)

    }

    @Test
    fun isInvalidJson(){
        var result: Boolean
        try{
            val json = JSONObject(badJson)
            result = true
        }catch (e: JSONException){
            result = false
        }

        assertEquals(false, result)

    }


    @Test
    fun testServerResponseObjectCreation(){
        //validate that there should be ten objects in the articles array.
        val gson = Gson()
        val serverResponse = gson.fromJson(testJson, ServerResponse::class.java)
        val responses = serverResponse.response
        val articles = responses.docs
        assertEquals(10, articles.size)
    }
}
