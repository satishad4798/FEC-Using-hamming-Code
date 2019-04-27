//! Simulation: 3-node network with ping daemons

import org.arl.fjage.Message
import org.arl.unet.*
import org.arl.mac.*
import org.arl.unet.phy.*

import org.arl.fjage.RealTimePlatform

println '''
  
 				      FEC using Hamming Code
			...............................................................................
				* Node 1 will generate Hamming code after taking input from the user
				* Node 1 sends a encripted data to node 2
				* Node 2 introduces the error in cettain position 
				* Node 2 sends the corrupted data to node 3
				* Node 3 will Corrected the code back.
			................................................................................
			!! You can Check the console shell by............
				telnet local host 5102
				telnet local host 5103
			................................................................................
			# You can interact with node 1 in the console shell. For example, try:
			> send 
			................................................................................
			# When you are done, exit the shell by pressing ^C or entering:
			> shutdown
			................................................................................
'''

platform = RealTimePlatform

                                                                                              // run simulation forever
simulate {
  node '1', address: 1, remote: 1101, location: [0, 0, 0], shell: true, stack: { container ->
    container.shell.addInitrc "${script.parent}/send_script.groovy"
  }
  node '2', address: 2, remote: 1102, location: [0.5.km, 0, 0], shell:5102, stack: { container ->
    container.add 'mul', new ErrorAgent()
  }
  node '3', address: 3, remote: 1103, location: [1.km, 0, 0], shell:5103, stack: { container ->
    container.add 'mul', new CorrectionAgent()
  }
}
