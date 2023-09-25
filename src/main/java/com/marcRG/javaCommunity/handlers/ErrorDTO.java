package com.marcRG.javaCommunity.handlers;

import com.marcRG.javaCommunity.exception.ErrorCodes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDTO {
    private Integer httpCode;
    private ErrorCodes codes;
    private  String message;
    private List<String> error=new ArrayList<>();
}
