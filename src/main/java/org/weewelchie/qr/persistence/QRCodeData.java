package org.weewelchie.qr.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "qr_code_data")
public class QRCodeData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "content", unique = true)
    private String content;

    @Column(name = "in_use")
    private Boolean inUse;


    public QRCodeData()
    {

    }

    public QRCodeData(String data, boolean inUse)
    {
        this.setContent(data);
        this.setInUse(inUse);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getInUse() {
        return inUse;
    }

    public void setInUse(Boolean inUse) {
        this.inUse = inUse;
    }

    @Override
    public String toString() {
        return "QRCodeData{" +
                "id=" + id +
                ", qrcodeData='" + content + '\'' +
                ", inUse=" + inUse +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QRCodeData that = (QRCodeData) o;
        return Objects.equals(id, that.id) && Objects.equals(content, that.content) && Objects.equals(inUse, that.inUse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, inUse);
    }
}
