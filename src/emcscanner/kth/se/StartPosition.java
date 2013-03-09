package emcscanner.kth.se;

public class StartPosition {

	/**
	 * Moves the closest corner of the selected area to the scanners position.
	 */
	public static void moveStartPosToMesurmentPos(){
		int mPX = Scan.getMesurmentPosX();
		int mPY = Scan.getMesurmentPosY();
		int eX = (int) (SettingsPanel.getTABLE_WIDTH() - (SettingsPanel.getAREA_SELECTED_IMAGE_DEPENDENT_END_X() * Program.TIONDELS_MILLI_METER_PIXEL));
		int eY = (int) (SettingsPanel.getTABLE_HEIGHT() - (SettingsPanel.getAREA_SELECTED_IMAGE_DEPENDENT_END_Y() * Program.TIONDELS_MILLI_METER_PIXEL));
		int sX = (int) (SettingsPanel.getTABLE_WIDTH() - (SettingsPanel.getAREA_SELECTED_IMAGE_DEPENDENT_START_X() * Program.TIONDELS_MILLI_METER_PIXEL));
		int sY = (int) (SettingsPanel.getTABLE_HEIGHT() - (SettingsPanel.getAREA_SELECTED_IMAGE_DEPENDENT_START_Y() * Program.TIONDELS_MILLI_METER_PIXEL));
		
		SettingsPanel.scanPanel.scan.setMoveStartPosToMesurmentPosBoolean(true);
		
		int dEX = mPX - eX;
		int dSX = mPX - sX;
		int dEY = mPY - eY;
		int dSY = mPY - sY;
		int dY;
		int dX;
		int x = 0;
		int y = 0;	
		
		if ((Math.abs(dEX) < (Math.abs(dSX))))
		{
			SettingsPanel.scanPanel.scan.setMoveFX(false);
			dX = dEX;
		}
		else
		{
			SettingsPanel.scanPanel.scan.setMoveFX(true);
			dX = dSX;
		}
		if ((Math.abs(dEY) < (Math.abs(dSY))))
		{
			SettingsPanel.scanPanel.scan.setMoveUY(true);
			dY = dEY;
		}
		else 
		{
			SettingsPanel.scanPanel.scan.setMoveUY(false);
			dY = dSY;
		}
		boolean moveXStoped = false;
		boolean moveYStoped = false;
		while (!moveXStoped || !moveYStoped){
			if (x < Math.abs(dX) && y < Math.abs(dY))
			{
				if (SettingsPanel.scanPanel.scan.isMoveFX() && SettingsPanel.scanPanel.scan.isMoveUY())
					Motor.moveMotorFXUY();
				else if (!SettingsPanel.scanPanel.scan.isMoveFX() && SettingsPanel.scanPanel.scan.isMoveUY())
					Motor.moveMotorBXUY();
				else if (SettingsPanel.scanPanel.scan.isMoveFX() && !SettingsPanel.scanPanel.scan.isMoveUY())
					Motor.moveMotorFXDY();
				else
					Motor.moveMotorBXDY();
				x++;
				y++;
			}
			else
			{
				if (x < Math.abs(dX))
				{
					if (SettingsPanel.scanPanel.scan.isMoveFX())
						Motor.moveMotorFX(x);
					else
						Motor.moveMotorBX(x);
					x++;
				}
				else 
					moveXStoped = true;
				if (y < Math.abs(dY))
				{	
					if (SettingsPanel.scanPanel.scan.isMoveUY())
						Motor.moveMotorUY(y);
					else
						Motor.moveMotorDY(y);
					y++;
				}
				else
					moveYStoped = true;
			}
		}
	}

	/**
	 * Moves the scanner point to the center of the first area that we want to scan.
	 */
	public static void moveToStartCeneter() {
		/* Two for loops that moves the axis to the middle of the first area */
		for (int y = 0; y < SettingsPanel.scanPanel.scan.getSizeY() / 2; y++)
		{
			if (SettingsPanel.scanPanel.scan.isMoveUY())
				Motor.moveMotorUY(y);
			else
				Motor.moveMotorDY(y);
		}
		for (int x = 0; x < SettingsPanel.scanPanel.scan.getSizeX() / 2; x++)
		{
			if (SettingsPanel.scanPanel.scan.isMoveFX())
				Motor.moveMotorFX(x);
			else
				Motor.moveMotorBX(x);
		}

		SettingsPanel.scanPanel.scan.setMoveStartPosToMesurmentPosBoolean(false);
	}
}
