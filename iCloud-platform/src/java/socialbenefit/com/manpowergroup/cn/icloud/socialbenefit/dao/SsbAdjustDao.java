package com.manpowergroup.cn.icloud.socialbenefit.dao;

import java.sql.SQLException;
import java.util.List;

import com.manpowergroup.cn.icloud.socialbenefit.entity.SsbAjust;

public interface SsbAdjustDao {
    public void saveSsbAdjustment(List <SsbAjust> ssbAdjustList) throws SQLException;
}
