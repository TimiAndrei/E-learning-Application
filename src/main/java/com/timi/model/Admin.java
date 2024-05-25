package com.timi.model;

public class Admin extends User {
    private String telephone;

    public Admin() {
        super();
    }

    public Admin(int id, String email, String username, String password, String telephone) {
        super(id, email, username, password);
        this.telephone = telephone;
    }

    public Admin(String email, String username, String password, String telephone) {
        super(email, username, password, "ADMIN");
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean validateEmail(String email) {
        return email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
    }

    @Override
    public String toString() {
        return "Admin ["+ super.toString() + ", telephone=" + telephone + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Admin other = (Admin) obj;
        if (telephone == null) {
            if (other.telephone != null)
                return false;
        } else if (!telephone.equals(other.telephone))
            return false;
        return true;
    }

    
}
