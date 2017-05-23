package com.mimi.Network;

import android.content.Context;
import android.os.Build;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.lang.reflect.Type;

import io.reactivex.disposables.Disposable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by wangxingjian on 2017/5/23.
 */

public abstract class CommonAPIHelper<T> implements BaseAPIHelper<T>{

    protected Retrofit retrofit;
    protected OkHttpClient.Builder httpClient;
    protected OkHttpClient client;
    protected Context context;
    protected final String URL = "http://www.baidu.com/";

    public CommonAPIHelper(){

    }

    public CommonAPIHelper(Context context){
        this.context = context;
    }

    public CommonAPIHelper<T> init(){
        return init(URL);
    }

    public CommonAPIHelper<T> init(String url){
        return init(url,initHeader());
    }

    public CommonAPIHelper<T> init(String url,OkHttpClient paraClient){
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(initGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .client(paraClient)
                .build();
        return this;
    }

    public <S> S getService(final Class<S> service){
        if(retrofit!=null)
            return retrofit.create(service);
        else
            return init().getService(service);
    }


    @Override
    public void doSubscribe(Disposable d) {

    }

    @Override
    public void doError(Throwable e) {

    }

    @Override
    public void doCompleted() {

    }

    protected Gson initGson(){
        JsonSerializer<Number> numberJsonSerializer = new JsonSerializer<Number>() {
            @Override
            public JsonElement serialize(Number src, Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(String.valueOf(src));
            }
        };

        JsonDeserializer<Integer> deserializer = new JsonDeserializer<Integer>() {
            @Override
            public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                try {
                    return Integer.parseInt(json.getAsString());
                } catch (NumberFormatException e) {
                    return -1;
                }
            }
        };

        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .registerTypeHierarchyAdapter(Number.class,numberJsonSerializer)
                .registerTypeAdapter(Integer.class,deserializer)
                .create();
        return gson;
    }

    /**
     * This method is going to create a header for one request.
     * By using interceptor, retrofit can intercept a request before it sends and add something in front of it.
     */
    protected OkHttpClient initHeader(){
        httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("User-Agent", "mimi")
                        .header("SystemVersion", Build.VERSION.RELEASE)
                        .header("DeviceName",android.os.Build.MODEL)
                        .header("OS","Android")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
        client = httpClient.build();
        return client;
    }

    protected boolean isError = false;
    public OkHttpClient initHeader(final String[]... args) throws CustomException{
        httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request;
                Request.Builder builder= original.newBuilder();
                for(String[] arg : args){
                    if(arg.length != 2){
                        isError = true;
                        break;
                    }else{
                        builder.header(arg[0],arg[1]);
                    }
                }
                request = builder.method(original.method(), original.body()).build();
                return chain.proceed(request);
            }
        });
        client = httpClient.build();
        if(isError)
            throw new CustomException();
        else
            return client;
    }
}
