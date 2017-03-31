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

    public static final String API_MODULE_APPLICATION_JSON = "application/json;";
    public static final String API_MODULE_CHARSET = " charset=utf-8";
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
    public static final int CALL_MANAGER_FLAG_TYPE_NONE = -1;

    /** FILE HELPER **/
    public static final String FILE_HELPER_BYTES_UTF8 = "UTF-8";

    /** HTTP STATUS CODE **/
    public static final int HTTP_STATUS_CODE_SUCCESS = 200;
    public static final int HTTP_STATUS_CODE_SUCCESS_CREATE = 201;
    public static final int HTTP_STATUS_CODE_SUCCESS_NO_CONTENT = 204;
    public static final int HTTP_STATUS_CODE_ERROR_UNAUTHORIZED = 401;
    public static final int HTTP_STATUS_CODE_ERROR_INTERNAL = 500;

    /** TESTS **/
    public static final String TESTS_FILE_VALIDATE_SUCCESS = "validate/success.json";
    public static final String TESTS_FILE_VALIDATE_INVALID_TOKEN = "validate/invalid_token.json";

    public static final int TESTS_DELAY_SMALL = 1000;
    public static final int TESTS_DELAY_MEDIUM = 2500;
    public static final int TESTS_DELAY_LARGE = 5000;

    public static final String TESTS_ACCOUNT_NAME = "name";
    public static final String TESTS_ACCOUNT_PASSWORD = "password";
    public static final String TESTS_ACCOUNT_TOKEN = "token";

}
