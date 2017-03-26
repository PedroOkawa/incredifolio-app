package com.daltonicchameleon.portfolio.util.constants;

/**
 * portfolio-app
 * Created in 3/23/17 by the following authors:
 * Pedro Okawa
 */
public class Constants {

    /** API STATUS CODES **/
    public static final int API_STATUS_CODE_EXPIRED = 5009;

    /** API MODULE **/
    public static final int API_MODULE_TIMEOUT = 60;
    public static final String API_MODULE_URL = "https://api.daltonicchameleon.com/";
    public static final String API_MODULE_DATE_FORMAT_COMPLETE = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private static final String API_MODULE_APPLICATION_JSON = "application/json;";
    private static final String API_MODULE_CHARSET = " charset=utf-8";
    public static final String API_MODULE_CONTENT_TYPE = API_MODULE_APPLICATION_JSON + API_MODULE_CHARSET;

    /** API SERVICE **/
    public static final String API_SERVICE_CONTENT_TYPE = "Content-Type";

    public static final String API_SERVICE_FIELD_TOKEN = "x-access-token";
    public static final String API_SERVICE_FIELD_USERNAME = "username";
    public static final String API_SERVICE_FIELD_PASSWORD = "password";

    /** BASE DIALOG **/
    public static final float BASE_DIALOG_WIDTH_PERCENTAGE = 0.9f;

    /** BUNDLE **/
    public static final String BUNDLE_FRAGMENT = "fragment";

    /** CALL MANAGER **/
    public static final int CALL_MANAGER_FLAG_TYPE_NONE = 0;
    public static final int CALL_MANAGER_FLAG_TYPE_NEW_TASK = 1;

    /** HTTP STATUS CODE **/
    public static final int HTTP_STATUS_CODE_SUCCESS = 200;
    public static final int HTTP_STATUS_CODE_SUCCESS_CREATE = 201;
    public static final int HTTP_STATUS_CODE_SUCCESS_NO_CONTENT = 204;

}
