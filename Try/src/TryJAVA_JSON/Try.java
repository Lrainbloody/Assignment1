/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TryJAVA_JSON;

/**
 *
 * @author Lrainbloody
 */
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Try {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
            Scanner scan = null;
            Data dataBuff[];
            int value=100;
            int counterline=0;
            try{
                scan = new Scanner(new File("promotion1.log"));
                dataBuff = new Data[value];  //กี่รายการ
                while(scan.hasNextLine()){    
                    String data = scan.nextLine();
                    String []data1 = data.split("\\|"); //[0]วันที่,[1]เริ่มโทร,[2]จบการโทร,[3]เบอร์,[4]โปรโมชั่น

                    dataBuff[counterline]=new Data(data1[0],data1[1],data1[2],data1[3],data1[4]);

                    System.out.println("Tel: "+dataBuff[counterline].Tel);
                    //System.out.println(" Start time: "+dataBuff[counterline].Starttime);

                    //ตำนวณนาที
                    dataBuff[counterline].SumMin(data1[1]);
                    int StartSec=dataBuff[counterline].usesec;
                    int StartMin=dataBuff[counterline].usemin;
                    
                    dataBuff[counterline].SumMin(data1[2]);
                    int EndSec=dataBuff[counterline].usesec;
                    int EndMin=dataBuff[counterline].usemin;
                    
                    int UseTime=(EndMin-StartMin);
                    int checkSec=(60-StartSec)+EndSec;
                    if(checkSec>59) UseTime++;
                    //ราคา
                    int cost=(UseTime-1)*1+3;
                    dataBuff[counterline].Data(cost);
                    System.out.println(" Cost: "+dataBuff[counterline].cost);
                    counterline++;                  
                }
                //JSon
                
                JSONObject obj = new JSONObject();
                JSONArray list_cost = new JSONArray();
                for(int i=0;i<counterline;i++){
                    list_cost.add(dataBuff[i].cost);              
                }
                JSONArray list_Tel = new JSONArray();
                for(int i=0;i<counterline;i++){
                    list_Tel.add(dataBuff[i].Tel);              
                }
                obj.put("Tel",list_Tel);
                obj.put("cost",list_cost);
                
                try (FileWriter file = new FileWriter("D:\\MFEC\\Try\\TelCost.json")) {

                    file.write(obj.toJSONString());
                    file.flush();

                } catch (IOException e) {
                        e.printStackTrace();
                }

                System.out.print(obj);
                          
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
    }
    

   
}



