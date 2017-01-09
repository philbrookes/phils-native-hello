package com.feedhenry.helloworld;

import android.content.Context;
import android.util.Log;

import com.feedhenry.sdk.AppProps;
import com.feedhenry.sdk.CloudProps;
import com.feedhenry.sdk.FHActCallback;
import com.feedhenry.sdk.FHHttpClient;
import com.feedhenry.sdk.api.FHCloudRequest;

import org.json.fh.JSONObject;

import cz.msebera.android.httpclient.Header;

public class NewFHCloudRequest extends FHCloudRequest {
    public String mPath = "";
    public Methods mMethod = Methods.GET;
    public Header[] mHeaders = null;
    public JSONObject mArgs = new JSONObject();

    public NewFHCloudRequest(Context context) {
        super(context);
    }

    @Override
    public void executeAsync(FHActCallback pCallback) {
        switch (mMethod) {
            case GET:
                FHHttpClient.get(getURL(), buildHeaders(mHeaders), mArgs, pCallback, false);
                break;
            case PUT:
                FHHttpClient.put(getURL(), buildHeaders(mHeaders), mArgs, pCallback, false);
                break;
            case POST:
                FHHttpClient.post(getURL(), buildHeaders(mHeaders), mArgs, pCallback, false);
                break;
            case DELETE:
                FHHttpClient.delete(getURL(), buildHeaders(mHeaders), mArgs, pCallback, false);
                break;
            default:
                break;
        }
    }

    private String getURL() {
        String host = "https://local-proxy:8080/" + AppProps.getInstance().getAppId();
        Log.d(NewFHCloudRequest.class.getName(), host + (mPath.startsWith("/") ? mPath : '/' + mPath));
        return host + (mPath.startsWith("/") ? mPath : '/' + mPath);
    }
}
