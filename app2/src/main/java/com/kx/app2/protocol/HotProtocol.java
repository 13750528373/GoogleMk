package com.kx.app2.protocol;

import com.kx.app2.base.BasicProtocol;

import java.util.List;

/**
 * Created by KX on 2017/9/11.
 */
public class HotProtocol extends BasicProtocol<List<String>>{
    @Override
    public String getInterfaceName() {
        return "hot";
    }
}
