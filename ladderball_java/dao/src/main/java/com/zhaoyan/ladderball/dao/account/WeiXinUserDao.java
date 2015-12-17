package com.zhaoyan.ladderball.dao.account;


import com.zhaoyan.ladderball.domain.account.db.WeiXinUser;

public interface WeiXinUserDao {
    /**
     * 根据id查询
     */
    WeiXinUser getWeiXinUserById(long id);

    /**
     * 根据微信id查询
     */
    WeiXinUser getWeiXinUserByWeiXinId(String weiXinId);

    /**
     * 添加
     */
    void addWeiXinUser(WeiXinUser user);

    /**
     * 修改
     */
    void modifyWeiXinUser(WeiXinUser user);

    /**
     * 删除
     */
    void deleteWeiXinUser(WeiXinUser user);
}
