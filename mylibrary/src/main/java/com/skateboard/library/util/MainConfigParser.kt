package com.skateboard.library.util

import com.skateboard.library.bean.mainconfig.MainConfig
import com.skateboard.library.bean.mainconfig.MainConfigItem
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by skateboard on 2017/9/9.
 */
object MainConfigParser {

    fun parse(data:String,mainConfig: MainConfig)
    {

        val jsonData=JSONObject(data)

        val baseJson=jsonData.optJSONObject("base")

        if(baseJson!=null)
        {
            val keyIter=baseJson.keys()

            while(keyIter.hasNext())
            {
                val key=keyIter.next().toString()

                val jsonArray=baseJson.optJSONArray(key)

                parseArray(jsonArray, mainConfig)
            }
        }

    }

    fun parseArray(jsonArray: JSONArray,mainConfig: MainConfig)
    {
        val length=jsonArray.length()

        for(i in 0..length-1)
        {

            val jsonItem=jsonArray.optJSONObject(i)

            if(jsonItem!=null)
            {

                //parse nlid
                val mainConfigItem=MainConfigItem()

                val nlid=jsonItem.optString("nlid")

                mainConfigItem.nlid=nlid

                //parse url

                val url=jsonItem.optString("url")

                mainConfigItem.url=url

                //parse params
                val params=jsonItem.optJSONObject("params")

                if(params!=null)
                {
                    val keyIter=params.keys()

                    while(keyIter.hasNext())
                    {
                        val key=keyIter.next().toString()

                        val param=params.optString(key)

                        mainConfigItem.params[key]=param
                    }
                }

                mainConfig.dataSource[nlid]=mainConfigItem
            }
        }

    }

}