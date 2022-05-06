package net.renfei.utils;

import net.renfei.model.kitbox.IkAnalyzeVO;
import net.renfei.services.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 文本处理工具
 *
 * @author renfei
 */
public class WordUtils {
    Logger logger = LoggerFactory.getLogger(WordUtils.class);
    private final SearchService searchService;
    /**
     * 获取关键字个数
     */
    private final static Integer NUM = 10;
    /**
     * 截取关键字在几个单词以上的数量
     */
    private final static Integer QUANTITY = 2;

    public WordUtils(SearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * 传入String类型的文章，智能提取单词放入list中
     *
     * @param article  文章内容
     * @param quantity 单词最小字数
     * @return
     */
    private List<String> extract(String article, Integer quantity) {
        //定义一个list来接收将要截取出来单词
        List<String> list = new ArrayList<>();
        List<IkAnalyzeVO> ikAnalyzeList;
        try {
            ikAnalyzeList = searchService.getIkAnalyzeTerms(article);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return new ArrayList<>();
        }
        for (IkAnalyzeVO ikAnalyzeVO : ikAnalyzeList
        ) {
            if (ikAnalyzeVO.getWord().length() > 1 && ikAnalyzeVO.getWord().length() <= quantity) {
                list.add(ikAnalyzeVO.getWord());
            }
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

    /**
     * 获取文章关键词
     *
     * @param article 文章内容
     * @return
     * @throws IOException
     */
    public String[] getKeyWords(String article) {
        return getKeyWords(article, QUANTITY, NUM);
    }

    /**
     * 获取文章关键词
     *
     * @param article 文章内容
     * @param number  获取关键词数量
     * @return
     * @throws IOException
     */
    public String[] getKeyWords(String article, int number) {
        return getKeyWords(article, QUANTITY, number);
    }

    /**
     * 提取关键字方法
     *
     * @param article  文章内容
     * @param quantity 每个词最小字数
     * @param number   获取关键词数量
     * @return
     * @throws IOException
     */
    public String[] getKeyWords(String article, Integer quantity, Integer number) {
        //调用提取单词方法
        List<String> keyWordsList = extract(article, quantity);
        //list转map并计次数
        Map<String, Integer> map = list2Map(keyWordsList);
        //使用Collections的比较方法进行对map中value的排序
        ArrayList<Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> (o2.getValue() - o1.getValue()));
        //排序后的长度，以免获得到null的字符
        if (list.size() < number) {
            number = list.size();
        }
        //设置将要输出的关键字数组空间
        String[] keyWords = new String[number];
        //循环排序后的数组
        for (int i = 0; i < list.size(); i++) {
            //判断个数
            if (i < number) {
                //设置关键字进入数组
                keyWords[i] = list.get(i).getKey();
            } else {
                break;
            }
        }
        return keyWords;
    }
}
