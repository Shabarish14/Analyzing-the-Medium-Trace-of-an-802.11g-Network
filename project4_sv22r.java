package org.emp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

 public class proj4 {

          

           private static List<Double> inputData = new ArrayList<Double>();
           private static int packet_ack_count = 0;
           private static List<Integer> list =  new ArrayList<Integer>();
           private static int temp = 0;
           private static int fresh_pck_ack_count = 0;
           private static int pck_count = 0;
           private static double packet_Length;
           public static void main(String[] args) {

                      String s = null;

        proj4 classObj = new proj4();

                      try {

                                 BufferedReader br= new BufferedReader(new FileReader(new File("C:\\Users\\SHABARISH\\Downloads\\MAC_testdata1")));

                                 while ((s = br.readLine()) != null) {

                                            String[] inputArray = null;

                                            inputArray = s.split("\\s+");

                                            if(inputArray.length > 0) {

                                                       Double a = Double.parseDouble(inputArray[0]);

                                                       Double b = Double.parseDouble(inputArray[1]);

                                                       inputData.add(Math.sqrt(a*a + b*b));

                                            }

                                 }

 

                                 double bits= ((1500*8)/18);

                                 packet_Length=  Math.round(bits/4);

                                 for(int i=0; i<inputData.size(); i++)

                                 {

                                            if(inputData.get(i)>=0.355)

                                            {         

                                                       i = classObj.countPackets(i);

                                            }

                                 }

                                 System.out.println("Total number of packets from node A                           : "+pck_count);

                                 System.out.println("Total number of packets with ACK from node A    : "+packet_ack_count );

                                 System.out.println("Total number of fresh packets from node A                    : "+packet_ack_count );

                                 System.out.println("Total number of fresh packets with ACK from node A : "+ fresh_pck_ack_count);

 

                      } catch (Exception e) {

                                 System.out.println("Exception in main block " + e);

                      }

           }

          

           public int countPackets(int start_Index) {

                      int return_Index = start_Index;
                      
                      int counter = 0;

                      

                      if( inputData.size() < start_Index+packet_Length)

                                 return return_Index;

                      for(int i = start_Index; i <= start_Index+packet_Length; i++) {

                                 if(inputData.get(i) >= 0.355) {

                                            counter++;

                                            return_Index = i;

                                 }

                      }

                      if(counter > 95) {

                                 pck_count++;

                                 temp++;

                                 for(int i = (int) (start_Index+packet_Length+1); i <= start_Index+packet_Length+14; i++) {

                                            if(inputData.get(i) >= 0.18 && inputData.get(i) <= 0.27) {

                                                       return_Index = i;

                                                       packet_ack_count ++;

                                                       if(list.size()>1) {

                                                                  if((temp - list.get(list.size() - 1)) == 1) {

                                                                             fresh_pck_ack_count++;

                                                                  }

                                                       }

                                                       if(list.size() == 0) {

                                                                  fresh_pck_ack_count++;

                                                       }

                                                       list.add(temp);

                                                       break;

                                            }

                                 }

                      }

                      return return_Index;

           }

}
