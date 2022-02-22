package net.renfei.model.search;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Document(indexName = "searchitem")
public class SearchItem implements Serializable {
    private static final long serialVersionUID = -314420603322403668L;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getOriginalId() {
        return originalId;
    }

    public void setOriginalId(Long originalId) {
        this.originalId = originalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
