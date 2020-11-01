import javax.sound.sampled.Line;
import java.io.*;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import java.util.HashMap;
import  java.util.*;
import java.nio.file.*;

class Main {
    public static void main(String[] args) throws IOException {
        //call the check case function  using the object for Main class
        String[] wordList = new String[]{"hello"};
        Main mn = new Main();

        //please mention the file name along with extension if using windows i.e for a.txt --> a.txt.txt
        System.out.println(mn.format("O:\\a.txt",wordList,"UpperCase"));
    }
    static String checkCase(String word){
        if(isUpperCase(word)) return "UpperCase";
        else if(isLowerCase(word)) return  "LowerCase";
        else if(isTitleCase(word)) return  "TitleCase";
        else
        return "No matching case!";
    }
    public static String changeCase(String word,String format){
        if(format.equals("UpperCase")){
            return word.toUpperCase();
        }
        else if(format.equals("LowerCase")){
            return word.toLowerCase();
        }
        else {
            char ch = word.charAt(0);
            return (Character.toUpperCase(ch)+word.substring(1));
        }
    }
    public static boolean isUpperCase(String s)
    {
        for (int i=0; i<s.length(); i++)
        {
            if (!Character.isUpperCase(s.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
    public static boolean isLowerCase(String s)
    {
        for (int i=0; i<s.length(); i++)
        {
            if (!Character.isLowerCase(s.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
    public static boolean isTitleCase(String s)
    {
        if(Character.isUpperCase(s.charAt(0))) {
            for (int i = 1; i < s.length(); i++) {
                if (!Character.isLowerCase(s.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        else return false;
    }

    Long format(String filePath, String[] wordsToFormat, String format) throws IOException {
        Long count = 0L;

        //file object from give file path
        File file = new File(filePath);

        FileReader fr = new FileReader(file);


        //Buffered file for fast I/O
        BufferedReader br = new BufferedReader(fr);

        File tempFile = new File("O:\\temp.txt");
        FileWriter fw = new FileWriter(tempFile);


        //mapping each word to be formatted into a hash map to reduce complexity()
        HashMap<String, Integer> map = new HashMap<>();

        for (String word : wordsToFormat) {
            map.put(word,0);
        }

        //processng file
        String str =br.readLine();
        while (str!=null){
            String[] arr = str.split(" ");

            for (String word : wordsToFormat) {
              if(map.containsKey(word)){
                  count++;
              }
            }
            str= br.readLine();
        }
        br = new BufferedReader(new FileReader(file));
        for (String word : wordsToFormat) {
            String Line = br.readLine();
            while(Line !=null){

                System.out.println("Replacing "+word);
                fw.write(Line.replaceAll(word,changeCase(word,format)));
                Line=br.readLine();
            }
        }
        br.close();
        fw.close();
        fr.close();
        file.delete();

        tempFile.renameTo(file);
        return count;
    }
}
