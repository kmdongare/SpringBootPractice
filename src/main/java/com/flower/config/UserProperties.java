package com.flower.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flower.model.UserDetails;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@Component
@ConfigurationProperties("user")
public class UserProperties {

    private String   jsonPaylod;
    private String  title;
    private int  modifiedUserId;

    public String getJsonPaylod() {
        return jsonPaylod;
    }

    public void setJsonPaylod(String jsonPaylod) {
        this.jsonPaylod = jsonPaylod;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getModifiedUserId() {
        return modifiedUserId;
    }

    public void setModifiedUserId(int modifiedUserId) {
        this.modifiedUserId = modifiedUserId;
    }
}
