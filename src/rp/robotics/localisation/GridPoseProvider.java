package rp.robotics.localisation;

import rp.robotics.navigation.GridPose;

public interface GridPoseProvider {

	GridPose getGridPose();

	void setGridPose(GridPose _pose);

}
