package com.kx.app2.protocol;

import com.kx.app2.base.BasicProtocol;
import com.kx.app2.bean.ApkItem;

import java.util.List;

/**
 * Created by KX on 2017/9/9.
 */

public class GameProtocol2 extends BasicProtocol<List<ApkItem>>{

    @Override
    public String getInterfaceName() {
        return "game";
    }
}
