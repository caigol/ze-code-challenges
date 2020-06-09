package br.com.caique.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.locationtech.jts.awt.PointShapeFactory.Point;
import org.locationtech.jts.geom.MultiPolygon;


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
	
	@Column(nullable = false)
    private String document;
	
	@Column(columnDefinition="multipolygon", nullable = false)
    private MultiPolygon converageArea;
	
	@Column(columnDefinition="point", nullable = false)
    private Point adress;
    
    
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

	public MultiPolygon getConverageArea() {
		return converageArea;
	}

	public void setConverageArea(MultiPolygon converageArea) {
		this.converageArea = converageArea;
	}

	public Point getAdress() {
		return adress;
	}

	public void setAdress(Point adress) {
		this.adress = adress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		result = prime * result + ((converageArea == null) ? 0 : converageArea.hashCode());
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
		if (adress == null) {
			if (other.adress != null)
				return false;
		} else if (!adress.equals(other.adress))
			return false;
		if (converageArea == null) {
			if (other.converageArea != null)
				return false;
		} else if (!converageArea.equals(other.converageArea))
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
