package net.renfei.model.kitbox;

import java.io.Serializable;

/**
 * @author renfei
 */
public class KeyWordsAO implements Serializable {
    private static final long serialVersionUID = -314420603322403668L;
    private String word;
    private Integer quantity;
    private Integer number;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
