//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.10.29 at 08:42:42 PM BST 
//


package ws.biotea.ld2rdf.util.ncbo.annotator.jaxb.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}annotatorResultBean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "annotatorResultBean"
})
@XmlRootElement(name = "data")
public class Data {

    @XmlElement(required = true)
    protected AnnotatorResultBean annotatorResultBean;

    /**
     * Gets the value of the annotatorResultBean property.
     * 
     * @return
     *     possible object is
     *     {@link AnnotatorResultBean }
     *     
     */
    public AnnotatorResultBean getAnnotatorResultBean() {
        return annotatorResultBean;
    }

    /**
     * Sets the value of the annotatorResultBean property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnnotatorResultBean }
     *     
     */
    public void setAnnotatorResultBean(AnnotatorResultBean value) {
        this.annotatorResultBean = value;
    }

}
