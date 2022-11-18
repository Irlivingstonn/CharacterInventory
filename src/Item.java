
// Suggestions: Use Dictionary to sort name and value

import java.util.HashMap;
import java.util.Map;


// Todo:
//      - Items should be sortable in descending order by value (largest to smallest)
//      - Print: name (value)
// add item and its value
public class Item {
    //Character character_constructor = new Character();

    private String item_name;
    private Integer item_value;

    public Item(String name, int value){
        this.item_name = name;
        this.item_value = value;
    }

    public void set_item_name(String name){
        this.item_name = name;
    }

    public void set_item_value(Integer value){
        this.item_value = value;
    }

    public String get_item_name(){
        return this.item_name;
    }

    public Integer get_item_value() {
        return this.item_value;
    }







    }
