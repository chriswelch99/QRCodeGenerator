package org.weewelchie.qr.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName = "";

    @Column(name="last_name")
    private String lastName = "";

    @Column(name="dob")
    private Date dob;

    @Column(name="troop")
    private String troop = "";

    @Column(name="qrcode_id")
    private String QRCodeID = "";

    public Person()
    {

    }
    public Person(String firstName, String lastName, Date dob, String troop)
    {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setDob(dob);
        this.setTroop(troop);
        //Hash data into QRCode
        this.setQRCodeID();


    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getTroop() {
        return troop;
    }

    public void setTroop(String troop) {
        this.troop = troop;
    }

    public String getQRCodeID() {
        return QRCodeID;
    }

    public void setQRCodeID(String QRCodeID) {
        this.QRCodeID = QRCodeID;
    }

    private void setQRCodeID()
    {
        //GenerateHash String from firstName,lastName,dob,troop

        String qrCodeIDHash = "hahsh";
        this.setQRCodeID(qrCodeIDHash);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", troop='" + troop + '\'' +
                ", QRCodeID='" + QRCodeID + '\'' +
                '}';
    }
}
