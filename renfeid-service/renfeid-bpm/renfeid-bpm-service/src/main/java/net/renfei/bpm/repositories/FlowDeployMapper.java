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
package net.renfei.bpm.repositories;

import net.renfei.bpm.api.entity.FlowProcDefDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程定义查询
 *
 * @author renfei
 */
@Mapper
public interface FlowDeployMapper {
    List<FlowProcDefDTO> queryDeployment(String name);
}
