package com.armin.revolut.helpers;

import com.armin.revolut.endpoints.vo.TransferRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class JsonResponseTransformerImplTest {
    JsonResponseTransformerImpl transformer;

    @BeforeAll
    void init(){
        transformer = new JsonResponseTransformerImpl();
    }

    @Test
    void render() {
        TransferRequest request = new TransferRequest("11111", "22222", BigDecimal.ONE);
        String json = transformer.render(request);
    }

    @Test
    void fromJson() {
    }
}