package net.renfei.model.kitbox;

import java.io.Serializable;

/**
 * <p>Title: IkAnalyzeVO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 * @date : 2020-11-09 00:01
 */
public class IkAnalyzeVO implements Serializable {
    private String word;
    private String type;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
