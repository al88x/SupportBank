package training.supportbank.model.xml;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class SupportTransaction {

    @XmlAttribute(name = "Date")
    private String date;

    @XmlElement(name = "Description")
    private String description;

    @XmlElement(name = "Value")
    private String value;

    @XmlElement(name = "Parties")
    private Parties parties;


    public SupportTransaction() {
    }

    public SupportTransaction(String date, String description, String value, Parties parties) {
        this.date = date;
        this.description = description;
        this.value = value;
        this.parties = parties;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Parties getParties() {
        return parties;
    }

    public void setParties(Parties parties) {
        this.parties = parties;
    }
}
