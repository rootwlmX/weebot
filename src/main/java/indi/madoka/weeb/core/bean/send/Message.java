package indi.madoka.weeb.core.bean.send;

/**
 * @author  Arcueid
 */
public class Message{
    private Data data;
    private String type;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
