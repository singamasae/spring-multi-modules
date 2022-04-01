package com.guestpro.iot.emoney.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
public class AuthResponse implements Serializable {
    private String access_token;
}
