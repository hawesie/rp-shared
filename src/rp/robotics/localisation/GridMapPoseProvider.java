package rp.robotics.localisation;

import lejos.geom.Point;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.Pose;
import rp.robotics.mapping.IGridMap;
import rp.robotics.navigation.GridPose;
import rp.robotics.navigation.Heading;

/**
 * Provides a continuous pose from a grid pose.
 * 
 * @author Nick Hawes
 *
 */
public class GridMapPoseProvider implements PoseProvider {

	private final GridPoseProvider m_poser;
	private final IGridMap m_map;

	public GridMapPoseProvider(GridPoseProvider _poser, IGridMap _map) {
		m_poser = _poser;
		m_map = _map;
	}

	@Override
	public Pose getPose() {
		GridPose gp = m_poser.getGridPose();
		Point position = m_map.getCoordinatesOfGridPosition(gp.getX(),
				gp.getY());
		return new Pose(position.x, position.y, Heading.toDegrees(gp
				.getHeading()));
	}

	@Override
	public void setPose(Pose _pose) {

	}

}
