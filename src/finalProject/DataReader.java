package finalProject;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ashley
 */
public class DataReader {
    protected int[] photoAvailable = new int[5];
    protected String[] direct = new String[5];
    BufferedReader br;
    FileWriter writer;
    public DataReader() throws Exception{
        File file = new File("data.txt");
        br = new BufferedReader(new FileReader(file));
        int i;
        br.readLine();
        for(int a = 0; a<5;a++){
            br.skip(20);
            i = br.read() - 48 ;
            br.skip(2);
            this.photoAvailable[i] = br.read() - 48;
            br.skip(2);
            direct[a] = br.readLine();
        }   
            br.close();
            //br = new BufferedReader(new FileReader(file));
    }
    public void update(int index,String newDirectory){
            //direct[index] = directory;
            //write new directory to file
            File file = new File("data.txt");
            String content = "";
            try{
                br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                int k = 0;
                while(line != null){
                    if(k == index+1){
                        content += line.substring(0, 23);
                        content += "1  ";
                        content += newDirectory + System.lineSeparator();
                        k++;
                        line = br.readLine();
                    }
                    else{
                        content += line + System.lineSeparator();
                        k++;
                        line = br.readLine();
                    }
                }
                //writer = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt"));
                bw.write(content);
                bw.close();
                br.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }  
            photoAvailable[index] = 1;
            direct[index] = newDirectory;
    }
       
}
