package br.com.caique.utils;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.ParseException;

public class GeometryUtil {

	private static WKTReader wktReader = new WKTReader();
	
	public static Geometry wktToGeometry(String wellKnownText) {
		Geometry geometry = null;
		
		try {
			geometry = wktReader.read(wellKnownText);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return geometry;
	}
	
}