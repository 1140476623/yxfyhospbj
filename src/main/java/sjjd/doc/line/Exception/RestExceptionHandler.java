package sjjd.doc.line.Exception;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@ControllerAdvice
public class RestExceptionHandler {
    //运行时异常
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String runtimeExceptionHandler(RuntimeException runtimeException) {
        runtimeException.printStackTrace();
        return ReturnFormat.retParam(1000, runtimeException.getStackTrace(), runtimeException.getMessage());
    }

    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public String nullPointerExceptionHandler(NullPointerException ex) {
        ex.printStackTrace();
        return ReturnFormat.retParam(1001, ex.getStackTrace(), ex.getMessage());
    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public String classCastExceptionHandler(ClassCastException ex) {
        ex.printStackTrace();
        return ReturnFormat.retParam(1002, ex.getStackTrace(), ex.getMessage());
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public String iOExceptionHandler(IOException ex) {
        ex.printStackTrace();
        return ReturnFormat.retParam(1003, ex.getStackTrace(), ex.getMessage());
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    @ResponseBody
    public String noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        ex.printStackTrace();
        return ReturnFormat.retParam(1004, ex.getStackTrace(), ex.getMessage());
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    public String indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        ex.printStackTrace();
        return ReturnFormat.retParam(1005, ex.getStackTrace(), ex.getMessage());
    }

    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public String requestNotReadable(HttpMessageNotReadableException ex) {
        System.out.println("400..requestNotReadable");
        ex.printStackTrace();
        return ReturnFormat.retParam(400, ex.getStackTrace(), ex.getMessage());
    }

    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public String requestTypeMismatch(TypeMismatchException ex) {
        System.out.println("400..TypeMismatchException");
        ex.printStackTrace();
        return ReturnFormat.retParam(400, ex.getStackTrace(), ex.getMessage());
    }

    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public String requestMissingServletRequest(MissingServletRequestParameterException ex) {
        System.out.println("400..MissingServletRequest");
        ex.printStackTrace();

        return ReturnFormat.retParam(400, ex.getStackTrace(), ex.getMessage());
    }

    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public String request405() {
        System.out.println("405...");
        return ReturnFormat.retParam(405, null, null);
    }

    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseBody
    public String request406() {
        System.out.println("404...");
        return ReturnFormat.retParam(406, null, null);
    }

    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    @ResponseBody
    public String server500(RuntimeException runtimeException) {
        System.out.println("500...");
        return ReturnFormat.retParam(406, runtimeException.getStackTrace(), runtimeException.getMessage());
    }

}
