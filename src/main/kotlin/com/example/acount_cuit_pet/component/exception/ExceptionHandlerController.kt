package com.example.acount_cuit_pet.component.exception

import com.example.acount_cuit_pet.component.api.ApiResponse
import com.example.acount_cuit_pet.component.api.ApiResult
import lombok.extern.slf4j.Slf4j
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Slf4j
class ExceptionHandlerController {

    private fun getPos(e: Exception): String {
        return if (e.stackTrace.isNotEmpty()) {
            val stackTraceElement = e.stackTrace[0]
            val filerName = if (stackTraceElement.fileName == null) {
                "未找到错误文件"
            } else {
                stackTraceElement.fileName
            }
            val number = stackTraceElement.lineNumber
            "$filerName:$number"
        } else {
            ""
        }
    }

    fun baseHandler(e: Exception, apiCode: ApiResponse): ApiResult<String> {
        val pos = getPos(e)
        e.printStackTrace()
        val message = if (e.message.isNullOrEmpty()) "未知错误" else e.message

        return ApiResult.error(pos, message!!, apiCode)
    }

    @ExceptionHandler(value = [Exception::class])
    @ResponseBody
    fun exceptionHandler(exception: Exception): ApiResult<String> {
        val unknownError = ApiResponse.UNKNOWN_ERROR
        return baseHandler(exception, unknownError)
    }

    @ExceptionHandler(value = [ProjectException::class])
    @ResponseBody
    fun projectExceptionHandler(exception: ProjectException): ApiResult<String> {
        return baseHandler(exception, exception.apiResponse)
    }

    //validation异常处理
    @ResponseBody
    @ExceptionHandler(value = [BindException::class])
    fun validExceptionHandler(bindException: BindException):ApiResult<String>{
        val pos = getPos(bindException)
        val message = bindException.bindingResult.allErrors[0].defaultMessage?:bindException.message
        return ApiResult.error(pos, message,ApiResponse.PARAM_ERROR)
    }
}