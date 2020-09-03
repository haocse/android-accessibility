package com.accessibilityexample.controllers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.accessibilityexample.R;
import com.accessibilityexample.interfaces.ListItemAction;
import com.accessibilityexample.models.InnerSounds;
import com.accessibilityexample.networktask.DownloadTask;
import com.accessibilityexample.ui.DrawView;
import com.accessibilityexample.ui.adapters.SearchItemsListAdapter;
import com.accessibilityexample.utils.NetworkTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sotsys-014 on 5/10/16.
 */

public class WindowPositionController implements ListItemAction, NetworkTask.NetworkTaskListener {
    private WindowManager windowManager;
    private ImageView iconizedWindowView;
    private LinearLayout mainContainer;
    private View searchedItemView;
    ArrayList<InnerSounds> sounds = new ArrayList<>();
    private String searchString;
    private Context context;
    private DownloadTask downloadTask;
    private String TAG = "WindowController";
    private String currntApplicationPackage;

    private boolean isKeyboardOpnend = false;


    private WindowTouchController windowTouchController;
//    AccessibilityNodeInfo accessibilityNodeInfo;


    public WindowPositionController(WindowManager windowManager, Context context) {
//        this.accessibilityNodeInfo = accessibilityNodeInfo;
        this.windowManager = windowManager;
        this.context = context;
        onCreate();
    }

    public void onCreate() {
        fillDummyData();

        if (mainContainer == null) {
            iconizedWindowView = new ImageView(context);
            mainContainer = new LinearLayout(context);
            LinearLayout.LayoutParams linearLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayoutParam.setLayoutDirection(LinearLayoutManager.HORIZONTAL);
            mainContainer.setLayoutParams(linearLayoutParam);
            iconizedWindowView.setImageResource(R.mipmap.ic_launcher);

            WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT);

            params.gravity = Gravity.TOP | Gravity.LEFT;
            params.x = 0;
            params.y = 10;

            searchedItemView = LayoutInflater.from(context).inflate(R.layout.content_floatingview, null);
            RecyclerView rvSounds = (RecyclerView) searchedItemView.findViewById(R.id.rvItems);
            SearchItemsListAdapter searchSoundListAdapter = new SearchItemsListAdapter(context, sounds, this);

            rvSounds.setAdapter(searchSoundListAdapter);
            rvSounds.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));


            View close = searchedItemView.findViewById(R.id.ivClose);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchedItemView.setVisibility(searchedItemView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                }
            });
            // check all children


//            mainContainer.removeAllViews();
//            for (int i = 0; i < this.accessibilityNodeInfo.getChildCount(); i++) {
//                if (this.accessibilityNodeInfo.getChild(i) != null) {
//                    Rect rect = new Rect();
//                    this.accessibilityNodeInfo.getChild(i).getBoundsInScreen(rect);
//                    Log.d(">>>", "accessibilityNodeInfo");
//                    DrawView drawView = new DrawView(context, rect);
////                    drawView.setBackgroundColor(Color.YELLOW);
//
//                    try {
//                        mainContainer.addView(drawView);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }

