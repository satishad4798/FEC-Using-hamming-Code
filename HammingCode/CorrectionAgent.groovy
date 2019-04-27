import org.arl.fjage.Message
import org.arl.unet.*
import org.arl.mac.*
class CorrectionAgent extends UnetAgent {

  final static int PROTOCOL = Protocol.DATA
 	def bits = new int[100]
 	def parity=new int[100];

  void startup() {
    def phy = agentForService Services.PHYSICAL
    subscribe topic(phy)
   }

  void processMessage(Message msg) {
    if (msg instanceof DatagramNtf && msg.protocol == PROTOCOL) { 
    	bits=msg.data                                                //Recived msg is stored in bits[]
    int sum=0,n,m;
           for(int i=0;i<25;i++) {
                 if(bits[i]==0||bits[i]==1) {                      //Extracting codeword length and   from array
			               continue; 
		              }
                        n=bits[i];  
                      	 m=bits[++i]; 
                        break;
                          
           }

    System.out.println("\n\n\nData received by the node 3:");
    	for(int i=0;i<n;i++){
    		 System.out.print(bits[i]);
    	}

	 System.out.println("\n parity bits:");     
    	for(int i=0;i<m;i++){                                    //Check Parity Bits at Reciver
     			parity[i]=checkParity(n,(int)Math.pow(2,i));
      			System.out.println(" P"+i+":"+parity[i]);
      		if(parity[i])
      			sum+=(int)Math.pow(2,i);
      }
   
     System.out.println("error found at bit position:"+sum);
   		 bits[sum-1]=1-bits[sum-1];                              //Corrected Error Bit
     System.out.println("Corrected codeword: ");
    	display(n);
     System.out.println("");
     System.out.println("After correcting the Data: ");
    	int j=0;
    		for(int i=0;i<n;i++)                               //Extractng actual databit excluding parity  bits
      			if((i!=(((int)Math.pow(2,j))-1)) || (j>m))
      				System.out.print(bits[i]);
     		    else 
      				j++;
     System.out.println("");
  
        send new DatagramReq(recipient: msg.sender,to: 1, protocol: Protocol.DATA, data: bits) //Sending Corrected Data word to Node 1  
    }
  }

	void display(int n){
  		for(int i=0;i<n;i++)
    		System.out.print(bits[i]);
  	}     


	int checkParity(int n,int m){
  		int i,sum=0,c=0;
  			for(i=m-1;i<n;i++){     
      			sum+=bits[i];
      			c++;
      			if(c==m) {
					c=0;
      				i+=m;
   				 }
 			 }
  			if(sum%2==0)
  				return 0;
  			else 
  				return 1;
	}
}
