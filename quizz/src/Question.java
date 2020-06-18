import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question {
    private Type type;
    private String text;
    private Map<String, Integer> options;

    public Question() {
        options = new HashMap<String, Integer>();
    }

    public void setType(String type) {
        if (type.equalsIgnoreCase("single")) this.type = Type.SINGLE;
        else if (type.equalsIgnoreCase("multiple")) this.type = Type.MULTIPLE;
        else this.type = Type.TEXT;
    }

    public Type getType() {
        return this.type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void addOption(String text, Integer value) {
        options.put(text, value);
    }

    public List<String> getOptionsTexts() {
        return new ArrayList<>(options.keySet());
    }

    public int getPoints(String optionText) {
        return options.get(optionText);
    }

    enum Type {
        SINGLE,
        MULTIPLE,
        TEXT
    }

    public void printQuestion(){
        System.out.println(type);
        System.out.println(text);

        ArrayList<String> list = new ArrayList<>(options.keySet());
        for (String key : list) {
            System.out.println(key);
            System.out.println(options.get(key));
        }
    }
}
