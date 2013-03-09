package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
/**
 * 
 * @author Jonas
 *
 */
public class ManualPanel extends JPanel {
	/**
	 * ID
	 */
	private static final long serialVersionUID = 345342245319951982L;

	/* Build a component controller */
    private SwingController controller = new SwingController();
    private SwingViewBuilder factory = new SwingViewBuilder(controller);
    
    private JPanel viewerComponentPanel;

	/* PDF File Location */
	private String filePath = "F:/ElectromagneticScanner/Electromagnetic Scanner/PDF Manual/GroupAssignment.pdf";
	
	private BorderLayout manulLayout = new BorderLayout(); 
	private Dimension MANUAL_PANEL_DIMENSION = new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2), 0);
	
	public ManualPanel(){	
		this.setLayout(manulLayout);
		this.setPreferredSize(MANUAL_PANEL_DIMENSION);

        viewerComponentPanel = factory.buildViewerPanel();

        /* add copy keyboard command */
        ComponentKeyBinding.install(controller, viewerComponentPanel);

        /* add interactive mouse link annotation support via callback */
        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));
        
        /*Opening a PDF */
        controller.openDocument(filePath);
        
        this.add(viewerComponentPanel, BorderLayout.CENTER);
	}
}
