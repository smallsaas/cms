package com.jfeat.am.module.notice.services.service;


import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vincenthuang on 06/08/2018.
 */
@Deprecated
public class StateMachine {

    private static StateMachine inst;

    public static StateMachine createInstance(){
        if(inst==null){
            inst = new StateMachine();
        }
        return inst;
    }


    private Map<String, String> states = new HashMap<>();


    /**
     * 检查状态
     *
     * @param current
     * @param status
     */
    public boolean checkStatus(String current, String status) {
        if (states.containsKey(generateKey(current, status))) {
            return true;
        }
        throw new BusinessException(BusinessCode.ErrorStatus);
    }


    /**
     * 状态注册
     *
     * @param state 当前状态
     * @param route 路由新状态
     */
    public StateMachine registerState(String state, String route) {
        states.put(generateKey(state, route), route);
        return this;
    }

    private String generateKey(String state, String route) {
        return String.format("%s-%s", state, route);
    }
}
