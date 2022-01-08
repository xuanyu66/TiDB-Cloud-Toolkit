package com.github.xuanyu66.tidbcloudtoolkit.backend;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BaseHttpClient {

  private final int readTimeout;

  private final int writeTimeout;

  private final int connectTimeout;

  private final String userName;

  private final String password;

  private final OkHttpClient delegate;

  private BaseHttpClient(Builder builder) {
    readTimeout = builder.readTimeout;
    writeTimeout = builder.writeTimeout;
    connectTimeout = builder.connectTimeout;
    userName = builder.userName;
    password = builder.password;
    delegate = new OkHttpClient.Builder()
        .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS))
        .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
        .writeTimeout(writeTimeout, TimeUnit.MILLISECONDS)
        .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
        .addInterceptor(
            new BearerAuthInterceptor(AuthTokenResolver.getUserToken(userName, password)))
        .build();
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public Response request(Request request) throws IOException {
    return delegate.newCall(request).execute();
  }

  public static final class Builder {

    private int readTimeout = 5000;
    private int writeTimeout = 5000;
    ;
    private int connectTimeout = 5000;
    ;
    private String userName;
    private String password;

    private Builder() {
    }

    @Nonnull
    public Builder withReadTimeout(int val) {
      readTimeout = val;
      return this;
    }

    @Nonnull
    public Builder withWriteTimeout(int val) {
      writeTimeout = val;
      return this;
    }

    @Nonnull
    public Builder withConnectTimeout(int val) {
      connectTimeout = val;
      return this;
    }

    @Nonnull
    public Builder withUserName(@Nonnull String val) {
      userName = val;
      return this;
    }

    @Nonnull
    public Builder withPassword(@Nonnull String val) {
      password = val;
      return this;
    }

    @Nonnull
    public BaseHttpClient build() {
      return new BaseHttpClient(this);
    }
  }
}