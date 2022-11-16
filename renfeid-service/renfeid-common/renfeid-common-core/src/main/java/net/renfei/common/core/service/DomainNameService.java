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

/**
 * 域名解析服务
 *
 * @author renfei
 */
public interface DomainNameService {
    /**
     * 添加域名解析记录
     *
     * @param host     主机名
     * @param domain   域名
     * @param type     解析类型
     * @param value    记录值
     * @param ttl      TTL
     * @param priority MX记录的优先级
     * @return
     */
    String addDomainRecord(String host, String domain, String type, String value, long ttl, Long priority);

    /**
     * 根据域名解析记录ID删除解析记录
     *
     * @param recordId 域名解析记录ID
     * @return
     */
    boolean deleteDomainRecord(String recordId);

    /**
     * 根据记录值删除解析记录
     *
     * @param host   主机名
     * @param domain 域名
     * @param type   解析类型
     * @return
     */
    boolean deleteDomainRecord(String host, String domain, String type);

    /**
     * 根据域名解析记录ID删修改析记录
     *
     * @param recordId 域名解析记录ID
     * @param host     主机名
     * @param type     解析类型
     * @param value    记录值
     * @param ttl      TTL
     * @param priority MX记录的优先级
     * @return
     */
    String updateDomainRecord(String recordId, String host, String type, String value, long ttl, Long priority);
}
