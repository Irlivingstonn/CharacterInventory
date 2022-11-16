import java.util.*;


// Todo:
//      - Menu: Enters Input
//              + Adds letters (display error message)
//      - 1: Add credits to character
//              + Doesn't enter number (display error message)
//      - 1: Naming characters
//              + Lowercase the whole name and match


public class Main {
    public static void main(String[] args) {
        // Declaring variables
        //Item item_constructor = new Item();
        //Character character_constructor = new Character();
        Scanner scanner = new Scanner(System.in);
        Integer END_PROGRAM = 9;
        ArrayList<Character> created_characters = new ArrayList<>();


        // field for the characters name and credits
        //Map<String, Integer> characters_with_credits = new HashMap();

        // field for what the character has (item objects the character possesses)
        //Map<String, String> characters_with_items = new HashMap();




        Integer command = get_user_number("1. Create a character\n2. Character adds an item\n3. Character drops an item\n4. Character sells an item to a vendor\n5. Character sells an item to another character\n6. List Characters\n7. List a character's inventory by value\n8. List all characters' inventories by value\n9. Quit\nWhat would you like to do? ", scanner);
        while(command != END_PROGRAM){
            created_characters = computting_command(command, scanner, created_characters);
            // here for formatting purposes
            System.out.println("\n------------------------------------------------------------------------\n");
            command = get_user_number("1. Create a character\n2. Character adds an item\n3. Character drops an item\n4. Character sells an item to a vendor\n5. Character sells an item to another character\n6. List Characters\n7. List a character's inventory by value\n8. List all characters' inventories by value\n9. Quit\nWhat would you like to do? ", scanner);


        }
        System.out.println("Program Finished... Goodbye!");

    }

    // if they enter 1
    public static ArrayList<Character> creating_new_character(String name_prompt, String credit_prompt, Scanner scanner, ArrayList<Character> created_characters){
        // Gets Name
        String new_character_name = getting_name(name_prompt, scanner, created_characters);
        // Gets Credits
        Integer new_credit = creating_characters_credits(credit_prompt, scanner);
        // Makes new items list for character
        ArrayList<Item> items = new ArrayList<>();

        // Creates a new Character
        Character new_character = new Character(new_character_name, new_credit, items);

        // Updates created characters list
        created_characters.add(new_character);

        // Prints the result
        System.out.println(new_character.get_name() + " added");

        // returns the created characters
        return created_characters;
    }

    private static String getting_name(String name_prompt, Scanner scanner, ArrayList<Character> created_characters){
        System.out.print(name_prompt);

        // Used a "scanner.nextInt" prior to nextLine, so I have to get another nextLine
        // Since it skips the first one
        String new_character_name = scanner.nextLine();
        new_character_name = scanner.nextLine();

        //add created_characters to this
        while (character_is_invalid(new_character_name, created_characters)){
            System.out.print(name_prompt);
            new_character_name = scanner.nextLine();
        }

        return new_character_name;

    }

    private static Boolean character_is_invalid(String new_character_name, ArrayList<Character> created_characters){

        for (Character new_character : created_characters){
            if ((new_character.get_name()).equals(new_character_name)){
                System.out.println("   Error: A Character With that Name Already Exists");
                System.out.println("          Please Try Again");

                return true;
            }
        }
        return false;
    }

    private static Integer creating_characters_credits(String credit_prompt, Scanner scanner){
        System.out.print(credit_prompt);
        Integer new_credit = scanner.nextInt();

        while (credit_is_valid(new_credit)){
            System.out.print(credit_prompt);
            new_credit = scanner.nextInt();
        }

        return new_credit;

    }

    private static Boolean credit_is_valid(Integer new_credit){
        if (new_credit < 0){
            System.out.println("   Error: Invalid Amount of Credits");
            System.out.println("          Please Try Again");
            return true;
        }
        return false;
    }

