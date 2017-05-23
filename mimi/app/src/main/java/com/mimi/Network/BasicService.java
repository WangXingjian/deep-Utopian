package com.mimi.Network;

import com.mimi.Entity.BasicDataInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by wangxingjian on 2017/5/23.
 */

public interface BasicService {

    @GET("/mobile/lottery/game/")
    Observable<String> getBasicData();
}
