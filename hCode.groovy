Scanner sc=new Scanner(System.in);
int ch=1;
while(ch==1) {
	def input =new int[100];
	def parity=new int[100];
	def bits=new int[100];
	
	System.out.println("Enter the number of bits");
	int n=sc.nextInt();

	System.out.println("Enter bits to be transfered");
	for(int i=0;i<n;i++) {
		input[i]=sc.nextInt();
		}
int m=0;
	for(int x=1;x<32;x++){
		if(Math.pow(2,x)>=x+n+1){
			m=x;
			break;
		}
	}
	m=m-1;
	System.out.print("You Entered : ");
		display(input,n);
		System.out.println("");
int k=0,flag;
	for(int i=0;i<n;i++){	
		flag=0;														
		for(int j=0;j<=m;j++)
				if(i+1==Math.pow(2,j))
				{	flag=1;
					bits[i]=7;
					n++;
					break;
				}
			if(flag==0){
				bits[i]=input[k];
				k++;
			}
		}

	for(int i=0;i<=m;i++){
			bits[(int)Math.pow(2,i)-1]=parity[i]=calculateParity(bits,n,(int)Math.pow(2,i));
			System.out.println("P"+i+":"+parity[i]);
			
		}			

		System.out.println("Hamming Code Generated: ");
		display(bits,n);
		System.out.println("");
		System.out.println("Enter Error Bit: ");
		k=sc.nextInt();
		bits[k-1]=1-bits[k-1];													//CREATE ERROR AT SPECIFIED BIT
		System.out.println("Transmitted Data: ");
		display(bits,n);
		System.out.println("");
		int sum=0;
		for(int i=0;i<=m;i++)													//CHECK PARITY BITS AT RECEIVER
		{
			parity[i]=checkParity(bits,n,(int)Math.pow(2,i));
			System.out.println("P"+i+":"+parity[i]);
			if(parity[i])sum+=(int)Math.pow(2,i);
		}
		System.out.println("Error Found at Bit Position "+sum);
		bits[sum-1]=1-bits[sum-1];												//CORRECT ERROR BIT
		System.out.println("Corrected Data: ");
		display(bits,n);
		System.out.println("");
		System.out.println("After correcting the Data: ");
		int j=0;
		for(i=0;i<n;i++)														//EXTRACT ACTUAL BITS EXCLUDING PARITY BITS
			if((i!=(((int)Math.pow(2,j))-1)) || (j>m))
			System.out.print(bits[i]);
			else 
			j++;
			System.out.println("");
		System.out.println("Enter 1 to continue 0.to end");
		ch=sc.nextInt();
		}
		//while(i);
				
	void display(int[] arr,int n)
	{
	for(int i=0;i<n;i++)
		System.out.print(arr[i]);
	}			
	int calculateParity(int[] arr,int n,int m)
{
	int i,sum=0,c=0;
	for(i=m-1;i<n;i++)
	{
			if(i==m-1) {
				sum=0;
				c++;
			}
			else 	
			{
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
int checkParity(int[] arr,int n,int m)
{
	int i,sum=0,c=0;
	for(i=m-1;i<n;i++)
	{
			sum+=arr[i];
			c++;
			if(c==m)
			{c=0;
			i+=m;
		}
	}
	if(sum%2==0)
	return 0;
	else 
	return 1;
}