    // if they enter 2
    public static ArrayList<Character> set_characters_item(String identifying_character_prompt, String name_item_prompt, String item_value_prompt, Scanner scanner, ArrayList<Character> created_characters){
        // declaring variable
        Boolean found_character = false;

        if (created_characters.isEmpty()){
            System.out.println("   Error: You have not entered in any Characters");
            System.out.println("          Please Try Again when you have entered in one");
        }

        else{

            System.out.print(identifying_character_prompt);

            // added two nextLines since i used nextInt prior to this
            String getting_specific_character = scanner.nextLine();
            getting_specific_character = scanner.nextLine();

            // Looks for name of character
            for (Character character: created_characters) {

                // If it can find the specific character, then it'll see if the character has the item
                if (getting_specific_character.equals(character.get_name())){

                    // Creates a new Item
                    String new_item_name = getting_item_name(name_item_prompt, scanner);
                    // Gets items value
                    Integer item_value = getting_item_value(item_value_prompt, scanner);

                    // Creates a new object with the name and value
                    Item new_item = new Item(new_item_name, item_value);

                    // Records Item(Object) for Character
                    character.add_item(new_item);


                    // Uses Boolean if it found the Character
                    found_character = true;
                }

            }

            // If it couldn't find the character; print error message
            if(!found_character){
                System.out.println("   Error: Couldn't Find a Character with that Name");
            }

        }




        return created_characters;
    }

    public static Integer getting_item_value(String item_value_prompt, Scanner scanner){
        System.out.print(item_value_prompt);
        Integer new_item_value = scanner.nextInt();

        while(item_value_is_valid(new_item_value)){
            new_item_value = scanner.nextInt();
        }

        return new_item_value;
    }

    public static Boolean item_value_is_valid(Integer new_credit){
        if (new_credit < 0){
            System.out.println("   Error: Invalid Item Value (Make the Value positive)");
            System.out.println("          Please Try Again");
            return true;
        }
        return false;
    }

    public static String getting_item_name(String name_item_prompt, Scanner scanner){
        System.out.print(name_item_prompt);
        String new_item_name = scanner.nextLine();

        return new_item_name;
    }

    // if they enter 6
    public static void list_characters(ArrayList<Character> created_characters){
        if (created_characters.isEmpty()){
            System.out.println("   Error: You Don't Have any Characters, Try Creating One");
        }
        else{
            for (Character name: created_characters) {
                System.out.println(name.get_name() + " " + "(" + name.get_credits() + ")");
            }
        }

    }

