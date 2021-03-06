package module5;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import de.fhpotsdam.unfolding.marker.Marker;
import processing.core.PGraphics;

/**
 * Implements a visual marker for ocean earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 *
 */
public class OceanQuakeMarker extends EarthquakeMarker {
	public UnfoldingMap locationMap;

	public OceanQuakeMarker(PointFeature quake, UnfoldingMap map) {
		super(quake);

		// setting field in earthquake marker
		isOnLand = false;
		locationMap = map;
	}

	/** Draw the earthquake as a square */
	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		pg.rect(x - radius, y - radius, 2 * radius, 2 * radius);
		// TODO: if this marker has been clicked on, lines are drawn between
		// this marker and all cities within its threat circle.
		// If this marker has not been clicked, these lines should disappear.
		
		if (this.getClicked()) {
			pg.stroke(pg.color(46, 255, 67));
			for (Marker marker : threatenedCityMarkers) {
				lineBetweenMarker(pg, x, y, marker);
			}
			pg.stroke(pg.color(0, 0, 0));
		}
	}
	
	// Draw line between the OceanQuakeMarker and Marker m
	public void lineBetweenMarker(PGraphics pg, float x, float y, Marker m) {
		float accurateX = ((CityMarker)m).accurateX;
		float accurateY = ((CityMarker)m).accurateY;
//		float accurateX = getScreenPosition(m).x;
//		float accurateY = getScreenPosition(m).y;
		pg.line(x, y, accurateX, accurateY);
	}

	private ScreenPosition getScreenPosition(Marker m) {
		return locationMap.getScreenPosition(m.getLocation());
	}
}
