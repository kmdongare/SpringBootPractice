package com.flower.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flower.config.UserProperties;
import com.flower.exception.UserServiceException;
import com.flower.model.UserDetails;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@Component
public class FlowerCommonUtility {

    @Autowired
    private UserProperties userProperties;

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public List<UserDetails> getUserObjectFromJson() throws UserServiceException {
        List<UserDetails> userDetailsList = null;
        try {
            UserDetails[] userDetailsArray = readJsonAdnConvertToObject();
            if (userDetailsArray.length > 0)
                return userDetailsList = Arrays.asList(userDetailsArray);
        } catch (Exception e) {
            throw new UserServiceException("Exception occur while populating user data from JSON "+e.getCause());
        }
        return userDetailsList;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private UserDetails[] readJsonAdnConvertToObject() throws UserServiceException {
        try {
            JSONObject jsonObject = null;
            UserDetails[] userDetailsArray = null;
            InputStream is = new URL(userProperties.getJsonPaylod()).openStream();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                String jsonText = readAll(rd);
                ObjectMapper objectMapper = new ObjectMapper();
                userDetailsArray = objectMapper.readValue(jsonText, UserDetails[].class);

            } finally {
                is.close();
            }
            return userDetailsArray;
        } catch (IOException e) {
            throw new UserServiceException("Exception Occure while Reading Json File."+e.getCause());
        }

    }
}
