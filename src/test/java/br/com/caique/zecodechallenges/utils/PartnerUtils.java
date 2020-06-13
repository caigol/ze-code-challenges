package br.com.caique.zecodechallenges.utils;

import br.com.caique.model.Partner;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

public class PartnerUtils {

    private static final GeometryFactory GF = new GeometryFactory();

    public static Partner buildPartner(Long partnerId) {
        Partner partner = new Partner();
        partner.setId(partnerId);
        partner.setTradingName("Adega da Cerveja - Pinheiros");
        partner.setOwnerName("Zé da Silva");
        partner.setDocument("1432132123891/0001");

        Polygon polygon1 = createPolygon1(GF);
        Polygon polygon2 = createPolygon2(GF);

        partner.setCoverageArea(GF.createMultiPolygon(new Polygon[] { polygon1, polygon2 }));
        partner.setAddress(createPoint(-46.57421, -21.785741));

        return partner;
    }

    private static Point createPoint(Double x, Double y) {
        return GF.createPoint(createCoordinate(x, y));
    }

    private static Coordinate createCoordinate(Double x, Double y) {
        return new Coordinate(x, y);
    }

    private static Polygon createPolygon2(GeometryFactory gf) {
        return gf.createPolygon(new Coordinate[] {
                createCoordinate(15d, 5d),
                createCoordinate(40d, 10d),
                createCoordinate(10d, 20d),
                createCoordinate(5d, 10d),
                createCoordinate(15d, 5d)
        });
    }

    private static Polygon createPolygon1(GeometryFactory gf) {
        return gf.createPolygon(new Coordinate[] {
                createCoordinate(30d, 20d),
                createCoordinate(45d, 40d),
                createCoordinate(10d, 40d),
                createCoordinate(30d, 20d)
        });
    }
}
