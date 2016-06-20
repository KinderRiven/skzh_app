package com.http;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.Map;

/* Created by KinderRiven on 2016/6/15. */
/* 发消息的*/

public class MessageSolver {

    private final String TAG = "Message";
    private String url;

    private RequestQueue mQueue;

    public MessageSolver(Context context, String url){
        mQueue = Volley.newRequestQueue(context);
        this.url = url;
    }

    public void setUrl(String url){
        this.url = url;
    }

       /* 你竟然看到了这里！
          我绝对不会告诉你这就是发送数据的函数的~
       */

    public void sendMessage(final Map<String, String> data){

        Log.i(TAG, "正在发送消息");

        Response.Listener<String> sucListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG,  "发送成功");
                if(response != null)
                    Log.i(TAG, response);
            }
        };

        Response.ErrorListener errListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,  "发送失败");
                if(error != null && error.getMessage() != null)
                    Log.i(TAG, error.getMessage());
            }
        };

        StringRequest request = new StringRequest(Request.Method.POST, url,
                sucListener, errListener) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = data;
                return map;
            }
        };
        this.mQueue.add(request);
    }

    public void recMessage(){

    }
}
