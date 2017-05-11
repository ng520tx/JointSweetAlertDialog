package com.handy.sweetalert;


import android.app.Activity;

/**
 * SweetAlertDialog 提示框创建工具类
 * <p>
 * Created by LiuJie on 2016/10/25.
 */
public class SweetDialogUtil {
    private static SweetDialogUtil sweetDialogUtil = null;

    private SweetAlertDialog sweetAlertDialog;
    private SweetAlertDialog progressDialog;
    private SweetAlertDialog successDialog;
    private SweetAlertDialog warninglDialog;
    private SweetAlertDialog errorDialog;
    private SweetAlertDialog normalDialog;

    private boolean cancelable_Progress = false; //点击手机返回键可否关闭进度框
    private boolean canceledOnTouchOutside_Progress = false; //点击外部屏幕可否关闭进度框

    private boolean cancelable_Others = true; //点击手机返回键可否关闭对话框
    private boolean canceledOnTouchOutside_Others = true; //点击外部屏幕可否关闭对话框

    private SweetDialogUtil() {
    }

    public synchronized static SweetDialogUtil getInstance() {
        if (sweetDialogUtil == null) {
            sweetDialogUtil = new SweetDialogUtil();
        }
        sweetDialogUtil.cancelable_Progress = false;
        sweetDialogUtil.canceledOnTouchOutside_Progress = false;
        sweetDialogUtil.cancelable_Others = true;
        sweetDialogUtil.canceledOnTouchOutside_Others = true;
        return sweetDialogUtil;
    }

    public SweetDialogUtil setProgressDialogCancelable(boolean cancelable_Progress, boolean canceledOnTouchOutside_Progress) {
        this.cancelable_Progress = cancelable_Progress;
        this.canceledOnTouchOutside_Progress = canceledOnTouchOutside_Progress;
        return this;
    }

    public SweetDialogUtil setOthersDialogCancelable(boolean cancelable_Others, boolean canceledOnTouchOutside_Others) {
        this.cancelable_Others = cancelable_Others;
        this.canceledOnTouchOutside_Others = canceledOnTouchOutside_Others;
        return this;
    }

    /**
     * 显示进度框
     *
     * @param activity Activity基类
     * @param title    标题内容
     */
    public SweetAlertDialog showProgress(Activity activity, String title) {
        if (activity != null && !activity.isFinishing()) {
            dismissAll(activity);
            return showDialog(activity, SweetAlertDialog.PROGRESS_TYPE, title, null, null, null, null, null);
        }
        return null;
    }

    /**
     * 显示成功提示框
     *
     * @param activity     Activity基类
     * @param title        标题内容
     * @param confirmText  确认按钮内容
     * @param successClick 确认按钮点击事件
     */
    public SweetAlertDialog showSuccess(Activity activity, String title, String confirmText, SweetAlertDialog.OnSweetClickListener successClick) {
        if (activity != null && !activity.isFinishing()) {
            dismissAll(activity);
            return showDialog(activity, SweetAlertDialog.SUCCESS_TYPE, title, null, confirmText, null, successClick, null);
        }
        return null;
    }

    /**
     * 警告提示框
     *
     * @param activity    Activity基类
     * @param title       标题内容
     * @param content     正文内容
     * @param confirmText 确认按钮内容
     * @param errorClick  确认按钮点击事件
     */
    public SweetAlertDialog showWarning(Activity activity, String title, String content, String confirmText, SweetAlertDialog.OnSweetClickListener errorClick) {
        if (activity != null && !activity.isFinishing()) {
            dismissAll(activity);
            return showDialog(activity, SweetAlertDialog.WARNING_TYPE, title, content, confirmText, null, errorClick, null);
        }
        return null;
    }

    /**
     * 失败提示框
     *
     * @param activity    Activity基类
     * @param title       标题内容
     * @param content     正文内容
     * @param confirmText 确认按钮内容
     * @param errorClick  确认按钮点击事件
     */
    public SweetAlertDialog showError(Activity activity, String title, String content, String confirmText, SweetAlertDialog.OnSweetClickListener errorClick) {
        if (activity != null && !activity.isFinishing()) {
            dismissAll(activity);
            return showDialog(activity, SweetAlertDialog.ERROR_TYPE, title, content, confirmText, null, errorClick, null);
        }
        return null;
    }

