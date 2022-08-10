/*
 *   Copyright 2022 RenFei(i@renfei.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.renfei.common.core.service;

import net.renfei.common.core.repositories.entity.CoreCommentsWithBLOBs;

import java.util.List;

/**
 * 审核服务
 *
 * @author renfei
 */
public interface AuditService {
    /**
     * 审核评论
     *
     * @param commentId 评论ID
     */
    void auditComment(long commentId, RedisService redisService, String redisKey);

    /**
     * 发送通知
     *
     * @param comment
     * @param content
     */
    void sendNotify(CoreCommentsWithBLOBs comment, List<String> content);
}
