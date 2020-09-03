package com.accessibilityexample.Service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.graphics.Rect;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.accessibilityexample.controllers.WindowPositionController;

/**
 * Created by sotsys-014 on 4/10/16.
 */

public class MyAccessibilityService extends AccessibilityService {
//    private final AccessibilityServiceInfo info = new AccessibilityServiceInfo();
    private static final String TAG = "MyAccessibilityService";
    private static final String TAGEVENTS = "TAGEVENTS";
    private String currntApplicationPackage = "";

    private WindowPositionController windowController;
    private WindowManager windowManager;
    private boolean showWindow = false;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Log.d(TAG, "onAccessibilityEvent");
        final String sourcePackageName = (String) accessibilityEvent.getPackageName();
        currntApplicationPackage = sourcePackageName;
        Log.d(TAG, "sourcePackageName:" + sourcePackageName);
        Log.d(TAG, "parcelable:" + accessibilityEvent.getText().toString());
        Log.d(TAG, "className:" + accessibilityEvent.getClassName());

        AccessibilityNodeInfo source = accessibilityEvent.getSource();

        if (accessibilityEvent.getEventType()==AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED){
            AccessibilityNodeInfo currentNode = getRootInActiveWindow();

//            visibleOnScreen(source);
//            for (int i = 0; i < currentNode.getChildCount(); i++) {
//                System.out.println(">>>>> "  + currentNode.getChild(i).getClassName());
//                if (currentNode.getChild(i).getClassName().equals("android.widget.TextView")) System.out.println(">>>>> "  + currentNode.getChild(i).getText());
//            }
//            if (currentNode!=null && currentNode.getClassName().equals("android.widget.FrameLayout") && currentNode.getChild(2)!=null && currentNode.getChild(2).getClassName().equals("android.widget.TextView") && currentNode.getChild(2).getContentDescription().equals("Search")) {
//                currentNode.getChild(2).performAction(AccessibilityNodeInfo.ACTION_CLICK);
//            }

            windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
//            AccessibilityNodeInfo currentNode = getRootInActiveWindow();
//            System.out.println(">>>" + currentNode.toString());
            if (windowController == null){
                windowController = new WindowPositionController(windowManager, getApplicationContext());
            }
//        Log.d(">>>", accessibilityEvent.getSource().toString());
//
            showWindow = true;
            windowController.notifyDatasetChanged(accessibilityEvent.getText().toString(), currntApplicationPackage, source);
//            if (currentNode!=null && currentNode.getClassName().equals("android.widget.FrameLayout") && currentNode.getChild(2)!=null && currentNode.getChild(2).getClassName().equals("android.widget.TextView") && currentNode.getChild(2).getContentDescription().equals("Search")) {
//                currentNode.getChild(2).performAction(AccessibilityNodeInfo.ACTION_CLICK);
//            }
        }

//        if (source == null) return;


//        Log.d(TAG, "item count:" + source.getChildCount());
//
//        for (int i = 0; i < source.getChildCount(); i++) {
//            Rect rect = new Rect();
//            source.getChild(i).getBoundsInScreen(rect);
//            Log.d(TAGEVENTS, rect.toString());
//        }
//
//
//        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

