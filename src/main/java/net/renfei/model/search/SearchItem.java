package net.renfei.model.search;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: SearchItem</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Document(indexName = "searchitem")
public class SearchItem implements Serializable {
    public static final String INDEX_COORDINATES = "searchitem";
    @Id
    private String uuid;
    /**
     * 原始ID
     */
    @Field(type = FieldType.Long)
    private Long originalId;
    /**
     * 标题
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;
    /**
     * 正文
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String content;
    /**
     * 链接
     */
    @Field(type = FieldType.Keyword, index = false)
    private String url;
    /**
     * 类型
     */
    @Field(type = FieldType.Keyword)
    private String type;
    /**
     * 发布时间
     */
    @Field(type = FieldType.Date, format = DateFormat.date_optional_time)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private Date date;
    /**
     * 不会对图片地址查询,指定为false
     */
    @Field(type = FieldType.Keyword, index = false)
    private String image;
}
