package com.communityapp.model;
import com.communityapp.config.GeneralUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Data
@AllArgsConstructor
//@NoArgsConstructor
@Table(name="confirmationToken")
public class ConfirmationToken {

    private static final long serialVersionUID = 1L;

    private static final int EXPIRATION = 60 * 24;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="token_id")
    private long tokenid;

    @Column(name="confirmation_token")
    private String confirmationToken;

    private Date expiryDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public ConfirmationToken() {}

    public ConfirmationToken(User user) {
        this.user = user;
        createdDate = new Date();
        confirmationToken = generateOTP(); //UUID.randomUUID().toString();
        expiryDate = GeneralUtils.calculateExpiryDate(EXPIRATION);
    }

    public String generateOTP() {
        return new DecimalFormat("0000")
                .format(new Random().nextInt(9999));
    }


    public String updateToken(final String confirmationToken) {
        this.user = user;
        createdDate = new Date();
        this.confirmationToken = confirmationToken;
        this.expiryDate = GeneralUtils.calculateExpiryDate(EXPIRATION);

        return confirmationToken;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
        result = prime * result + ((confirmationToken == null) ? 0 : confirmationToken.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConfirmationToken other = (ConfirmationToken) obj;
        if (expiryDate == null) {
            if (other.expiryDate != null) {
                return false;
            }
        } else if (!expiryDate.equals(other.expiryDate)) {
            return false;
        }
        if (confirmationToken == null) {
            if (other.confirmationToken != null) {
                return false;
            }
        } else if (!confirmationToken.equals(other.confirmationToken)) {
            return false;
        }
        if (user == null) {
            if (other.user != null) {
                return false;
            }
        } else if (!user.equals(other.user)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Token [String=").append(confirmationToken).append("]").append("[Expires").append(expiryDate).append("]");
        return builder.toString();
    }



}