//            searchedItemView.setVisibility(View.GONE);
//            mainContainer.addView(searchedItemView);
            try {
//                mainContainer.addView(iconizedWindowView);
                windowManager.addView(mainContainer, params);

            } catch (Exception e) {
                e.printStackTrace();
            }

            initWindowTouchListener(params);

            mainContainer.setOnTouchListener(windowTouchController);


            mainContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "setOnClickListener");
                    searchedItemView.setVisibility(searchedItemView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                }
            });


        }



    }

    public void visibleOnScreen(AccessibilityNodeInfo node) {

        if (node == null) return;


        if (node.getChildCount() == 0) {
//            Rect rect = new Rect();
//            node.getBoundsInScreen(rect);
//            node.performAction(AccessibilityNodeInfo.ACTION_CLICK);

//            System.out.println("1º - Node Information: " + node.toString());
            // TextView and EditText...
            System.out.println("1º getClassName "  + node.getClassName());
            System.out.println("1º text "  + node.getText());
            Rect rect = new Rect();
            node.getBoundsInScreen(rect);
            System.out.println("1º text "  +rect.toString());
//            if (node.getClassName().equals("android.widget.TextView")) System.out.println("1º text "  + node.getText());
//            if (node.getClassName().equals("android.widget.TextView")) System.out.println("1º text "  + node.getText());
//            if (node.getClassName().equals("android.widget.TextView")) System.out.println("1º text "  + node.getText());
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

//    public static void clickAtPosition(int x, int y, AccessibilityNodeInfo node) {
//        if (node == null) return;
//
//        if (node.getChildCount() == 0) {
//            Rect buttonRect = new Rect();
//            node.getBoundsInScreen(buttonRect);
//            if (buttonRect.contains(x, y)) {
//                // Maybe we need to think if a large view covers item?
//                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                System.out.println("1º - Node Information: " + node.toString());
//            }
//        } else {
//            Rect buttonRect = new Rect();
//            node.getBoundsInScreen(buttonRect);
//            if (buttonRect.contains(x, y)) {
//                // Maybe we need to think if a large view covers item?
//                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                System.out.println("2º - Node Information: " + node.toString());
//            }
//            for (int i = 0; i < node.getChildCount(); i++) {
//                clickAtPosition(x, y, node.getChild(i));
//            }
//        }
//    }

    private void initWindowTouchListener(WindowManager.LayoutParams params) {
        windowTouchController = new WindowTouchController(params, mainContainer, windowManager);
    }

    private void fillDummyData() {
        if (sounds.size() == 0) {
            for (int index = 0; index < 10; index++) {
                InnerSounds innerSounds = new InnerSounds();
                innerSounds.image = "http://www.online-image-editor.com//styles/2014/images/example_image.png";
                innerSounds.link = "http://www.online-image-editor.com//styles/2014/images/example_image.png";
                sounds.add(innerSounds);
            }
        }
    }


    public void onDestroy() {
        if (mainContainer != null)
            windowManager.removeView(mainContainer);
        mainContainer = null;
    }

    public void onPause() {
        if (mainContainer != null)
            windowManager.removeView(mainContainer);
        mainContainer = null;
    }


    public void onResume() {

    }

    public void notifyDatasetChanged(String searchString, String currntApplicationPackage, AccessibilityNodeInfo accessibilityNodeInfo) {
        System.out.println("1----");
        System.out.println("1----" + accessibilityNodeInfo.getPackageName());
        visibleOnScreen(accessibilityNodeInfo);
        if ((searchString.length() == 0 || searchString.equalsIgnoreCase("[]")) && mainContainer != null) {
            if (mainContainer != null)
                windowManager.removeView(mainContainer);
            mainContainer = null;
        }
        if (mainContainer == null) {
            onCreate();
        }
        this.searchString = searchString;
        this.currntApplicationPackage = currntApplicationPackage;
    }

    public void setIconized() {
        if (searchedItemView != null)
            searchedItemView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(InnerSounds innerSounds, int position) {
        downloadTask = new DownloadTask(context, "Downloading....", this, false);
        downloadTask.setDownloadFileFromUrl(innerSounds.link);
        downloadTask.execute();
    }


    @Override
    public void OnTaskCompleted(boolean success, boolean canceled) {
        shareFile(downloadTask.getFilePath());
    }


    private void shareFile(String sharePath) {

        if(sharePath==null)
            return;
        Uri uri = Uri.fromFile(new File(sharePath));//sharePath
        Intent share = new Intent(Intent.ACTION_SEND);
        if (currntApplicationPackage != null)
            share.setPackage(currntApplicationPackage);
        share.setType("iamge/jpg");
        share.putExtra(Intent.EXTRA_TITLE, "Example");
        share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        share.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(share);
    }
}
