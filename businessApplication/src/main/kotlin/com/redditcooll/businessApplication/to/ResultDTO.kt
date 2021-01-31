package com.redditcooll.businessApplication.to

import java.io.Serializable
import com.alibaba.fastjson.JSON

class ResultDTO<T : Serializable?> : Serializable {
    var errorMsg: String? = null
    var errorCode: String? = null
    var isSuccess = false
    var module: T? = null
        private set

    constructor(errorCode: String?, errorMsg: String?, obj: T) : super() {
        this.errorCode = errorCode
        this.errorMsg = errorMsg
        isSuccess = false
        module = obj
    }

    constructor() {
        buildSuccessResult<Serializable>()
    }

    constructor(obj: T) {
        isSuccess = true
        module = obj
    }

    fun setModule(module: T) {
        this.module = module
    }

    fun toJsonString(): String {
        return JSON.toJSONString(this)
    }

    companion object {
        private const val serialVersionUID = 3682481175041925854L
        private const val DEFAULT_ERR_CODE = "xin.unknown.error"
        fun <T : Serializable?> buildSuccessResult(): ResultDTO<T?> {
            return ResultDTO(null)
        }

        fun <T : Serializable?> buildSuccessResult(obj: T): ResultDTO<T> {
            return ResultDTO(obj)
        }

        fun <T : Serializable?> buildFailedResult(errCode: String?, errMsg: String?, obj: T): ResultDTO<T> {
            return ResultDTO(errCode, errMsg, obj)
        }

        fun <T : Serializable?> buildFailedResult(errCode: String?, errMsg: String?): ResultDTO<T?> {
            return ResultDTO(errCode, errMsg, null)
        }

        fun <T : Serializable?> buildFailedResult(errMsg: String?): ResultDTO<T?> {
            return ResultDTO(DEFAULT_ERR_CODE, errMsg, null)
        }
    }
}