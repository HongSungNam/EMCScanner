package emcscanner.kth.se;
/**
 * 
 * @author Jonas
 *
 */
public class Sleep {
	/**
	 * When X and Y motor moved
	 */
	private void doubleSleep(){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * When X motor moved
	 */
	private void singleSleepXMoved(){
		try {
			Thread.sleep(83);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * When Y motor moved
	 */
	private void singleSleepYMoved(){
		try {
			Thread.sleep(83);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Scanning values
	 */
	private void scaningValues(){
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
