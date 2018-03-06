package com.future.association.news.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MyWebViewClient extends WebViewClient {

	ProgressBar mProgressBar;
	Context context;

	public MyWebViewClient(ProgressBar mProgressBar,Context context) {
		super();
		this.mProgressBar = mProgressBar;
		this.context = context;
	}

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		// TODO Auto-generated method stub
		if (url.startsWith("tel:")){ 
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url)); 
            context.startActivity(intent); 
        }else 
       	 view.loadUrl(url);
	     return true;
	}

	@Override
	public void onPageFinished(WebView view, String url) {
		// TODO Auto-generated method stub
		mProgressBar.setVisibility(View.GONE);
		super.onPageFinished(view, url);
	}

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		// TODO Auto-generated method stub
		mProgressBar.setVisibility(View.VISIBLE);
		super.onPageStarted(view, url, favicon);
	}
}
