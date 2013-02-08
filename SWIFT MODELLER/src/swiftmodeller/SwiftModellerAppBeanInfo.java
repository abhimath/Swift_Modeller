/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package swiftmodeller;

import java.beans.*;

/**
 *
 * @author WOLVERINE
 */
public class SwiftModellerAppBeanInfo extends SimpleBeanInfo {

    // Bean descriptor//GEN-FIRST:BeanDescriptor
    /*lazy BeanDescriptor*/
    private static BeanDescriptor getBdescriptor(){
        BeanDescriptor beanDescriptor = new BeanDescriptor  ( swiftmodeller.SwiftModellerApp.class , null ); // NOI18N//GEN-HEADEREND:BeanDescriptor

    // Here you can add code for customizing the BeanDescriptor.

        return beanDescriptor;     }//GEN-LAST:BeanDescriptor


    // Property identifiers//GEN-FIRST:Properties
    private static final int PROPERTY_context = 0;
    private static final int PROPERTY_exitListeners = 1;
    private static final int PROPERTY_mainFrame = 2;
    private static final int PROPERTY_mainView = 3;
    private static final int PROPERTY_propertyChangeListeners = 4;

    // Property array 
    /*lazy PropertyDescriptor*/
    private static PropertyDescriptor[] getPdescriptor(){
        PropertyDescriptor[] properties = new PropertyDescriptor[5];
    
        try {
            properties[PROPERTY_context] = new PropertyDescriptor ( "context", swiftmodeller.SwiftModellerApp.class, "getContext", null ); // NOI18N
            properties[PROPERTY_exitListeners] = new PropertyDescriptor ( "exitListeners", swiftmodeller.SwiftModellerApp.class, "getExitListeners", null ); // NOI18N
            properties[PROPERTY_mainFrame] = new PropertyDescriptor ( "mainFrame", swiftmodeller.SwiftModellerApp.class, "getMainFrame", null ); // NOI18N
            properties[PROPERTY_mainView] = new PropertyDescriptor ( "mainView", swiftmodeller.SwiftModellerApp.class, "getMainView", null ); // NOI18N
            properties[PROPERTY_propertyChangeListeners] = new PropertyDescriptor ( "propertyChangeListeners", swiftmodeller.SwiftModellerApp.class, "getPropertyChangeListeners", null ); // NOI18N
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Properties

    // Here you can add code for customizing the properties array.

        return properties;     }//GEN-LAST:Properties

    // EventSet identifiers//GEN-FIRST:Events
    private static final int EVENT_exitListener = 0;
    private static final int EVENT_propertyChangeListener = 1;

    // EventSet array
    /*lazy EventSetDescriptor*/
    private static EventSetDescriptor[] getEdescriptor(){
        EventSetDescriptor[] eventSets = new EventSetDescriptor[2];
    
        try {
            eventSets[EVENT_exitListener] = new EventSetDescriptor ( swiftmodeller.SwiftModellerApp.class, "exitListener", org.jdesktop.application.Application.ExitListener.class, new String[] {"canExit", "willExit"}, "addExitListener", "removeExitListener" ); // NOI18N
            eventSets[EVENT_propertyChangeListener] = new EventSetDescriptor ( swiftmodeller.SwiftModellerApp.class, "propertyChangeListener", java.beans.PropertyChangeListener.class, new String[] {"propertyChange"}, "addPropertyChangeListener", "removePropertyChangeListener" ); // NOI18N
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Events

    // Here you can add code for customizing the event sets array.

        return eventSets;     }//GEN-LAST:Events

    // Method identifiers//GEN-FIRST:Methods
    private static final int METHOD_addPropertyChangeListener0 = 0;
    private static final int METHOD_exit1 = 1;
    private static final int METHOD_exit2 = 2;
    private static final int METHOD_getApplication3 = 3;
    private static final int METHOD_getInstance4 = 4;
    private static final int METHOD_getInstance5 = 5;
    private static final int METHOD_hide6 = 6;
    private static final int METHOD_launch7 = 7;
    private static final int METHOD_main8 = 8;
    private static final int METHOD_quit9 = 9;
    private static final int METHOD_removePropertyChangeListener10 = 10;
    private static final int METHOD_show11 = 11;
    private static final int METHOD_show12 = 12;
    private static final int METHOD_show13 = 13;

    // Method array 
    /*lazy MethodDescriptor*/
    private static MethodDescriptor[] getMdescriptor(){
        MethodDescriptor[] methods = new MethodDescriptor[14];
    
        try {
            methods[METHOD_addPropertyChangeListener0] = new MethodDescriptor(org.jdesktop.application.AbstractBean.class.getMethod("addPropertyChangeListener", new Class[] {java.lang.String.class, java.beans.PropertyChangeListener.class})); // NOI18N
            methods[METHOD_addPropertyChangeListener0].setDisplayName ( "" );
            methods[METHOD_exit1] = new MethodDescriptor(org.jdesktop.application.Application.class.getMethod("exit", new Class[] {})); // NOI18N
            methods[METHOD_exit1].setDisplayName ( "" );
            methods[METHOD_exit2] = new MethodDescriptor(org.jdesktop.application.Application.class.getMethod("exit", new Class[] {java.util.EventObject.class})); // NOI18N
            methods[METHOD_exit2].setDisplayName ( "" );
            methods[METHOD_getApplication3] = new MethodDescriptor(swiftmodeller.SwiftModellerApp.class.getMethod("getApplication", new Class[] {})); // NOI18N
            methods[METHOD_getApplication3].setDisplayName ( "" );
            methods[METHOD_getInstance4] = new MethodDescriptor(org.jdesktop.application.Application.class.getMethod("getInstance", new Class[] {java.lang.Class.class})); // NOI18N
            methods[METHOD_getInstance4].setDisplayName ( "" );
            methods[METHOD_getInstance5] = new MethodDescriptor(org.jdesktop.application.Application.class.getMethod("getInstance", new Class[] {})); // NOI18N
            methods[METHOD_getInstance5].setDisplayName ( "" );
            methods[METHOD_hide6] = new MethodDescriptor(org.jdesktop.application.Application.class.getMethod("hide", new Class[] {org.jdesktop.application.View.class})); // NOI18N
            methods[METHOD_hide6].setDisplayName ( "" );
            methods[METHOD_launch7] = new MethodDescriptor(org.jdesktop.application.Application.class.getMethod("launch", new Class[] {java.lang.Class.class, java.lang.String[].class})); // NOI18N
            methods[METHOD_launch7].setDisplayName ( "" );
            methods[METHOD_main8] = new MethodDescriptor(swiftmodeller.SwiftModellerApp.class.getMethod("main", new Class[] {java.lang.String[].class})); // NOI18N
            methods[METHOD_main8].setDisplayName ( "" );
            methods[METHOD_quit9] = new MethodDescriptor(org.jdesktop.application.Application.class.getMethod("quit", new Class[] {java.awt.event.ActionEvent.class})); // NOI18N
            methods[METHOD_quit9].setDisplayName ( "" );
            methods[METHOD_removePropertyChangeListener10] = new MethodDescriptor(org.jdesktop.application.AbstractBean.class.getMethod("removePropertyChangeListener", new Class[] {java.lang.String.class, java.beans.PropertyChangeListener.class})); // NOI18N
            methods[METHOD_removePropertyChangeListener10].setDisplayName ( "" );
            methods[METHOD_show11] = new MethodDescriptor(org.jdesktop.application.SingleFrameApplication.class.getMethod("show", new Class[] {javax.swing.JDialog.class})); // NOI18N
            methods[METHOD_show11].setDisplayName ( "" );
            methods[METHOD_show12] = new MethodDescriptor(org.jdesktop.application.SingleFrameApplication.class.getMethod("show", new Class[] {javax.swing.JFrame.class})); // NOI18N
            methods[METHOD_show12].setDisplayName ( "" );
            methods[METHOD_show13] = new MethodDescriptor(org.jdesktop.application.SingleFrameApplication.class.getMethod("show", new Class[] {org.jdesktop.application.View.class})); // NOI18N
            methods[METHOD_show13].setDisplayName ( "" );
        }
        catch( Exception e) {}//GEN-HEADEREND:Methods

    // Here you can add code for customizing the methods array.
    
        return methods;     }//GEN-LAST:Methods

    private static java.awt.Image iconColor16 = null;//GEN-BEGIN:IconsDef
    private static java.awt.Image iconColor32 = null;
    private static java.awt.Image iconMono16 = null;
    private static java.awt.Image iconMono32 = null;//GEN-END:IconsDef
    private static String iconNameC16 = null;//GEN-BEGIN:Icons
    private static String iconNameC32 = null;
    private static String iconNameM16 = null;
    private static String iconNameM32 = null;//GEN-END:Icons

    private static final int defaultPropertyIndex = -1;//GEN-BEGIN:Idx
    private static final int defaultEventIndex = -1;//GEN-END:Idx

    
//GEN-FIRST:Superclass

    // Here you can add code for customizing the Superclass BeanInfo.

//GEN-LAST:Superclass
	
    /**
     * Gets the bean's <code>BeanDescriptor</code>s.
     * 
     * @return BeanDescriptor describing the editable
     * properties of this bean.  May return null if the
     * information should be obtained by automatic analysis.
     */
    public BeanDescriptor getBeanDescriptor() {
	return getBdescriptor();
    }

    /**
     * Gets the bean's <code>PropertyDescriptor</code>s.
     * 
     * @return An array of PropertyDescriptors describing the editable
     * properties supported by this bean.  May return null if the
     * information should be obtained by automatic analysis.
     * <p>
     * If a property is indexed, then its entry in the result array will
     * belong to the IndexedPropertyDescriptor subclass of PropertyDescriptor.
     * A client of getPropertyDescriptors can use "instanceof" to check
     * if a given PropertyDescriptor is an IndexedPropertyDescriptor.
     */
    public PropertyDescriptor[] getPropertyDescriptors() {
	return getPdescriptor();
    }

    /**
     * Gets the bean's <code>EventSetDescriptor</code>s.
     * 
     * @return  An array of EventSetDescriptors describing the kinds of 
     * events fired by this bean.  May return null if the information
     * should be obtained by automatic analysis.
     */
    public EventSetDescriptor[] getEventSetDescriptors() {
	return getEdescriptor();
    }

    /**
     * Gets the bean's <code>MethodDescriptor</code>s.
     * 
     * @return  An array of MethodDescriptors describing the methods 
     * implemented by this bean.  May return null if the information
     * should be obtained by automatic analysis.
     */
    public MethodDescriptor[] getMethodDescriptors() {
	return getMdescriptor();
    }

    /**
     * A bean may have a "default" property that is the property that will
     * mostly commonly be initially chosen for update by human's who are 
     * customizing the bean.
     * @return  Index of default property in the PropertyDescriptor array
     * 		returned by getPropertyDescriptors.
     * <P>	Returns -1 if there is no default property.
     */
    public int getDefaultPropertyIndex() {
        return defaultPropertyIndex;
    }

    /**
     * A bean may have a "default" event that is the event that will
     * mostly commonly be used by human's when using the bean. 
     * @return Index of default event in the EventSetDescriptor array
     *		returned by getEventSetDescriptors.
     * <P>	Returns -1 if there is no default event.
     */
    public int getDefaultEventIndex() {
        return defaultEventIndex;
    }

    /**
     * This method returns an image object that can be used to
     * represent the bean in toolboxes, toolbars, etc.   Icon images
     * will typically be GIFs, but may in future include other formats.
     * <p>
     * Beans aren't required to provide icons and may return null from
     * this method.
     * <p>
     * There are four possible flavors of icons (16x16 color,
     * 32x32 color, 16x16 mono, 32x32 mono).  If a bean choses to only
     * support a single icon we recommend supporting 16x16 color.
     * <p>
     * We recommend that icons have a "transparent" background
     * so they can be rendered onto an existing background.
     *
     * @param  iconKind  The kind of icon requested.  This should be
     *    one of the constant values ICON_COLOR_16x16, ICON_COLOR_32x32, 
     *    ICON_MONO_16x16, or ICON_MONO_32x32.
     * @return  An image object representing the requested icon.  May
     *    return null if no suitable icon is available.
     */
    public java.awt.Image getIcon(int iconKind) {
        switch ( iconKind ) {
        case ICON_COLOR_16x16:
            if ( iconNameC16 == null )
                return null;
            else {
                if( iconColor16 == null )
                    iconColor16 = loadImage( iconNameC16 );
                return iconColor16;
            }
        case ICON_COLOR_32x32:
            if ( iconNameC32 == null )
                return null;
            else {
                if( iconColor32 == null )
                    iconColor32 = loadImage( iconNameC32 );
                return iconColor32;
            }
        case ICON_MONO_16x16:
            if ( iconNameM16 == null )
                return null;
            else {
                if( iconMono16 == null )
                    iconMono16 = loadImage( iconNameM16 );
                return iconMono16;
            }
        case ICON_MONO_32x32:
            if ( iconNameM32 == null )
                return null;
            else {
                if( iconMono32 == null )
                    iconMono32 = loadImage( iconNameM32 );
                return iconMono32;
            }
	default: return null;
        }
    }

}