    public static void selling_item(String finding_selling_character_prompt, String finding_buying_character_prompt, String selling_item_prompt, Scanner scanner, ArrayList<Character> created_characters){
        // declaring variable
        Boolean found_seller = false;
        Boolean found_buyer = false;
        Boolean found_item = false;
        Boolean can_sell_item = false;

        // Making a temperary holder for the item
        Item temp_holder = new Item("temp", 0);

        // made this variable so error messages wouldnt print twice
        Boolean first_condition_for_buyer = false;
        Boolean first_condition_for_seller = false;

        String seller = "no_name";
        String buyer = "no_name";

        String item_to_be_sold;


        if (created_characters.isEmpty()){
            System.out.println("   Error: You have not entered in any Characters");
            System.out.println("          Please Try Again when you have entered in one");
        }
        else if (created_characters.size() < 2){
            System.out.println("   Error: There needs to be at least 2 characters created");
            System.out.println("          Please Try Again");
        }




        else{

            // Finding the character that's selling
            System.out.print(finding_selling_character_prompt);
            seller = scanner.nextLine();
            seller = scanner.nextLine();

            // Looks for name of character that's selling
            for (Character finding_seller: created_characters) {

                // if it can find the name of the character, and it has items
                if ((seller.equals(finding_seller.get_name())) && !(finding_seller.get_items().isEmpty())){
                    // found the seller (updates value)
                    found_seller = true;

                    // Gets the buyer name
                    System.out.print(finding_buying_character_prompt);
                    buyer = scanner.nextLine();


                    // Looks for the buyer in the array
                    for (Character finding_buyer: created_characters) {

                        // found the name of the buyer, but it's the same name as the seller
                        // prints an error message
                        if ((buyer.equals(finding_buyer.get_name())) && (buyer.equals(seller))){
                            found_buyer = true;
                            first_condition_for_seller = true;
                            System.out.println("   Error: That's the Seller's Name! Use a different name");
                        }

                        // finds the buyer and the buyer name isn't the name of the seller
                        else if ((buyer.equals(finding_buyer.get_name())) && !(buyer.equals(seller))){
                            // found the buyer (updates value)
                            found_buyer = true;

                            // Gets the item that's going to be sold
                            System.out.print(selling_item_prompt);
                            item_to_be_sold = scanner.nextLine();


                            // Looking for specific item in selling character's inventory
                            for (Item item : finding_seller.get_items()){
                                // checks to see if the buyer can afford it
                                if (item.get_item_name().equals(item_to_be_sold) && !((finding_buyer.get_credits() - item.get_item_value()) < 0)){
                                    found_item = true;
                                    can_sell_item = true;

                                    // gives the item to the buyer
                                    finding_buyer.add_item(item);

                                    // removes credits from buyer
                                    finding_buyer.remove_credits(item.get_item_value());

                                    // Puts the items value in a temporary holder
                                    temp_holder.set_item_name(item.get_item_name());
                                    temp_holder.set_item_value(item.get_item_value());
                                }

                                else if (item.get_item_name().equals(item_to_be_sold) && ((finding_buyer.get_credits() - item.get_item_value()) < 0)){
                                    found_item = true;
                                    System.out.println("   Error: The Buyer Could Not Afford This Item");
                                    System.out.println("          Please Try Again");
                                }

                            }

                            // made if statement here so if the user enters something invalid it wouldn't automatically get rid of the item
                            if (found_seller && (found_buyer) && !(first_condition_for_buyer) && (found_item) && can_sell_item){

                                //removes item from seller
                                finding_seller.get_items().remove(finding_seller.get_item(temp_holder.get_item_name()));
                                // adds credits to seller
                                finding_seller.add_credits(temp_holder.get_item_value());

                            }
                        }
                    }
                }
                // if it can find the name of the character, but it doesn't have any items
                //      then it prints the error message
                else if((seller.equals(finding_seller.get_name())) && (finding_seller.get_items().isEmpty()) && !(found_seller)){
                    found_seller = true;
                    first_condition_for_buyer = true;
                    System.out.println("   Error: " + finding_seller.get_name() + " does not have any items");
                    System.out.println("          Please Try Again When they have items");
                }

            }

            // if it couldn't find the seller then it prints an error message
            if (!(found_seller)){
                System.out.println("   Error: Couldn't Find a Character with that Name");
            }

            // else if it found the seller, but it couldn't find the buyer then it prints an error message
            // and an error message didn't print prior to finding the buyer
            else if (found_seller && !(found_buyer) && !(first_condition_for_buyer)) {
                System.out.println("   Error: Couldn't Find a Character with that Name");

            }

            // couldn't find the item
            else if (found_seller && (found_buyer) && !(first_condition_for_buyer) &&  !(found_item) && !(first_condition_for_seller)){
                System.out.println("   Error: Couldn't Find an Item with that Name");
            }

        }


    }

    // nothing down here ------------------------------------------------------------


    public static ArrayList<Character> computting_command(Integer command, Scanner scanner, ArrayList<Character> created_characters){
        if (command == 1){
            //character_constructor.set_new_character("What would you like to name the character? ", "How many credits does the character have? ", scanner);
            created_characters = creating_new_character("What would you like to name the character? ", "How many credits does the character have? ", scanner, created_characters);
        }

        if (command == 2){
            created_characters = set_characters_item("Which character is adding the item? ", "What is the name of the item?  ", "What is the item's value? ", scanner, created_characters);
        }
        if (command == 5){
            selling_item("Which character is selling the item? ", "Which character is buying the item? ", "What is the name of the item? ", scanner, created_characters);
        }

        if (command == 6){
            list_characters(created_characters);
        }

        return created_characters;
    }

    public static Integer get_user_number(String prompt, Scanner scanner){
        System.out.print(prompt);
        Integer command = scanner.nextInt();

        while(is_valid(command)){
            System.out.print(prompt);
            command = scanner.nextInt();
        }


    return command;

    }

    public static Boolean is_valid(Integer command){
        if (command == 9){
            return false;
        }

        if (command < 1){
            System.out.println("   Error: Value does not exist, enter a number from the list\n");
            return true;
        }

        if (command > 9){
            System.out.println("   Error: Value does not exist, enter a number from the list\n");
            return true;
        }


        return false;
    }

}