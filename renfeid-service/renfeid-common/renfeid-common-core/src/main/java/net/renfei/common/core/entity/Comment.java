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
package net.renfei.common.core.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论实体
 *
 * @author renfei
 */
@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = -314420603322403668L;
    private String id;
    private SystemTypeEnum sysType;
    private String objectId;
    private String authorId;
    private Date addtime;
    private Boolean isDelete;
    private String parentId;
    private Boolean isOwner;
    private String author;
    private String authorEmail;
    private String authorUrl;
    private String authorIp;
    private String authorAddress;
    private String content;
}
