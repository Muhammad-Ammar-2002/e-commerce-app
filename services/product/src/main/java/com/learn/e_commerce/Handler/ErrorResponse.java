package com.learn.e_commerce.Handler;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {

}
