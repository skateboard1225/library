package com.skateboard.peopleforce.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.skateboard.library.bean.mainconfig.MainConfig
import com.skateboard.library.util.MainConfigParser
import com.skateboard.peopleforce.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainConfigData="{\n" +
                "    \"base\": {\n" +
                "        \"dataSource\": [{\n" +
                "            \"nlid\": \"nl.feed.menu\",\n" +
                "            \"url\": \"https://neulion-a.akamaihd.net/nlmobile/crtv/assets/menu.json\"\n" +
                "        }, {\n" +
                "            \"nlid\": \"nl.feed.localization\",\n" +
                "            \"url\": \"https://neulion-a.akamaihd.net/nlmobile/crtv/assets/Localization/localization_en.string\"\n" +
                "        }, {\n" +
                "            \"nlid\": \"nl.image.path.dl\",\n" +
                "            \"url\": \"https://neulionsmbnyc-a.akamaihd.net/u/mt1/crtv/thumbs/dl/{id}{deviceType}.jpg\",\n" +
                "            \"params\": {\n" +
                "                \"deviceType\": \"_{deviceName}\"\n" +
                "            }\n" +
                "        }, {\n" +
                "            \"nlid\": \"nl.image.path.program\",\n" +
                "            \"url\": \"https://neulionsmbnyc-a.akamaihd.net/u/mt1/crtv/thumbs/{imgName}{deviceType}{imgSize}.{imgSuffix}\",\n" +
                "            \"params\": {\n" +
                "                \"sImg\": \"_es\",\n" +
                "                \"bImg\": \"_eb\",\n" +
                "                \"lImg\": \"_el\",\n" +
                "                \"pImg\": \"_ep\",\n" +
                "                \"xImg\": \"_es\",\n" +
                "                \"bgImg\": \"_bg\",\n" +
                "                \"deviceType\": \"_{deviceName}\",\n" +
                "                \"regex\": \"(_es.jpg)|(_es.JPG)|(_es.png)|(_es.PNG)\",\n" +
                "                \"_deviceName\": \"iphone|ipad|android|androidtab\"\n" +
                "            }\n" +
                "        }, {\n" +
                "            \"nlid\": \"nl.image.path.category\",\n" +
                "            \"url\": \"https://neulionsmbnyc-a.akamaihd.net/u/mt1/crtv/thumbs/categories/{cid}{deviceType}{imgSize}.jpg\",\n" +
                "            \"params\": {\n" +
                "                \"sImg\": \"_es\",\n" +
                "                \"bImg\": \"_eb\",\n" +
                "                \"deviceType\": \"_{deviceName}\",\n" +
                "                \"regex\": \"(_es.jpg)|(_es.JPG)|(_es.png)|(_es.PNG)\",\n" +
                "                \"_deviceName\": \"iphone|ipad|android|androidtab\"\n" +
                "            }\n" +
                "        }, {\n" +
                "            \"nlid\": \"nl.feed.solr.program\",\n" +
                "            \"url\": \"\",\n" +
                "            \"params\": {\n" +
                "                \"programSearch\": \"{solr_program}/usersearch?start={start}&rows={row}&wt=json&q={keywords}\"\n" +
                "            }\n" +
                "        }, {\n" +
                "            \"nlid\": \"nl.feed.solr.category\",\n" +
                "            \"url\": \"\",\n" +
                "            \"params\": {\n" +
                "                \"topCategories\": \"{solr_category}/usersearch?q=parentId:526%20AND%20status:2&sort=rank+asc&fl=name,tags&wt=json\",\n" +
                "                \"subCategoires\": \"{solr_category}/usersearch?q=show:true&fl=name,catId,seoName,tags,show&sort=rank+asc&rows=100&wt=json\",\n" +
                "                \"recentAddedShow\": \"\"\n" +
                "            }\n" +
                "        }],\n" +
                "        \"services\": [{\n" +
                "            \"nlid\": \"nl.service.app\",\n" +
                "            \"url\": \"https://www.crtv.com\"\n" +
                "        }, {\n" +
                "            \"nlid\": \"nl.service.app.secure\",\n" +
                "            \"url\": \"https://www.crtv.com\"\n" +
                "        }, {\n" +
                "            \"nlid\": \"nl.service.upgrade\",\n" +
                "            \"url\": \"https://www.crtv.com/packages?webview=true\"\n" +
                "        }, {\n" +
                "            \"nlid\": \"nl.service.localeupdate\",\n" +
                "            \"url\": \"\"\n" +
                "        }, {\n" +
                "            \"nlid\": \"nl.service.personalization\",\n" +
                "            \"url\": \"https://solr-qa.neulion.com/personalization_mt/v1\",\n" +
                "            \"params\": {\n" +
                "                \"ps\": 10\n" +
                "            }\n" +
                "        }, {\n" +
                "            \"nlid\": \"nl.service.app.blackout\",\n" +
                "            \"url\": \"http://maps.googleapis.com/maps/api/staticmap?center={city},{state},{countryName}&zoom=9&size=400x400&sensor=false&format=jpg\"\n" +
                "        }, {\n" +
                "            \"nlid\": \"nl.service.qos\",\n" +
                "            \"url\": \"http://nlqosdrecv01.neulion.com/msdrecv/ProxyBean\",\n" +
                "            \"params\": {\n" +
                "                \"siteID\": \"crtv\",\n" +
                "                \"productID\": \"crtv\",\n" +
                "                \"sampleInterval\": 30\n" +
                "            }\n" +
                "        }, {\n" +
                "            \"nlid\": \"nl.player.strategy\",\n" +
                "            \"params\": {\n" +
                "                \"sdk\": 100,\n" +
                "                \"decoder\": \"neulion_hardware\",\n" +
                "                \"comments\": \"neulion_software/neulion_hardware/native_hardware\"\n" +
                "            }\n" +
                "        }, {\n" +
                "            \"nlid\": \"nl.service.interval\",\n" +
                "            \"params\": {\n" +
                "                \"default\": 300,\n" +
                "                \"sessionPoll\": 30\n" +
                "            }\n" +
                "        }, {\n" +
                "            \"nlid\": \"nl.service.chromecast\",\n" +
                "            \"enabled\": true,\n" +
                "            \"params\": {\n" +
                "                \"enablePlayList\": false,\n" +
                "                \"appId\": \"0F1C6CF3\",\n" +
                "                \"appId_Stage\": \"0F1C6CF3\"\n" +
                "            }\n" +
                "        }, {\n" +
                "            \"nlid\": \"nl.service.gaa\",\n" +
                "            \"params\": {\n" +
                "                \"gaa\": \"UA-52803434-11\"\n" +
                "            }\n" +
                "        }],\n" +
                "        \"appParams\": [{\n" +
                "            \"nlid\": \"nl.app.settings\",\n" +
                "            \"params\": {\n" +
                "                \"debugLog\": true,\n" +
                "                \"useAirPlay\": true,\n" +
                "                \"timeZone\": \"America/New_York\",\n" +
                "                \"timeZone_format\": \"America/New_York\",\n" +
                "                \"timezone_suffix\": \"ET\",\n" +
                "                \"pageSize\": 10,\n" +
                "                \"latestEpisodesType\": \"2\",\n" +
                "                \"solr_program\": \"http://solrcloud1.neulion.com/solr/mt2_program/crtv\",\n" +
                "                \"solr_category\": \"http://solrcloud1.neulion.com/solr/mt2_program/crtv\",\n" +
                "                \"showSeoName\": \"shows\",\n" +
                "                \"featuedSeoName\": \"archives\"\n" +
                "            }\n" +
                "        }, {\n" +
                "            \"nlid\": \"nl.app.greeting\",\n" +
                "            \"enabled\": false,\n" +
                "            \"params\": {\n" +
                "                \"title\": \"@nlkey/nl.app.greeting.title\",\n" +
                "                \"message\": \"@nlkey/nl.app.greeting.message\",\n" +
                "                \"forceUpgrade\": false,\n" +
                "                \"upgradeUrl\": \"https://itunes.apple.com/us/app\",\n" +
                "                \"signature\": \"@nlkey/nl.p.share.signature\"\n" +
                "            }\n" +
                "        }, {\n" +
                "            \"nlid\": \"nl.app.info\",\n" +
                "            \"params\": {\n" +
                "                \"nlurl\": \"http://www.neulion.com\",\n" +
                "                \"email\": {\n" +
                "                    \"enable\": true,\n" +
                "                    \"to\": \"support@crtv.com\",\n" +
                "                    \"subject\": \"Support Request\"\n" +
                "                }\n" +
                "            }\n" +
                "        }, {\n" +
                "            \"nlid\": \"nl.app.share\",\n" +
                "            \"params\": {\n" +
                "                \"subject\": \"{title}\",\n" +
                "                \"content\": \"Checkout this video \\r\\n\",\n" +
                "                \"program\": \"I'm watching on my <a href=\\\"https://play.google.com/store/apps/details?id=com.levintv\\\">CRTV App</a>! available in the Google Play. \\r\\n https://www.crtv.com/video/{seoName}\",\n" +
                "                \"news\": \"{link}\"\n" +
                "            }\n" +
                "        }, {\n" +
                "            \"nlid\": \"nl.app.about\",\n" +
                "            \"params\": {\n" +
                "                \"desc\": \"nl app about\"\n" +
                "            }\n" +
                "        }]\n" +
                "    },\n" +
                "    \"locale\": {\n" +
                "        \"country\": {},\n" +
                "        \"language\": {\n" +
                "            \"en\": [{\n" +
                "                \"nlid\": \"nl.feed.localization\",\n" +
                "                \"url\": \"https://neulion-a.akamaihd.net/nlmobile/crtv/assets/Localization/localization_en.string\",\n" +
                "                \"params\": {\n" +
                "                    \"primaryName\": \"English\",\n" +
                "                    \"secondaryName\": \"English\",\n" +
                "                    \"languageKey\": \"en_US\"\n" +
                "                }\n" +
                "            }]\n" +
                "        }\n" +
                "    }\n" +
                "}\n"

        val mainConfig=MainConfig()

        MainConfigParser.parse(mainConfigData,mainConfig)

        println("success")
    }
}