    /**
     * 显示默认提示框
     *
     * @param activity     Activity基类
     * @param title        标题内容
     * @param content      正文内容
     * @param confirmText  确认按钮内容
     * @param cancelText   取消按钮内容
     * @param confirmClick 确认按钮点击事件
     * @param cancelClick  取消按钮点击事件
     */
    public SweetAlertDialog showNormal(Activity activity, String title, String content, String confirmText, String cancelText, SweetAlertDialog.OnSweetClickListener confirmClick, SweetAlertDialog.OnSweetClickListener cancelClick) {
        if (activity != null && !activity.isFinishing()) {
            dismissAll(activity);
            return showDialog(activity, SweetAlertDialog.NORMAL_TYPE, title, content, confirmText, cancelText, confirmClick, cancelClick);
        }
        return null;
    }

    /**
     * 显示进度框
     */
    private SweetAlertDialog showDialog(Activity activity, int dialogType, String title, String content, String confirmText, String cancelText, SweetAlertDialog.OnSweetClickListener confirmClick, SweetAlertDialog.OnSweetClickListener cancelClick) {
        if (activity != null && !activity.isFinishing()) {
            switch (dialogType) {
                case SweetAlertDialog.PROGRESS_TYPE:
                    progressDialog = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);
                    sweetAlertDialog = progressDialog;
                    break;
                case SweetAlertDialog.SUCCESS_TYPE:
                    successDialog = new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE);
                    sweetAlertDialog = successDialog;
                    break;
                case SweetAlertDialog.WARNING_TYPE:
                    warninglDialog = new SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE);
                    sweetAlertDialog = warninglDialog;
                    break;
                case SweetAlertDialog.ERROR_TYPE:
                    errorDialog = new SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE);
                    sweetAlertDialog = errorDialog;
                    break;
                case SweetAlertDialog.NORMAL_TYPE:
                    normalDialog = new SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE);
                    sweetAlertDialog = normalDialog;
                    break;
                default:
                    break;
            }
            if (dialogType == SweetAlertDialog.PROGRESS_TYPE) {
                sweetAlertDialog.setCancelable(cancelable_Progress);
                sweetAlertDialog.setCanceledOnTouchOutside(canceledOnTouchOutside_Progress);
            } else {
                sweetAlertDialog.setCancelable(cancelable_Others);
                sweetAlertDialog.setCanceledOnTouchOutside(canceledOnTouchOutside_Others);
            }

            sweetAlertDialog.setTitleText(isEmpty(title) ? null : title);
            sweetAlertDialog.setContentText(isEmpty(content) ? null : content);
            sweetAlertDialog.setCancelText(isEmpty(cancelText) ? null : title);
            sweetAlertDialog.setConfirmText(isEmpty(confirmText) ? null : confirmText);

            if (confirmClick != null)
                sweetAlertDialog.setConfirmClickListener(confirmClick);
            if (cancelClick != null)
                sweetAlertDialog.setCancelClickListener(cancelClick);

            sweetAlertDialog.show();
            return sweetAlertDialog;
        }
        return null;
    }

    /**
     * 关闭全部进度框
     */
    public void dismissAll(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            try {
                if (sweetAlertDialog != null && sweetAlertDialog.isShowing()) {
                    try {
                        sweetAlertDialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        sweetAlertDialog = null;
                    }
                }
                if (progressDialog != null && progressDialog.isShowing()) {
                    try {
                        progressDialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        progressDialog = null;
                    }
                }
                if (successDialog != null && successDialog.isShowing()) {
                    try {
                        successDialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        successDialog = null;
                    }
                }
                if (warninglDialog != null && warninglDialog.isShowing()) {
                    try {
                        warninglDialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        warninglDialog = null;
                    }
                }
                if (errorDialog != null && errorDialog.isShowing()) {
                    try {
                        errorDialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        errorDialog = null;
                    }
                }
                if (normalDialog != null && normalDialog.isShowing()) {
                    try {
                        normalDialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        normalDialog = null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ===================================================================
     * 判断给定字符串是否空白串。
     * 空白串是指由空格、制表符、回车符、换行符组成的字符串
     *
     * @param input 判断的字符串内容
     * @return boolean 若输入字符串为null或空字符串，返回true
     */
    public boolean isEmpty(String input) {
        if (input == null || "".equals(input) || input.length() == 0)
            return true;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }
}