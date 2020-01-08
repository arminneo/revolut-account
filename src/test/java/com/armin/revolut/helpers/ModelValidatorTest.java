package com.armin.revolut.helpers;

import com.armin.revolut.endpoints.vo.TransferRequest;
import com.armin.revolut.exceptions.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ModelValidatorTest {

    @Test
    void validate() {

        Assertions.assertThrows(BadRequestException.class, () -> {
            ModelValidator.validate(new TransferRequest());
        });

        Assertions.assertThrows(BadRequestException.class, () -> {
            ModelValidator.validate(null);
        });

        try {
            TransferRequest request = new TransferRequest("11111", "22222", BigDecimal.ZERO);
            ModelValidator.validate(request);
            Assertions.fail();
        } catch (BadRequestException ex) {
            assertEquals(ex.getErrors().size(), 1);
        }

    }

}