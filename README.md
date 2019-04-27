simualtion is done in unetstack  download at : https://www.unetstack.net/devguide.html


# Introduction


Hamming code is a set of error-correction codes that can be used to detect and correct the errors that can occur when the data is moved or stored from the sender to the receiver.
It is technique developed by R.W. Hamming for error correction. 



# Redundant bits –

Redundant bits are extra binary bits that are generated and added to the information-carrying bits of data transfer to ensure that no bits were lost during the data transfer.
The number of redundant bits can be calculated using the following formula:

 2^r ≥ m + r + 1 
 where, r = redundant bit, m = data bit


# Minimum Distance for Error Detection

• Now let us find the minimum Hamming distance in a
code if we want to be able to detect up to s errors.

• If s errors occur during transmission, the Hamming
distance between the sent codeword and received
codeword is s.

• If our system is to detect up to s errors, the minimum
distance between the valid codes must be (s + 1), so
that the received codeword does not match a valid
codeword.

• In other words, if the minimum distance between all
valid codewords is (s + 1), the received codeword
cannot be erroneously mistaken for another codeword.
The error will be detected.

• We need to clarify a point here: Although a code with
d min = s + 1 may be able to detect more than s errors
in some special cases, only s or fewer errors are
guaranteed to be detected.


# How to Run?
step 1:open terminal on the directory 

step 2:bin/unet samples/HammingCode_final/ham-sim.groovy 

step 3:to send data from node 1 to node 2       -- send

step 4: To open nodes 2  - telnet localhost 5102/5103

step 5: to view the notification received by node 2 and node 3  
- subscribe phy
-  ntf.data


# Terminologies

hCode.groovy--Compelete FEC using Hamming code using groovy 

send_script--An Exclosure at Node1 taking input from the user and generates Hamming Code 

ham-sim---Simulation Script

ErrorAgent---- Agent Created on Node 2 to implement the error at random position

CorrectionAgent--Agent created on Node 3 to Correct the corrupted bits of error.
