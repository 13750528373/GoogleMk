package com.kx.app2.protocol;

import com.kx.app2.base.BasicProtocol;
import com.kx.app2.bean.ApkItem;

import java.util.List;

/**
 * Created by KX on 2017/9/8.
 */


public class AppProtocol extends BasicProtocol<List<ApkItem>>{
    @Override
    public String getInterfaceName() {
        return "app";
    }
}
