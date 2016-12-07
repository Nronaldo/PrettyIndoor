package it.cnr.isti.wnlab.indoornavigator.framework.util.kalmanfilter;

import android.util.Log;

import it.cnr.isti.wnlab.indoornavigator.framework.IndoorPosition;
import it.cnr.isti.wnlab.indoornavigator.framework.PositionFilter2D;

/**
 * Kalman Filter for indoor localization.
 * Initializes x vector = (x,y,1,1) and P matrix
 * with d(P) = (initPosVar, initPosVar, heading, stepLength).
 */
public class IndoorKalmanFilter extends AbstractSimpleKalmanFilter implements PositionFilter2D {

    public static final int N = 4;
    public static final int X_POSITION_IN_VECTOR = 0;
    public static final int Y_POSITION_IN_VECTOR = 1;

    /**
     * @param startPosition The position where the filtering starts from.
     * @param initPosVar Initial P[0][0], P[1][1]
     * @param heading Initial P[2][2]
     * @param stepLength Initial P[3][3]
     */
    public IndoorKalmanFilter(IndoorPosition startPosition,
                              float initPosVar, float heading, float stepLength) {
        super(N);

        // Initial x vector
        x[0] = startPosition.x; // x
        x[1] = startPosition.y; // y
        x[2] = 1.f;
        x[3] = 1.f;
        Log.d("INDOOR X VECTOR", x[0] + "," + x[1] + "," + x[2] + "," + x[3]);

        // Initial P matrix
        mP[0][0] = initPosVar;
        mP[1][1] = initPosVar;
        mP[2][2] = heading;
        mP[3][3] = stepLength;
    }

    @Override
    public IndoorPosition positionInstance(int floor, long timestamp) {
        return new IndoorPosition(x[X_POSITION_IN_VECTOR], x[Y_POSITION_IN_VECTOR], floor, timestamp);
    }
}
