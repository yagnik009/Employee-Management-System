package com.Backend.Employee.Management.System.Entity;

import java.util.Date;

public class JwtResponse {
    private final String jwtToken;
    private final String username;
    private final String roles;
    private final Date expiryTime;

    private JwtResponse(Builder builder) {
        this.jwtToken = builder.jwtToken;
        this.username = builder.username;
        this.roles = builder.roles;
        this.expiryTime = builder.expiryTime;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public String getUsername() {
        return username;
    }

    public String getRoles() {
        return roles;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String jwtToken;
        private String username;
        private String roles;
        private Date expiryTime;

        public Builder jwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder roles(String roles) {
            this.roles = roles;
            return this;
        }

        public Builder expiryTime(Date expiryTime) {
            this.expiryTime = expiryTime;
            return this;
        }

        public JwtResponse build() {
            return new JwtResponse(this);
        }
    }
}
