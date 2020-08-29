package com.flower.model;

public class EndPoint {
    private int endPointVisitCounter;
    private String endPointName;
    private String endpintDEscription;

    public EndPoint(int endPointVisitCounter, String endPointName, String endpintDEscription) {
        this.endpintDEscription = endpintDEscription;
        this.endPointVisitCounter = endPointVisitCounter;
        this.endPointName = endPointName;
    }


    public int getEndPointVisitCounter() {
        return endPointVisitCounter;
    }

    public void setEndPointVisitCounter(int endPointVisitCounter) {
        this.endPointVisitCounter = endPointVisitCounter;
    }

    public String getEndPointName() {
        return endPointName;
    }

    public void setEndPointName(String endPointName) {
        this.endPointName = endPointName;
    }

    public String getEndpintDEscription() {
        return endpintDEscription;
    }

    public void setEndpintDEscription(String endpintDEscription) {
        this.endpintDEscription = endpintDEscription;
    }
}
