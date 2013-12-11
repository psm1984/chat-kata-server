package core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 11/12/13
 * Time: 9:44
 */
public class Saying {
    @JsonProperty
    private long id;
    @JsonProperty
    private String content;

    private Saying() {
    }

    public Saying(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean equals(Object obj) {
        Saying objComp = (Saying) obj;
        return (this.getId() == objComp.getId() &&
                this.getContent().equals(objComp.getContent()));
    }

}