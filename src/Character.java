import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Character {

    public String name;
    public Integer num_of_credits;
    public ArrayList<Item> items;

    public Character(String name, Integer credits, ArrayList<Item> items){
        this.name = name;
        this.num_of_credits = credits;
        this.items = items;
    }

    // Setters
    public void set_name(String new_name){
        this.name = new_name;
    }

    public void remove_credits(Integer new_credits){
        this.num_of_credits -= new_credits;
    }

    public void add_credits(Integer new_credits){
        this.num_of_credits += new_credits;

    }

    /**
    public void set_item(String new_item_name){
        this
    }
     **/



    // Getters

    public String get_name(){
        return this.name;
    }

    public Integer get_credits(){
        return this.num_of_credits;
    }

    public ArrayList<Item> get_items(){
        return this.items;
    }

    public Item get_item(String itemName){
        for (Item item: this.get_items()){
            if(itemName.equals(item.get_item_name())){
                return item;
            }
        }

        // needed a return statement below this so i got this first item of the arraylist
        return this.get_items().get(0);
    }


    public void add_item(Item item){
        this.items.add(item);
    }


    public boolean dropItem(String itemName){

        //System.out.println("here's the items so far: " + this.get_item(itemName).get_item_name());

        if (itemName.equals(this.get_item(itemName).get_item_name())){
                this.get_items().remove(this.get_item(itemName));
                return true;
        }

        return false;
    }

    public boolean sellItemToVendor(String itemName){
        return true;

    }

    public boolean sellItemToCharacter(String itemName, Character buyer){
        return true;

    }



}
