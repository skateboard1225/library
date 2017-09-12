package com.skateboard.library.network.type;

/**
 * Created by skateboard on 2017/9/11.
 */

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import okhttp3.HttpUrl;
import retrofit2.http.Url;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/** Make a GET request. */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface TypeMainConfigJava {
    /**
     * A relative or absolute path, or full URL of the endpoint. This value is optional if the first
     * parameter of the method is annotated with {@link Url @Url}.
     * <p>
     * See {@linkplain retrofit2.Retrofit.Builder#baseUrl(HttpUrl) base URL} for details of how
     * this is resolved against a base URL to create the full endpoint URL.
     */
}
