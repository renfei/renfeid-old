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
package net.renfei.pro.discuz.service;

import net.renfei.pro.discuz.repositories.entity.DiscuzForumPostDO;
import net.renfei.pro.discuz.model.DiscuzInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author renfei
 */
public interface DiscuzService {
    DiscuzInfo getDiscuzInfo(String userName);
    List<DiscuzForumPostDO> getAllPost();

    String uCenterSynLogin(String username);

    void uCenterSynSignUp(String username, String email, HttpServletRequest request);
}
