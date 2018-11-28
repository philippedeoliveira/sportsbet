package fr.philippedeoliveira.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "businessUnit")
public class BusinessUnit {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "label")
    private String label;
    @Column(name = "location")
    private String location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationLabel() {
        if (this.label.equals(this.location)) {
            return this.location;
        } else {
            return this.location + " - " + this.label;
        }
    }
}
