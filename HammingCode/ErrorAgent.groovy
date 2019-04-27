import org.arl.fjage.Message
import org.arl.unet.*								                       
import org.arl.mac.*
import java.util.Random;
class ErrorAgent extends UnetAgent {						             
	final static int PROTOCOL = Protocol.DATA
 	def bits = new int[25]
  
  	void startup() {
    	def phy = agentForService Services.PHYSICAL
    	subscribe topic(phy)
   	}

  	void processMessage(Message msg) {
    	if (msg instanceof DatagramNtf && msg.protocol == PROTOCOL){
    		 bits =msg.data                                                   //Recived msg is stored in bits[]
    int n=1;
		Random random=new Random();					
		int rand_int=random.nextInt(100);

             for(int i=0;i<25;i++){                                        //Extracting codeword length  from array
                if(bits[i]==0||bits[i]==1){
                	  continue;  
               	}
                 n=bits[i];       
                 break;              
             }

       		System.out.println("Data received by node 2 : ");
              	for(int i=0;i<n;i++) {
              		System.out.print(bits[i]+"");
            	}
                if( bits [rand_int %n]==0)				                           //Randomly Flips the bits
                    bits [rand_int %n]=1;
                else
                    bits [rand_int % n]=0;

           	System.out.println("\nAfter adding the error in node 2:");
                         
            for(int i=0;i<n;i++){
              	System.out.print(bits[i]+"");
            }
			 System.out.println("");
            
                         
			println " After flipping bit " +bits;
            send new DatagramReq(recipient: msg.sender,to: 3, protocol: Protocol.DATA, data: bits) //Sending Corrupted bit to Node 3
  		}
  	}
}