      /*  if (accessibilityEvent.getEventType() == AccessibilityEvent.CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION) {
            Log.d(TAGEVENTS, "CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION");
        }
        if (accessibilityEvent.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            Log.d(TAGEVENTS, "TYPE_WINDOW_STATE_CHANGED");
        }
        if (accessibilityEvent.getEventType() == AccessibilityEvent.CONTENT_CHANGE_TYPE_SUBTREE) {
            Log.d(TAGEVENTS, "CONTENT_CHANGE_TYPE_SUBTREE");
        }
        if (accessibilityEvent.getEventType() == AccessibilityEvent.CONTENT_CHANGE_TYPE_TEXT) {
            Log.d(TAGEVENTS, "CONTENT_CHANGE_TYPE_TEXT");
        }
        if (accessibilityEvent.getEventType() == AccessibilityEvent.INVALID_POSITION) {
            Log.d(TAGEVENTS, "INVALID_POSITION");
        }
        if (accessibilityEvent.getEventType() == AccessibilityEvent.CONTENT_CHANGE_TYPE_UNDEFINED) {
            Log.d(TAGEVENTS, "CONTENT_CHANGE_TYPE_UNDEFINED");
        }
        if (accessibilityEvent.getEventType() == AccessibilityEvent.TYPE_ANNOUNCEMENT) {
            Log.d(TAGEVENTS, "TYPE_ANNOUNCEMENT");
        }
        if (accessibilityEvent.getEventType() == AccessibilityEvent.TYPE_ASSIST_READING_CONTEXT) {
            Log.d(TAGEVENTS, "TYPE_ASSIST_READING_CONTEXT");
        }
        if (accessibilityEvent.getEventType() == AccessibilityEvent.TYPE_GESTURE_DETECTION_END) {
            Log.d(TAGEVENTS, "TYPE_GESTURE_DETECTION_END");
        }
        if (accessibilityEvent.getEventType() == AccessibilityEvent.TYPE_VIEW_CLICKED) {
            Log.d(TAGEVENTS, "TYPE_VIEW_CLICKED");
        }
        if (accessibilityEvent.getEventType() == AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_START) {
            Log.d(TAGEVENTS, "TYPE_TOUCH_EXPLORATION_GESTURE_START");
        }
        if (accessibilityEvent.getEventType() == AccessibilityEvent.TYPE_GESTURE_DETECTION_START) {
            Log.d(TAGEVENTS, "TYPE_GESTURE_DETECTION_START");
        }
        if (accessibilityEvent.getEventType() == AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED) {
            Log.d(TAGEVENTS, "TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED");
        }
        if (accessibilityEvent.getEventType() == AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED) {
            Log.d(TAGEVENTS, "TYPE_VIEW_ACCESSIBILITY_FOCUSED");
        }
        if (accessibilityEvent.getEventType() == AccessibilityEvent.TYPE_WINDOWS_CHANGED) {
            Log.d(TAGEVENTS, "TYPE_WINDOWS_CHANGED");
        }*/

//        if (windowController == null){
//            windowController = new WindowPositionController(windowManager, getApplicationContext(), accessibilityEvent.getSource());
//        }
////        Log.d(">>>", accessibilityEvent.getSource().toString());
////
//        showWindow = true;
//        windowController.notifyDatasetChanged(accessibilityEvent.getText().toString(), currntApplicationPackage);
//
//        return;
//        if (accessibilityEvent.getPackageName() == null || !(accessibilityEvent.getPackageName().equals("com.bsb.hike") || !(accessibilityEvent.getPackageName().equals("com.whatsapp") || accessibilityEvent.getPackageName().equals("com.facebook.orca") || accessibilityEvent.getPackageName().equals("com.twitter.android") || accessibilityEvent.getPackageName().equals("com.facebook.katana") || accessibilityEvent.getPackageName().equals("com.facebook.lite"))))
//            showWindow = false;

//        if (accessibilityEvent.getEventType() == AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED) {
//            Log.d(TAGEVENTS, "TYPE_VIEW_TEXT_CHANGED");
//
//            showWindow = true;
//            windowController.notifyDatasetChanged(accessibilityEvent.getText().toString(), currntApplicationPackage);
//        } else if(accessibilityEvent.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED){
//            Log.d(TAGEVENTS, "TYPE_WINDOW_STATE_CHANGED:"+accessibilityEvent.getContentDescription());
//
//            if (accessibilityEvent.getPackageName() == null) return;
//
//            if (accessibilityEvent.getPackageName().equals("com.whatsapp") && (accessibilityEvent.getContentDescription() == null || !accessibilityEvent.getContentDescription().equals("Type a message")))
//                showWindow = false;
//            if (accessibilityEvent.getPackageName().equals("com.facebook.katana") && (accessibilityEvent.getText().toString().equals("[What's on your mind?]") || accessibilityEvent.getText().toString().equals("[Search]")))
//                showWindow = false;
//            if (accessibilityEvent.getPackageName().equals("com.twitter.android") && (accessibilityEvent.getText().toString().equals("[What\u2019s happening?]") || accessibilityEvent.getText().toString().equals("[Search Twitter]")))
//                showWindow = false;
//            if (accessibilityEvent.getContentDescription()!=null && (accessibilityEvent.getContentDescription().toString().equals("Textbox in chat thread")))
//                showWindow = true;
//
//
//            //remove window when keyboard closed or user moved from chatting to other things
//            if (windowController != null && !showWindow)
//                windowController.onDestroy();
//        }
    }

    @Override
    public void onInterrupt() {

    }

    public void visibleOnScreen(AccessibilityNodeInfo node) {

        if (node == null) return;


        if (node.getChildCount() == 0) {
//            Rect rect = new Rect();
//            node.getBoundsInScreen(rect);
//            node.performAction(AccessibilityNodeInfo.ACTION_CLICK);

            System.out.println("1º - Node Information: " + node.toString());
            if (node.getClassName().equals("android.widget.TextView")) System.out.println("1º text "  + node.getText());
//            CharSequence text = node.getText();
//            CharSequence description = node.getContentDescription();
//            CharSequence className = node.getClassName();
//            String viewId = node.getViewIdResourceName();
//
////            List<AccessibilityNodeInfo> nodeInfo = node.findAccessibilityNodeInfosByViewId(viewId);
////            for (int i = 0; i < nodeInfo.size(); i++) {
////                System.out.println(">>> " + nodeInfo.toString());
////            }
//
//
//
//            System.out.println("| "
//                    + "text: " + text + " \t"
//                    + "description: " + description + " \t"
//                    + String.format("%-40s", "ID: " + viewId) + " \t"
//                    + String.format("%-40s", "class: " + className) + " \t"
//                    + String.format("%-30s", "location: " + rect) + " \t");

//            if (buttonRect.contains(x, y)) {
//                // Maybe we need to think if a large view covers item?
//                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                System.out.println("1º - Node Information: " + node.toString());
//            }
        } else {
//            Rect rect = new Rect();
//            node.getBoundsInScreen(rect);
//
//            CharSequence text = node.getText();
//            CharSequence description = node.getContentDescription();
//            CharSequence className = node.getClassName();
//            String viewId = node.getViewIdResourceName();
//
////            List<AccessibilityNodeInfo> nodeInfo = node.findAccessibilityNodeInfosByViewId(viewId);
////            for (int i = 0; i < nodeInfo.size(); i++) {
////                System.out.println(">>> " + nodeInfo.toString());
////            }
//
//            System.out.println("| "
//                    + "text: " + text + " \t"
//                    + "description: " + description + " \t"
//                    + String.format("%-40s", "ID: " + viewId) + " \t"
//                    + String.format("%-40s", "class: " + className) + " \t"
//                    + String.format("%-30s", "location: " + rect) + " \t");

//            if (buttonRect.contains(x, y)) {
//                // Maybe we need to think if a large view covers item?
//                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                System.out.println("2º - Node Information: " + node.toString());
//            }
            System.out.println("2º - Node Information: " + node.toString());
            if (node.getClassName().equals("android.widget.TextView")) System.out.println("2º - text "  + node.getText());
            for (int i = 0; i < node.getChildCount(); i++) {

                visibleOnScreen(node.getChild(i));
            }
        }
    }

    @Override
    protected void onServiceConnected() {
        System.out.println("onServiceConnected");
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK;
        info.notificationTimeout = 100;
        info.packageNames = null;
    }

        /**
         * Check if Accessibility Service is enabled.
         *
         * @param mContext
         * @return <code>true</code> if Accessibility Service is ON, otherwise <code>false</code>
         */
    public static boolean isAccessibilitySettingsOn(Context mContext) {
        int accessibilityEnabled = 0;
        //your package /   accesibility service path/class
        final String service = "com.accessibilityexample/com.accessibilityexample.Service.MyAccessibilityService";

        boolean accessibilityFound = false;
        try {
            accessibilityEnabled = Settings.Secure.getInt(
                    mContext.getApplicationContext().getContentResolver(),
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
            Log.v(TAG, "accessibilityEnabled = " + accessibilityEnabled);
        } catch (Settings.SettingNotFoundException e) {
            Log.e(TAG, "Error finding setting, default accessibility to not found: "
                    + e.getMessage());
        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');

        if (accessibilityEnabled == 1) {
            Log.v(TAG, "***ACCESSIBILIY IS ENABLED*** -----------------");
            String settingValue = Settings.Secure.getString(
                    mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                TextUtils.SimpleStringSplitter splitter = mStringColonSplitter;
                splitter.setString(settingValue);
                while (splitter.hasNext()) {
                    String accessabilityService = splitter.next();

                    Log.v(TAG, "-------------- > accessabilityService :: " + accessabilityService);
                    if (accessabilityService.equalsIgnoreCase(service)) {
                        Log.v(TAG, "We've found the correct setting - accessibility is switched on!");
                        return true;
                    }
                }
            }
        } else {
            Log.v(TAG, "***ACCESSIBILIY IS DISABLED***");
        }

        return accessibilityFound;
    }
}