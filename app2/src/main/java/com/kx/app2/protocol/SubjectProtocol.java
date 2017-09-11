package com.kx.app2.protocol;

import com.kx.app2.base.BasicProtocol;
import com.kx.app2.bean.SubjectBean;

import java.util.List;

/**
 * Created by KX on 2017/9/11.
 */
public class SubjectProtocol  extends BasicProtocol<List<SubjectBean>>{
    @Override
    public String getInterfaceName() {
        return "subject";

    }
}
