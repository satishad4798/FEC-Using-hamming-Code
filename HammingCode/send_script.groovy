import org.arl.unet.*
import org.arl.unet.phy.*
import org.arl.unet.*
import org.arl.unet.phy.*
import org.arl.unet.mac.*
import org.arl.fjage.*

Scanner sc=new Scanner(System.in);
	subscribe phy
        def input =new int[100];
		def parity=new int[100];
		def bits=new int[25];
	int n;
	System.out.println("Enter the number of bits");				
	 	n=sc.nextInt();                                     //Dataword lenght
	System.out.println("Enter bits to be transfered");
		for(int i=0;i<n;i++) {
			input[i]=sc.nextInt();
        }
    int index;
    
	int m=0;
		for(int r=1;r<32;r++){						//Generate no.of Parity bits
			if(Math.pow(2,r)>=r+n+1){                              // 2^r>=n+r+1
				m=r;
				break;
			}
		}
	
   	System.out.print("You Entered : ");
		display(input,n);
	System.out.println("");
	int k=0,flag;
		for(int i=0;i<n;i++){	                                  //Place the parity bits to (2^i)th position
			flag=0;						//remaining position with Dataword 
				for(int j=0;j<m;j++)
					if(i+1==Math.pow(2,j)){
						flag=1;
						bits[i]=7;
						n++;
						break;
					}
					if(flag==0){
						bits[i]=input[k];                         
						k++;
					}
		}
             
            bits[n]=n;											//Last 2 bits of the array is assigned with n & m respectively 
            bits[n+1]=m;

        System.out.println(" parity bits:");
		for(int i=0;i<m;i++){										//Caluclate Parity bits	
			bits[(int)Math.pow(2,i)-1]=parity[i]=calculateParity(bits,n,(int)Math.pow(2,i));
			System.out.println(" P"+i+":"+parity[i]);	
		}

		System.out.println("Hamming Code Generated: ");					
			display(bits,n);
		System.out.println("");


	void display(int[] arr,int n){										//Display function
		for(int i=0;i<n;i++)
			System.out.print(arr[i]);
	}	

	int calculateParity(int[] arr,int n,int m){							// Caluclates Parity
		int i,sum=0,c=0;
			for(i=m-1;i<n;i++){
				if(i==m-1) {
					sum=0;
					c++;
				}
				else {
					sum+=arr[i];
					c++;
				}
				if(c==m){
				c=0;
				i+=m;
				}
			}
		if(sum%2==0)
			return 0;
		else 
			return 1;
	}

	
		

send = { 

  phy << new DatagramReq(to: 2, protocol: Protocol.DATA, data: bits)	//Sends to node 2
 
}

