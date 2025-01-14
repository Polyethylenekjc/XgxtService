package com.api.xgxt.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class StatuesCode<T> {
    static public final ResponseEntity<String> OK = ResponseEntity.ok().body("{\"code\":200,\"msg\":\"OK\"}");
    static public final ResponseEntity<String> INTSRVERROR = ResponseEntity.internalServerError().body("{\"code\":500,\"msg\":\"内部服务器错误,请联系kjc\"}");

    static public final ResponseEntity<String> StatuesOkMsg(String data) {
        return ResponseEntity.ok().body("{\"code\":200,\"data\":\"" + data + "\"}");
    }

    static public final ResponseEntity<String> StatuesOk() {
        return OK;
    }

    static public final ResponseEntity<String> InternalServerError() {
        return INTSRVERROR;
    }

    static public final ResponseEntity<String> InternalServerErrorMsg(String msg) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"code\":500,\"msg\":\"" + msg + "\"}");
    }

    static public final ResponseEntity<String> BadRequest(String msg) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"code\":400,\"msg\":\"" + msg + "\"}");
    }
    static public final ResponseEntity<String> Unauthorized(String msg) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"code\":401,\"msg\":\"" + msg + "\"}");
    }
}
