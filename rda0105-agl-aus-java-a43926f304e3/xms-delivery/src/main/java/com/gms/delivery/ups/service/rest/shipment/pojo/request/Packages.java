package com.gms.delivery.ups.service.rest.shipment.pojo.request;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.gms.delivery.dhl.xmlpi.datatype.shipment.request.Piece;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Packages", propOrder = {
    "Package"
})

public class Packages {

	
	@XmlElement(name = "Package", required = true)
    protected List<Package> Package ;

    
    public List<Package> getPackage() {
        if (Package == null) {
        	Package = new ArrayList<Package>();
        }
        return this.Package;
    }
}
	