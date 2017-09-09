package com.kx.app2.protocol;

import com.kx.app2.base.BasicProtocol;
import com.kx.app2.bean.HomeBean;

/**
 * Created by KX on 2017/9/9.
 */

public class HomeProtocol extends BasicProtocol<HomeBean> {
    @Override
    public String getInterfaceName() {
        return "home";
    }
}
