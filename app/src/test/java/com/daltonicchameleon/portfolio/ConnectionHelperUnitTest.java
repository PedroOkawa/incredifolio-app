package com.daltonicchameleon.portfolio;

import com.daltonicchameleon.portfolio.util.api.RetrofitException;
import com.daltonicchameleon.portfolio.util.constants.Constants;
import com.daltonicchameleon.portfolio.util.helper.ConnectionHelper;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * portfolio-app by
 * Created in 3/30/17 by the following authors:
 * Pedro Okawa
 */
public class ConnectionHelperUnitTest {

    private ConnectionHelper connectionHelper;

    @Before
    public void initialize() {
        connectionHelper = new ConnectionHelper();
    }

    @Test
    public void generateHttpError() {
        ResponseBody responseBody = Mockito.mock(ResponseBody.class);
        Response response = Response.error(Constants.HTTP_STATUS_CODE_ERROR_INTERNAL, responseBody);
        HttpException httpException = Mockito.mock(HttpException.class);
        Mockito.when(httpException.response()).thenReturn(response);

        RetrofitException retrofitException = (RetrofitException) connectionHelper.asRetrofitException(httpException, null);

        Assert.assertThat(retrofitException.getType(), Is.is(RetrofitException.Type.HTTP));
    }

    @Test
    public void generateNetworkError() {
        IOException ioException = Mockito.mock(IOException.class);

        RetrofitException retrofitException = (RetrofitException) connectionHelper.asRetrofitException(ioException, null);

        Assert.assertThat(retrofitException.getType(), Is.is(RetrofitException.Type.NETWORK));
    }

    @Test
    public void generateRuntimeError() {
        RuntimeException runtimeException = Mockito.mock(RuntimeException.class);

        RetrofitException retrofitException = (RetrofitException) connectionHelper.asRetrofitException(runtimeException, null);

        Assert.assertThat(retrofitException.getType(), Is.is(RetrofitException.Type.RUNTIME));
    }

    @Test
    public void generateUnexpectedError() {
        IllegalAccessException nullPointerException = Mockito.mock(IllegalAccessException.class);

        RetrofitException retrofitException = (RetrofitException) connectionHelper.asRetrofitException(nullPointerException, null);

        Assert.assertThat(retrofitException.getType(), Is.is(RetrofitException.Type.UNEXPECTED));
    }

}
