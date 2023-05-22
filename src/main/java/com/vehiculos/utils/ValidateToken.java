package com.vehiculos.utils;

import javax.servlet.http.HttpServletRequest;

public interface ValidateToken {
    void isValid(HttpServletRequest request);
}
