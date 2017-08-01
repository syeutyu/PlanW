package com.example.user.junier;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import static com.facebook.FacebookSdk.sdkInitialize;

public class InviteFriend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_invite_friend);

        shareFacebook();
        finish();
    }
    public void shareFacebook()
    {
        ShareLinkContent content = new ShareLinkContent.Builder()
                //링크의 콘텐츠 제목
                .setContentTitle("Test ")

                //게시물에 표시될 썸네일 이미지의 URL
                .setImageUrl(Uri.parse("https://www.google.co.kr/"))

                //공유될 링크
                //.setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.handykim.nbit.everytimerfree"))

                //게일반적으로 2~4개의 문장으로 구성된 콘텐츠 설명
                .setContentDescription("테, 스, 트, 문장")
                .build();

        ShareDialog shareDialog = new ShareDialog(this);
        shareDialog.show(content, ShareDialog.Mode.FEED);   //AUTOMATIC, FEED, NATIVE, WEB 등이 있으며 이는 다이얼로그 형식을 말합니다.
    }





}
