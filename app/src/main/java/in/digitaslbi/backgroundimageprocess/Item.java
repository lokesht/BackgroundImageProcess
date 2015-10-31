package in.digitaslbi.backgroundimageprocess;

/**
 * Created by Lokesh on 31-10-2015.
 */
public class Item {

    String name;
    String url;

    public Item(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
