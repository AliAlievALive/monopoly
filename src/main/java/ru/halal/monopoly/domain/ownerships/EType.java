package ru.halal.monopoly.domain.ownerships;

public enum EType {
    AIRPORT("airport"), CITY("city"), COMMUNAL("communal");

    private String type;

    EType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}