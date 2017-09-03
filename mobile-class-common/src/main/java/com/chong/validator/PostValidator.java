package com.chong.validator;

import com.chong.entity.Post;
import com.chong.entity.SubPost;

/**
 * 帖子验证器
 * Created by LXChild on 07/04/2017.
 */
public final class PostValidator {

    private PostValidator() {}

    public static void notEmptyPost(Post post) {
        BaseValidator.notEmpty(post, "帖子不能为空");
        BaseValidator.notEmpty(post.getCreatorId(), "创建者 ID 不能为空");
        BaseValidator.notEmptyString(post.getTitle(), "帖子标题不能为空");
        BaseValidator.notEmptyString(post.getContent(), "帖子内容不能为空");
        BaseValidator.notEmpty(post.getCreateTime(), "帖子创建时间不能为空");
    }

    public static void notEmptySubPost(SubPost subPost) {
        BaseValidator.notEmpty(subPost, "帖子不能为空");
        BaseValidator.notEmpty(subPost.getParentId(), "主贴 ID 不能为空");
        BaseValidator.notEmpty(subPost.getCreatorId(), "创建者 ID 不能为空");
        BaseValidator.notEmptyString(subPost.getContent(), "帖子内容不能为空");
        BaseValidator.notEmpty(subPost.getCreateTime(), "帖子创建时间不能为空");
    }
}
