package emcscanner.kth.se;
/**
 * 
 * @author Jonas
 *
 */
public class Motor {
	/**
	 * Function for moving the motors 1 step up and 1 step forward
	 */
	public static void moveMotorFXUY(){
		//doubleSleep();
	}
	/**
	 * Function for moving the motors 1 step down and 1 step forward
	 */
	public static void moveMotorFXDY(){
		//doubleSleep();
	}
	/**
	 * Function for moving the motors 1 step up and 1 step backward
	 */
	public static void moveMotorBXUY(){
		//doubleSleep();
	}
	/**
	 * Function for moving the motors 1 step down and 1 step backward
	 */
	public static void moveMotorBXDY(){
		//doubleSleep();
	}
	
	/**
	 * Function for moving the motors 1 step up Y
	 * @param i
	 */
	public static void moveMotorUY(int i){
		if (!SettingsPanel.scanPanel.scan.isMoveStartPosToMesurmentPosBoolean())
			SettingsPanel.scanPanel.scan.setyMoved(SettingsPanel.scanPanel.scan.getyMoved() + 1);
		//singleSleep();
	}
	/**
	 * Function for moving the motors 1 step down Y
	 * @param i
	 */
	public static void moveMotorDY(int i){
		if (!SettingsPanel.scanPanel.scan.isMoveStartPosToMesurmentPosBoolean())
			SettingsPanel.scanPanel.scan.setyMoved(SettingsPanel.scanPanel.scan.getyMoved() - 1);
		//singleSleep();
	}
	/**
	 * @param i
	 */
	public static void moveMotorFX(int i){
		moveMotorFX(i, 1);
	}
	/**
	 * Function for moving the motors 1 step forward X
	 * @param i
	 */
	public static void moveMotorFX(int i, int n){
		for (int j = 0; j < n; j++)
			if (!SettingsPanel.scanPanel.scan.isMoveStartPosToMesurmentPosBoolean())
				SettingsPanel.scanPanel.scan.setxMoved(SettingsPanel.scanPanel.scan.getxMoved() + 1);
		//singleSleep();
	}
	/**
	 * @param i
	 */
	public static void moveMotorBX(int i){
		moveMotorBX(i, 1);
	}
	/**
	 * Function for moving the motors n steps back X
	 * @param i
	 */
	public static void moveMotorBX(int i, int n){
		for (int j = 0; j < n; j++)
			if (!SettingsPanel.scanPanel.scan.isMoveStartPosToMesurmentPosBoolean())
				SettingsPanel.scanPanel.scan.setxMoved(SettingsPanel.scanPanel.scan.getxMoved() - 1);
		//singleSleep();
	}
}
