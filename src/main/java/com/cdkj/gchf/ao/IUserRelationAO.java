/**
 * @Title IUserRelationAO.java
 * @Package com.std.user.ao
 * @Description
 * @author xieyj
 * @date 2016年8月31日 上午11:39:25
 * @version V1.0
 */
package com.cdkj.gchf.ao;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.UserRelation;

/**
 * @author: xieyj
 * @since: 2016年8月31日 上午11:39:25
 * @history:
 */
public interface IUserRelationAO {

    public Paginable<UserRelation> queryUserRelationPage(int start, int limit,
                                                         UserRelation condition);

    /**
     * 关注某人
     *
     * @param userId
     * @param toUser
     * @create: 2016年8月31日 上午11:47:10 xieyj
     * @history:
     */
    public void followUser(String userId, String toUser, String type);

    /**
     * 取消关注
     *
     * @param userId
     * @param toUser
     * @create: 2016年8月31日 上午11:47:30 xieyj
     * @history:
     */
    public void unfollowUser(String userId, String toUser, String type);

    /**
     * 用户关系
     *
     * @param userId
     * @param toUser
     * @return
     * @create: 2017年5月11日 上午11:08:50 asus
     * @history:
     */
    public boolean isExistUserRelation(String userId, String toUser, String type);
}
