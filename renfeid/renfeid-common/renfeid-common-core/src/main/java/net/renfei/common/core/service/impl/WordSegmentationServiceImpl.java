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
package net.renfei.common.core.service.impl;

import net.renfei.common.core.service.WordSegmentationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;
import org.wltea.analyzer.dic.CustomDictionary;
import org.wltea.analyzer.dic.Dictionary;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分词服务
 *
 * @author renfei
 */
@Service
public class WordSegmentationServiceImpl implements WordSegmentationService {
    private final static Logger logger = LoggerFactory.getLogger(WordSegmentationServiceImpl.class);

    public WordSegmentationServiceImpl() {
        Dictionary dictionary = Dictionary.getInstance();
        if (!dictionary.isLoadedCustomDictionary()) {
            // 没有加载自定义词典，加载自定义词典
            CustomDictionary customDictionary = new CustomDictionary();
            // TODO
            // customDictionary.setMainDictInputStream(new FileInputStream("/Users/renfei/Downloads/sogou.txt"));
            dictionary.loadCustomDictionary(customDictionary);
        }
    }

    /**
     * 从内容中提取10个关键字，基于词频
     *
     * @param content 内容
     * @return
     */
    public List<String> extractKeywords(String content) {
        return extractKeywords(content, 2, 10);
    }

    /**
     * 提取关键字，基于词频
     *
     * @param content  文章内容
     * @param quantity 每个词最小字数
     * @param number   获取关键词数量
     * @return
     */
    public List<String> extractKeywords(String content, Integer quantity, Integer number) {
        //调用提取单词方法
        List<String> keyWordsList = wordSegmentation(content, quantity);
        //list转map并计次数
        Map<String, Integer> map = list2Map(keyWordsList);
        //使用Collections的比较方法进行对map中value的排序
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> (o2.getValue() - o1.getValue()));
        //排序后的长度，以免获得到null的字符
        if (list.size() < number) {
            number = list.size();
        }
        List<String> keyWords = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            //判断个数
            if (i < number) {
                //设置关键字进入数组
                keyWords.add(list.get(i).getKey());
            } else {
                break;
            }
        }
        return keyWords;
    }

    /**
     * 对内容进行分词
     *
     * @param content  内容
     * @param quantity 单词最小字数
     * @return
     */
    @Override
    public List<String> wordSegmentation(String content, Integer quantity) {
        //定义一个list来接收将要截取出来单词
        List<String> list = new ArrayList<>();
        IKSegmentation ikSeg = new IKSegmentation(new StringReader(content), false);
        try {
            Lexeme l;
            while ((l = ikSeg.next()) != null) {
                if (l.getLexemeText().length() < quantity) {
                    continue;
                }
                list.add(l.getLexemeText());
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return new ArrayList<>();
        }
        return list;
    }

    /**
     * 将list中的集合转换成Map中的key，value为数量默认为1
     *
     * @param list 关键词列表
     * @return
     */
    private Map<String, Integer> list2Map(List<String> list) {
        Map<String, Integer> map = new HashMap<>();
        //循环获得的List集合
        for (String key : list) {
            //判断这个集合中是否存在该字符串
            if (list.contains(key)) {
                //将集中获得的字符串放在map的key键上
                //并计算其value是否有值，如有则+1操作
                map.put(key, map.get(key) == null ? 1 : map.get(key) + 1);
            }
        }
        return map;
    }
}
