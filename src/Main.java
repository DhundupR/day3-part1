
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;



public class Main {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/Input.txt");
        ArrayList<String> spefics = specfics(fileData);
        int sum = 0;
        for(int i = 0; i < spefics.size(); i++){
           sum += amount(spefics.get(i));
            System.out.println(spefics.get(i));
        }

        System.out.println(sum);
    }

    public static int amount(String val){
        int index1 = val.indexOf("(")+1;
        int index2 = val.indexOf(")");
        String newData = val.substring(index1,index2);
        String[] sperate = newData.split(",");
        int sum = 1;
        try {
            for (int i = 0; i < sperate.length; i++) {
                sum *= Integer.parseInt(sperate[i]);
            }
        }catch(Exception e){
            return 0;
        }
        return sum;
        }


    public static ArrayList<String> specfics(ArrayList<String> fileData){
        ArrayList<String> specifics = new ArrayList<>();
        for(int i = 0; i < fileData.size(); i++){
            while (fileData.get(i).contains("mul")){
                fileData.set(i,newString(fileData.get(i)));
                if(check(fileData.get(i))){
                    specifics.add(fileData.get(i).substring(0,fileData.get(i).indexOf(")")+1));
                    fileData.set(i,fileData.get(i).substring(fileData.get(i).indexOf(")")+1));

                } else {
                    fileData.set(i,fileData.get(i).substring(fileData.get(i).indexOf("mul")+1));
                }


            }

        }
        return specifics;
    }


    public static String newString(String data){
        int index = data.indexOf("mul(");
        data = data.substring(index);
        return data;
    }


    public static boolean check(String data){
        int index = data.indexOf(")");

        if(countContained(data.substring(0,index),"(") > 1 || countContained(data.substring(0,index),"mul") > 1 || countContained(data.substring(0,index),",") > 1){

            return false;
        }
        if (data.substring(0,index).contains("(") && data.substring(0,index).contains(",")){
            return  true;
        }
        return false;
    }


    public static int countContained(String data,String find){
        int amt = 0;
        for(int i = 0; i < data.length(); i++){
            if(data.substring(i,i+1).equals(find)){


                amt++;
            }
        }
        return amt;
    }














    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.equals(""))
                    fileData.add(line);
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}







