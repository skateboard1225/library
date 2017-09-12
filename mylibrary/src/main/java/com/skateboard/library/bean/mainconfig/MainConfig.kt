package com.skateboard.library.bean.mainconfig

import java.io.Serializable

/**
 * Created by skateboard on 2017/9/9.
 */
class MainConfig:Serializable
{
    var dataSource: MutableMap<String,MainConfigItem> = mutableMapOf()

}