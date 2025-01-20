package com.Backend.Employee.Management.System.Entity;

public class JwtResponse {
    private final String jwtToken;
    private final String username;
    private final String roles;

    private JwtResponse(Builder builder) {
        this.jwtToken = builder.jwtToken;
        this.username = builder.username;
        this.roles = builder.roles;
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String jwtToken;
        private String username;
        private String roles;

        // Builder methods to set fields
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

        // Build method to return the final object
        public JwtResponse build() {
            return new JwtResponse(this);
        }
    }
}




