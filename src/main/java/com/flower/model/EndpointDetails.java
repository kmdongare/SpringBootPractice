package com.flower.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EndpointDetails {
    private int totalEndpoint;
    private List<EndPoint> endPoints;

    public int getTotalEndpoint() {
        return totalEndpoint;
    }

    public void setTotalEndpoint(int totalEndpoint) {
        this.totalEndpoint = totalEndpoint;
    }

    public List<EndPoint> getEndPoints() {
        return endPoints;
    }

    public void setEndPoints(List<EndPoint> endPoints) {
        this.endPoints = endPoints;
    }
}

	
