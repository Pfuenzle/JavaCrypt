/**
 * Created by Pfuenzle on 16.10.2018.
 */

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.HeadlessException;

public class JavaCrypt {

    public static void main(String[] args) {
        System.out.println("Paste the code in this window and press enter two times!");
        Scanner sc = new Scanner(System.in);

        String codeToCrypt = "";

        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            if(nextLine.equals(""))
                break;
            codeToCrypt += nextLine;
        }

        ArrayList<Character> array = stringToCharArray(codeToCrypt);
        String result = obfuscateCharArray(array);

        System.out.println(result);
        copyToClipboard(result);

        sc.close();
    }

    private static ArrayList<Character> stringToCharArray(String in)
    {
        ArrayList<Character> array = new ArrayList<>();
        for(int i = 0 ; i < in.length(); i++)
        {
            array.add(in.charAt(i));
        }
        return array;
    }

    private static String obfuscateCharArray(ArrayList<Character> in)
    {
        String base = "\\u";
        String result = "/*\\u002a\\u002f";

        for(int i = 0; i < in.size(); i++)
        {
            result += (base + String.format("%04x", (int) in.get(i)));
        }

        result += "\\u002f\\u002a */";

        return result;
    }

    private static void copyToClipboard(String in)
    {
	    try
	    {
	            StringSelection selection = new StringSelection(in);
	            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	            clipboard.setContents(selection, selection);
	            System.out.println("Copied to Clipboard");
	    }
	    catch (Exception e) {
            e.printStackTrace();
        }
    }
}
