package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

public class ManualPanel extends JPanel {
	public ManualPanel(){	
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2), 0));

		/* PDF File Location */
		String filePath = "F:/ElectromagneticScanner/Electromagnetic Scanner/PDF Manual/GroupAssignment.pdf";
		
		/* build a component controller */
        SwingController controller = new SwingController();

        SwingViewBuilder factory = new SwingViewBuilder(controller);

        JPanel viewerComponentPanel = factory.buildViewerPanel();

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
