package com.skateboard.library.bean.mainconfig

import java.io.Serializable

/**
 * Created by skateboard on 2017/9/9.
 */
class MainConfigItem: Serializable {

    var url:String?=""

    var nlid:String?=""

    var params: MutableMap<String,String> = mutableMapOf()



}