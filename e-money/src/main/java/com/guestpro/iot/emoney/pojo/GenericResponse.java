package com.guestpro.iot.emoney.pojo;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class GenericResponse implements Serializable {
    private boolean success;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    @JsonAnySetter
    public void setData(String key, Object value) {
        data.put(key, value);
    }
}
