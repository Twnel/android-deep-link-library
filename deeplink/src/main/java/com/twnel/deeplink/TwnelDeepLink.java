package com.twnel.deeplink;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by Yesid Lazaro on 11/25/15.
 */
public class TwnelDeepLink {
    private Activity context;
    private String companyId;
    private String appPackageName;
    private String activityClassName;
    private boolean showDialog;
    private String dialogTitle;
    private String dialogMessage;
    private String dialogNextButtonText;

    private TwnelDeepLink(Builder builder) {
        setContext(builder.context);
        setCompanyId(builder.companyId);
        setAppPackageName(builder.appPackageName);
        setActivityClassName(builder.activityClassName);
        setShowDialog(builder.showDialog);
        setDialogTitle(builder.dialogTitle);
        setDialogMessage(builder.dialogMessage);
        setDialogNextButtonText(builder.dialogNextButtonText);
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setAppPackageName(String appPackageName) {
        this.appPackageName = appPackageName;
    }

    public void setActivityClassName(String activityClassName) {
        this.activityClassName = activityClassName;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

    public void setDialogTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
    }

    public void setDialogMessage(String dialogMessage) {
        this.dialogMessage = dialogMessage;
    }

    public void setDialogNextButtonText(String dialogNextButtonText) {
        this.dialogNextButtonText = dialogNextButtonText;
    }

    public void navigate() {
        if (context!=null) {
            Intent intent = new Intent();
            intent.setClassName(Constants.TWNEL_PACKAGE, Constants.TWNEL_ACTIVITY);
            ResolveInfo resolveInfo = context.getPackageManager()
                    .resolveActivity(intent,
                            PackageManager.MATCH_DEFAULT_ONLY);
            //Twnel App installed
            if (resolveInfo != null) {
                Bundle extras = new Bundle();
                //company Id, package name and activity class name
                extras.putString(Constants.APP_LINK_JID, companyId);
                extras.putString(Constants.APP_PACKAGE_NAME, appPackageName);
                extras.putString(Constants.APP_ACTIVITY_NAME, activityClassName);

                //Extras for detect in Twnel App if starting from App Link
                intent.putExtra(Constants.AL_APPLINK_DATA, extras);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
                //Twnel App not installed(now we redirect to play store)
            } else {
                if (showDialog) {
                    if (TextUtils.isEmpty(dialogTitle) || TextUtils.isEmpty(dialogMessage) || TextUtils.isEmpty(dialogNextButtonText)) {
                        throw new IllegalArgumentException("please set title, subject, and button text  for the alert dialog");
                    } else {
                        showDialog(context, dialogTitle, dialogMessage, dialogNextButtonText, companyId);
                    }
                } else {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.INSTALLATION_LINK + companyId));
                    context.startActivity(browserIntent);
                }
            }
        }else {
            throw new IllegalArgumentException("set a valid activity for context argument");
        }
    }

    private void showDialog(final Context context, String title, String subject, String nextButtonText, final String companyId) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(subject);
        alertDialog.setPositiveButton(nextButtonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.INSTALLATION_LINK + companyId));
                context.startActivity(browserIntent);
            }
        });
        alertDialog.show();
    }

    public static final class Builder {
        private Activity context;
        private String companyId;
        private String appPackageName;
        private String activityClassName;
        private boolean showDialog;
        private String dialogTitle;
        private String dialogMessage;
        private String dialogNextButtonText;
        public Builder() {
        }

        public Builder context(@NonNull Activity val) {
            context = val;
            return this;
        }

        public Builder companyId(@NonNull String val) {
            companyId = val;
            return this;
        }

        public Builder appPackageName(@NonNull String val) {
            appPackageName = val;
            return this;
        }

        public Builder activityClassName(@NonNull String val) {
            activityClassName = val;
            return this;
        }

        public Builder showDialog(boolean val) {
            showDialog = val;
            return this;
        }

        public Builder dialogTitle(@Nullable String val) {
            dialogTitle = val;
            return this;
        }

        public Builder dialogMessage(@Nullable String val) {
            dialogMessage = val;
            return this;
        }
        public Builder dialogNextButtonText(@Nullable String val) {
            dialogNextButtonText = val;
            return this;
        }

        public TwnelDeepLink build() {
            return new TwnelDeepLink(this);
        }


    }


}
