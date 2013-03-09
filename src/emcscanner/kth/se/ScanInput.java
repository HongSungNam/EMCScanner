package emcscanner.kth.se;

public class ScanInput {

	/**
	 * 
	 * @param f
	 * @return
	 */
	public static int scanInput(int f){
		/* Function with an string input and then convert it to integer and return */
		/* We will get values between 100 and -100 i think */
		int convertedFromStringInput = (int) (Math.random() * 400 + 380 );
		
		SettingsPanel.scanPanel.scan.getFileFrequencyPrint()[f].print(convertedFromStringInput + " ");
		return convertedFromStringInput;
	}
}
