import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class testlim {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//String cmd = "/opt/ibm/platformsymphony/1.2.8/linux2.6-glibc2.3-x86_64/etc/lim -V";
		String cmd = "/bin/date";

    	Runtime run = Runtime.getRuntime();  

    	try {  
            Process p = run.exec(cmd);
            BufferedInputStream in = new BufferedInputStream(p.getInputStream());  
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));  

            String lineStr;
            while ((lineStr = inBr.readLine()) != null)
            	System.out.println(lineStr);
            if (p.waitFor() != 0) {  
                if (p.exitValue() == 1)  
                    System.err.println("Command failed");
            }  
            inBr.close();  
            in.close();
        }catch (Exception e){
        	e.printStackTrace();
        }
	}

}
