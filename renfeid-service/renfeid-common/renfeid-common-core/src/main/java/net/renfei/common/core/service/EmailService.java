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

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 邮件发送服务
 *
 * @author renfei
 */
public interface EmailService {
    boolean send(String to, String name, String subject, List<String> contents);

    boolean send(String to, String cc, String name, String subject, List<String> contents);

    boolean send(String to, String name, String subject, String contents);

    boolean send(String to, String cc, String name, String subject, String contents);

    boolean send(String to, String name, String subject, List<String> contents, Map<String, File> attachment);

    boolean send(String to, String cc, String name, String subject, List<String> contents, Map<String, File> attachment);

    boolean send(String to, String name, String subject, String contents, Map<String, File> attachment);

    boolean send(String to, String cc, String name, String subject, String contents, Map<String, File> attachment);
}
