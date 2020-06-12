package br.com.caique.model;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="partner")
public class Partner implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="trading_name", nullable = false)
    private String tradingName;
	
	@Column(name="owner_name", nullable = false)
    private String ownerName;
	
	@Column(nullable = false, unique = true)
    private String document;
	
	@Column(columnDefinition="multipolygon", nullable = false)
    private MultiPolygon coverageArea;
	
	@Column(columnDefinition="point", nullable = false)
	private Point address;
    
    
    public Partner() {
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTradingName() {
		return tradingName;
	}

	public void setTradingName(String tradingName) {
		this.tradingName = tradingName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public MultiPolygon getCoverageArea() {
		return coverageArea;
	}

	public void setCoverageArea(MultiPolygon coverageArea) {
		this.coverageArea = coverageArea;
	}

	public Point getAddress() {
		return address;
	}

	public void setAddress(Point address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((coverageArea == null) ? 0 : coverageArea.hashCode());
		result = prime * result + ((document == null) ? 0 : document.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ownerName == null) ? 0 : ownerName.hashCode());
		result = prime * result + ((tradingName == null) ? 0 : tradingName.hashCode());
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
		Partner other = (Partner) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (coverageArea == null) {
			if (other.coverageArea != null)
				return false;
		} else if (!coverageArea.equals(other.coverageArea))
			return false;
		if (document == null) {
			if (other.document != null)
				return false;
		} else if (!document.equals(other.document))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ownerName == null) {
			if (other.ownerName != null)
				return false;
		} else if (!ownerName.equals(other.ownerName))
			return false;
		if (tradingName == null) {
			if (other.tradingName != null)
				return false;
		} else if (!tradingName.equals(other.tradingName))
			return false;
		return true;
	}
	
    
    
    

}
