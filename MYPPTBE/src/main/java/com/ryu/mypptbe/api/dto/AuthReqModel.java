package com.ryu.mypptbe.api.dto;


import lombok.*;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;

@Getter
@NoArgsConstructor
public class AuthReqModel {
    private String id;
    private String password;

    @Builder
    public AuthReqModel(
            String id,
            String password){

        this.id = id;
        this.password =password;
    }
}
