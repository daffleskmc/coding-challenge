package au.com.parcelpoint.coding_challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Robot {

    Map<String, List<String>> mapBox;
    int arm; // position of robot arm
    int armLastPos; // arm last position
    int numColumn;

    public Robot(int numBox, int numColumn) {
        this.arm = -1;
        this.armLastPos = numBox - 1;
        this.numColumn = numColumn;
        this.mapBox = new HashMap<String, List<String>>();
        for (int i = 0; i < numBox; i++) {
            this.mapBox.put(getKey(i), new ArrayList<>(numColumn));
        }
    }

    public Map<String, List<String>> simulateArm(final String input) {
        // loop input string
        for (int i = 0; i < input.length(); i++) {
            // read each character of input
            char c = input.charAt(i);
            // check robot command
            switch (c) {
                case 'F':
                    if (arm < armLastPos) {
                        arm++; // increment arm position
                    }
                    break;
                case 'D':
                    i++; // increment position
                    while (i < input.length() && input.charAt(i) != '{') { // check if next of D is not '{'
                        i++; // increment position
                    }
                    if (i < input.length() && input.charAt(i) == '{') { // check if next of D is '{'
                        i = i + getStringInParenthesis(i + 1, input); // get new position of i by adding the string inside parenthesis
                    }
                    break;
                case 'R':
                    arm = -1; // return to default position
                    break;
                default:
                    break;
            }
        }
        return mapBox;
    }

    private int getStringInParenthesis(int i, String input) {
        String str = "";
        for (int j = i; j < input.length(); j++) { // loop each char in the string found inside the parenthesis
            if (input.charAt(j) == '}') { // check if char is '}'
                i = j; // then new position of i = j
                break;
            }
            str += input.charAt(j); // append each character
        }

        if (arm >= 0 && i < input.length() && input.charAt(i) == '}') { // check if arm position is valid, i is less than input length, and '}' is found
            List listStr = mapBox.get(getKey(arm)); // check column with arm parameter
            if (listStr.size() < numColumn) {
                listStr.add(str); // add string in the list
            }
        }
        // increment arm position if position is not yet in last column
        if (arm < armLastPos) {
            arm++;
        }
        return str.length() + 1; // return length of string plus 1 to include that '}'
    }

    private String getKey(int i) {
        return Character.toString((char) (i + 65));
    }
}
