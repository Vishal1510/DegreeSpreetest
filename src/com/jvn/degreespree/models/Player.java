
package com.jvn.degreespree.models;

import com.jvn.degreespree.controllers.GameController;

/**
 * Created by john on 10/5/15.
 */
public interface Player {

    String getPlayerName();

    BoardPosition getBoardPosition();

    void setBoardPosition(BoardPosition position);

    boolean isHuman();

    void startTurn();

    void endTurn();

    public int getLearning();

    public void setLearning(int learning);

    public int getCraft();

    public void setCraft(int craft);

    public int getIntegrity();

    public void setIntegrity(int integrety);

    public int getQualityPoints();

    public void setQualityPoints(int qualityPoints);

    public int getMovesLeft();

    public void decrementMovesLeft();

    public void bind(GameController controller);

}
