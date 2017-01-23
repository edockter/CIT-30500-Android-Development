package edu.iupui.ericdock.magic8ball;

/**
 * Created by ericd on 1/18/2017.
 */

public class Response {
    public String mResponseString;
    public static Response[] sAllResponses;

    Response (String responseString) {
        mResponseString = responseString;
    }

    public String getResponseString() {
        return mResponseString;
    }

    public void setResponseString(String responseString) {
        mResponseString = responseString;
    }
}
