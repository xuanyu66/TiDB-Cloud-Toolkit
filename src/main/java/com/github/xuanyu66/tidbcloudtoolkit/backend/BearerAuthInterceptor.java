package com.github.xuanyu66.tidbcloudtoolkit.backend;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

public class BearerAuthInterceptor implements Interceptor {

  public BearerAuthInterceptor(String credentials) {
    this.credentials = credentials;
  }

  private final String credentials;

  @NotNull
  @Override
  public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
    Request request = chain.request();
    Request authenticatedRequest = request.newBuilder()
        .header("Authorization", credentials).build();
    return chain.proceed(authenticatedRequest);
  }
}