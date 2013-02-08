/*
 * ModellerGUIApp.java
 */

package modellergui;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
/**
 * The main class of the application.
 */
public class ModellerGUIApp extends SingleFrameApplication {
    /**
	 * At startup create and show the main frame of the application.
	 * @return 
	 */
    @Override protected void startup() {
        show(new ModellerGUIView(this));
    }

    @Override public void exit(java.util.EventObject event)
    {
        pulltheplug();
    }

    public void pulltheplug()
    {
        new CloseApp(this.getMainFrame(), true);
    }
    /**
	 * This method is to initialize the specified window by injecting resources.
	 * Windows shown in our application come fully initialized from the GUI
	 * builder, so this additional configuration is not needed.
	 * @param root
	 * @return 
	 */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of ModellerGUIApp
     */
    public static ModellerGUIApp getApplication() {
        return Application.getInstance(ModellerGUIApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(ModellerGUIApp.class, args);
    }
}
